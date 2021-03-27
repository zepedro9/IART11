package iart.t4g11;

import java.util.ArrayList;

public class Search {
    public static final int FILLED = 1;
    public static final int EMPTY = 0;

    // Function that given a board, will search for a solution using the Depth First Search Method, returning the final cost of the solution
    public int depth_first_search(Board board) {
        ArrayList<Pair<Integer, Integer>> nextNodes = getNextNodes(board);
        Board nextBoard = board;
        while(!isFailCondition(nextBoard)) {
            Pair<Integer, Integer> nextNode = nextNodes.get(0);
            nextBoard = expandNode(board, nextNode);
        }
        // Obviously not finished, needs backtrack functionality
        return -1;
    }

    // Function that given a board, will search for a solution using the Breadth First Search Method, returning the final cost of the solution
    public int breadth_first_search(Board board) {
        ArrayList<Pair<Integer, Integer>> nextNodes = getNextNodes(board);
        return -1;
    }

    // Function that given a board, will search for a solution using the A* Search Method, returning the final cost of the solution
    public int a_star_search(Board board) {
        ArrayList<Pair<Integer, Integer>> nextNodes = getNextNodes(board);
        return -1;
    }

    // Function that given a board and the node to expand, expands the board with the node and returns it
    private Board expandNode(Board board, Pair<Integer, Integer> node) {
        Game aux = new Game(board);
        aux.fillPoolRow(node.getA(), node.getB());
        return aux.getBoard();
    }

    // Function that returns a list of nodes (Pair<Row, Pool>) that the algorithm can follow next
    private ArrayList<Pair<Integer, Integer>> getNextNodes(Board board) {
        ArrayList<Pair<Integer, Integer>> nodes = new ArrayList<>(); // Each node is composed of a Pair<Row, Pool>
        int poolsNum = board.getPoolsNum();

        for(int p = poolsNum; p >= 1; p--) {
            int aux = 0;
            ArrayList<Pair<Integer, Integer>> positions = board.getPoolPositions(p);
            for(Pair<Integer, Integer> position : positions)
                if(position.getA() > aux && board.getValue(position.getA(), position.getB()) == EMPTY) aux = position.getA();
            nodes.add(new Pair<>(aux ,p));
        }

        return nodes;
    }

    // Function that returns true or false depending on if the board of the game is breaking rules (fail condition)
    private boolean isFailCondition(Board board) {
        int length = board.getLength();
        int[] getRowRules = board.getRowsRules();
        int[] getColRules = board.getColsRules();
        //int[][] getPools = board.getPools();

        if(!breaksRows(board, length, getRowRules)) return true; //Check row constraints
        if(!breaksCols(board, length, getColRules)) return true; //Check column constraints
        //if(!breaksPools(calcResult.getBoard(), length, getPools)) return true; //Check pool gravity constraints

        return false;
    }

    // Function that returns true or false depending on if the board of the game is won (end condition)
    private boolean isEndCondition(Board board) {
        int length = board.getLength();
        int[] getRowRules = board.getRowsRules();
        int[] getColRules = board.getColsRules();
        //int[][] getPools = board.getPools();

        if(!checkRows(board, length, getRowRules)) return false; //Check row constraints
        if(!checkCols(board, length, getColRules)) return false; //Check column constraints
        //if(!checkPools(calcResult.getBoard(), length, getPools)) return false; //Check pool gravity constraints

        return true;
    }

    // Function that checks if all the row constraints of the board are met
    private boolean checkRows(Board board, int length, int[] getRowRules) {
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
    private boolean checkCols(Board board, int length, int[] getColRules) {
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
    private boolean checkPools(Board board, int length, int[][] pools) {
        ArrayList<Pair<Integer, Integer>> positions;
        Pair<Integer, Integer> pos;
        boolean isFilled = false;

        for(int p = 1; p <= board.getPoolsNum(); p++) {
            positions = board.getPoolPositions(p);
            for (Pair<Integer, Integer> position : positions) {
                pos = position;
                if (isFilled) {
                    if (board.getValue(pos.getA(), pos.getB()) == EMPTY) return false;
                } else {
                    if (board.getValue(pos.getA(), pos.getB()) == FILLED) {
                        isFilled = true;
                        if (!checkPoolRowFull(board, positions, pos.getA())) return false;
                    }
                }
            }
        }

        return true;
    }

    // Auxiliary function used by checkPools() that checks if all positions in a row are filled
    private boolean checkPoolRowFull(Board board, ArrayList<Pair<Integer, Integer>> positions, int row) {
        for (Pair<Integer, Integer> pos : positions) {
            if(pos.getA() == row) if(board.getValue(pos.getA(), pos.getB()) == EMPTY) return false;
        }

        return true;
    }

    // Function that checks if any of the row constraints of the board are being broken
    private boolean breaksRows(Board board, int length, int[] getRowRules) {
        int aux;

        for(int r = 0; r < length; r++) {
            aux = 0;

            for(int c = 0; c < length; c++) {
                aux += board.getValue(r, c);
            }

            if(aux > getRowRules[r]) return true;
        }

        return false;
    }

    // Function that checks if any of the column constraints of the board are being broken
    private boolean breaksCols(Board board, int length, int[] getColRules) {
        int aux;

        for(int c = 0; c < length; c++) {
            aux = 0;

            for(int r = 0; r < length; r++) {
                aux += board.getValue(r, c);
            }

            if(aux > getColRules[c]) return true;
        }

        return false;
    }
}
