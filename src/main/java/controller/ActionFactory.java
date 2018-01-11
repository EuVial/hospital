package controller;

import controller.diagnosis.DiagnosisDeleteAction;
import controller.diagnosis.DiagnosisEditAction;
import controller.diagnosis.DiagnosisListAction;
import controller.diagnosis.DiagnosisSaveAction;
import controller.patient.*;
import controller.patient.view.DiseaseHistoryAction;
import controller.patient.view.PatientDiagnosisAction;
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
        actions.put("/patient/view", PatientViewAction.class);
        actions.put("/patient/view/patient_diagnosis", PatientDiagnosisAction.class);
        actions.put("/patient/view/disease_history", DiseaseHistoryAction.class);

        actions.put("/diagnosis/list", DiagnosisListAction.class);
        actions.put("/diagnosis/edit", DiagnosisEditAction.class);
        actions.put("/diagnosis/save", DiagnosisSaveAction.class);
        actions.put("/diagnosis/delete", DiagnosisDeleteAction.class);
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
