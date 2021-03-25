package iart.t4g11;

public class Main {

    public static void main(String[] args) {
        PredefinedBoards puzzles = new PredefinedBoards(); //Load default puzzles
        Board board;

        if(args.length >= 1) { // If there is an argument giving the board to be used, select that board, otherwise use default board (currently Board nº1)
            try {
                board = puzzles.getBoard(Integer.parseInt(args[0]));
            } catch (NumberFormatException e) {
                board = puzzles.getBoard(1);
            }
        } else board = puzzles.getBoard(1);

        Game game = new Game(board);

        System.out.print("\n" + game.getBoard().getName());
        game.printBoard();
        System.out.println(game.isGameFinished());
    }
}
