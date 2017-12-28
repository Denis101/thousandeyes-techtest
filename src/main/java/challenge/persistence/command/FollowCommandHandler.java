package challenge.persistence.command;

import challenge.persistence.client.H2Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * <h1>FollowCommandHandler</h1>
 */
@Service
public class FollowCommandHandler {

    private static final Logger LOG = LoggerFactory.getLogger(FollowCommandHandler.class);

    private static final String QUERY = "INSERT INTO followers(person_id, follower_person_id)" +
            " VALUES(?, ?)";

    private final H2Client h2Client;

    @Autowired
    public FollowCommandHandler(H2Client h2Client) {
        this.h2Client = h2Client;
    }

    /**
     * Inserts a new follower into the database
     * @param personId the ID of the person to be followed
     * @param followerPersonId the ID of the follower
     * @return whether the operation was successful
     * @should return true if the follower is added
     * @should return false if no rows were affected
     * @should return null if there a SQLException was thrown
     */
    public Boolean handle(int personId, int followerPersonId) {
        try {
            Connection connection = h2Client.getConnection();
            PreparedStatement statement = connection.prepareStatement(QUERY);
            statement.setInt(1, personId);
            statement.setInt(2, followerPersonId);

            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            LOG.error("Failed to connect to database", ex);
            return null;
        }
    }
}
