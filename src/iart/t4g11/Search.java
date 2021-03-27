package iart.t4g11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class Search {
    public static final int FILLED = 1;
    public static final int EMPTY = 0;

    // Function that given a board, will search for a solution using the Depth First Search Method, returning the final cost of the solution
    public void depth_first_search(Board board) {
        if(isEndCondition(board)) {
            System.out.println("GAME DONE");
            return;
        }
        ArrayList<Pair> nextNodes;
        ArrayList<int[][]> wrongStates = new ArrayList<>();
        Stack<Pair> route = new Stack<>();
        boolean searchComplete = false;
        Board nextBoard;
        int cost = 0;
        int i = 1;

        // TODO: Infinite loop, ???
        while(!searchComplete) {
            nextNodes = getNextNodes(board);
            Iterator<Pair> iterator = nextNodes.iterator();
            while(iterator.hasNext()) {
                Pair node = iterator.next();
                if(!wrongStates.contains(board.getCurrentState())) {
                    System.out.println(i + node.toString());
                    i++;
                    nextBoard = expandNode(board, node);
                    System.out.println(nextBoard.toString());
                    System.out.println(isFailCondition(nextBoard));
                    cost++;
                    if(isEndCondition(nextBoard)) {
                        System.out.println("GAME DONE");
                        return;
                    } else if(isFailCondition(nextBoard)) {
                        wrongStates.add(nextBoard.getCurrentState());
                        if(!iterator.hasNext()) {
                            board = goBackNode(board, route.pop()).duplicateBoard();
                            break;
                        }
                    } else {
                        route.push(node);
                        System.out.println(i + route.toString());
                        board = nextBoard.duplicateBoard();
                        break;
                    }
                } else {
                    if(!iterator.hasNext()) {
                        System.out.println("Failed after this: ");
                        System.out.println(board.toString());
                        System.out.println(getNextNodes(board));
                        board = goBackNode(board, route.pop()).duplicateBoard();
                        break;
                    }
                }
            }
        }

        /*while(!searchComplete) {
            Iterator<Pair<Integer, Integer>> iterator = nextNodes.iterator();
            while(iterator.hasNext()) {
                Pair<Integer, Integer> node = iterator.next();
                if(!visitedNodes.contains(node)) {
                    nextBoard = expandNode(board, node);
                    System.out.println("Next step: \n" + expandNode(board, node) + "\n" + node.toString());
                    System.out.println(isFailCondition(expandNode(board, node)));
                    System.out.println(route.toString());
                    if(isFailCondition(expandNode(board, node))) return;
                    if(isEndCondition(nextBoard)) {
                        route.push(node);
                        cost++;
                        searchComplete = true;
                        break;
                    } else if(isFailCondition(nextBoard)) {
                        visitedNodes.add(node);
                        if(!iterator.hasNext()) {
                            System.out.println("Has to go back");
                            nextBoard = goBackNode(board, route.pop());
                            nextNodes = getNextNodes(nextBoard);
                            break;
                        }
                    } else {
                        route.push(node);
                        System.out.println("Current board: \n" + board);
                        cost++;
                        visitedNodes.add(node);
                        nextNodes = getNextNodes(nextBoard);
                        board = nextBoard;
                        System.out.println("Currents board: \n" + board);
                        break;
                    }
                }
            }
        }*/

        System.out.println("Got to the end");
    }

    // Function that given a board, will search for a solution using the Breadth First Search Method, returning the final cost of the solution
    public int breadth_first_search(Board board) {
        ArrayList<Pair> nextNodes = getNextNodes(board);
        return -1;
    }

    // Function that given a board, will search for a solution using the A* Search Method, returning the final cost of the solution
    public int a_star_search(Board board) {
        ArrayList<Pair> nextNodes = getNextNodes(board);
        return -1;
    }

    // Function that given a board and the node to expand, expands the board with the node and returns it
    private Board expandNode(Board board, Pair node) {
        Game aux = new Game(board.duplicateBoard());
        aux.fillPoolRow(node.getA(), node.getB());
        return aux.getBoard();
    }

    // Function that given a board and the node, returns the board previous to the expansion of that node
    private Board goBackNode(Board board, Pair node) {
        Game aux = new Game(board.duplicateBoard());
        aux.emptyPoolRow(node.getA(), node.getB());
        return aux.getBoard();
    }

    // Function that returns a list of nodes (Pair<Row, Pool>) that the algorithm can follow next
    private ArrayList<Pair> getNextNodes(Board board) {
        ArrayList<Pair> nodes = new ArrayList<>(); // Each node is composed of a Pair<Row, Pool>
        int poolsNum = board.getPoolsNum();

        for(int p = poolsNum; p >= 1; p--) {
            int aux = -1;
            ArrayList<Pair> positions = board.getPoolPositions(p);
            for(Pair position : positions)
                if(position.getA() > aux && board.getValue(position.getA(), position.getB()) == EMPTY) aux = position.getA();
            if(aux != -1) nodes.add(new Pair(aux ,p));
        }

        return nodes;
    }

    // Function that returns true or false depending on if the board of the game is breaking rules (fail condition)
    private boolean isFailCondition(Board board) {
        int length = board.getLength();
        int[] getRowRules = board.getRowsRules();
        int[] getColRules = board.getColsRules();
        //int[][] getPools = board.getPools();

        if(breaksRows(board, length, getRowRules)) return true; //Check row constraints
        if(breaksCols(board, length, getColRules)) return true; //Check column constraints
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
        ArrayList<Pair> positions;
        Pair pos;
        boolean isFilled = false;

        for(int p = 1; p <= board.getPoolsNum(); p++) {
            positions = board.getPoolPositions(p);
            for (Pair position : positions) {
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
    private boolean checkPoolRowFull(Board board, ArrayList<Pair> positions, int row) {
        for (Pair pos : positions) {
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

    public void test(Board board) {

        System.out.println("3wtf: " + board.toString());

        expandNode(board, getNextNodes(board).get(0));

        System.out.println("4wtf: " + board.toString());
    }
}
