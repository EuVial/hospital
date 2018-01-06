package service.user;

import dao.entity.user.User;
import service.ServiceException;

import java.util.List;

public interface UserService {
    User findById(Integer id) throws ServiceException;

    List<User> findAll() throws ServiceException;

    void save(User user) throws ServiceException;

    boolean canDelete(User user) throws ServiceException;

    void delete(User user) throws ServiceException;

    User findByLoginAndPassword(String login, String password) throws ServiceException;
}
