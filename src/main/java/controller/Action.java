package controller;

import util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

abstract class Action {
    private ServiceFactory serviceFactory;

    public final ServiceFactory getServiceFactory() {
        return serviceFactory;
    }

    public final void setServiceFactory(final ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    abstract Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
