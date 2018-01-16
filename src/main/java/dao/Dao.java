package dao;

import java.io.Serializable;
import java.util.List;

/**
 * Unified interface for managing the persistent state of objects
 * @param <T> persistent object type
 * @param <PK> primary key type
 **/
public interface Dao<PK extends Serializable, T extends Identified<PK>> {

    /** Returns an object corresponding to a record with a primary key or null */
    T read(PK key) throws PersistException;

    /** Creates a new record that corresponds to the object */
    T persist(T object) throws PersistException;

    /** Saves the state of the object in the database */
    void update(T object) throws PersistException;

    /** Deletes an entry about the object from the database that corresponds to the primary key */
    void delete(T object) throws PersistException;

    /** Returns a list of objects matching all records in the database */
    List<T> getAll() throws PersistException;

    public boolean isInitiatesTransfers(T object) throws PersistException;
}
