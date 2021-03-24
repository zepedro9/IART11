package iart.t4g11;

import java.util.ArrayList;

public class Board {
    private int[] rowsRules;
    private int[] colsRules;
    private int[][] pools;
    private int[][] currentState;

    private int length;
    private int poolsNum;

    public Board(int[] rows, int[] cols, int[][] pools, int[][] currentState) {
        this.rowsRules = rows;
        this.colsRules = cols;
        this.pools = pools;
        this.currentState = currentState;
        this.length = rows.length;
        this.poolsNum = calculatePoolsNum();
    }

    private int calculatePoolsNum() {
        int max = 0;

        for(int r = 0; r < length; r++) {
            for(int c = 0; c < length; c++) {
                if(getValue(r, c) > max) max = getValue(r, c);
            }
        }

        return max;
    }

    public int getPoolsNum() {
        return poolsNum;
    }

    public int getLength() {
        return length;
    }

    public int[] getRowsRules() {
        return rowsRules;
    }

    public int[] getColsRules() {
        return colsRules;
    }

    public int[][] getPools() {
        return pools;
    }

    public int[][] getCurrentState() {
        return currentState;
    }

    public int getValue(int r, int c) {
        return currentState[r][c];
    }

    public int getPool(int r, int c) {
        return pools[r][c];
    }

    public ArrayList<Position<Integer, Integer>> getPoolValues(int pool) {
        ArrayList<Position<Integer, Integer>> positions = new ArrayList<>();

        for(int r = 0; r < length; r++) {
            for(int c = 0; c < length; c++) {
                if(getPool(r, c) == pool) positions.add(new Position<>(r ,c));
            }
        }

        return positions;
    }
}
