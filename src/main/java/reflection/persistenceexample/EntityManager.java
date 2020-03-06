package reflection.persistenceexample;

import java.sql.SQLException;

public interface  EntityManager<T> {

    static <T> EntityManager<T> of(Class<T> personClass) {
        return new  EntityManagerImpl<>();
    }

    void persist(T t) throws SQLException;
}
