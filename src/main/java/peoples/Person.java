package peoples;

public class Person {
    private final int id;
    private final String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName1() {
        return name;
    }

    public String coolPresentation() {
        return String.format("Person{id=%d, name='%s'}", id, name);
    }
}
