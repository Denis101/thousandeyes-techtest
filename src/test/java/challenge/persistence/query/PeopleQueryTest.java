package challenge.persistence.query;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test for {@link PeopleQuery}
 */
public class PeopleQueryTest {
    /**
     * @verifies return the query
     * @see PeopleQuery#getQuery()
     */
    @Test
    public void getQuery_shouldReturnTheQuery() throws Exception {
        PeopleQuery query = new PeopleQuery("test", 1);
        assertEquals("test", query.getQuery());
    }

    /**
     * @verifies return the personId
     * @see PeopleQuery#getPersonId()
     */
    @Test
    public void getPersonId_shouldReturnThePersonId() throws Exception {
        PeopleQuery query = new PeopleQuery("test", 1);
        assertEquals(1, query.getPersonId());
    }
}
