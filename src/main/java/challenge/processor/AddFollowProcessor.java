package challenge.processor;

import challenge.persistence.command.AddFollowCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <h1>AddFollowProcessor</h1>
 */
@Service
public class AddFollowProcessor {

    private final AddFollowCommandHandler addFollowCommandHandler;

    @Autowired
    public AddFollowProcessor(AddFollowCommandHandler addFollowCommandHandler) {
        this.addFollowCommandHandler = addFollowCommandHandler;
    }

    /**
     * Process a request to add a new user followed to a user
     * @param userId the user ID
     * @param followId the ID of the new user to follow
     * @return whether the operation succeeded
     */
    public Boolean process(int userId, int followId) {
        return addFollowCommandHandler.handle(userId, followId);
    }
}
