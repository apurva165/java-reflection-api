package reflection.persistence;

import reflection.persistence.model.Person;

public class WritingObjects  {
    EntityManager<Person> entityManager  = EntityManager.of(Person.class);

    Person linda = new Person("linda", 31);
    Person james = new Person("james", 31);
    Person susan = new Person("susan", 31);

        entityManager.persist(linda);
        entityManager.persist(james);
        entityManager.persist(susan);



}
