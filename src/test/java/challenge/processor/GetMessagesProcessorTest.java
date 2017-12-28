package challenge.processor;

import challenge.model.Message;
import challenge.persistence.query.MessagesQueryHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * Test for {@link GetMessagesProcessor}
 */
@RunWith(MockitoJUnitRunner.class)
public class GetMessagesProcessorTest {

    @Mock
    MessagesQueryHandler mockMessagesQueryHandler;

    private GetMessagesProcessor processor;
    private List<Message> messages;

    @Before
    public void setUp() {
        processor = new GetMessagesProcessor(mockMessagesQueryHandler);

        messages = new ArrayList<>();
        Message message = new Message(1, 1, "test");
        messages.add(message);
    }

    /**
     * @verifies return a list of messages
     * @see GetMessagesProcessor#process(int)
     */
    @Test
    public void process_shouldReturnAListOfMessages() throws Exception {
        when(mockMessagesQueryHandler.handle(anyInt())).thenReturn(messages);
        assertEquals(messages, processor.process(1));
    }

    /**
     * @verifies return null if the query failed
     * @see GetMessagesProcessor#process(int)
     */
    @Test
    public void process_shouldReturnNullIfTheQueryFailed() throws Exception {
        when(mockMessagesQueryHandler.handle(anyInt())).thenReturn(null);
        assertNull(processor.process(1));
    }
}
