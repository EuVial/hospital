package dao.datasource;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import constants.Constants;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
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
            e.printStackTrace();
        }
        return connection;
    }







    //    private final static Logger LOGGER = Logger.getLogger(String.valueOf(DataSource.class));
//    private static String jdbcUrl;
//    private static String jdbcUser;
//    private static String jdbcPassword;
//    public static void init(String jdbcDriver, String jdbcUrl, String jdbcUser, String jdbcPassword) {
//        try {
//            Class.forName(jdbcDriver);
//        } catch (ClassNotFoundException e) {
//            LOGGER.warn("Driver not found");
//        }
//        DataSource.jdbcUrl = jdbcUrl;
//        DataSource.jdbcUser = jdbcUser;
//        DataSource.jdbcPassword = jdbcPassword;
//    }
//
//    public static Connection getConnection() throws SQLException {
//        Connection connection = null;
//        try {
//            connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
//        } catch (SQLException e) {
//            LOGGER.warn("Can't get connection to " + jdbcUrl);
//        }
//        return connection;
//    }
}
