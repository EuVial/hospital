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
//public class UserEditController extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Integer id = null;
//        try {
//            id = Integer.parseInt(request.getParameter("id"));
//        } catch(NumberFormatException e) {}
//        if(id != null) {
////            TODO: try-with-resources
//            Connection connection = null;
//            try {
////                connection = DataSource.getConnection();
//                connection = DataSource.getInstance().getConnection();
//                MySqlUserDao dao = new MySqlUserDao(connection);
//                UserServiceImpl service = new UserServiceImpl();
//                service.setUserDao(dao);
//                User user = service.findById(id);
//                request.setAttribute("user", user);
////            } catch(SQLException | ServiceException e) {
//            } catch(ServiceException e) {
//                throw new ServletException(e);
//            } finally {
//                try { connection.close(); } catch(Exception e) {}
//            }
//        }
//        request.setAttribute("roles", UserRole.values());
//        request.getRequestDispatcher("/WEB-INF/jsp/user/edit.jsp").forward(request, response);
//    }
//}
//
