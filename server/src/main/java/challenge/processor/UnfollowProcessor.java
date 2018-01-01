package challenge.processor;

import challenge.persistence.command.UnfollowCommandHandler;
import challenge.persistence.query.FollowExistsQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <h1>UnfollowProcessor</h1>
 */
@Service
public class UnfollowProcessor {

    private final FollowExistsQueryHandler followExistsQueryHandler;
    private final UnfollowCommandHandler unfollowCommandHandler;

    @Autowired
    public UnfollowProcessor(final FollowExistsQueryHandler followExistsQueryHandler,
                             final UnfollowCommandHandler unfollowCommandHandler) {
        this.followExistsQueryHandler = followExistsQueryHandler;
        this.unfollowCommandHandler = unfollowCommandHandler;
    }

    /**
     * Process a request to remove a following for a given user
     * @param userId the user ID
     * @param followId the ID of the user to unfollow
     * @return whether the operation succeeded
     * @should return null if the follow exists query failed
     * @should return null if the unfollow query failed
     * @should return true if the unfollow query was a success
     * @should return false if the follow does not exist
     */
    public Boolean process(int userId, int followId) {
        Boolean followExists = followExistsQueryHandler.handle(followId, userId);
        if (followExists == null) {
            return null;
        }

        return followExists
                ? unfollowCommandHandler.handle(followId, userId)
                : Boolean.FALSE;
    }
}
