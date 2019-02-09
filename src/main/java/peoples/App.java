package peoples;

public class App {
    public static void main(String[] args) {
        Human adam = Human.createAdam();
        Human eve = Human.createEve();
        Human next = new Human("Alex", adam, eve);
    }

}
