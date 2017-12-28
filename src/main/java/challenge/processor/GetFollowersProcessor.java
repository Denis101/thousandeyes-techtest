package challenge.processor;

import challenge.model.Person;
import challenge.persistence.query.GetFollowersQuery;
import challenge.persistence.query.PeopleQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h1>GetFollowersProcessor</h1>
 */
@Service
public class GetFollowersProcessor {

    private final PeopleQueryHandler peopleQueryHandler;

    @Autowired
    public GetFollowersProcessor(PeopleQueryHandler peopleQueryHandler) {
        this.peopleQueryHandler = peopleQueryHandler;
    }

    /**
     * Gets the list of followers for a given user
     * @param userId the user ID
     * @return a list of followers
     */
    public List<Person> process(int userId) {
        return peopleQueryHandler.handle(new GetFollowersQuery(userId));
    }
}
