package repository;

import java.util.List;

public interface Repository<T,Y> {
    T getById(Y id);
    List<T> getAll();
    void save(T t);
    void delete(T t);
    void update(Y id,T nuevo);


}
