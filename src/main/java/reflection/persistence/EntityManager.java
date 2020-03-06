package reflection.persistence;

import reflection.persistence.model.Person;

public interface EntityManager<T> {

    static <T> EntityManager<T> of(Class<T> personClass) {
        return new  EntityManagerImpl<>();
    }

    void persist(T t);
}
