package challenge.persistence.query;

import challenge.model.Message;
import challenge.persistence.client.H2Client;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Test for {@link MessagesQueryHandler}
 */
@RunWith(MockitoJUnitRunner.class)
public class MessagesQueryHandlerTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    @Mock
    private H2Client mockH2Client;

    private MessagesQueryHandler handler;

    @Before
    public void setUp() throws SQLException {
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockH2Client.getConnection()).thenReturn(mockConnection);
        handler = new MessagesQueryHandler(mockH2Client);
    }

    /**
     * @verifies return a list of messages
     * @see MessagesQueryHandler#handle(int)
     */
    @Test
    public void handle_shouldReturnAListOfMessages() throws Exception {
        // Arrange
        Message message = new Message(1, 2, "test");
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt(MessagesQueryHandler.ID)).thenReturn(message.getId());
        when(mockResultSet.getInt(MessagesQueryHandler.PERSON_ID)).thenReturn(message.getPersonId());
        when(mockResultSet.getString(MessagesQueryHandler.CONTENT)).thenReturn(message.getContent());

        // Act
        List<Message> result = handler.handle(1);

        // Assert
        assertFalse(result.isEmpty());
        assertEquals(message, result.get(0));
    }

    /**
     * @verifies return an empty list when no records found
     * @see MessagesQueryHandler#handle(int)
     */
    @Test
    public void handle_shouldReturnAnEmptyListWhenNoRecordsFound() throws Exception {
        when(mockResultSet.next()).thenReturn(false);
        assertTrue(handler.handle(1).isEmpty());
    }

    /**
     * @verifies return null when a SQLException is thrown
     * @see MessagesQueryHandler#handle(int)
     */
    @Test
    public void handle_shouldReturnNullWhenASQLExceptionIsThrown() throws Exception {
        when(mockResultSet.next()).thenThrow(new SQLException());
        assertNull(handler.handle(1));
    }
}
