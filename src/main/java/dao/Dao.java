package dao;

import java.io.Serializable;
import java.util.List;

/**
 * Unified interface for managing the persistent state of objects
 * @param <T> persistent object type
 * @param <PK> primary key type
 **/
public interface Dao<PK extends Serializable, T extends Identified<PK>> {

    /** Создает новую запись и соответствующий ей объект */
//    T create() throws PersistException;

    /** Возвращает объект соответствующий записи с первичным ключом key или null */
    T read(PK key) throws PersistException, PersistException;

    /** Создает новую запись, соответствующую объекту object */
    T persist(T object) throws PersistException;

    /** Сохраняет состояние объекта group в базе данных */
    void update(T object) throws PersistException;

    /** Удаляет запись об объекте из базы данных соответствующую первичному ключу key */
    void delete(T object) throws PersistException;

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    List<T> getAll() throws PersistException;
}
