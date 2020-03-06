package reflection.persistence;

import reflection.persistence.model.Meta;
public class EntityManagerImpl<T> implements EntityManager<T> {

    @Override
    public void persist(T t) {
        Metamodel metamodel = Metamodel.of(t.getClass());
    }
}

