package iart.t4g11;

import java.lang.reflect.Array;
import java.util.*;

public class Main {

    // TODO: Pool gravity rules are probably going to be removed, as the game only allows the user/computer to
    //  fill one row at a time, filling everything below as well, which makes breaking the gravity rule impossible

    public static void main(String[] args) {
        PredefinedBoards puzzles = new PredefinedBoards(); //Load default puzzles
        Board board;
        Search search = new Search();

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


        search.depth_first_search(game.getBoard());
    }
}
