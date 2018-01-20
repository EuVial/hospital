package constants;

public class Constants {
    public static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static String JDBC_URL = "jdbc:mysql://localhost:3306/hospital?useSSL=false&useUnicode=true&characterEncoding=UTF-8";
    public static String JDBC_USER = "root";
    public static String JDBC_PASSWORD = "pass";
    public static String REGEX_LOGIN = "^[a-zA-Z]+\\w*";
    public static String REGEX_NAME = "^([A-Z][a-z]+)|([А-Я][а-я]+)";
}
