package challenge.controller;

import challenge.constant.MimeType;
import challenge.model.Message;
import challenge.model.Person;
import challenge.processor.GetMessagesProcessor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import challenge.processor.AddFollowProcessor;
import challenge.processor.GetFollowersProcessor;
import challenge.processor.GetFollowingProcessor;

import javax.validation.Valid;
import java.util.List;

/**
 * <h1>PersonController</h1>
 */
@RestController
@RequestMapping(value = "/${api.version}/person")
public class PersonController {

    private final GetFollowersProcessor getFollowersProcessor;
    private final GetFollowingProcessor getFollowingProcessor;
    private final AddFollowProcessor addFollowProcessor;
    private final GetMessagesProcessor getMessagesProcessor;

    /**
     * @param getFollowersProcessor the processor for getting a list of followers for a given user
     * @param getFollowingProcessor the processor for getting a list of users followed for a given user
     * @param addFollowProcessor the processor for adding a new user followed for a given user
     * @param getMessagesProcessor the processor for getting a list of messages for a given user
     */
    @Autowired
    public PersonController(final GetFollowersProcessor getFollowersProcessor,
                            final GetFollowingProcessor getFollowingProcessor,
                            final AddFollowProcessor addFollowProcessor,
                            final GetMessagesProcessor getMessagesProcessor) {
        this.getFollowersProcessor = getFollowersProcessor;
        this.getFollowingProcessor = getFollowingProcessor;
        this.addFollowProcessor = addFollowProcessor;
        this.getMessagesProcessor = getMessagesProcessor;
    }

    /**
     * Get the list of followers for a given user
     * @param id the user ID
     * @return a HTTP application/json response with a list of users
     * @should return a 200 OK response if the user is found
     * @should return a 404 Not Found response if the user has no followers
     * @should return a 503 Service Unavailable response if the result is null
     */
    @RequestMapping(value = "/{id}/followers", produces = MimeType.APPLICATION_JSON, method = RequestMethod.GET)
    public ResponseEntity getFollowers(@PathVariable @NotEmpty @Valid int id) {
        List<Person> followers = getFollowersProcessor.process(id);

        if (followers == null) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }

        return followers.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(followers);
    }

    /**
     * Get the list of users followed for a given user
     * @param id the user ID
     * @return a HTTP application/json response with a list of users
     * @should return a 200 OK response if the user is found
     * @should return a 404 Not Found response if the user has no followers
     * @should return a 503 Service Unavailable response if the result is null
     */
    @RequestMapping(value = "/{id}/following", produces = MimeType.APPLICATION_JSON, method = RequestMethod.GET)
    public ResponseEntity getFollowing(@PathVariable @NotEmpty @Valid int id) {
        List<Person> following = getFollowingProcessor.process(id);

        if (following == null) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }

        return following.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(following);
    }

    /**
     * Add a new user to the given user's following list
     * @param id the user ID
     * @param followId the user ID to follow
     * @return a HTTP application/json response indicating success/failure
     * @should return a 200 OK response if the insert was a success
     */
    @RequestMapping(value = "/{id}/follow/{followId}", produces = MimeType.APPLICATION_JSON, method = RequestMethod.PUT)
    public ResponseEntity follow(@PathVariable("id") @NotEmpty @Valid int id,
                                 @PathVariable("followId") @NotEmpty @Valid int followId) {
        boolean result = addFollowProcessor.process(id, followId);

        return !result
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok("{ \"success\": true ");
    }

    /**
     * Get the list of messages for a given user
     * @param id the user ID
     * @param search a search string to filter messages
     * @return a HTTP application/json response with a list of messages
     * @should return a 200 OK response if the user is found
     * @should return a 404 Not Found response if the user has no messages
     * @should return a 503 Service Unavailable response if the result is null
     */
    @RequestMapping(value = "/{id}/messages", produces = MimeType.APPLICATION_JSON, method = RequestMethod.GET)
    public ResponseEntity getMessages(@PathVariable @NotEmpty @Valid int id,
                              @RequestParam(value = "search", required = false) @Valid String search) {
        List<Message> messages = getMessagesProcessor.process(id);

        if (messages == null) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }

        return messages.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(messages);
    }
}
