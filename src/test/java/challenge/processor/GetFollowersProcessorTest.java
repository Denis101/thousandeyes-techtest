package challenge.processor;

import challenge.model.Person;
import challenge.persistence.query.GetFollowersQuery;
import challenge.persistence.query.PeopleQuery;
import challenge.persistence.query.PeopleQueryHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Test for {@link GetFollowersProcessor}
 */
@RunWith(MockitoJUnitRunner.class)
public class GetFollowersProcessorTest {

    @Mock
    private PeopleQueryHandler peopleQueryHandler;

    private GetFollowersProcessor processor;
    private List<Person> people;

    @Before
    public void setUp() {
        processor = new GetFollowersProcessor(peopleQueryHandler);

        people = new ArrayList<>();
        Person person = new Person(1, "test", "test");
        people.add(person);
    }

    /**
     * @verifies return a list of followers
     * @see GetFollowersProcessor#process(int)
     */
    @Test
    public void process_shouldReturnAListOfFollowers() throws Exception {
        when(peopleQueryHandler.handle(any(GetFollowersQuery.class))).thenReturn(people);
        assertEquals(people, processor.process(1));
    }

    /**
     * @verifies return null if the query failed
     * @see GetFollowersProcessor#process(int)
     */
    @Test
    public void process_shouldReturnNullIfTheQueryFailed() throws Exception {
        when(peopleQueryHandler.handle(any(GetFollowersQuery.class))).thenReturn(null);
        assertNull(processor.process(1));
    }
}
