package challenge.processor;

import challenge.model.Person;
import challenge.query.GetPeopleFollowedByPersonQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h1>GetFollowingProcessor</h1>
 */
@Service
public class GetFollowingProcessor {

    private final GetPeopleFollowedByPersonQuery getPeopleFollowedByPersonQuery;

    @Autowired
    public GetFollowingProcessor(GetPeopleFollowedByPersonQuery getPeopleFollowedByPersonQuery) {
        this.getPeopleFollowedByPersonQuery = getPeopleFollowedByPersonQuery;
    }

    /**
     * Gets the list of users followed by a given user
     * @param userId the user ID
     * @return the list of users followed
     */
    public List<Person> process(String userId) {
        return getPeopleFollowedByPersonQuery.handle(Integer.parseInt(userId));
    }
}
