package challenge.query;

import challenge.client.H2Client;
import challenge.model.Person;
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
 * <h1>GetFollowersForPersonQuery</h1>
 */
@Service
public class GetFollowersForPersonQuery {

    private static final Logger LOG = LoggerFactory.getLogger(GetFollowersForPersonQuery.class);

    private static final String QUERY = "SELECT p.* FROM followers AS f" +
                    " JOIN people AS p ON (p.ID = f.follower_person_id)" +
                    " WHERE f.person_id = ?";

    private static final String ID = "id";
    private static final String HANDLE = "handle";
    private static final String NAME = "name";

    private final H2Client h2Client;

    /**
     * @param h2Client the database client
     */
    @Autowired
    public GetFollowersForPersonQuery(H2Client h2Client) {
        this.h2Client = h2Client;
    }

    /**
     * Gets a list of people who follow the person with the given ID
     * @param personId the person ID
     * @return a list of people
     * @throws SQLException if the connection to the database is broken
     */
    public List<Person> handle(int personId) {
        try {
            Connection connection = h2Client.getConnection();

            PreparedStatement statement = connection.prepareStatement(QUERY);
            statement.setInt(1, personId);

            ResultSet result = statement.executeQuery();

            List<Person> people = new ArrayList<>();
            while (result.next()) {
                Person person = new Person(
                        result.getInt(ID),
                        result.getString(HANDLE),
                        result.getString(NAME));

                people.add(person);
            }

            return people;
        } catch (SQLException ex) {
            LOG.error("Failed to connect to database", ex);
            return null;
        }
    }
}
