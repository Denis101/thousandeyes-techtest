package challenge.processor;

import challenge.persistence.command.AddFollowCommandHandler;
import challenge.persistence.query.FollowExistsQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <h1>AddFollowProcessor</h1>
 */
@Service
public class AddFollowProcessor {

    private final FollowExistsQueryHandler followExistsQueryHandler;
    private final AddFollowCommandHandler addFollowCommandHandler;

    @Autowired
    public AddFollowProcessor(FollowExistsQueryHandler followExistsQueryHandler, AddFollowCommandHandler addFollowCommandHandler) {
        this.followExistsQueryHandler = followExistsQueryHandler;
        this.addFollowCommandHandler = addFollowCommandHandler;
    }

    /**
     * Process a request to add a new user followed to a user
     * @param userId the user ID
     * @param followId the ID of the new user to follow
     * @return whether the operation succeeded
     */
    public Boolean process(int userId, int followId) {
        Boolean followExists = followExistsQueryHandler.handle(userId, followId);
        if (followExists == null) {
            return null;
        }

        return !followExists
                ? addFollowCommandHandler.handle(userId, followId)
                : false;
    }
}
