package model.connection;

import java.util.List;

/**
 * Created by rajmu on 17.03.28.
 */
public interface IDataAccessObject<T> {
    public List<T> getAll();

    public T getById(int id);

    public void create(T object);

    public void update(T object);

    public void delete(T object);
}
