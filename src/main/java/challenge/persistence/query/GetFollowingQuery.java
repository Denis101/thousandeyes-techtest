package challenge.persistence.query;

/**
 * <h1>GetFollowingQuery</h1>
 */
public class GetFollowingQuery extends PeopleQuery {

    private static final String QUERY = "SELECT p.* FROM followers AS f" +
            " JOIN people AS p ON (p.ID = f.person_id)" +
            " WHERE f.follower_person_id = ?";

    public GetFollowingQuery(int personId) {
        super(QUERY, personId);
    }
}
