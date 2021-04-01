package iart.t4g11;

public class Main {

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

        System.out.println("\n" + game.getBoard().getName());

        //search.depth_first_search(game.getBoard());
        //search.breadth_first_search(game.getBoard());
        //search.iterative_deepening(game.getBoard());
        search.greedy_search(game.getBoard());
        search.a_star_search(game.getBoard());
        //System.out.println(puzzles.getBoard(0).rulesMet());

        /*try {
            game.draw();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
