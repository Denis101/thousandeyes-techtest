package challenge.processor;

import challenge.model.Message;
import challenge.query.GetMessagesQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h1>GetMessageProcessor</h1>
 */
@Service
public class GetMessagesProcessor {

    private final GetMessagesQuery getMessagesQuery;

    @Autowired
    public GetMessagesProcessor(GetMessagesQuery getMessagesQuery) {
        this.getMessagesQuery = getMessagesQuery;
    }

    /**
     * Gets a list of messages for a given user
     * @param userId the user ID
     * @return a list of messages
     */
    public List<Message> process(String userId) {
        return getMessagesQuery.handle(Integer.parseInt(userId));
    }
}
