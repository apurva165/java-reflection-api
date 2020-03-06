package reflection.persistenceexample;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;

public class EntityManagerImpl<T> implements EntityManager<T> {
    private AtomicLong idgenerator = new AtomicLong(0L);

    @Override
    public void persist(T t) throws SQLException {
        MetaModel metamodel = MetaModel.of(t.getClass());
        String sql = metamodel.buildInsertRequest();
        PreparedStatement statement = prepareStatementWith(sql).andParameters(t);
        statement.executeUpdate();
    }

    private PreparedStatementWrapper prepareStatementWith(String sql) throws SQLException {
        Connection connection = DriverManager
                .getConnection("jdbc:h2:~/src/db-files/db-details", "sa", "");
        PreparedStatement statement = connection.prepareStatement(sql);
        return new PreparedStatementWrapper(statement);
    }

    private class PreparedStatementWrapper {

        private PreparedStatement statement;

        public PreparedStatementWrapper(PreparedStatement statement) {
            this.statement = statement;
        }

        public PreparedStatement andParameters(T t) throws SQLException {
            MetaModel metaModel = MetaModel.of(t.getClass());
            Class<?> primaryKeyType = metaModel.getPrimaryKey().getType();
            if(primaryKeyType == long.class){
                statement.setLong(1 , idgenerator.incrementAndGet());
            }
            for(int columnIndex = 0; columnIndex < metaModel.getColumns().size(); columnIndex++){
                ColumnField columnField = metaModel.getColumns().get(columnIndex);

            }
        }
    }
}

