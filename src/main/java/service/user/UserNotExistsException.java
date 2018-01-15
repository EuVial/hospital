package service.user;

import service.ServiceException;

public class UserNotExistsException extends ServiceException {
    private Integer id;

    public UserNotExistsException(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
