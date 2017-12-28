package challenge.controller;

import challenge.model.Message;
import challenge.model.Person;
import challenge.processor.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Test for {@link PersonController}
 */
@RunWith(MockitoJUnitRunner.class)
public class PersonControllerTest {

    @Mock
    private GetFollowersProcessor mockGetFollowersProcessor;

    @Mock
    private GetFollowingProcessor mockGetFollowingProcessor;

    @Mock
    private FollowProcessor mockFollowProcessor;

    @Mock
    private UnfollowProcessor mockUnfollowProcessor;

    @Mock
    private GetMessagesProcessor mockGetMessagesProcessor;

    private PersonController controller;
    private List<Person> people;
    private List<Message> messages;

    @Before
    public void setUp() {
        controller = new PersonController(
                mockGetFollowersProcessor,
                mockGetFollowingProcessor,
                mockFollowProcessor,
                mockUnfollowProcessor,
                mockGetMessagesProcessor);

        people = new ArrayList<>();
        Person person = new Person(1, "test", "test");
        people.add(person);

        messages = new ArrayList<>();
        Message message = new Message(1, 1, "test");
        messages.add(message);
    }

    /**
     * @verifies return a 200 OK response if the user is found
     * @see PersonController#getFollowers(int)
     */
    @Test
    public void getFollowers_shouldReturnA200OKResponseIfTheUserIsFound() throws Exception {
        when(mockGetFollowersProcessor.process(1)).thenReturn(people);

        ResponseEntity response = controller.getFollowers(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    /**
     * @verifies return a 404 Not Found response if the user has no followers
     * @see PersonController#getFollowers(int)
     */
    @Test
    public void getFollowers_shouldReturnA404NotFoundResponseIfTheUserHasNoFollowers() throws Exception {
        ResponseEntity response = controller.getFollowers(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    /**
     * @verifies return a 503 Service Unavailable response if the result is null
     * @see PersonController#getFollowers(int)
     */
    @Test
    public void getFollowers_shouldReturnA503ServiceUnavailableResponseIfTheResultIsNull() throws Exception {
        when(mockGetFollowersProcessor.process(1)).thenReturn(null);

        ResponseEntity response = controller.getFollowers(1);
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
    }

    /**
     * @verifies return a 200 OK response if the user is found
     * @see PersonController#getFollowing(int)
     */
    @Test
    public void getFollowing_shouldReturnA200OKResponseIfTheUserIsFound() throws Exception {
        when(mockGetFollowingProcessor.process(1)).thenReturn(people);

        ResponseEntity response = controller.getFollowing(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    /**
     * @verifies return a 404 Not Found response if the user has no followers
     * @see PersonController#getFollowing(int)
     */
    @Test
    public void getFollowing_shouldReturnA404NotFoundResponseIfTheUserHasNoFollowers() throws Exception {
        ResponseEntity response = controller.getFollowing(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    /**
     * @verifies return a 503 Service Unavailable response if the result is null
     * @see PersonController#getFollowing(int)
     */
    @Test
    public void getFollowing_shouldReturnA503ServiceUnavailableResponseIfTheResultIsNull() throws Exception {
        when(mockGetFollowingProcessor.process(1)).thenReturn(null);

        ResponseEntity response = controller.getFollowing(1);
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
    }

    /**
     * @verifies return a 200 OK response if the insert was a success
     * @see PersonController#follow(int, int)
     */
    @Test
    public void follow_shouldReturnA200OKResponseIfTheInsertWasASuccess() throws Exception {
        when(mockFollowProcessor.process(1, 2)).thenReturn(true);

        ResponseEntity response = controller.follow(1, 2);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    /**
     * @verifies return a 400 Bad Request response if the result was a failure
     * @see PersonController#follow(int, int)
     */
    @Test
    public void follow_shouldReturnA400BadRequestResponseIfTheResultWasAFailure() throws Exception {
        when(mockFollowProcessor.process(1, 2)).thenReturn(false);

        ResponseEntity response = controller.follow(1, 2);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    /**
     * @verifies return a 503 Service Unavailable response if the result is null
     * @see PersonController#follow(int, int)
     */
    @Test
    public void follow_shouldReturnA503ServiceUnavailableResponseIfTheResultIsNull() throws Exception {
        when(mockFollowProcessor.process(1, 2)).thenReturn(null);

        ResponseEntity response = controller.follow(1, 2);
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
    }

    /**
     * @verifies return a 200 OK response if the user is found
     * @see PersonController#getMessages(int, String)
     */
    @Test
    public void getMessages_shouldReturnA200OKResponseIfTheUserIsFound() throws Exception {
        when(mockGetMessagesProcessor.process(1)).thenReturn(messages);

        ResponseEntity response = controller.getMessages(1, "");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    /**
     * @verifies return a 404 Not Found response if the user has no messages
     * @see PersonController#getMessages(int, String)
     */
    @Test
    public void getMessages_shouldReturnA404NotFoundResponseIfTheUserHasNoMessages() throws Exception {
        ResponseEntity response = controller.getMessages(1, "");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    /**
     * @verifies return a 503 Service Unavailable response if the result is null
     * @see PersonController#getMessages(int, String)
     */
    @Test
    public void getMessages_shouldReturnA503ServiceUnavailableResponseIfTheResultIsNull() throws Exception {
        when(mockGetMessagesProcessor.process(1)).thenReturn(null);

        ResponseEntity response = controller.getMessages(1, "");
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
    }

    /**
     * @verifies return a 200 OK response if the insert was a success
     * @see PersonController#unfollow(int, int)
     */
    @Test
    public void unfollow_shouldReturnA200OKResponseIfTheInsertWasASuccess() throws Exception {
        when(mockUnfollowProcessor.process(1, 2)).thenReturn(true);

        ResponseEntity response = controller.unfollow(1, 2);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    /**
     * @verifies return a 400 Bad Request response if the result was a failure
     * @see PersonController#unfollow(int, int)
     */
    @Test
    public void unfollow_shouldReturnA400BadRequestResponseIfTheResultWasAFailure() throws Exception {
        when(mockUnfollowProcessor.process(1, 2)).thenReturn(false);

        ResponseEntity response = controller.unfollow(1, 2);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    /**
     * @verifies return a 503 Service Unavailable response if the result is null
     * @see PersonController#unfollow(int, int)
     */
    @Test
    public void unfollow_shouldReturnA503ServiceUnavailableResponseIfTheResultIsNull() throws Exception {
        when(mockUnfollowProcessor.process(1, 2)).thenReturn(null);

        ResponseEntity response = controller.unfollow(1, 2);
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
    }
}
