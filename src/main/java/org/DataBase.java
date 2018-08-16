package org;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataBase {
    private DataSource dataSource;

    public DataBase(String jndiName) {
        try {
            dataSource = (DataSource) new InitialContext().lookup("java:comp/env/" + jndiName);
        } catch (NamingException e) {
            // Handle error that it not configured in JNDI.
            throw new IllegalStateException(jndiName + " is missing in JNDI!", e);
        }
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
