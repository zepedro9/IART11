package iart.t4g11;

import java.util.ArrayList;

public class Game {
    private final int FILLED = 1;
    private final int EMPTY = 0;

    private Board board;
    private boolean gameFinished;

    public Game(Board board) {
        this.board = board;
        this.gameFinished = false;
    }

    public boolean isGameFinished() {
        int length = board.getLength();
        int[] getRowRules = board.getRowsRules();
        int[] getColRules = board.getColsRules();
        int[][] getPools = board.getPools();

        if(!checkRows(length, getRowRules)) return false;
        if(!checkCols(length, getColRules)) return false;
        //if(!checkPools(length, getPools)) return false; <- VERIFY

        return true;
    }

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

    private boolean checkPools(int length, int[][] pools) {
        ArrayList<Position<Integer, Integer>> positions;
        Position<Integer, Integer> pos;
        boolean isFilled = false;

        for(int p = 1; p <= board.getPoolsNum(); p++) {
            positions = board.getPoolValues(p);
            for (Position<Integer, Integer> position : positions) {
                pos = position;
                if (isFilled) {
                    if (board.getValue(pos.getRow(), pos.getCol()) == EMPTY) return false;
                } else {
                    if (board.getValue(pos.getRow(), pos.getCol()) == FILLED) isFilled = true;
                    if (!checkPoolRowFull(positions, pos.getRow())) return false;
                }
            }
        }

        return true;
    }

    private boolean checkPoolRowFull(ArrayList<Position<Integer, Integer>> positions, int row) {
        for (Position<Integer, Integer> pos : positions) {
            if(pos.getRow() == row) if(board.getValue(pos.getRow(), pos.getCol()) == EMPTY) return false;
        }

        return true;
    }
}
