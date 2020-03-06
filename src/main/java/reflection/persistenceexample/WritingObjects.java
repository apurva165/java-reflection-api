package reflection.persistenceexample;

import reflection.persistenceexample.model.Person;

import java.sql.SQLException;

public class WritingObjects  {
    public static void main(String[] args) throws SQLException {
        EntityManager<Person> entityManager  = EntityManager.of(Person.class);

        Person linda = new Person("linda", 31);
        Person james = new Person("james", 31);
        Person susan = new Person("susan", 31);

        entityManager.persist(linda);
        entityManager.persist(james);
        entityManager.persist(susan);
    }

}
