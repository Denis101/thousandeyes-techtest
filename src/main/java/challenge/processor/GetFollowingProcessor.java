package challenge.processor;

import challenge.model.Person;
import challenge.persistence.query.GetFollowingQuery;
import challenge.persistence.query.PeopleQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h1>GetFollowingProcessor</h1>
 */
@Service
public class GetFollowingProcessor {

    private final PeopleQueryHandler peopleQueryHandler;

    @Autowired
    public GetFollowingProcessor(PeopleQueryHandler peopleQueryHandler) {
        this.peopleQueryHandler = peopleQueryHandler;
    }

    /**
     * Gets the list of users followed by a given user
     * @param userId the user ID
     * @return the list of users followed
     */
    public List<Person> process(int userId) {
        return peopleQueryHandler.handle(new GetFollowingQuery(userId));
    }
}
