package controller;

import constants.Constants;
import dao.datasource.DataSource;
import org.apache.log4j.Logger;
import util.MainServiceFactoryImpl;
import util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {
    private final static Logger LOGGER =
            Logger.getLogger(String.valueOf(DispatcherServlet.class));

    @Override
    public void init() throws ServletException {
        try {
            DataSource.init(Constants.JDBC_DRIVER, Constants.JDBC_URL, Constants.JDBC_USER, Constants.JDBC_PASSWORD);
        } catch (ClassNotFoundException e) {
            LOGGER.error("Cannot initiate connection pool");
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    public ServiceFactory getServiceFactory() {
        return new MainServiceFactoryImpl();
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        String context = req.getContextPath();
        int postfixIndex = url.lastIndexOf(".html");
        if (postfixIndex != -1) {
            url = url.substring(context.length(), postfixIndex);
        } else {
            url = url.substring(context.length());
        }
        Action action = ActionFactory.getAction(url);
        Forward forward = null;
        if (action != null) {
            try (ServiceFactory factory = getServiceFactory()) {
                action.setServiceFactory(factory);
                forward = action.execute(req, resp);
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        if (forward != null && forward.isRedirect()) {
            resp.sendRedirect(context + forward.getUrl());
        } else {
            if (forward != null && forward.getUrl() != null) {
                url = forward.getUrl();
            }
            req.getRequestDispatcher("/WEB-INF/jsp" + url + ".jsp").forward(req, resp);
        }
    }
}
