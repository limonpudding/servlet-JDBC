package org;

import org.jetbrains.annotations.NotNull;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CreatorTables extends AbstractPageFactory {
    private static final String QUERY = "SELECT * FROM HISTORY";
    public CreatorTables(HttpServletRequest req, HttpServletResponse resp) {
        super(req, resp);
    }

    @Override
    public void build() throws Exception {
        String modeSort = page.getValue().getRequest().getParameter("mode");
        String orderSort = page.getValue().getRequest().getParameter("order");
        try {
            if ("1".equals(page.getValue().getRequest().getParameter("table"))) {
                page.getValue().getRequest().setAttribute("fullSessionsHistory", selectSessionsFromBD(modeSort, orderSort));
                page.getValue().getRequest().getRequestDispatcher("createTableSessions.jsp").forward(page.getValue().getRequest(), page.getValue().getResponse());
            } else {
                page.getValue().getRequest().setAttribute("operationsHistory", selectDataFromBD(modeSort, orderSort, page.getValue().getRequest().getParameter("id")));
                page.getValue().getRequest().getRequestDispatcher("createTableOperations.jsp").forward(page.getValue().getRequest(), page.getValue().getResponse());
            }
        } catch (IOException | ArithmeticException e) {
            page.getValue().getRequest().setAttribute("exception", e.getMessage());
            page.getValue().getResponse().getWriter().println("Error: " + e.getMessage());

        } catch (Exception e) {
            page.getValue().getRequest().setAttribute("exception", "Unknown error!");
            page.getValue().getResponse().getWriter().println("Error: unknown error");
        }
    }

    private List<SessionsRow> selectSessionsFromBD(String mode, String order) {
        try (Connection connection = dataBase.getValue().getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = getResultSessionsSet(mode, order, statement);// statement.executeQuery("SELECT DISTINCT ID,IP,TIMESTART,TIMEEND FROM HISTORY");
            return createSessionsList(rs);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private List<DBRow> selectDataFromBD(String mode, String order, String id) {
        try (Connection connection = dataBase.getValue().getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = getResultSet(mode, order, statement, id);
            return createRowList(rs);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private ResultSet getResultSet(String mode, String order, Statement statement, String id) throws SQLException {
        ResultSet rs;
        String orderStr;
        String modeStr;
        if ("desc".equals(order)) {
            orderStr = "desc";
        } else {
            orderStr = "asc";
        }
        if (mode == null)
            mode = "";
        switch (mode) {
            case "operation":
                modeStr = "OPERATION";
                break;
            case "firstOper":
                modeStr = "FIRSTOPERAND";
                break;
            case "secondOper":
                modeStr = "SECONDOPERAND";
                break;
            case "answer":
                modeStr = "ANSWER";
                break;
            case "time":
                modeStr = "TIME";
                break;
            default:
                modeStr = "TIME";
        }

        rs = statement.executeQuery(QUERY + " where '" + id + "'=ID ORDER BY " + modeStr + " " + orderStr);
        return rs;
    }

    private ResultSet getResultSessionsSet(String mode, String order, Statement statement) throws SQLException {
        ResultSet rs;
        String orderStr;
        String modeStr;
        if ("desc".equals(order)) {
            orderStr = "desc";
        } else {
            orderStr = "asc";
        }
        if (mode == null)
            mode = "";
        switch (mode) {
            case "idSession":
                modeStr = "ID";
                break;
            case "ip":
                modeStr = "IP";
                break;
            case "timeStart":
                modeStr = "TIMESTART";
                break;
            case "timeEnd":
                modeStr = "TIMEEND";
                break;
            default:
                modeStr = "ID";
        }

        rs = statement.executeQuery("select * from (select distinct sessions.id, sessions.ip,sessions.timestart,sessions.timeend, 'false' as operation from SESSIONS left join history on SESSIONS.id = HISTORY.id where operation is null\n" +
                "union all\n" +
                "select distinct sessions.id, sessions.ip,sessions.timestart,sessions.timeend, 'true' as operation from SESSIONS left join history on SESSIONS.id = HISTORY.id where operation is not null) as temp order by " + modeStr + " " + orderStr);
        return rs;
    }


    @NotNull
    private List<SessionsRow> createSessionsList(ResultSet rs) throws SQLException {
        List<SessionsRow> rows = new ArrayList<>();
        while (rs.next()) {
            SessionsRow row = new SessionsRow(rs);
            rows.add(row);
        }
        return rows;
    }

    @NotNull
    private List<DBRow> createRowList(ResultSet rs) throws SQLException {
        List<DBRow> rows = new ArrayList<>();
        while (rs.next()) {
            DBRow row = new DBRow(rs);
            rows.add(row);
        }
        return rows;
    }

    public class DBRow {
        public String id;
        public String ip;
        public String sessionStartTime;
        public String sessionEndTime;
        public String operationName;
        public String op1;
        public String op2;
        public String answer;
        public String time;

        private DBRow(ResultSet rs) throws SQLException {
            id = rs.getString(1);
            ip = rs.getString(2);
            sessionStartTime = rs.getString(3);
            sessionEndTime = rs.getString(4);
            operationName = rs.getString(5);
            op1 = rs.getString(6);
            op2 = rs.getString(7);
            answer = rs.getString(8);
            time = rs.getString(9);
        }

        public String id() {
            return id;
        }

        public String ip() {
            return ip;
        }

        public String sessionStartTime() {
            return sessionStartTime;
        }

        public String sessionEndTime() {
            return sessionEndTime;
        }

        public String operationName() {
            return operationName;
        }

        public String op1() {
            return op1;
        }

        public String op2() {
            return op2;
        }

        public String answer() {
            return answer;
        }

        public String time() {
            return time;
        }
    }

    public class SessionsRow {
        public String id;
        public String ip;
        public String sessionStartTime;
        public String sessionEndTime;
        public String operation;

        private SessionsRow(ResultSet rs) throws SQLException {
            id = rs.getString(1);
            ip = rs.getString(2);
            sessionStartTime = rs.getString(3);
            sessionEndTime = rs.getString(4);
            operation = rs.getString(5);
        }

        public String id() {
            return id;
        }

        public String ip() {
            return ip;
        }

        public String sessionStartTime() {
            return sessionStartTime;
        }

        public String sessionEndTime() {
            return sessionEndTime;
        }

        public String operation() {
            return operation;
        }
    }
}
