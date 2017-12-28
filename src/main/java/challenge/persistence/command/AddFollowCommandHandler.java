package challenge.persistence.command;

import challenge.persistence.client.H2Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <h1>AddFollowCommandHandler</h1>
 */
@Service
public class AddFollowCommandHandler {

    private static final Logger LOG = LoggerFactory.getLogger(AddFollowCommandHandler.class);

    private static final String FOLLOW_EXISTS_QUERY = "SELECT COUNT(*) FROM followers" +
            " WHERE person_id = ?" +
            " AND follower_person_id = ?";

    private static final String INSERT_QUERY = "INSERT INTO followers(person_id, follower_person_id)" +
            " VALUES(?, ?)";

    private final H2Client h2Client;

    @Autowired
    public AddFollowCommandHandler(H2Client h2Client) {
        this.h2Client = h2Client;
    }

    public Boolean handle(int personId, int followerPersonId) {
        try {
            return !followExists(personId, followerPersonId) && insert(personId, followerPersonId);
        } catch (SQLException ex) {
            LOG.error("Failed to connect to database", ex);
            return null;
        }
    }

    private boolean followExists(int personId, int followerPersonId) throws SQLException {
        Connection connection = h2Client.getConnection();
        PreparedStatement statement = getPreparedStatement(connection, FOLLOW_EXISTS_QUERY, personId, followerPersonId);
        ResultSet result = statement.executeQuery();
        result.next();
        return result.getInt(1) > 0;
    }

    private boolean insert(int personId, int followerPersonId) throws SQLException {
        Connection connection = h2Client.getConnection();
        PreparedStatement statement = getPreparedStatement(connection, INSERT_QUERY, personId, followerPersonId);
        int affectedRows = statement.executeUpdate();
        return affectedRows > 0;
    }

    private PreparedStatement getPreparedStatement(Connection connection,
                                                   String query, int personId,
                                                   int followerPersonId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, personId);
        statement.setInt(2, followerPersonId);

        return statement;
    }
}
