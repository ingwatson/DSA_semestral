import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionTest {
    private static final String URL = "jdbc:postgresql://localhost:5432/sklad_db";
    private static final String USER = "chigurh";
    private static final String PASSWORD = "anton";

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                if (connection != null) {
                    System.out.println("Connected to the database successfully!");
                } else {
                    System.out.println("Failed to make connection!");
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver not found. Include it in your library path.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed! Check output console");
            e.printStackTrace();
        }
    }
}