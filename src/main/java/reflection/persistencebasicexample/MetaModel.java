package reflection.persistencebasicexample;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

class MetaModel<T> {

    private Class<T> clss;

    public MetaModel(Class<T> clss) {
        this.clss = clss;
    }

    public static <T> MetaModel<T> of(Class<T> Clss) {
        return new MetaModel<>(Clss);
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
}
