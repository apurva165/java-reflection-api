package reflection.persistence;

import reflection.persistence.annotaions.Column;
import reflection.persistence.annotaions.PrimaryKey;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Metamodel{

    private Class<?> tClass;

    public Metamodel of(Class<?> tClass) {
     return new Metamodel(tClass);
        //  this.tClass = tClass;
    }

    public PrimaryKeyField getPrimaryKey() {

        Field[] declaredFields = tClass.getDeclaredFields();
        for (Field field : declaredFields) {

            PrimaryKey primaryKey = field.getAnnotation(PrimaryKey.class);
            if (primaryKey != null) {

                PrimaryKeyField primaryKeyField = new PrimaryKeyField(field);
                return primaryKeyField;
            }

        }
        throw  new  IllegalArgumentException("No key found");
    }
    public Metamodel(Class<?> tClass) {
        this.tClass = tClass;
    }
    public List<ColumnField> getColumns() {
        Field[] declaredFields = tClass.getDeclaredFields();
        List<ColumnField> columnFields = new ArrayList<>();
        for (Field field : declaredFields) {

            Column column = field.getAnnotation(Column.class);
            if (column != null) {

                ColumnField columnField = new ColumnField(field);
                columnFields.add(columnField);
                return  columnFields;
            }

        }
      return columnFields;
    }
}
