package challenge.persistence.query;

import challenge.persistence.client.H2Client;
import challenge.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>MessagesQueryHandler</h1>
 */
@Service
public class MessagesQueryHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GetFollowersQuery.class);

    private static final String QUERY = "SELECT m.* FROM messages AS m" +
            " JOIN people AS p ON (p.ID = m.person_id)" +
            " WHERE m.person_id = ?";

    private static final String ID = "id";
    private static final String PERSON_ID = "person_id";
    private static final String CONTENT = "content";

    private final H2Client h2Client;

    /**
     * @param h2Client the database client
     */
    @Autowired
    public MessagesQueryHandler(H2Client h2Client) {
        this.h2Client = h2Client;
    }

    /**
     * Gets a list of messages of a person with the given ID
     * @param personId the person ID
     * @return a list of messages
     * @throws SQLException if the connection to the database is broken
     */
    public List<Message> handle(int personId) {
        try {
            Connection connection = h2Client.getConnection();

            PreparedStatement statement = connection.prepareStatement(QUERY);
            statement.setInt(1, personId);

            ResultSet result = statement.executeQuery();

            List<Message> messages = new ArrayList<>();
            while (result.next()) {
                Message message = new Message(
                        result.getInt(ID),
                        result.getInt(PERSON_ID),
                        result.getString(CONTENT));

                messages.add(message);
            }

            return messages;
        } catch (SQLException ex) {
            LOG.error("Failed to connect to database", ex);
            return null;
        }
    }
}
