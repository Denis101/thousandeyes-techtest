package challenge.client;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Connection;

import static org.junit.Assert.assertEquals;

/**
 * Test for {@link H2Client}
 */
@RunWith(MockitoJUnitRunner.class)
public class H2ClientTest {

    @Mock
    private Connection mockConnection;

    /**
     * @verifies return the connection
     * @see H2Client#getConnection()
     */
    @Test
    public void getConnection_shouldReturnTheConnection() throws Exception {
        H2Client client = new H2Client("test", "test", "test", mockConnection);
        assertEquals(mockConnection, client.getConnection());
    }

    /**
     * @verifies close the connection to the database
     * @see H2Client#cleanUp()
     */
    @Test
    public void cleanUp_shouldCloseTheConnectionToTheDatabase() throws Exception {
        H2Client client = new H2Client("test", "test", "test", mockConnection);
        client.cleanUp();

        Mockito.verify(mockConnection, Mockito.times(1)).close();
    }
}
