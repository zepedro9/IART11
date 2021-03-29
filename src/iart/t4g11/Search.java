package iart.t4g11;

import java.util.*;

public class Search {
    public static final int FILLED = 1;
    public static final int EMPTY = 0;

    // Function that given a board, will search for a solution using the Depth First Search Method, returning the final cost of the solution
    public void depth_first_search(Board board) {
        if(isEndCondition(board)) {
            System.out.println("GAME DONE");
            return;
        }
        Pair wrongNode, currentNode;
        ArrayList<Pair> nextNodes;
        Stack<Pair> route = new Stack<>();
        boolean searchComplete = false;
        Board nextBoard;
        HashSet<String> visitedBoards = new HashSet<>();
        int cost = 0, i = 0;

        while(!searchComplete) {
            nextNodes = getNextNodes(board);
            Iterator<Pair> it = nextNodes.iterator();
            while(it.hasNext()) {
                i++;
                currentNode = it.next();
                nextBoard = expandNode(board, currentNode);
                if(!visitedBoards.contains(Arrays.deepToString(nextBoard.getCurrentState()))) {
                    visitedBoards.add(Arrays.deepToString(nextBoard.getCurrentState()));
                    System.out.println(i + ") Next board: ");
                    System.out.println(nextBoard.toString());
                    if(isEndCondition(nextBoard)) {
                        searchComplete = true;
                        System.out.println("GAME DONE");
                        break;
                    } else if(isFailCondition(nextBoard)) {
                        System.out.println("Result: This node failed");
                        if(!it.hasNext()) {
                            System.out.println("Result: Last node failed, going back");
                            wrongNode = route.pop();
                            board = goBackNode(board, wrongNode);
                        }
                    } else {
                        System.out.println("Result: This node succeeded");
                        cost++;
                        route.push(currentNode);
                        board = nextBoard.duplicateBoard();
                        break;
                    }
                } else if(!it.hasNext()) {
                    System.out.println("Result: Last node already visited, going back");
                    wrongNode = route.pop();
                    board = goBackNode(board, wrongNode);
                }
            }
        }

        System.out.println("GOT HERE WITH COST: " + cost);
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
    public Board expandNode(Board board, Pair node) {
        Game aux = new Game(board.duplicateBoard());
        aux.fillPoolRow(node.getA(), node.getB());
        return aux.getBoard();
    }

    // Function that given a board and the node, returns the board previous to the expansion of that node
    public Board goBackNode(Board board, Pair node) {
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

        if(breaksRows(board, length, getRowRules)) return true; //Check row constraints
        if(breaksCols(board, length, getColRules)) return true; //Check column constraints

        return false;
    }

    // Function that returns true or false depending on if the board of the game is won (end condition)
    private boolean isEndCondition(Board board) {
        int length = board.getLength();
        int[] getRowRules = board.getRowsRules();
        int[] getColRules = board.getColsRules();

        if(!checkRows(board, length, getRowRules)) return false; //Check row constraints
        if(!checkCols(board, length, getColRules)) return false; //Check column constraints

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
