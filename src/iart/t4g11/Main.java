package iart.t4g11;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        try {
            game.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
