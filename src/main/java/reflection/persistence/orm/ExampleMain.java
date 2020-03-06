package reflection.persistence.orm;

import reflection.persistence.ColumnField;
import reflection.persistence.Metamodel;
import reflection.persistence.PrimaryKeyField;
import reflection.persistence.model.Person;

import java.util.List;

public class ExampleMain {
    public static void main(String[] args) {
        Metamodel<Person> metamodel = Metamodel.of(Person.class);

        PrimaryKeyField primaryKeyField = metamodel.getPrimaryKey();

        List<ColumnField> columnFields = metamodel.getColumns();


        System.out.println(primaryKeyField.getName());
        System.out.println(columnFields.toString());
    }
}
