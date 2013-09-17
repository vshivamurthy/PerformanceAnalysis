import java.sql.Statement;

/**
* Created with IntelliJ IDEA.
* User: pairing
* Date: 9/16/13
* Time: 1:46 PM
* To change this template use File | Settings | File Templates.
*/
public interface StatementHolder {
    Statement statement();

    void close();
}
