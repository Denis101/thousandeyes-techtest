package challenge.persistence.query;

import challenge.persistence.client.H2Client;
import challenge.persistence.command.FollowCommandHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <h1>FollowExistsQueryHandler</h1>
 */
@Service
public class FollowExistsQueryHandler {

    private static final Logger LOG = LoggerFactory.getLogger(FollowCommandHandler.class);

    private static final String QUERY = "SELECT COUNT(*) FROM followers" +
            " WHERE person_id = ?" +
            " AND follower_person_id = ?";

    private final H2Client h2Client;

    @Autowired
    public FollowExistsQueryHandler(H2Client h2Client) {
        this.h2Client = h2Client;
    }

    /**
     * Checks if the followerPersonID user is following the personId user
     * @param personId the person ID
     * @param followerPersonId the follower ID
     * @return whether the follower is following the user
     * @should return true when the follow exists
     * @should return false when the follow does not exist
     * @should return null if a SQLException is thrown
     */
    public Boolean handle(int personId, int followerPersonId) {
        try {
            Connection connection = h2Client.getConnection();
            PreparedStatement statement = connection.prepareStatement(QUERY);
            statement.setInt(1, personId);
            statement.setInt(2, followerPersonId);

            ResultSet result = statement.executeQuery();
            result.next();
            return result.getInt(1) > 0;
        } catch (SQLException ex) {
            LOG.error("Failed to connect to database", ex);
            return null;
        }
    }
}
