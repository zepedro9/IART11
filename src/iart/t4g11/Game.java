package iart.t4g11;

import java.util.ArrayList;

public class Game {
    public static final int FILLED = 1;
    public static final int EMPTY = 0;

    private final Board board;

    public Game(Board board) {
        this.board = board;
    }

    // Function that returns the board
    public Board getBoard() {
        return board;
    }

    // Function that prints to the console the current state of the board
    public void printBoard() {
        System.out.println(board.toString());
    }

    // Function that fills a position on the board and also all the positions next to it and below, in the same pool
    public void fillPoolRow(int row, int pool) {
        for(int r = row; r < board.getLength(); r++) {
            fillPoolRowAux(r, pool);
        }
    }

    // Auxiliary function used by fillPoolRow() that fills a single row (r) of the a pool (pool)
    public void fillPoolRowAux(int row, int pool) {
        ArrayList<Pair<Integer, Integer>> positions = board.getPoolPositions(pool);

        for (Pair<Integer, Integer> pos : positions) {
            if(pos.getA() == row) board.fillPosition(pos.getA(), pos.getB());
        }
    }

    // Function that empties a position on the board and also all the positions next to it and below, in the same pool
    public void emptyPoolRow(int row, int pool) {
        for(int r = row; r < board.getLength(); r++) {
            emptyPoolRowAux(r, pool);
        }
    }

    // Auxiliary function used by emptyPoolRow() that fills a single row (r) of the a pool (pool)
    public void emptyPoolRowAux(int row, int pool) {
        ArrayList<Pair<Integer, Integer>> positions = board.getPoolPositions(pool);

        for (Pair<Integer, Integer> pos : positions) {
            if(pos.getA() == row) board.emptyPosition(pos.getA(), pos.getB());
        }
    }
}
