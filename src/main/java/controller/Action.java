package controller;

import util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Action {
    private ServiceFactory serviceFactory;

    public final ServiceFactory getServiceFactory() {
        return serviceFactory;
    }

    final void setServiceFactory(final ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    public abstract Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
