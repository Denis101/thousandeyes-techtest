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
 * Test for {@link UnfollowCommandHandler}
 */
@RunWith(MockitoJUnitRunner.class)
public class UnfollowCommandHandlerTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private H2Client mockH2Client;

    private UnfollowCommandHandler handler;

    @Before
    public void setUp() {
        when(mockH2Client.getConnection()).thenReturn(mockConnection);

        handler = new UnfollowCommandHandler(mockH2Client);
    }

    /**
     * @verifies return true if the follower is removd
     * @see UnfollowCommandHandler#handle(int, int)
     */
    @Test
    public void handle_shouldReturnTrueIfTheFollowerIsRemovd() throws Exception {
        //TODO auto-generated
        Assert.fail("Not yet implemented");
    }

    /**
     * @verifies return false if no rows were affected
     * @see UnfollowCommandHandler#handle(int, int)
     */
    @Test
    public void handle_shouldReturnFalseIfNoRowsWereAffected() throws Exception {
        //TODO auto-generated
        Assert.fail("Not yet implemented");
    }

    /**
     * @verifies return null if there a SQLException was thrown
     * @see UnfollowCommandHandler#handle(int, int)
     */
    @Test
    public void handle_shouldReturnNullIfThereASQLExceptionWasThrown() throws Exception {
        //TODO auto-generated
        Assert.fail("Not yet implemented");
    }
}
