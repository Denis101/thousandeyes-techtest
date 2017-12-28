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
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Test for {@link PeopleQueryHandler}
 */
@RunWith(MockitoJUnitRunner.class)
public class PeopleQueryHandlerTest {

    @Mock
    Connection mockConnection;

    @Mock
    H2Client mockH2Client;

    PeopleQueryHandler handler;
    List<Person> people;

    @Before
    public void setUp() {

        //when(mockConnection.prepareStatement(anyString())).thenReturn()
        when(mockH2Client.getConnection()).thenReturn(mockConnection);

        handler = new PeopleQueryHandler(mockH2Client);

        people = new ArrayList<>();
        Person person = new Person(1, "test", "test");
        people.add(person);
    }

    /**
     * @verifies return a list of people
     * @see PeopleQueryHandler#handle(PeopleQuery)
     */
    @Test
    public void handle_shouldReturnAListOfPeople() throws Exception {
        List<Person> result = handler.handle(new PeopleQuery("test", 1));

        assertNotNull(result);
    }

    /**
     * @verifies return an empty list when no records found
     * @see PeopleQueryHandler#handle(PeopleQuery)
     */
    @Test
    public void handle_shouldReturnAnEmptyListWhenNoRecordsFound() throws Exception {
        //TODO auto-generated
        Assert.fail("Not yet implemented");
    }

    /**
     * @verifies return null when a SQLException is thrown
     * @see PeopleQueryHandler#handle(PeopleQuery)
     */
    @Test
    public void handle_shouldReturnNullWhenASQLExceptionIsThrown() throws Exception {
        //TODO auto-generated
        Assert.fail("Not yet implemented");
    }
}
