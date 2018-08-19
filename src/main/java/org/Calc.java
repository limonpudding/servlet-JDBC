package org;

import org.jetbrains.annotations.NotNull;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Calc extends HttpServlet {
    public static String globalURL = "http://localhost/";
    private static final String QUERY = "SELECT * FROM HISTORY";
    SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    protected static String DBName;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            AbstractPageFactory.getFactory(req, resp).build();
        } catch (IOException | ArithmeticException e) {
            req.setAttribute("exception", e.getMessage());
            resp.getWriter().println("Error: " + e.getMessage());

        } catch (Exception e) {
            req.setAttribute("exception", "Unknown error!");
            resp.getWriter().println("Error: unknown error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String modeSort = req.getParameter("mode");
        String orderSort = req.getParameter("order");
        try {
            if ("1".equals(req.getParameter("table"))) {
                req.setAttribute("fullSessionsHistory", selectSessionsFromBD(modeSort, orderSort));
                req.getRequestDispatcher("createTableSessions.jsp").forward(req, resp);
            } else {
                req.setAttribute("operationsHistory", selectDataFromBD(modeSort, orderSort, req.getParameter("id")));
                req.getRequestDispatcher("createTableOperations.jsp").forward(req, resp);
            }
        } catch (IOException | ArithmeticException e) {
            req.setAttribute("exception", e.getMessage());
            resp.getWriter().println("Error: " + e.getMessage());

        } catch (Exception e) {
            req.setAttribute("exception", "Unknown error!");
            resp.getWriter().println("Error: unknown error");
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        System.out.println("Я сервайс");
    }

    @Override
    public void init() throws ServletException {
        super.init();
        DBName = this.getServletConfig().getInitParameter("DBName");
        try (Connection connection = new DataBase(DBName).getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("" +
                    "create table DIV\n" +
                    "(\n" +
                    "  ID            NVARCHAR2(40) default NULL not null\n" +
                    "    primary key,\n" +
                    "  FIRSTOPERAND  CLOB,\n" +
                    "  SECONDOPERAND CLOB,\n" +
                    "  ANSWER        CLOB,\n" +
                    "  IDSESSION     NVARCHAR2(40) default NULL,\n" +
                    "  TIME          TIMESTAMP\n" +
                    ");\n" +
                    "\n" +
                    "create table FIB\n" +
                    "(\n" +
                    "  ID           NVARCHAR2(40) default NULL not null\n" +
                    "    primary key,\n" +
                    "  FIRSTOPERAND CLOB,\n" +
                    "  ANSWER       CLOB,\n" +
                    "  IDSESSION    NVARCHAR2(40) default NULL,\n" +
                    "  TIME         TIMESTAMP\n" +
                    ");\n" +
                    "create table MUL\n" +
                    "(\n" +
                    "  ID            NVARCHAR2(40) default NULL not null\n" +
                    "    primary key,\n" +
                    "  FIRSTOPERAND  CLOB,\n" +
                    "  SECONDOPERAND CLOB,\n" +
                    "  ANSWER        CLOB,\n" +
                    "  IDSESSION     NVARCHAR2(40) default NULL,\n" +
                    "  TIME          TIMESTAMP\n" +
                    ");\n" +
                    "create table SESSIONS\n" +
                    "(\n" +
                    "  ID        NVARCHAR2(40) default NULL not null\n" +
                    "    primary key,\n" +
                    "  IP        NVARCHAR2(25),\n" +
                    "  TIMESTART TIMESTAMP,\n" +
                    "  TIMEEND   TIMESTAMP\n" +
                    ");\n" +
                    "create table SUB\n" +
                    "(\n" +
                    "  ID            NVARCHAR2(40) default NULL not null\n" +
                    "    primary key,\n" +
                    "  FIRSTOPERAND  CLOB,\n" +
                    "  SECONDOPERAND CLOB,\n" +
                    "  ANSWER        CLOB,\n" +
                    "  IDSESSION     NVARCHAR2(40) default NULL,\n" +
                    "  TIME          TIMESTAMP\n" +
                    ");\n" +
                    "create table SUM\n" +
                    "(\n" +
                    "  ID            NVARCHAR2(40) default NULL not null\n" +
                    "    primary key,\n" +
                    "  FIRSTOPERAND  CLOB,\n" +
                    "  SECONDOPERAND CLOB,\n" +
                    "  ANSWER        CLOB,\n" +
                    "  IDSESSION     NVARCHAR2(40) default NULL,\n" +
                    "  TIME          TIMESTAMP\n" +
                    ");\n" +
                    "\n" +
                    "\n" +
                    "create view HISTORY as\n" +
                    "  SELECT\n" +
                    "    SESSIONS.id,\n" +
                    "    SESSIONS.ip,\n" +
                    "    SESSIONS.TIMESTART,\n" +
                    "    SESSIONS.TIMEEND,\n" +
                    "    'division' as operation,\n" +
                    "    DIV.FIRSTOPERAND,\n" +
                    "    DIV.SECONDOPERAND,\n" +
                    "    DIV.ANSWER,\n" +
                    "    DIV.TIME\n" +
                    "  FROM DIV\n" +
                    "    join SESSIONS on DIV.IDSESSION = SESSIONS.ID\n" +
                    "  union all\n" +
                    "  SELECT\n" +
                    "    SESSIONS.id,\n" +
                    "    SESSIONS.ip,\n" +
                    "    SESSIONS.TIMESTART,\n" +
                    "    SESSIONS.TIMEEND,\n" +
                    "    'multiply' as operation,\n" +
                    "    MUL.FIRSTOPERAND,\n" +
                    "    MUL.SECONDOPERAND,\n" +
                    "    MUL.ANSWER,\n" +
                    "    MUL.TIME\n" +
                    "  FROM MUL\n" +
                    "    join SESSIONS on MUL.IDSESSION = SESSIONS.ID\n" +
                    "  union all\n" +
                    "  SELECT\n" +
                    "    SESSIONS.id,\n" +
                    "    SESSIONS.ip,\n" +
                    "    SESSIONS.TIMESTART,\n" +
                    "    SESSIONS.TIMEEND,\n" +
                    "    'substraction' as operation,\n" +
                    "    SUB.FIRSTOPERAND,\n" +
                    "    SUB.SECONDOPERAND,\n" +
                    "    SUB.ANSWER,\n" +
                    "    SUB.TIME\n" +
                    "  FROM SUB\n" +
                    "    join SESSIONS on SUB.IDSESSION = SESSIONS.ID\n" +
                    "  union all\n" +
                    "  SELECT\n" +
                    "    SESSIONS.id,\n" +
                    "    SESSIONS.ip,\n" +
                    "    SESSIONS.TIMESTART,\n" +
                    "    SESSIONS.TIMEEND,\n" +
                    "    'summation' as operation,\n" +
                    "    SUM.FIRSTOPERAND,\n" +
                    "    SUM.SECONDOPERAND,\n" +
                    "    SUM.ANSWER,\n" +
                    "    SUM.TIME\n" +
                    "  FROM SUM\n" +
                    "    join SESSIONS on SUM.IDSESSION = SESSIONS.ID\n" +
                    "  union all\n" +
                    "  SELECT\n" +
                    "    SESSIONS.id,\n" +
                    "    SESSIONS.ip,\n" +
                    "    SESSIONS.TIMESTART,\n" +
                    "    SESSIONS.TIMEEND,\n" +
                    "    'fibonacci' as operation,\n" +
                    "    FIB.FIRSTOPERAND,\n" +
                    "    null        as SECONDOPERAND,\n" +
                    "    FIB.ANSWER,\n" +
                    "    FIB.TIME\n" +
                    "  FROM FIB\n" +
                    "    join SESSIONS on FIB.IDSESSION = SESSIONS.ID\n"
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private List<SessionsRow> selectSessionsFromBD(String mode, String order) {
        try (Connection connection = new DataBase(DBName).getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = getResultSessionsSet(mode, order, statement);// statement.executeQuery("SELECT DISTINCT ID,IP,TIMESTART,TIMEEND FROM HISTORY");
            return createSessionsList(rs);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private List<DBRow> selectDataFromBD(String mode, String order, String id) {
        try (Connection connection = new DataBase(DBName).getConnection()) {
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


    public static String getDBName() {
        return DBName;
    }
}