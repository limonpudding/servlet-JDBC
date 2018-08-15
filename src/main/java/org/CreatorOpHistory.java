package org;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreatorOpHistory extends AbstractPageFactory {

    SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    public CreatorOpHistory(HttpServletRequest req, HttpServletResponse resp) {
        super(req, resp);
    }

    public void build() throws Exception {
        page.getValue().getRequest().setAttribute("fullOperationsHistory", selectDataFromBD());
        page.getValue().getRequest().getRequestDispatcher("ophistory.jsp").forward(page.getValue().getRequest(), page.getValue().getResponse());
    }

    private List<DBRow> selectDataFromBD(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.151:1521:gmudb", "internship", "internship");
            Statement statement = connection.createStatement();
            String sqlFormat="yyyy.MM.dd HH24:mi:ss";
            ResultSet rs = statement.executeQuery("select * from HISTORY");
            List<DBRow> rows = new ArrayList<DBRow>();
            DBRow row;
            while(rs.next()) {
                row = new DBRow();
                row.id = rs.getString(1);
                row.ip = rs.getString(2);
                row.sessionStartTime = rs.getString(3);
                row.sessionEndTime = rs.getString(4);
                row.operationName = rs.getString(5);
                row.op1 = rs.getString(6);
                row.op2 = rs.getString(7);
                row.answer = rs.getString(8);
                row.time = rs.getString(9);
                rows.add(row);
            }
            return rows;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
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

        public String id() {
            return id;
        }

        public String ip() {
            return ip;
        }

        public String sessionStartTime(){
            return sessionStartTime;
        }

        public String sessionEndTime(){
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

        public String time(){
            return time;
        }
    }
}
