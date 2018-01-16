package dao.datasource;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import constants.Constants;
import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    private final static Logger LOGGER =
            Logger.getLogger(String.valueOf(DataSource.class));

    private static DataSource dataSource;
    private ComboPooledDataSource comboPooledDataSource;
    private static String jdbcUrl;
    private static String jdbcUser;
    private static String jdbcPassword;

    public static void init(String jdbcDriver, String jdbcUrl, String jdbcUser, String jdbcPassword) throws ClassNotFoundException {
        Class.forName(jdbcDriver);
        DataSource.jdbcUrl = jdbcUrl;
        DataSource.jdbcUser = jdbcUser;
        DataSource.jdbcPassword = jdbcPassword;
    }

    private DataSource() {
        try {
            comboPooledDataSource = new ComboPooledDataSource();
            comboPooledDataSource
                    .setDriverClass(Constants.JDBC_DRIVER);
            comboPooledDataSource
                    .setJdbcUrl(Constants.JDBC_URL);
            comboPooledDataSource.setUser(Constants.JDBC_USER);
            comboPooledDataSource.setPassword(Constants.JDBC_PASSWORD);
        }
        catch (PropertyVetoException ex1) {
                LOGGER.error("Incorrect parameters sent to connection pool");
                ex1.printStackTrace();
            }
        }

    public static DataSource getInstance() {
        if (dataSource == null)
            dataSource = new DataSource();
        return dataSource;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = comboPooledDataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Connection pool cannot get connection");
            e.printStackTrace();
        }
        return connection;
    }
}
