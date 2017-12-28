package challenge.persistence.command;

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
 * Test for {@link FollowCommandHandler}
 */
@RunWith(MockitoJUnitRunner.class)
public class FollowCommandHandlerTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private H2Client mockH2Client;

    private FollowCommandHandler handler;

    @Before
    public void setUp() {
        when(mockH2Client.getConnection()).thenReturn(mockConnection);

        handler = new FollowCommandHandler(mockH2Client);
    }

    /**
     * @verifies return true if the follower is added
     * @see FollowCommandHandler#handle(int, int)
     */
    @Test
    public void handle_shouldReturnTrueIfTheFollowerIsAdded() throws Exception {
        //TODO auto-generated
        Assert.fail("Not yet implemented");
    }

    /**
     * @verifies return false if no rows were affected
     * @see FollowCommandHandler#handle(int, int)
     */
    @Test
    public void handle_shouldReturnFalseIfNoRowsWereAffected() throws Exception {
        //TODO auto-generated
        Assert.fail("Not yet implemented");
    }

    /**
     * @verifies return null if there a SQLException was thrown
     * @see FollowCommandHandler#handle(int, int)
     */
    @Test
    public void handle_shouldReturnNullIfThereASQLExceptionWasThrown() throws Exception {
        //TODO auto-generated
        Assert.fail("Not yet implemented");
    }
}
