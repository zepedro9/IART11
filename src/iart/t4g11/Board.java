package iart.t4g11;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Board implements Serializable {
    public static final int FILLED = 1;
    public static final int EMPTY = 0;

    private final int[] rowsRules;
    private final int[] colsRules;
    private final int[][] pools;
    private final int[][] currentState;

    private final String name;
    private final int length;
    private final int poolsNum;

    public Board(String name, int[] rows, int[] cols, int[][] pools, int[][] currentState) {
        this.name = name;
        this.rowsRules = rows;
        this.colsRules = cols;
        this.pools = pools;
        this.currentState = currentState;
        this.length = rows.length;
        this.poolsNum = calculatePoolsNum();
    }

    public Board duplicateBoard() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            oos.flush();
            oos.close();
            bos.close();
            byte[] byteData = bos.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(byteData);
            return (Board) new ObjectInputStream(bais).readObject();
        } catch (Exception e) {
            System.err.println("FATAL: Error duplicating board!");
            System.exit(-1);
            return null;
        }
    }

    // Function that calculates the number of pools in a board
    private int calculatePoolsNum() {
        int max = 0;

        for(int r = 0; r < length; r++) {
            for(int c = 0; c < length; c++) {
                if(getPoolOfPosition(r, c) > max) max = getPoolOfPosition(r, c);
            }
        }

        return max;
    }

    // Function that transforms the board to readable string form
    public String toString() {
        StringBuilder str = new StringBuilder();

        for(int r = 0; r < length; r++) {
            str.append("\n").append(Arrays.toString(currentState[r]));
        }

        return str.toString();
    }

    // Function that returns the name of the board
    public String getName() {
        return name;
    }

    // Function that returns the number of pools in the board
    public int getPoolsNum() {
        return poolsNum;
    }

    // Function that returns the width/height of a board
    public int getLength() {
        return length;
    }

    // Function that returns the list of row constraints
    public int[] getRowsRules() {
        return rowsRules;
    }

    // Function that returns the list of column constraints
    public int[] getColsRules() {
        return colsRules;
    }

    // Function that returns the list of positions on the board in terms of pools they belong to
    public int[][] getPools() {
        return pools;
    }

    // Function that returns the list of positions of the current state of the board
    public int[][] getCurrentState() {
        return currentState;
    }

    // Function that returns the value in a given position (r,c)
    public int getValue(int r, int c) {
        return currentState[r][c];
    }

    // Function that returns the pool in a given position (r,c) is in
    public int getPoolOfPosition(int r, int c) {
        return pools[r][c];
    }

    // Function that returns a list of all positions of a given pool (pool)
    public ArrayList<Pair> getPoolPositions(int pool) {
        ArrayList<Pair> positions = new ArrayList<>();

        for(int r = 0; r < length; r++) {
            for(int c = 0; c < length; c++) {
                if(getPoolOfPosition(r, c) == pool) positions.add(new Pair(r ,c));
            }
        }

        return positions;
    }

    // Function that fills a position (r,c) on the board
    public void fillPosition(int r, int c) {
        currentState[r][c] = FILLED;
    }

    // Function that empties a position (r,c) on the board
    public void emptyPosition(int r, int c) {
        currentState[r][c] = EMPTY;
    }
}
