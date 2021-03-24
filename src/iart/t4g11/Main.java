package iart.t4g11;

public class Main {

    public static void main(String[] args) {
        Board board6x6 = new Board(
                new int[] {3, 2, 5, 4, 3, 3},
                new int[] {4, 3, 2, 3, 4, 4},
                new int[][] {new int[] {1, 1, 1, 2, 2, 2}, new int[] {3, 3, 1, 1, 2, 2}, new int[] {3, 3, 1, 4, 4, 2}, new int[] {3, 1, 1, 1, 5, 5}, new int[] {3, 1, 1, 5, 5, 5}, new int[] {3, 5, 5, 5, 6, 6}},
                new int[][] {new int[] {0, 0, 0, 1, 1, 1}, new int[] {0, 0, 0, 0, 1, 1}, new int[] {1, 1, 0, 1, 1, 1}, new int[] {1, 1, 1, 1, 0, 0}, new int[] {1, 1, 1, 0, 0, 0}, new int[] {1, 0, 0, 0, 1, 1}});

        Board boardComplete = new Board(
                new int[] {4, 3, 3, 1, 1, 3},
                new int[] {1, 2, 3, 3, 5, 1},
                new int[][] {new int[] {1, 2, 2, 2, 2, 3}, new int[] {1, 1, 1, 4, 4, 3}, new int[] {5, 5, 4, 4, 4, 3}, new int[] {3, 5, 5, 3, 4, 3}, new int[] {3, 3, 3, 3, 4, 3}, new int[] {6, 6, 6, 3, 3, 3}},
                new int[][] {new int[] {0, 1, 1, 1, 1, 0}, new int[] {1, 1, 1, 0, 0, 0}, new int[] {0, 0, 1, 1, 1, 0}, new int[] {0, 0, 0, 0, 1, 0}, new int[] {0, 0, 0, 0, 1, 0}, new int[] {0, 0, 0, 1, 1, 1}});

        Game game = new Game(board6x6);

        System.out.println(game.isGameFinished());
    }
}
