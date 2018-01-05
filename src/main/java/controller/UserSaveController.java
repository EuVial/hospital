//package controller;
//
//import dao.datasource.DataSource;
//import dao.entity.user.User;
//import dao.entity.user.UserRole;
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
//
//public class UserSaveController extends HttpServlet {
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        User user = new User();
//        try {
//            user.setId(Integer.parseInt(req.getParameter("id")));
//        } catch(NumberFormatException e) {}
//        user.setLogin(req.getParameter("login"));
//        user.setFirstName(req.getParameter("first_name"));
//        user.setLastName(req.getParameter("last_name"));
//        try {
//            user.setRole(UserRole.values()[Integer.parseInt(req.getParameter("role"))]);
//        } catch(NumberFormatException | ArrayIndexOutOfBoundsException e) {}
//        if(user.getLogin() != null && user.getFirstName() != null
//                && user.getLastName() != null && user.getRole() != null) {
//            Connection connection = null;
//            try {
////                connection = DataSource.getConnection();
//                connection = DataSource.getInstance().getConnection();
//                MySqlUserDao dao = new MySqlUserDao(connection);
//                UserServiceImpl service = new UserServiceImpl();
//                service.setUserDao(dao);
//                service.setDefaultPassword("12345");
//                service.save(user);
////            } catch(SQLException | ServiceException e) {
//            } catch(ServiceException e) {
//                throw new ServletException(e);
//            } finally {
//                try { connection.close(); } catch(Exception e) {}
//            }
//        }
//        resp.sendRedirect(req.getContextPath() + "/user/list.html");
//    }
//}
