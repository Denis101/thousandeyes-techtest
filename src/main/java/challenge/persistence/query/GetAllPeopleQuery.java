package challenge.persistence.query;

/**
 * <h1>GetAllPeopleQuery</h1>
 */
public class GetAllPeopleQuery extends PeopleQuery {

    private static final String QUERY = "SELECT * FROM people";

    public GetAllPeopleQuery() {
        super(QUERY, -1);
    }
}
