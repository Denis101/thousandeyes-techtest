package challenge.client;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test for {@link H2Client}
 */
public class H2ClientTest {
    /**
     * @verifies return the connection
     * @see H2Client#getConnection()
     */
    @Test
    public void getConnection_shouldReturnTheConnection() throws Exception {
        H2Client client = new H2Client("test", "test", "test");
    }

    /**
     * @verifies close the connection to the database
     * @see H2Client#cleanUp()
     */
    @Test
    public void cleanUp_shouldCloseTheConnectionToTheDatabase() throws Exception {
        //TODO auto-generated
        Assert.fail("Not yet implemented");
    }
}
