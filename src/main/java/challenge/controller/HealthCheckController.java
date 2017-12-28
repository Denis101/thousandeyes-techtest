package challenge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>HealthCheckController</h1>
 */
@RestController
@RequestMapping(value = "/health")
public class HealthCheckController {

    private static final Logger LOG = LoggerFactory.getLogger(HealthCheckController.class);

    /**
     * Returns a 200 OK response as a shallow health check
     * @return a ResponseEntity with the HTTP Status 200 OK
     * @should return a HTTP 200 OK response
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Void> healthCheck() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
