package challenge.persistence.query;

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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Test for {@link FollowExistsQueryHandler}
 */
@RunWith(MockitoJUnitRunner.class)
public class FollowExistsQueryHandlerTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    @Mock
    private H2Client mockH2Client;

    private FollowExistsQueryHandler handler;

    @Before
    public void setUp() throws SQLException {
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockH2Client.getConnection()).thenReturn(mockConnection);
        handler = new FollowExistsQueryHandler(mockH2Client);
    }

    /**
     * @verifies return true when the follow exists
     * @see FollowExistsQueryHandler#handle(int, int)
     */
    @Test
    public void handle_shouldReturnTrueWhenTheFollowExists() throws Exception {
        when(mockResultSet.getInt(1)).thenReturn(1);
        assertTrue(handler.handle(1, 2));
    }

    /**
     * @verifies return false when the follow does not exist
     * @see FollowExistsQueryHandler#handle(int, int)
     */
    @Test
    public void handle_shouldReturnFalseWhenTheFollowDoesNotExist() throws Exception {
        when(mockResultSet.getInt(1)).thenReturn(0);
        assertFalse(handler.handle(1, 2));
    }

    /**
     * @verifies return null if a SQLException is thrown
     * @see FollowExistsQueryHandler#handle(int, int)
     */
    @Test
    public void handle_shouldReturnNullIfASQLExceptionIsThrown() throws Exception {
        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException());
        assertNull(handler.handle(1, 2));
    }
}
