/**
 * Created with IntelliJ IDEA.
 * User: vshivamurthy
 * Date: 9/19/13
 * Time: 12:28 PM
 * To change this template use File | Settings | File Templates.
 */
import java.io.PrintStream;
import java.sql.ResultSetMetaData;

public class CreateHTMLTable {

    public void dumpData(java.sql.ResultSet rs, PrintStream out, String header)
            throws Exception {
        int rowCount = 0;
        synchronized (out)
        {
        out.println("<h2> " + header + "<h2>");
        out.println("<P ALIGN='left'><TABLE BORDER=1>");
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        // table header
        out.println("<TR>");
        for (int i = 0; i < columnCount; i++) {
            out.println("<TH>" + rsmd.getColumnLabel(i + 1) + "</TH>");
        }
        out.println("</TR>");
        // the data
        while (rs.next()) {
            rowCount++;
            out.println("<TR>");
            for (int i = 0; i < columnCount; i++) {
                out.println("<TD>" + rs.getString(i + 1) + "</TD>");
            }
            out.println("</TR>");
        }
        out.println("</TABLE></P>");
        }
    }
}
