package challenge.persistence.command;

import challenge.persistence.client.H2Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Test for {@link UnfollowCommandHandler}
 */
@RunWith(MockitoJUnitRunner.class)
public class UnfollowCommandHandlerTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private H2Client mockH2Client;

    private UnfollowCommandHandler handler;

    @Before
    public void setUp() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockH2Client.getConnection()).thenReturn(mockConnection);
        handler = new UnfollowCommandHandler(mockH2Client);
    }

    /**
     * @verifies return true if the follower is removed
     * @see UnfollowCommandHandler#handle(int, int)
     */
    @Test
    public void handle_shouldReturnTrueIfTheFollowerIsRemoved() throws Exception {
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        assertTrue(handler.handle(1, 2));
    }

    /**
     * @verifies return false if no rows were affected
     * @see UnfollowCommandHandler#handle(int, int)
     */
    @Test
    public void handle_shouldReturnFalseIfNoRowsWereAffected() throws Exception {
        when(mockPreparedStatement.executeUpdate()).thenReturn(0);
        assertFalse(handler.handle(1, 2));
    }

    /**
     * @verifies return null if there a SQLException was thrown
     * @see UnfollowCommandHandler#handle(int, int)
     */
    @Test
    public void handle_shouldReturnNullIfThereASQLExceptionWasThrown() throws Exception {
        when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException());
        assertNull(handler.handle(1, 2));
    }
}
