package challenge.persistence.query;

import challenge.persistence.client.H2Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Connection;

import static org.mockito.Mockito.when;

/**
 * Test for {@link MessagesQueryHandler}
 */
@RunWith(MockitoJUnitRunner.class)
public class MessagesQueryHandlerTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private H2Client mockH2Client;

    private MessagesQueryHandler handler;

    @Before
    public void setUp() {
        when(mockH2Client.getConnection()).thenReturn(mockConnection);

        handler = new MessagesQueryHandler(mockH2Client);
    }

    /**
     * @verifies return a list of messages
     * @see MessagesQueryHandler#handle(int)
     */
    @Test
    public void handle_shouldReturnAListOfMessages() throws Exception {
        //TODO auto-generated
        Assert.fail("Not yet implemented");
    }

    /**
     * @verifies return an empty list when no records found
     * @see MessagesQueryHandler#handle(int)
     */
    @Test
    public void handle_shouldReturnAnEmptyListWhenNoRecordsFound() throws Exception {
        //TODO auto-generated
        Assert.fail("Not yet implemented");
    }

    /**
     * @verifies return null when a SQLException is thrown
     * @see MessagesQueryHandler#handle(int)
     */
    @Test
    public void handle_shouldReturnNullWhenASQLExceptionIsThrown() throws Exception {
        //TODO auto-generated
        Assert.fail("Not yet implemented");
    }
}
