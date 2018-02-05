package service.user;

import service.ServiceException;

public class UserPasswordIncorrectException extends ServiceException {
    private Integer id;

    public UserPasswordIncorrectException(final Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
