import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA.
 * User: pairing
 * Date: 9/16/13
 * Time: 1:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class StatementFactory {

    public static StatementHolder getHiveStatement() {
        return new StatementHolder() {

            public Connection connection;
            public Statement statement;

            @Override
            public Statement statement() {
                try {
                    if (connection == null) {
                        connection = HiveConnection.getConnection();
                    }
                    if (statement == null) {
                        statement = connection.createStatement();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return statement;
            }

            @Override
            public void close() {
                if (statement != null) {
                    try {
                        statement().close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

}
