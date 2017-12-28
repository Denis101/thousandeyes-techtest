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
 * Test for {@link FollowExistsQueryHandler}
 */
@RunWith(MockitoJUnitRunner.class)
public class FollowExistsQueryHandlerTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private H2Client mockH2Client;

    private FollowExistsQueryHandler handler;

    @Before
    public void setUp() {
        when(mockH2Client.getConnection()).thenReturn(mockConnection);

        handler = new FollowExistsQueryHandler(mockH2Client);
    }

    /**
     * @verifies return true when the follow exists
     * @see FollowExistsQueryHandler#handle(int, int)
     */
    @Test
    public void handle_shouldReturnTrueWhenTheFollowExists() throws Exception {
        //TODO auto-generated
        Assert.fail("Not yet implemented");
    }

    /**
     * @verifies return false when the follow does not exist
     * @see FollowExistsQueryHandler#handle(int, int)
     */
    @Test
    public void handle_shouldReturnFalseWhenTheFollowDoesNotExist() throws Exception {
        //TODO auto-generated
        Assert.fail("Not yet implemented");
    }

    /**
     * @verifies return null if a SQLException is thrown
     * @see FollowExistsQueryHandler#handle(int, int)
     */
    @Test
    public void handle_shouldReturnNullIfASQLExceptionIsThrown() throws Exception {
        //TODO auto-generated
        Assert.fail("Not yet implemented");
    }
}
