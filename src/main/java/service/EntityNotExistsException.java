package service;

public class EntityNotExistsException extends ServiceException {
    private Integer id;

    public EntityNotExistsException(final Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
