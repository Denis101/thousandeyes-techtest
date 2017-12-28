package challenge.persistence.query;

/**
 * <h1>GetFollowersQuery</h1>
 */
public class GetFollowersQuery extends PeopleQuery {

    private static final String QUERY = "SELECT p.* FROM followers AS f" +
            " JOIN people AS p ON (p.ID = f.follower_person_id)" +
            " WHERE f.person_id = ?";

    public GetFollowersQuery(int personId) {
        super(QUERY, personId);
    }
}
