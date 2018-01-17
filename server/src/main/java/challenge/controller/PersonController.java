package challenge.controller;

import challenge.constant.MimeType;
import challenge.model.Message;
import challenge.model.Person;
import challenge.persistence.command.AddMessageCommandHandler;
import challenge.persistence.query.GetFollowingQuery;
import challenge.persistence.query.GetPersonQuery;
import challenge.persistence.query.PeopleQueryHandler;
import challenge.processor.*;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <h1>PersonController</h1>
 */
@RestController
@RequestMapping(value = "/${api.version}/person")
public class PersonController {

    private final GetFollowersProcessor getFollowersProcessor;
    private final GetFollowingProcessor getFollowingProcessor;
    private final FollowProcessor followProcessor;
    private final UnfollowProcessor unfollowProcessor;
    private final GetMessagesProcessor getMessagesProcessor;
    private final AddMessageCommandHandler addMessageCommandHandler;

    private final PeopleQueryHandler peopleQueryHandler;

    /**
     * @param getFollowersProcessor the processor for getting a list of followers for a given user
     * @param getFollowingProcessor the processor for getting a list of users followed for a given user
     * @param followProcessor the processor for adding a new user followed for a given user
     * @param unfollowProcessor the processor for removing a follow for a given user
     * @param getMessagesProcessor the processor for getting a list of messages for a given user
     */
    @Autowired
    public PersonController(final GetFollowersProcessor getFollowersProcessor,
                            final GetFollowingProcessor getFollowingProcessor,
                            final FollowProcessor followProcessor,
                            final UnfollowProcessor unfollowProcessor,
                            final GetMessagesProcessor getMessagesProcessor,
                            final AddMessageCommandHandler addMessageCommandHandler,
                            final PeopleQueryHandler peopleQueryHandler) {
        this.getFollowersProcessor = getFollowersProcessor;
        this.getFollowingProcessor = getFollowingProcessor;
        this.followProcessor = followProcessor;
        this.unfollowProcessor = unfollowProcessor;
        this.getMessagesProcessor = getMessagesProcessor;
        this.addMessageCommandHandler = addMessageCommandHandler;
        this.peopleQueryHandler = peopleQueryHandler;
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
     * @should return a 400 Bad Request response if the result was a failure
     * @should return a 503 Service Unavailable response if the result is null
     */
    @RequestMapping(value = "/{id}/follow/{followId}", produces = MimeType.APPLICATION_JSON, method = RequestMethod.PUT)
    public ResponseEntity follow(@PathVariable("id") @NotEmpty @Valid int id,
                                 @PathVariable("followId") @NotEmpty @Valid int followId) {
        Boolean result = followProcessor.process(id, followId);

        if (result == null) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }

        return !result
                ? ResponseEntity.badRequest().build()
                : ResponseEntity.ok("{ \"success\": true }");
    }

    /**
     * Remove a follow for a given user
     * @param id the user ID
     * @param followId the user ID to stop following
     * @return a HTTP application/json response indicating success/failure
     * @should return a 200 OK response if the insert was a success
     * @should return a 400 Bad Request response if the result was a failure
     * @should return a 503 Service Unavailable response if the result is null
     */
    @RequestMapping(value = "/{id}/follow/{followId}", produces = MimeType.APPLICATION_JSON, method = RequestMethod.DELETE)
    public ResponseEntity unfollow(@PathVariable("id") @NotEmpty @Valid int id,
                                   @PathVariable("followId") @NotEmpty @Valid int followId) {
        Boolean result = unfollowProcessor.process(id, followId);

        if (result == null) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }

        return !result
                ? ResponseEntity.badRequest().build()
                : ResponseEntity.ok("{ \"success\": true }");
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
        List<Message> messages = getMessagesProcessor.process(id, search);

        if (messages == null) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }

        return messages.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(messages);
    }

    @RequestMapping(value = "/{id}/messages", produces = MimeType.APPLICATION_JSON, method = RequestMethod.POST)
    public ResponseEntity sendMessage(@PathVariable @NotEmpty @Valid int id,
                                      @RequestBody @NotEmpty @Valid String content) {
        Boolean success = addMessageCommandHandler.handle(id, content);

        if (success == null) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }

        return !success
                    ? ResponseEntity.badRequest().build()
                    : ResponseEntity.ok("{ \"success\": true }");
    }

    @RequestMapping(value = "/{id}/distance-to/{theirId}", produces = MimeType.APPLICATION_JSON, method = RequestMethod.GET)
    public ResponseEntity distanceTo(@PathVariable @NotEmpty @Valid int id,
                                     @PathVariable @NotEmpty @Valid int theirId) {
        List<Person> myFollowing = peopleQueryHandler.handle(new GetFollowingQuery(id));
        return ResponseEntity.ok(id != theirId
                ? getHops(theirId, myFollowing, new ArrayList<>(Collections.singletonList(id)), 1, 0)
                : 0);
    }

    private int getHops(int expectedId, List<Person> following, List<Integer> visited, int currentHops, int result) {
        for (Person person : following) {
            printAtDepth(person.toString(), currentHops);
            if (person.getId() == expectedId) {
                return currentHops;
            }

            if (visited.contains(person.getId())) {
                continue;
            }

            visited.add(person.getId());
            List<Person> personFollowing = peopleQueryHandler.handle(new GetFollowingQuery(person.getId()));
            result = getHops(expectedId, personFollowing, visited, currentHops + 1, result);
        }

        return result;
    }

    private void printAtDepth(String content, int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.printf(" ");
        }
        System.out.printf("%s :: %d ::\n", content, depth);
    }

    @RequestMapping(value = "/{id}/graph", produces = MimeType.APPLICATION_JSON, method = RequestMethod.GET)
    public ResponseEntity graph(@PathVariable @NotEmpty @Valid int id) {
        return ResponseEntity.ok(getGraph(peopleQueryHandler.handle(new GetPersonQuery(id)).get(0)));

    }

    private Person getGraph(Person person) {
        ArrayList visited = new ArrayList<>(Collections.singletonList(person));
        LinkedList<Person> queue = new LinkedList<>(Collections.singletonList(person));

        while (queue.size() > 0) {
            Person current = queue.poll();
            current.setFollowing(peopleQueryHandler.handle(new GetFollowingQuery(current.getId())));

            current.getFollowing().forEach(p -> {
                if (!visited.contains(p.getId())) {
                    visited.add(p.getId());
                    queue.add(p);
                }
            });
        }

        return person;
    }
}
