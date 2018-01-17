package challenge.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Test for {@link Person}
 */
public class PersonTest {

    private static final String HANDLE_STRING = "handle";
    private static final String NAME_STRING = "name";
    private static final List<Person> FOLLOWING = new ArrayList<>();

    /**
     * @verifies return the value of id
     * @see Person#getId()
     */
    @Test
    public void getId_shouldReturnTheValueOfId() throws Exception {
        Person person = new Person(1, HANDLE_STRING, NAME_STRING, FOLLOWING);
        assertEquals(1, person.getId());
    }

    /**
     * @verifies return the value of handle
     * @see Person#getHandle()
     */
    @Test
    public void getHandle_shouldReturnTheValueOfHandle() throws Exception {
        Person person = new Person(1, HANDLE_STRING, NAME_STRING, FOLLOWING);
        assertEquals(HANDLE_STRING, person.getHandle());
    }

    /**
     * @verifies return the value of name
     * @see Person#getName()
     */
    @Test
    public void getName_shouldReturnTheValueOfName() throws Exception {
        Person person = new Person(1, HANDLE_STRING, NAME_STRING, FOLLOWING);
        assertEquals(NAME_STRING, person.getName());
    }

//    @Test
//    public void equalsVerifier() {
//        EqualsVerifier.forClass(Person.class).usingGetClass().verify();
//    }
}
