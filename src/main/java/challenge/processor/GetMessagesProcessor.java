package challenge.processor;

import challenge.model.Message;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h1>GetMessageProcessor</h1>
 */
@Service
public class GetMessagesProcessor {

    /**
     * Gets a list of messages for a given user
     * @param userId the user ID
     * @return a list of messages
     */
    public List<Message> process(String userId) {
        return null;
    }

    // Get messages query
//    SELECT m.* FROM MESSAGES AS m
//    JOIN PEOPLE AS p ON (p.ID = m.PERSON_ID)
//    WHERE m.PERSON_ID = 1;
}
