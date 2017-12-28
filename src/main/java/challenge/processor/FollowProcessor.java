package challenge.processor;

import challenge.persistence.command.AddFollowCommandHandler;
import challenge.persistence.query.FollowExistsQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <h1>FollowProcessor</h1>
 */
@Service
public class FollowProcessor {

    private final FollowExistsQueryHandler followExistsQueryHandler;
    private final AddFollowCommandHandler addFollowCommandHandler;

    @Autowired
    public FollowProcessor(FollowExistsQueryHandler followExistsQueryHandler, AddFollowCommandHandler addFollowCommandHandler) {
        this.followExistsQueryHandler = followExistsQueryHandler;
        this.addFollowCommandHandler = addFollowCommandHandler;
    }

    /**
     * Process a request to add a new user followed to a user
     * @param userId the user ID
     * @param followId the ID of the new user to follow
     * @return whether the operation succeeded
     * @should return null if the follow exists query failed
     * @should return null if the add follow query failed
     * @should return true if the add follow query was a success
     * @should return false if the follow already exists
     */
    public Boolean process(int userId, int followId) {
        Boolean followExists = followExistsQueryHandler.handle(userId, followId);
        if (followExists == null) {
            return null;
        }

        return !followExists
                ? addFollowCommandHandler.handle(userId, followId)
                : Boolean.FALSE;
    }
}
