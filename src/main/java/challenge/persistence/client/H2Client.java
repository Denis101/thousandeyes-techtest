package challenge.persistence.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.sql.*;

/**
 * <h1>H2Client</h1>
 */
@Service
public class H2Client {

    private final Connection connection;

    /**
     * @param url the H2 database URL
     * @param username the username to connect to the H2 database
     * @param password the password to connect to the H2 database
     * @throws SQLException if no connection to the database can be made
     * @throws ClassNotFoundException if the class for the H2 database driver cannot be found
     */
    @Autowired
    public H2Client(final @Value("${spring.datasource.url}") String url,
                    final @Value("${h2.username}") String username,
                    final @Value("${h2.password}") String password) throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        connection = DriverManager.getConnection(url, username, password);
    }

    /**
     * Constructor for unit tests
     * @param url the H2 database URL
     * @param username the username to connect to the H2 database
     * @param password the password to connect to the H2 database
     * @throws SQLException if no connection to the database can be made
     */
    public H2Client(final @Value("${spring.datasource.url}") String url,
                    final @Value("${h2.username}") String username,
                    final @Value("${h2.password}") String password,
                    final Connection connection) throws SQLException {
        this.connection = connection;
    }

    /**
     * Gets the connection
     * @return the connection
     * @should return the connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Cleans up the object dependencies
     * @throws SQLException when there is no connection to the database
     * @should close the connection to the database
     */
    @PreDestroy
    public void cleanUp() throws SQLException {
        connection.close();
    }
}
