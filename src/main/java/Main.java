import dao.PersistException;
import dao.datasource.DataSource;
import dao.mysql.patient.MySqlDiagnosisToPatientDao;
import domain.patient.DiagnosisToPatient;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) throws PersistException {
        Connection connection = DataSource.getInstance().getConnection();
        MySqlDiagnosisToPatientDao diagnosisToPatientDao = new MySqlDiagnosisToPatientDao(connection);
        DiagnosisToPatient diagnosisToPatient = diagnosisToPatientDao.readInfo(1);
        System.out.println(diagnosisToPatient.getDoctor().getFirstName());
    }
//        TestUserDao();
////        Dao<Integer, UserSpecialization> specializationDao = null;
////        UserSpecialization specialization = null;
////        try {
////            specializationDao = new MySqlSpecializationDao(DataSource.getConnection());
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////        List<UserSpecialization> specializationList = specializationDao.getAll();
////        for (UserSpecialization specialization : specializationList) {
////            System.out.println(specialization.getTitle());
////        }
////        UserSpecialization specializationTest = new UserSpecialization();
////        specializationTest.setTitle("TestSpec");
////        specializationDao.persist(specializationTest);
////        for (UserSpecialization specialization : specializationList) {
////            System.out.println(specialization.getTitle());
////        }
//    }
//
//    private static void TestUserDao() throws PersistException {
////        DataSource.init(Constants.JDBC_DRIVER, Constants.JDBC_URL, Constants.JDBC_USER, Constants.JDBC_PASSWORD);
//        Dao<Integer, User> userDao = null;
//        User oldUser = null;
////        try {
////            userDao = new MySqlUserDao(DataSource.getConnection());
//            userDao = new MySqlUserDao(DataSource.getInstance().getConnection());
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
//        List<User> userList = userDao.getAll();
//        for (User user : userList) {
//            if (user.getFirstName().equals("testFN")) {
//                oldUser = user;
//                oldUser.setLastName("UpdatedName");
//                userDao.update(oldUser);
//                userDao.delete(oldUser);
//            }
//        }
//        User userTest = new User();
//        userTest.setFirstName("testFN");
//        userTest.setLastName("testLN");
//        userTest.setLogin("testLogin");
//        userTest.setPassword("testPass");
//        userTest.setRole(UserRole.values()[2]);
////        userTest.setSpecialization(new UserSpecialization());
////        userTest.getSpecialization().setId(1);
//        userDao.persist(userTest);
//
//        for (User user : userDao.getAll()) {
//            System.out.println(user.getFirstName());
//        }
//        for (User user : userDao.getAll()) {
//            System.out.println(user.getRole());
//        }
//    }
}
