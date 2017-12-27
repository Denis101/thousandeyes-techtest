package challenge.model;

/**
 * <h1>Person</h1>
 */
public class Person {

    private final int id;
    private final String handle;
    private final String name;

    public Person(final int id, final String handle, final String name) {
        this.id = id;
        this.handle = handle;
        this.name = name;
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
     * Get the handle
     * @return the value of handle
     * @should return the value of handle
     */
    public String getHandle() {
        return handle;
    }

    /**
     * Get the name
     * @return the value of name
     * @should return the value of name
     */
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != person.id) return false;
        if (handle != null ? !handle.equals(person.handle) : person.handle != null) return false;
        return name != null ? name.equals(person.name) : person.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (handle != null ? handle.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", handle='" + handle + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
