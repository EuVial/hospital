package service.user;

import service.ServiceException;

public class UserLoginNotUniqueException extends ServiceException {
    private String login;

    public UserLoginNotUniqueException(String login) {
        super();
        this.login = login;
    }

    public String getLogin() {
        return login;
    }
}