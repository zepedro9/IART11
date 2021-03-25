package iart.t4g11;

import java.util.ArrayList;

public class Game {
    public final int FILLED = 1;
    private final int EMPTY = 0;

    private final Board board;

    public Game(Board board) {
        this.board = board;
    }

    // Function that return true or false depending on if the board of the game is finished
    public boolean isGameFinished() {
        int length = board.getLength();
        int[] getRowRules = board.getRowsRules();
        int[] getColRules = board.getColsRules();
        int[][] getPools = board.getPools();

        if(!checkRows(length, getRowRules)) return false; //Check row constraints
        if(!checkCols(length, getColRules)) return false; //Check column constraints
        if(!checkPools(length, getPools)) return false; //Check pool gravity constraints

        return true;
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
    public void fillPoolOnPosition(int r, int c) {
        int pool = board.getPoolOfPosition(r, c);
        for(int row = r; row < board.getLength(); row++) {
            fillPoolRow(row, pool);
        }
    }

    // Auxiliary function used by fillPoolOnPosition() that fills a single row (r) of the a pool (pool)
    public void fillPoolRow(int r, int pool) {
        ArrayList<Position<Integer, Integer>> positions = board.getPoolPositions(pool);

        for (Position<Integer, Integer> pos : positions) {
            if(pos.getRow() == r) board.fillPosition(pos.getRow(), pos.getCol());
        }
    }

    // Function that empties a position on the board and also all the positions next to it and below, in the same pool
    public void emptyPoolOnPosition(int r, int c) {
        int pool = board.getPoolOfPosition(r, c);
        for(int row = r; row < board.getLength(); row++) {
            emptyPoolRow(row, pool);
        }
    }

    // Auxiliary function used by emptiesPoolOnPosition() that fills a single row (r) of the a pool (pool)
    public void emptyPoolRow(int r, int pool) {
        ArrayList<Position<Integer, Integer>> positions = board.getPoolPositions(pool);

        for (Position<Integer, Integer> pos : positions) {
            if(pos.getRow() == r) board.emptyPosition(pos.getRow(), pos.getCol());
        }
    }

    // Function that checks if all the row constraints of the board are met
    private boolean checkRows(int length, int[] getRowRules) {
        int aux;

        for(int r = 0; r < length; r++) {
            aux = 0;

            for(int c = 0; c < length; c++) {
                aux += board.getValue(r, c);
            }

            if(getRowRules[r] != aux) return false;
        }

         return true;
    }

    // Function that checks if all the column constraints of the board are met
    private boolean checkCols(int length, int[] getColRules) {
        int aux;

        for(int c = 0; c < length; c++) {
            aux = 0;

            for(int r = 0; r < length; r++) {
                aux += board.getValue(r, c);
            }

            if(getColRules[c] != aux) return false;
        }

        return true;
    }

    // Function that checks if all the pool gravity constraints of the board are met
    private boolean checkPools(int length, int[][] pools) {
        ArrayList<Position<Integer, Integer>> positions;
        Position<Integer, Integer> pos;
        boolean isFilled = false;

        for(int p = 1; p <= board.getPoolsNum(); p++) {
            positions = board.getPoolPositions(p);
            for (Position<Integer, Integer> position : positions) {
                pos = position;
                if (isFilled) {
                    if (board.getValue(pos.getRow(), pos.getCol()) == EMPTY) return false;
                } else {
                    if (board.getValue(pos.getRow(), pos.getCol()) == FILLED) {
                        isFilled = true;
                        if (!checkPoolRowFull(positions, pos.getRow())) return false;
                    }
                }
            }
        }

        return true;
    }

    // Auxiliary function used by checkPools() that checks if all positions in a row are filled
    private boolean checkPoolRowFull(ArrayList<Position<Integer, Integer>> positions, int row) {
        for (Position<Integer, Integer> pos : positions) {
            if(pos.getRow() == row) if(board.getValue(pos.getRow(), pos.getCol()) == EMPTY) return false;
        }

        return true;
    }
}
