package service.user;

import domain.user.User;
import service.EntityService;
import service.ServiceException;

public interface UserService extends EntityService<User> {
    User findByLoginAndPassword(String login, String password) throws ServiceException;

    void changePassword(Integer userId, String oldPassword, String newPassword) throws ServiceException;
}
