package challenge.controller;

import challenge.constant.MimeType;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import challenge.processor.AddFollowProcessor;
import challenge.processor.GetFollowersProcessor;
import challenge.processor.GetFollowingProcessor;

import javax.validation.Valid;
import java.util.List;

/**
 * <h1>FollowerController</h1>
 */
@RestController
public class FollowerController {

    private final GetFollowersProcessor getFollowersProcessor;
    private final GetFollowingProcessor getFollowingProcessor;
    private final AddFollowProcessor addFollowProcessor;

    /**
     * @param getFollowersProcessor the processor for getting a list of followers for a given user
     * @param getFollowingProcessor the processor for getting a list of users followed for a given user
     * @param addFollowProcessor the processor for adding a new user followed for a given user
     */
    @Autowired
    public FollowerController(final GetFollowersProcessor getFollowersProcessor,
                              final GetFollowingProcessor getFollowingProcessor,
                              final AddFollowProcessor addFollowProcessor) {
        this.getFollowersProcessor = getFollowersProcessor;
        this.getFollowingProcessor = getFollowingProcessor;
        this.addFollowProcessor = addFollowProcessor;
    }

    /**
     * Get the list of followers for a given user
     * @param id the user ID
     * @return a HTTP application/json response with a list of users
     * @should return a 200 OK response if the user is found
     * @should return a 404 Not Found response if the user ID does not exist
     */
    @RequestMapping(value = "/{id}/followers", produces = MimeType.APPLICATION_JSON, method = RequestMethod.GET)
    public ResponseEntity getFollowers(@PathVariable @NotEmpty @Valid String id) {
        List<String> followers = getFollowersProcessor.process(id);

        return followers == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(followers);
    }

    /**
     * Get the list of users followed for a given user
     * @param id the user ID
     * @return a HTTP application/json response with a list of users
     * @should return a 200 OK response if the user is found
     * @should return a 404 Not Found response if the user ID does not exist
     */
    @RequestMapping(value = "/{id}/following", produces = MimeType.APPLICATION_JSON, method = RequestMethod.GET)
    public ResponseEntity getFollowing(@PathVariable @NotEmpty @Valid String id) {
        List<String> following = getFollowingProcessor.process(id);
        return following == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(following);
    }

    /**
     * Add a new user to the given user's following list
     * @param id the user ID
     * @param followId the user ID to follow
     * @return a HTTP application/json response indicating success/failure
     * @should return a 200 OK response if the user is found
     * @should return a 404 Not Found response if the user ID does not exist
     * @should return a 404 Not Found response if the followed user ID does not exist
     */
    @RequestMapping(value = "/{id}/follow/{followId}", produces = MimeType.APPLICATION_JSON, method = RequestMethod.PUT)
    public ResponseEntity follow(@PathVariable @NotEmpty @Valid String id,
                                 @PathVariable @NotEmpty @Valid String followId) {
        boolean result = addFollowProcessor.process(id, followId);

        return !result
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok("{ \"success\": true ");
    }
}
