package peoples;

public class Human {
    private final String name;
    private final Human father;
    private final Human mother;

    public Human(String name, Human father, Human mother) {
        this.name = name;
        this.father = father;
        this.mother = mother;
    }

    public static Human createAdam() {
        return new Human("Adam", null, null);
    }

    public static Human createEve() {
        return new Human("Eve", null, null);
    }
}
