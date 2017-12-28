package challenge.controller;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

/**
 * Test for {@link HealthCheckController}
 */
public class HealthCheckControllerTest {
    /**
     * @verifies return a HTTP 200 OK response
     * @see HealthCheckController#healthCheck()
     */
    @Test
    public void healthCheck_shouldReturnAHTTP200OKResponse() throws Exception {
        HealthCheckController controller = new HealthCheckController();

        ResponseEntity response = controller.healthCheck();

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
