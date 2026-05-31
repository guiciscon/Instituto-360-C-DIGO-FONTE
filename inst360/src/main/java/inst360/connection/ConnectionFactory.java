package inst360.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    private static final String URL =
        "jdbc:postgresql://localhost:5432/inst360";

    private static final String USER = "postgres";

    private static final String PASSWORD = "@Han2026";

    public static Connection getConnection() {

        try {

            Class.forName("org.postgresql.Driver");

            return DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Erro na conexão com banco",
                    e
            );
        }
    }
}