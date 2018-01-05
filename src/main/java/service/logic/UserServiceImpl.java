package service.logic;

import dao.entity.user.User;
import dao.mysql.user.MySqlUserDao;
import dao.PersistException;
import service.ServiceException;
import service.user.UserLoginNotUniqueException;
import service.EntityNotExistsException;
import service.user.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    //TODO: LOGGER
    private MySqlUserDao userDao;
    private String defaultPassword;

    public void setUserDao(MySqlUserDao userDao) {
        this.userDao = userDao;
    }

    public void setDefaultPassword(String defaultPassword) {
        this.defaultPassword = defaultPassword;
    }

    @Override
    public User findById(Integer id) throws ServiceException {
        try {
            return userDao.read(id);
        } catch(PersistException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findAll() throws ServiceException {
        try {
            return userDao.getAll();
        } catch(PersistException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(User user) throws ServiceException {
        try {
//          if the user with the current id exists - try to read the data about him
            if(user.getId() != null) {
                User storedUser = userDao.read(user.getId());
                if(storedUser != null) {
                    user.setPassword(storedUser.getPassword());
                    if(storedUser.getLogin().equals(user.getLogin()) || userDao.readByLogin(user.getLogin()) == null) {
                        userDao.update(user);
                    } else {
                        throw new UserLoginNotUniqueException(user.getLogin());
                    }
                } else {
                    throw new EntityNotExistsException(user.getId());
                }
            } else {
//              if a new user is saved, then save the new user and assign a standard password
                user.setPassword(defaultPassword);
                if(userDao.readByLogin(user.getLogin()) == null) {
                    userDao.persist(user);
                } else {
                    throw new UserLoginNotUniqueException(user.getLogin());
                }
            }
        } catch(PersistException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean canDelete(User user) throws ServiceException {
        try {
            return !userDao.isInitiatesTransfers(user);
        } catch(PersistException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(User user) throws ServiceException {
        if(user.getId() != null) {
            try {
                userDao.delete(user);
            } catch (PersistException e) {
                throw new ServiceException(e);
            }
        }
    }
}
