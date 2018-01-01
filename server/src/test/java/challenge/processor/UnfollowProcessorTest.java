package challenge.processor;

import challenge.persistence.command.UnfollowCommandHandler;
import challenge.persistence.query.FollowExistsQueryHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test for {@link UnfollowProcessor}
 */
@RunWith(MockitoJUnitRunner.class)
public class UnfollowProcessorTest {
    private static int PERSON_ID = 1;
    private static int FOLLOWER_ID = 2;

    @Mock
    private FollowExistsQueryHandler mockFollowExistsQueryHandler;

    @Mock
    private UnfollowCommandHandler mockUnfollowCommandHandler;

    private UnfollowProcessor processor;

    @Before
    public void setUp() {
        processor = new UnfollowProcessor(
                mockFollowExistsQueryHandler,
                mockUnfollowCommandHandler);
    }

    /**
     * @verifies return null if the follow exists query failed
     * @see UnfollowProcessor#process(int, int)
     */
    @Test
    public void process_shouldReturnNullIfTheFollowExistsQueryFailed() throws Exception {
        when(mockFollowExistsQueryHandler.handle(anyInt(), anyInt())).thenReturn(null);

        assertNull(processor.process(PERSON_ID, FOLLOWER_ID));
        verify(mockFollowExistsQueryHandler, times(1)).handle(FOLLOWER_ID, PERSON_ID);
        verify(mockUnfollowCommandHandler, times(0)).handle(FOLLOWER_ID, PERSON_ID);
    }

    /**
     * @verifies return null if the unfollow query failed
     * @see UnfollowProcessor#process(int, int)
     */
    @Test
    public void process_shouldReturnNullIfTheUnfollowQueryFailed() throws Exception {
        when(mockFollowExistsQueryHandler.handle(anyInt(), anyInt())).thenReturn(true);
        when(mockUnfollowCommandHandler.handle(anyInt(), anyInt())).thenReturn(null);

        assertNull(processor.process(PERSON_ID, FOLLOWER_ID));
        verify(mockFollowExistsQueryHandler, times(1)).handle(FOLLOWER_ID, PERSON_ID);
        verify(mockUnfollowCommandHandler, times(1)).handle(FOLLOWER_ID, PERSON_ID);
    }

    /**
     * @verifies return true if the add follow query was a success
     * @see UnfollowProcessor#process(int, int)
     */
    @Test
    public void process_shouldReturnTrueIfTheUnfollowQueryWasASuccess() throws Exception {
        when(mockFollowExistsQueryHandler.handle(anyInt(), anyInt())).thenReturn(true);
        when(mockUnfollowCommandHandler.handle(anyInt(), anyInt())).thenReturn(true);

        assertTrue(processor.process(PERSON_ID, FOLLOWER_ID));
        verify(mockFollowExistsQueryHandler, times(1)).handle(FOLLOWER_ID, PERSON_ID);
        verify(mockUnfollowCommandHandler, times(1)).handle(FOLLOWER_ID, PERSON_ID);
    }

    /**
     * @verifies return false if the follow does not exist
     * @see UnfollowProcessor#process(int, int)
     */
    @Test
    public void process_shouldReturnFalseIfTheFollowDoesNotExist() throws Exception {
        when(mockFollowExistsQueryHandler.handle(anyInt(), anyInt())).thenReturn(false);

        assertFalse(processor.process(PERSON_ID, FOLLOWER_ID));
        verify(mockFollowExistsQueryHandler, times(1)).handle(FOLLOWER_ID, PERSON_ID);
        verify(mockUnfollowCommandHandler, times(0)).handle(FOLLOWER_ID, PERSON_ID);
    }
}
