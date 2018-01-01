package challenge.persistence.query;

/**
 * <h1>GetPersonQuery</h1>
 */
public class GetPersonQuery extends PeopleQuery {

    private static final String QUERY = "SELECT * FROM people WHERE id = ?";

    public GetPersonQuery(int personId) {
        super(QUERY, personId);
    }
}
