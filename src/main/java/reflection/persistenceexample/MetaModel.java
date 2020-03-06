package reflection.persistenceexample;

import reflection.persistencebasicexample.Column;
import reflection.persistencebasicexample.ColumnField;
import reflection.persistencebasicexample.PrimaryKey;
import reflection.persistencebasicexample.PrimaryKeyField;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class MetaModel {

    private Class<?> clss;

    public MetaModel(Class<?> clss) {
        this.clss = clss;
    }

    public static MetaModel of(Class<?> Clss) {
        return new MetaModel(Clss);
    }

    public PrimaryKeyField getPrimaryKey() {
        Field[] fields = clss.getDeclaredFields();

        for (Field field : fields) {
            PrimaryKey primaryKey = field.getAnnotation(PrimaryKey.class);
            if (primaryKey != null) {
                return new PrimaryKeyField(field);
            }
        }
        throw new IllegalArgumentException("No PK Found");
    }

    public List<ColumnField> getColumns() {
        Field[] fields = clss.getDeclaredFields();
        List<ColumnField> columnFields = new ArrayList<>();

        for (Field field : fields) {
            Column column = field.getAnnotation(Column.class);
            if (column != null) {
                ColumnField columnField = new ColumnField(field);
                columnFields.add(columnField);
            }
        }
        return columnFields;
    }

    public String buildInsertRequest() {
        //insert into person (id, name, age) value(?, ?, ?)
        String primaryKeyColumnName = getPrimaryKey().getName();
        List<String> columnNames = getColumns().stream().map(columnField -> columnField.getClass().getName()).collect(Collectors.toList());
        columnNames.add(0, primaryKeyColumnName);
        String columnElement = String.join(", ", columnNames);
        int numberOfColumns = getColumns().size() + 1;
        String questionMarkElement = IntStream.range(0, numberOfColumns)
                .mapToObj(index -> "?")
                .collect(Collectors.joining(", "));
        return "insert into " + this.clss.getSimpleName() + " (" + columnElement + ") values (" + questionMarkElement + ")";
    }
}
