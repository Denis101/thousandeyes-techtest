package challenge.processor;

import challenge.persistence.command.FollowCommandHandler;
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
 * Test for {@link FollowProcessor}
 */
@RunWith(MockitoJUnitRunner.class)
public class FollowProcessorTest {

    private static int PERSON_ID = 1;
    private static int FOLLOWER_ID = 2;

    @Mock
    private FollowExistsQueryHandler mockFollowExistsQueryHandler;

    @Mock
    private FollowCommandHandler mockFollowCommandHandler;

    private FollowProcessor processor;

    @Before
    public void setUp() {
        processor = new FollowProcessor(
                mockFollowExistsQueryHandler,
                mockFollowCommandHandler);
    }

    /**
     * @verifies return null if the follow exists query failed
     * @see FollowProcessor#process(int, int)
     */
    @Test
    public void process_shouldReturnNullIfTheFollowExistsQueryFailed() throws Exception {
        when(mockFollowExistsQueryHandler.handle(anyInt(), anyInt())).thenReturn(null);
        when(mockFollowCommandHandler.handle(anyInt(), anyInt())).thenReturn(null);

        assertNull(processor.process(PERSON_ID, FOLLOWER_ID));
        verify(mockFollowExistsQueryHandler, times(1)).handle(FOLLOWER_ID, PERSON_ID);
        verify(mockFollowCommandHandler, times(0)).handle(FOLLOWER_ID, PERSON_ID);
    }

    /**
     * @verifies return null if the add follow query failed
     * @see FollowProcessor#process(int, int)
     */
    @Test
    public void process_shouldReturnNullIfTheAddFollowQueryFailed() throws Exception {
        when(mockFollowExistsQueryHandler.handle(anyInt(), anyInt())).thenReturn(false);
        when(mockFollowCommandHandler.handle(anyInt(), anyInt())).thenReturn(null);

        assertNull(processor.process(PERSON_ID, FOLLOWER_ID));
        verify(mockFollowExistsQueryHandler, times(1)).handle(FOLLOWER_ID, PERSON_ID);
        verify(mockFollowCommandHandler, times(1)).handle(FOLLOWER_ID, PERSON_ID);
    }

    /**
     * @verifies return true if the add follow query was a success
     * @see FollowProcessor#process(int, int)
     */
    @Test
    public void process_shouldReturnTrueIfTheAddFollowQueryWasASuccess() throws Exception {
        when(mockFollowExistsQueryHandler.handle(anyInt(), anyInt())).thenReturn(false);
        when(mockFollowCommandHandler.handle(anyInt(), anyInt())).thenReturn(true);

        assertTrue(processor.process(PERSON_ID, FOLLOWER_ID));
        verify(mockFollowExistsQueryHandler, times(1)).handle(FOLLOWER_ID, PERSON_ID);
        verify(mockFollowCommandHandler, times(1)).handle(FOLLOWER_ID, PERSON_ID);
    }

    /**
     * @verifies return false if the follow already exists
     * @see FollowProcessor#process(int, int)
     */
    @Test
    public void process_shouldReturnFalseIfTheFollowAlreadyExists() throws Exception {
        when(mockFollowExistsQueryHandler.handle(anyInt(), anyInt())).thenReturn(true);

        assertFalse(processor.process(PERSON_ID, FOLLOWER_ID));
        verify(mockFollowExistsQueryHandler, times(1)).handle(FOLLOWER_ID, PERSON_ID);
        verify(mockFollowCommandHandler, times(0)).handle(FOLLOWER_ID, PERSON_ID);
    }
}
