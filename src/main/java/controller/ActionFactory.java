package controller;

import controller.account.AccountSaveAction;
import controller.password.PasswordResetAction;
import controller.password.PasswordSaveAction;
import controller.patient.*;
import controller.patient.view.DiseaseHistoryAction;
import controller.patient.view.diagnosis.PatientDiagnosisDeleteAction;
import controller.patient.view.diagnosis.PatientDiagnosisEditAction;
import controller.patient.view.diagnosis.PatientDiagnosisSaveAction;
import controller.patient.view.diagnosis.PatientDiagnosisViewAction;
import controller.patient.view.treatment.*;
import controller.user.UserDeleteAction;
import controller.user.UserEditAction;
import controller.user.UserListAction;
import controller.user.UserSaveAction;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private final static Logger LOGGER =
            Logger.getLogger(String.valueOf(ActionFactory.class));
    private static Map<String, Class<? extends Action>> actions = new HashMap<>();
    static {
        actions.put("/", MainAction.class);
        actions.put("/index", MainAction.class);

        actions.put("/login", LoginAction.class);
        actions.put("/logout", LogoutAction.class);

        actions.put("/password/save", PasswordSaveAction.class);
        actions.put("/password/reset", PasswordResetAction.class);

        actions.put("/account/save", AccountSaveAction.class);

        actions.put("/user/list", UserListAction.class);
        actions.put("/user/edit", UserEditAction.class);
        actions.put("/user/save", UserSaveAction.class);
        actions.put("/user/delete", UserDeleteAction.class);

        actions.put("/patient/list", PatientListAction.class);
        actions.put("/patient/edit", PatientEditAction.class);
        actions.put("/patient/save", PatientSaveAction.class);
        actions.put("/patient/delete", PatientDeleteAction.class);
        actions.put("/patient/view", PatientViewAction.class);
        actions.put("/patient/discharge", PatientDischargeAction.class);
        actions.put("/patient/discharge/done", PatientDischargeDoneAction.class);

        actions.put("/patient/view/diagnosis/view", PatientDiagnosisViewAction.class);
        actions.put("/patient/view/diagnosis/edit", PatientDiagnosisEditAction.class);
        actions.put("/patient/view/diagnosis/save", PatientDiagnosisSaveAction.class);
        actions.put("/patient/view/diagnosis/delete", PatientDiagnosisDeleteAction.class);

        actions.put("/patient/view/treatment/list", TreatmentListAction.class);
        actions.put("/patient/view/treatment/edit", TreatmentEditAction.class);
        actions.put("/patient/view/treatment/save", TreatmentSaveAction.class);
        actions.put("/patient/view/treatment/delete", TreatmentDeleteAction.class);
        actions.put("/patient/view/treatment/view", TreatmentViewAction.class);
        actions.put("/patient/view/treatment/done", TreatmentDoneAction.class);

        actions.put("/patient/view/disease_history", DiseaseHistoryAction.class);
    }

    public static Action getAction(String url) throws ServletException {
        Class<?> action = actions.get(url);
        if (action != null) {
            try {
                return (Action)action.newInstance();
            } catch (InstantiationException | IllegalAccessException | NullPointerException e) {
                LOGGER.error("Cannot create new instance for action " + e);
                throw new ServletException(e);
            }
        } else {
            return null;
        }
    }
}
