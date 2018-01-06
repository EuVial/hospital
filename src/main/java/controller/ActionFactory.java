package controller;

import controller.patient.PatientDeleteAction;
import controller.patient.PatientEditAction;
import controller.patient.PatientListAction;
import controller.patient.PatientSaveAction;
import controller.user.UserDeleteAction;
import controller.user.UserEditAction;
import controller.user.UserListAction;
import controller.user.UserSaveAction;

import javax.servlet.ServletException;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private static Map<String, Class<? extends Action>> actions = new HashMap<>();
    static {
        actions.put("/", MainAction.class);
        actions.put("/index", MainAction.class);

        actions.put("/login", LoginAction.class);
        actions.put("/logout", LogoutAction.class);

        actions.put("/user/list", UserListAction.class);
        actions.put("/user/edit", UserEditAction.class);
        actions.put("/user/save", UserSaveAction.class);
        actions.put("/user/delete", UserDeleteAction.class);

        actions.put("/patient/list", PatientListAction.class);
        actions.put("/patient/edit", PatientEditAction.class);
        actions.put("/patient/save", PatientSaveAction.class);
        actions.put("/patient/delete", PatientDeleteAction.class);
    }

    public static Action getAction(String url) throws ServletException {
        Class<?> action = actions.get(url);
        try {
            return (Action)action.newInstance();
        } catch(InstantiationException | IllegalAccessException | NullPointerException e) {
            throw new ServletException(e);
        }
    }
}
