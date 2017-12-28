package challenge.processor;

import org.springframework.stereotype.Service;

/**
 * <h1>AddFollowProcessor</h1>
 */
@Service
public class AddFollowProcessor {

    /**
     * Process a request to add a new user followed to a user
     * @param userId the user ID
     * @param followId the ID of the new user to follow
     * @return whether the operation succeeded
     */
    public boolean process(int userId, int followId) {
        return false;
    }


    // Check if person is already following
//    SELECT COUNT(*) FROM followers WHERE person_id = 1 AND follower_person_id = 10;
//
    // Insert new follower
//    INSERT INTO followers(person_id, follower_person_id) VALUES(1, 2);
}
