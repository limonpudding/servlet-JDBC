package org;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.Date;
import java.util.List;

public class Calc extends HttpServlet {
    public static String globalURL = "http://localhost/";
    SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    protected static String DBName;
    private ObjectMapper mapper = new ObjectMapper();

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
        StringBuffer jb = new StringBuffer();
        try {
            BufferedReader reader = req.getReader();
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                jb.append(line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Operation operation = mapper.readValue(jb.toString(), Operation.class);
        operation.result = CreatorAnswer.calc(operation.a, operation.b, operation.operation);
        mapper.writeValue(resp.getWriter(), operation);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        DataBase db = new DataBase(Calc.getDBName());
        try (Connection connection = db.getConnection()) {
            PreparedStatement insert = connection.prepareStatement("insert into SESSIONS (ID, IP, TIMESTART, TIMEEND) values (?,?,?,?)");
            PreparedStatement update = connection.prepareStatement("update SESSIONS set TIMEEND=? where SESSIONS.ID =?");

            insert.setString(1, req.getSession().getId());
            insert.setString(2, req.getRemoteAddr());
            insert.setTimestamp(3, new java.sql.Timestamp(req.getSession().getCreationTime()));
            insert.setTimestamp(4, new java.sql.Timestamp(req.getSession().getCreationTime()));

            update.setTimestamp(1, new java.sql.Timestamp(req.getSession().getLastAccessedTime()));
            update.setString(2, req.getSession().getId());

            if (req.getSession().isNew()) {
                insert.executeUpdate();
            } else {
                update.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Я сервайс");
    }

    @Override
    public void init() throws ServletException {
        super.init();
        DBName = this.getServletConfig().getInitParameter("DBName");
        try (Connection connection = new DataBase(DBName).getConnection()) {
            if (!connection.getMetaData().getDatabaseProductName().toUpperCase().contains("H2")) {
                return;
            }

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


    public static String getDBName() {
        return DBName;
    }
}