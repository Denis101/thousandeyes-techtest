package challenge.controller;

import challenge.constant.MimeType;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import challenge.processor.GetMessagesProcessor;

import javax.validation.Valid;

/**
 * <h1>MessageController</h1>
 */
@RestController
public class MessageController extends BaseController {

    private final GetMessagesProcessor getMessagesProcessor;

    /**
     * @param getMessagesProcessor the processor for getting a list of messages from a given user
     */
    @Autowired
    public MessageController(final GetMessagesProcessor getMessagesProcessor) {
        this.getMessagesProcessor = getMessagesProcessor;
    }

    /**
     * Get the list of messages for a given user
     * @param id the user ID
     * @param search a search string to filter messages
     * @return a HTTP application/json response with a list of messages
     * @should return a 200 OK response if the user is found
     * @should return a 404 Not Found response if the user does not exist
     */
    @RequestMapping(value = "/{id}/messages", produces = MimeType.APPLICATION_JSON, method = RequestMethod.GET)
    public ResponseEntity get(@PathVariable @NotEmpty @Valid String id,
                              @RequestParam("search") @Valid String search) {
        return ResponseEntity.notFound().build();
    }
}
