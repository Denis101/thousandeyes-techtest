package challenge.model;

/**
 * <h1>Follower</h1>
 */
public class Follower {

    private final int id;
    private final int personId;
    private final int followerPersonId;

    public Follower(int id, int personId, int followerPersonId) {
        this.id = id;
        this.personId = personId;
        this.followerPersonId = followerPersonId;
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
     * Get the followerPersonId
     * @return the value of followerPersonId
     * @should return the value of followerPersonId
     */
    public int getFollowerPersonId() {
        return followerPersonId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Follower follower = (Follower) o;

        if (id != follower.id) return false;
        if (personId != follower.personId) return false;
        return followerPersonId == follower.followerPersonId;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + personId;
        result = 31 * result + followerPersonId;
        return result;
    }

    @Override
    public String toString() {
        return "Follower{" +
                "id=" + id +
                ", personId=" + personId +
                ", followerPersonId=" + followerPersonId +
                '}';
    }
}
