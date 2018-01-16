package dao.mysql;

import dao.Dao;
import dao.Identified;
import dao.PersistException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *Abstract class providing the basic implementation of CRUD operations using JDBC.
 *
 * @param <T>  type of persistent object
 * @param <PK> type of primary key
 */
public abstract class AbstractJDBCDao<PK extends Integer,
        T extends Identified<PK>> implements Dao<PK, T> {

    /**
     * Returns the sql query to retrieve all records.
     *
     * SELECT * FROM [Table]
     */
    public abstract String getSelectQuery();

    /**
     * Returns a sql query to insert a new record into the database.
     *
     * INSERT INTO [Table] ([column, column, ...]) VALUES (?, ?, ...);
     */
    public abstract String getCreateQuery();

    /**
     * Returns a sql query to update the record.
     *
     * UPDATE [Table] SET [column = ?, column = ?, ...] WHERE id = ?;
     */
    public abstract String getUpdateQuery();

    /**
     * Returns a sql query to delete the record from the database.
     *
     * DELETE FROM [Table] WHERE id= ?;
     */
    public abstract String getDeleteQuery();

    /**
     * Returns a sql query checks whether this record is associated with other tables and whether it can be safely removed.
     *
     * SELECT COUNT(*) AS 'count' FROM [Initiate table] WHERE id = ? LIMIT 1;
     */
    public abstract String getInitiatesQuery();

    /**
     * Disassembles the ResultSet and returns a list of objects corresponding to the contents of the ResultSet.
     */
    protected abstract List<T> parseResultSet(ResultSet rs) throws PersistException;

    /**
     * Sets the insert arguments of the request according to the value of the fields of the object.
     */
    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws PersistException;

    /**
     * Sets the query update arguments according to the field value of the object.
     */
    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws PersistException;

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    @Override
    public T read(Integer key) throws PersistException {
        List<T> list;
        String sql = getSelectQuery();
        sql += " WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        if (list == null || list.size() == 0) {
            throw new PersistException("Record with PK = " + key + " not found");
        }
        if (list.size() > 1) {
            throw new PersistException("Received more than one record.");
        }
        return list.iterator().next();
    }

    @Override
    public List<T> getAll() throws PersistException {
        List<T> list;
        String sql = getSelectQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return list;
    }

    @Override
    public T persist(T object) throws PersistException {
        T persistInstance;
        // Add record
        String sql = getCreateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForInsert(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On persist modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        // Get the newly inserted record
        sql = getSelectQuery() + " WHERE id = last_insert_id();";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            List<T> list = parseResultSet(rs);
            if ((list == null) || (list.size() != 1)) {
                throw new PersistException("Exception on findByPK new persist data.");
            }
            persistInstance = list.iterator().next();
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return persistInstance;
    }

    @Override
    public void update(T object) throws PersistException {
        String sql = getUpdateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForUpdate(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On update modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    public void delete(T object) throws PersistException {
        String sql = getDeleteQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try {
                statement.setObject(1, object.getId());
            } catch (Exception e) {
                throw new PersistException(e);
            }
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On delete modify more then 1 record: " + count);
            }
            statement.close();
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    public boolean isInitiatesTransfers(T object) throws PersistException {
        String sql = getInitiatesQuery();
        Integer id = object.getId();
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                boolean result = true;
                if (resultSet.next()) {
                    result = resultSet.getBoolean("count");
                }
                return result;
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    public AbstractJDBCDao(Connection connection) {
        this.connection = connection;
    }
}