package model.connection;

import model.exception.ModelSyncException;

import java.util.List;

/**
 * Data Access Object Interface
 */
public interface IDataAccessObject<T> {
    public List<T> getAll() throws ModelSyncException;

    public T getById(int id) throws ModelSyncException;

    public T create(T object) throws ModelSyncException;

    public void update(T object) throws ModelSyncException;

    public void delete(T object) throws ModelSyncException;
}
