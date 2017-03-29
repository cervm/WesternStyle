package model.connection;

import model.exception.ModelSyncException;

import java.util.List;

/**
 * Created by rajmu on 17.03.28.
 */
public interface IDataAccessObject<T> {
    public List<T> getAll() throws ModelSyncException;

    public T getById(int id) throws ModelSyncException;

    public void create(T object) throws ModelSyncException;

    public void update(T object) throws ModelSyncException;

    public void delete(T object) throws ModelSyncException;
}
