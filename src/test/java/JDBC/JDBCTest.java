package JDBC;

import jdbc.JDBC;
import org.junit.Test;

public class JDBCTest {
    @Test
    public void testJDBC() {
        JDBC jdbc = new JDBC("org.apache.derby.jdbc.EmbeddedDriver","jdbc:derby:myDB");
        jdbc.createConnectionForDerbyDB();
        jdbc.createDB();

        jdbc.addBook("Thinking in Java (4th Edition)", "Dec 9, 2015", "Bruce Eckel");
        jdbc.addBook("In search of the best Java book for beginners", "Feb 1, 1999", "Laurence Vanhelsuwé");
        jdbc.addBook("Mastering Enterprise JavaBeans", "Aug 7, 2005", "Gerald Brose");

        jdbc.updateBook("Mastering Enterprise JavaBeans", "Aug 19, 2005", "Rima Patel Sriganesh");

        jdbc.deleteBook("In search of the best Java book for beginners");

        jdbc.printAllBooks();
    }
}
