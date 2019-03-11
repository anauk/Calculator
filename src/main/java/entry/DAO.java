package entry;

import java.util.Collection;
import java.util.List;

public interface DAO<T> {
    T get(int id);
    void put(T end);
    Collection<T> all();
    void delete(int id);
    boolean isEmpty();
}
