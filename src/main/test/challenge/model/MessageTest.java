package challenge.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test for {@link Message}
 */
public class MessageTest {

    private static final String CONTENT_STRING = "test";

    /**
     * @verifies return the value of id
     * @see Message#getId()
     */
    @Test
    public void getId_shouldReturnTheValueOfId() throws Exception {
        Message message = new Message(1, 2, CONTENT_STRING);
        assertEquals(1, message.getId());
    }

    /**
     * @verifies return the value of personId
     * @see Message#getPersonId()
     */
    @Test
    public void getPersonId_shouldReturnTheValueOfPersonId() throws Exception {
        Message message = new Message(1, 2, CONTENT_STRING);
        assertEquals(2, message.getPersonId());
    }

    /**
     * @verifies return the value of content
     * @see Message#getContent()
     */
    @Test
    public void getContent_shouldReturnTheValueOfContent() throws Exception {
        Message message = new Message(1, 2, CONTENT_STRING);
        assertEquals(CONTENT_STRING, message.getContent());
    }

    @Test
    public void equalsVerifier() {
        EqualsVerifier.forClass(Message.class).usingGetClass().verify();
    }
}
