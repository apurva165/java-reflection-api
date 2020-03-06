package reflection.persistenceexample;

import java.lang.reflect.Field;

public class ColumnField {
    private Field field;

    public ColumnField(Field field) {
        this.field = field;
    }

    public String getName() {
        return field.getName();
    }
}
