package challenge.persistence.query;

import challenge.model.Person;
import challenge.persistence.client.H2Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Test for {@link PeopleQueryHandler}
 */
@RunWith(MockitoJUnitRunner.class)
public class PeopleQueryHandlerTest {

    private static final PeopleQuery TEST_QUERY = new PeopleQuery("test", 1);

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    @Mock
    private H2Client mockH2Client;

    PeopleQueryHandler handler;

    @Before
    public void setUp() throws SQLException {
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockH2Client.getConnection()).thenReturn(mockConnection);
        handler = new PeopleQueryHandler(mockH2Client);
    }

    /**
     * @verifies return a list of people
     * @see PeopleQueryHandler#handle(PeopleQuery)
     */
    @Test
    public void handle_shouldReturnAListOfPeople() throws Exception {
        // Arrange
        Person person = new Person(1, "handle", "name");
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt(PeopleQueryHandler.ID)).thenReturn(person.getId());
        when(mockResultSet.getString(PeopleQueryHandler.HANDLE)).thenReturn(person.getHandle());
        when(mockResultSet.getString(PeopleQueryHandler.NAME)).thenReturn(person.getName());

        // Act
        List<Person> result = handler.handle(TEST_QUERY);

        // Assert
        assertFalse(result.isEmpty());
        assertEquals(person, result.get(0));
    }

    /**
     * @verifies return an empty list when no records found
     * @see PeopleQueryHandler#handle(PeopleQuery)
     */
    @Test
    public void handle_shouldReturnAnEmptyListWhenNoRecordsFound() throws Exception {
        when(mockResultSet.next()).thenReturn(false);
        assertTrue(handler.handle(TEST_QUERY).isEmpty());
    }

    /**
     * @verifies return null when a SQLException is thrown
     * @see PeopleQueryHandler#handle(PeopleQuery)
     */
    @Test
    public void handle_shouldReturnNullWhenASQLExceptionIsThrown() throws Exception {
        when(mockResultSet.next()).thenThrow(new SQLException());
        assertNull(handler.handle(TEST_QUERY));
    }
}
