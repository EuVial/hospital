package service;

import dao.entity.Entity;

import java.util.List;

public interface EntityService<T extends Entity> {
    T findById(Integer id) throws ServiceException;

    List<T> findAll() throws ServiceException;

    void save(T entity) throws ServiceException;

    boolean canDelete(T entity) throws ServiceException;

    void delete(T entity) throws ServiceException;
}
