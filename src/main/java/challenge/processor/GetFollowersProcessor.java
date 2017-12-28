package challenge.processor;

import challenge.model.Person;
import challenge.query.GetFollowersForPersonQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h1>GetFollowersProcessor</h1>
 */
@Service
public class GetFollowersProcessor {

    private final GetFollowersForPersonQuery getFollowersForPersonQuery;

    @Autowired
    public GetFollowersProcessor(GetFollowersForPersonQuery getFollowersForPersonQuery) {
        this.getFollowersForPersonQuery = getFollowersForPersonQuery;
    }

    /**
     * Gets the list of followers for a given user
     * @param userId the user ID
     * @return a list of followers
     */
    public List<Person> process(String userId) {
        return getFollowersForPersonQuery.handle(Integer.parseInt(userId));
    }
}
