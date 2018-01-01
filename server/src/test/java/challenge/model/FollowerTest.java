package challenge.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test for {@link Follower}
 */
public class FollowerTest {
    /**
     * @verifies return the value of id
     * @see Follower#getId()
     */
    @Test
    public void getId_shouldReturnTheValueOfId() throws Exception {
        Follower follower = new Follower(1, 2, 3);
        assertEquals(1, follower.getId());
    }

    /**
     * @verifies return the value of personId
     * @see Follower#getPersonId()
     */
    @Test
    public void getPersonId_shouldReturnTheValueOfPersonId() throws Exception {
        Follower follower = new Follower(1, 2, 3);
        assertEquals(2, follower.getPersonId());
    }

    /**
     * @verifies return the value of followerPersonId
     * @see Follower#getFollowerPersonId()
     */
    @Test
    public void getFollowerPersonId_shouldReturnTheValueOfFollowerPersonId() throws Exception {
        Follower follower = new Follower(1, 2, 3);
        assertEquals(3, follower.getFollowerPersonId());
    }

    @Test
    public void equalsVerifier() {
        EqualsVerifier.forClass(Follower.class).usingGetClass().verify();
    }
}
