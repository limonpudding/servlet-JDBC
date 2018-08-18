package org;

import org.jetbrains.annotations.NotNull;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CreatorOpHistory extends AbstractPageFactory {
    /*
                        <c:forEach var="row" items="${rs.rows}">
                        <tr>
                            <td class="col hidden" title="${row.id()}">
                                <c:choose>
                                    <c:when test="${row.operation=='false'}">
                                        ${row.id()}
                                    </c:when>
                                    <c:otherwise>
                                        <a href="#" onclick="createTable('${row.id()}')">${row.id()}</a>
                                    </c:otherwise>    <!-- else condition -->
                                </c:choose>
                            </td>
                            <td class="col hidden" title="${row.ip()}">
                                    ${row.ip()}
                            </td>
                            <td class="col">
                                    ${row.sessionStartTime()}
                            </td>
                            <td class="col">
                                    ${row.sessionEndTime()}
                            </td>
                        </tr>
                    </c:forEach>
     */
/*
select distinct sessions.id, sessions.ip,sessions.timestart,sessions.timeend, 'false' as operation from SESSIONS left join history on SESSIONS.id = HISTORY.id where operation is null
union all
select distinct sessions.id, sessions.ip,sessions.timestart,sessions.timeend, 'true' as operation from SESSIONS left join history on SESSIONS.id = HISTORY.id where operation is not null
 */
    private static final String QUERY = "SELECT * FROM HISTORY";
    SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    public CreatorOpHistory(HttpServletRequest req, HttpServletResponse resp) {
        super(req, resp);
    }

    public void build() throws Exception {
        String modeSort = page.getValue().getRequest().getParameter("mode");
        String orderSort = page.getValue().getRequest().getParameter("order");
        page.getValue().getRequest().setAttribute("fullOperationsHistory", selectDataFromBD(modeSort, orderSort));
        page.getValue().getRequest().setAttribute("fullSessionsHistory", selectSessionsFromBD(modeSort, orderSort));
        page.getValue().getRequest().getRequestDispatcher("ophistory.jsp").forward(page.getValue().getRequest(), page.getValue().getResponse());
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

    private List<DBRow> selectDataFromBD(String mode, String order) {
        try (Connection connection = dataBase.getValue().getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = getResultSet(mode, order, statement);
            return createRowList(rs);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private ResultSet getResultSet(String mode, String order, Statement statement) throws SQLException {
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

        rs = statement.executeQuery(QUERY + " " + "ORDER BY " + modeStr + " " + orderStr);
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
