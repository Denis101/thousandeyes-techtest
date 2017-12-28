package challenge.processor;

import challenge.model.Message;
import challenge.persistence.query.MessagesQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h1>GetMessageProcessor</h1>
 */
@Service
public class GetMessagesProcessor {

    private final MessagesQueryHandler messagesQueryHandler;

    @Autowired
    public GetMessagesProcessor(MessagesQueryHandler messagesQueryHandler) {
        this.messagesQueryHandler = messagesQueryHandler;
    }

    /**
     * Gets a list of messages for a given user
     * @param userId the user ID
     * @return a list of messages
     * @should return a list of messages
     * @should return null if the query failed
     */
    public List<Message> process(int userId) {
        return messagesQueryHandler.handle(userId);
    }
}
