package service;

public class EntityNotExistsException extends ServiceException {
    private Integer id;

    public EntityNotExistsException(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
