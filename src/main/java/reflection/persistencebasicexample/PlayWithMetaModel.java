package reflection.persistencebasicexample;

import java.util.List;

public class PlayWithMetaModel {
    public static void main(String[] args) {
        MetaModel<Person> metaModel = MetaModel.of(Person.class);

        PrimaryKeyField primaryKeyField = metaModel.getPrimaryKey();
        List<ColumnField> columnFields = metaModel.getColumns();

        System.out.println(primaryKeyField.getName());
        System.out.println(primaryKeyField.getType());
    }
}
