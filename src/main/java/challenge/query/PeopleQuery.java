package challenge.query;

/**
 * <h1>PeopleQuery</h1>
 */
public class PeopleQuery {

    private final String query;
    private final int personId;

    public PeopleQuery(String query, int personId) {
        this.query = query;
        this.personId = personId;
    }

    /**
     * Returns the query
     * @return the query
     * @should return the query
     */
    public String getQuery() {
        return query;
    }

    /**
     * Returns the personId
     * @return the personId
     * @should return the personId
     */
    public int getPersonId() {
        return personId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PeopleQuery that = (PeopleQuery) o;

        if (personId != that.personId) return false;
        return query != null ? query.equals(that.query) : that.query == null;
    }

    @Override
    public int hashCode() {
        int result = query != null ? query.hashCode() : 0;
        result = 31 * result + personId;
        return result;
    }

    @Override
    public String toString() {
        return "PeopleQuery{" +
                "query='" + query + '\'' +
                ", personId=" + personId +
                '}';
    }
}
