package service.user;

import domain.user.User;
import service.EntityService;
import service.ServiceException;

public interface UserService extends EntityService<User> {
//    User findById(Integer id) throws ServiceException;
//
//    List<User> findAll() throws ServiceException;
//
//    void save(User user) throws ServiceException;
//
//    boolean canDelete(User user) throws ServiceException;
//
//    void delete(User user) throws ServiceException;

    User findByLoginAndPassword(String login, String password) throws ServiceException;

    void changePassword(Integer userId, String oldPassword, String newPassword) throws ServiceException;
}
