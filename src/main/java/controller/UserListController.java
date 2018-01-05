//package controller;
//
//import dao.datasource.DataSource;
//import dao.entity.user.User;
//import dao.mysql.user.MySqlUserDao;
//import service.ServiceException;
//import service.logic.UserServiceImpl;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.sql.Connection;
//import java.util.List;
//
//public class UserListController extends HttpServlet {
////    @Override
////    public void init() throws ServletException {
////        DataSource.init(Constants.JDBC_DRIVER, Constants.JDBC_URL, Constants.JDBC_USER, Constants.JDBC_PASSWORD);
////    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Connection connection = null;
//        try {
////            connection = DataSource.getConnection();
//            connection = DataSource.getInstance().getConnection();
//            MySqlUserDao userDao = new MySqlUserDao(connection);
////            MySqlSpecializationDao specializationDao = new MySqlSpecializationDao(connection);
//            UserServiceImpl service = new UserServiceImpl();
//            service.setUserDao(userDao);
////            service.setSpecializationDao(specializationDao);
//            List<User> users = service.findAll();
//            request.setAttribute("users", users);
//            request.getRequestDispatcher("/WEB-INF/jsp/user/list.jsp").forward(request, response);
////            } catch(SQLException | ServiceException e) {
//        } catch(ServiceException e) {
//            throw new ServletException(e);
//        } finally {
//            try { connection.close(); } catch(Exception e) {}
//        }
//    }
//}
