package challenge.persistence.query;

import challenge.persistence.client.H2Client;
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
 * <h1>PeopleQueryHandler</h1>
 */
@Service
public class PeopleQueryHandler {
    private static final Logger LOG = LoggerFactory.getLogger(GetFollowersQuery.class);

    private static final String ID = "id";
    private static final String HANDLE = "handle";
    private static final String NAME = "name";

    private final H2Client h2Client;

    /**
     * @param h2Client the database client
     */
    @Autowired
    public PeopleQueryHandler(H2Client h2Client) {
        this.h2Client = h2Client;
    }

    /**
     * Gets a list of people who follow the person with the given ID
     * @param peopleQuery the query
     * @return a list of people
     * @throws SQLException if the connection to the database is broken
     */
    public List<Person> handle(PeopleQuery peopleQuery) {
        try {
            Connection connection = h2Client.getConnection();

            PreparedStatement statement = connection.prepareStatement(peopleQuery.getQuery());
            statement.setInt(1, peopleQuery.getPersonId());

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
