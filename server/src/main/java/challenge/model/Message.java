package challenge.model;

/**
 * <h1>Message</h1>
 */
public class Message {

    private final int id;
    private final int personId;
    private final String content;

    public Message(int id, int personId, String content) {
        this.id = id;
        this.personId = personId;
        this.content = content;
    }

    /**
     * Get the id
     * @return the value of id
     * @should return the value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Get the personId
     * @return the value of personId
     * @should return the value of personId
     */
    public int getPersonId() {
        return personId;
    }

    /**
     * Get the content
     * @return the value of content
     * @should return the value of content
     */
    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (id != message.id) return false;
        if (personId != message.personId) return false;
        return content != null ? content.equals(message.content) : message.content == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + personId;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", personId=" + personId +
                ", content='" + content + '\'' +
                '}';
    }
}
