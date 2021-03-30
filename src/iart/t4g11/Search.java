package iart.t4g11;

import java.util.*;

public class Search {
    private static final int FILLED = 1;
    private static final int EMPTY = 0;
    private static final int STEP_CAP = 10000000;

    // Function that given a board, will search for a solution using the Depth First Search method, returning the solution, the search cost to get to it through this method, and the time taken by it
    public void depth_first_search(Board board) {
        Pair wrongNode, currentNode;
        ArrayList<Pair> nextNodes;
        Stack<Pair> route = new Stack<>();
        boolean searchComplete = false;
        Board nextBoard;
        HashSet<String> visitedBoards = new HashSet<>();
        String currentStateStr;
        int cost = 0;
        long startTime;

        if(isEndCondition(board)) searchComplete = true;

        startTime = System.nanoTime();
        System.out.println("Solving...");

        while(!searchComplete) {
            nextNodes = getNextNodes(board);
            Iterator<Pair> it = nextNodes.iterator();
            while(it.hasNext()) {
                cost++;
                currentNode = it.next();
                nextBoard = expandNode(board, currentNode);
                currentStateStr = testForOutOfMemory(cost, nextBoard, startTime);
                if (currentStateStr == null) return;
                if(!visitedBoards.contains(currentStateStr)) {
                    visitedBoards.add(currentStateStr);
                    //System.out.println(cost + ") Next board: ");
                    //System.out.println(nextBoard.toString());
                    if(isEndCondition(nextBoard)) {
                        searchComplete = true;
                        board = nextBoard.duplicateBoard();
                        break;
                    } else if(isFailCondition(nextBoard)) {
                        //System.out.println("Result: This node failed");
                        if(!it.hasNext()) {
                            //System.out.println("Result: Last node failed, going back");
                            wrongNode = route.pop();
                            board = goBackNode(board, wrongNode);
                        }
                    } else {
                        //System.out.println("Result: This node succeeded");
                        route.push(currentNode);
                        board = nextBoard.duplicateBoard();
                        break;
                    }
                } else if(!it.hasNext()) {
                    //System.out.println("Result: Last node already visited, going back");
                    wrongNode = route.pop();
                    board = goBackNode(board, wrongNode);
                }
            }
        }

        finishSearch(board, cost, startTime);
    }

    // Function that given a board, will search for a solution using the Breadth First Search Method, returning the final cost of the solution
    public void breadth_first_search(Board board) {
        ArrayList<Pair> nextNodes = getNextNodes(board);
        HashSet<String> visitedBoards = new HashSet<>();
        Queue<Board> queue = new LinkedList<>();
        for(Pair node : nextNodes)
            queue.add(expandNode(board, node).duplicateBoard());
        int cost = 0;
        Board currentBoard = null;
        String currentStateStr;
        boolean searchComplete = false;
        long startTime;

        if(isEndCondition(board)) searchComplete = true;

        startTime = System.nanoTime();
        System.out.println("Solving...");

        while(!searchComplete) {
            if(queue.isEmpty()) {
                System.err.println("Failed to solve puzzle, no end condition found");
                return;
            }
            currentBoard = queue.remove().duplicateBoard();
            cost++;
            currentStateStr = testForOutOfMemory(cost, currentBoard, startTime);
            if (currentStateStr == null) return;
            if(!visitedBoards.contains(currentStateStr)) {
                visitedBoards.add(currentStateStr);
                if(isEndCondition(currentBoard)) {
                    searchComplete = true;
                } else if(!isFailCondition(currentBoard)) {
                    nextNodes = getNextNodes(currentBoard);
                    for(Pair node : nextNodes)
                        queue.add(expandNode(currentBoard, node).duplicateBoard());
                }
            }
        }

        assert currentBoard != null;
        finishSearch(currentBoard, cost, startTime);
    }

    public void iterative_deepening(Board board) {
        Pair wrongNode, currentNode;
        ArrayList<Pair> nextNodes;
        Stack<Pair> route = new Stack<>();
        boolean searchComplete = false, partialSearchComplete = false;
        Board nextBoard, saveBoard = board.duplicateBoard();
        HashSet<String> visitedBoards = new HashSet<>();
        String currentStateStr;
        int cost = 0, depth = 0, maxDepth = 1;
        long startTime;

        if(isEndCondition(board)) searchComplete = true;

        startTime = System.nanoTime();
        System.out.println("Solving...");

        while(!searchComplete) {
            while(!partialSearchComplete) {
                nextNodes = getNextNodes(board);
                Iterator<Pair> it = nextNodes.iterator();
                while(it.hasNext()) {
                    cost++;
                    currentNode = it.next();
                    nextBoard = expandNode(board, currentNode);
                    depth++;
                    currentStateStr = testForOutOfMemory(cost, nextBoard, startTime);
                    if (currentStateStr == null) return;
                    if(!visitedBoards.contains(currentStateStr)) {
                        visitedBoards.add(currentStateStr);
                        //System.out.println(cost + ") Next board: ");
                        //System.out.println("Depth: " + depth + " | Max Depth: " + maxDepth);
                        //System.out.println(nextBoard.toString());
                        if(isEndCondition(nextBoard)) {
                            searchComplete = true;
                            saveBoard = nextBoard.duplicateBoard();
                            break;
                        } else if(depth >= maxDepth) {
                            depth--;
                            if(!it.hasNext()) {
                                if(route.empty()) {
                                    //System.out.println("Result: Last node failed, resetting with bigger MaxDepth");
                                    partialSearchComplete = true;
                                    break;
                                }
                                wrongNode = route.pop();
                                board = goBackNode(board, wrongNode);
                                depth--;
                            }
                        } else if(isFailCondition(nextBoard)) {
                            //System.out.println("Result: This node failed");
                            if(!it.hasNext()) {
                                //System.out.println("Result: Last node failed, going back");
                                wrongNode = route.pop();
                                board = goBackNode(board, wrongNode);
                                depth--;
                            }
                        } else {
                            //System.out.println("Result: This node succeeded");
                            route.push(currentNode);
                            board = nextBoard.duplicateBoard();
                            break;
                        }
                    } else if(!it.hasNext()) {
                        //System.out.println("Result: Last node already visited, going back");
                        wrongNode = route.pop();
                        board = goBackNode(board, wrongNode);
                        depth--;
                    }
                }
            }
            depth = 1;
            maxDepth *= 2;
            visitedBoards.clear();
            board = saveBoard.duplicateBoard();
            route.clear();
            partialSearchComplete = false;
        }

        finishSearch(board, cost, startTime);
    }

    private void finishSearch(Board board, int cost, long startTime) {
        long endTime;
        long timeElapsed;
        endTime = System.nanoTime();
        timeElapsed = endTime - startTime;
        System.out.println("Solved board: " + board.toString());
        System.out.println("Solution to puzzle found with a search cost of: " + cost + " steps");
        System.out.println("Time taken to perform search: " + timeElapsed / 1000000000 + "." + (timeElapsed / 1000000 - (timeElapsed / 1000000000) * 1000) + " seconds");
    }

    private String testForOutOfMemory(int cost, Board currentBoard, long startTime) {
        String currentStateStr;
        long endTime;
        long timeElapsed;
        try { // If the puzzle becomes too complex to solve with this algorithm, throws OutOfMemory error
            currentStateStr = Arrays.deepToString(currentBoard.getCurrentState());
            if(cost >= STEP_CAP) throw new OutOfMemoryError();
        } catch(OutOfMemoryError e) {
            endTime = System.nanoTime();
            timeElapsed = endTime - startTime;
            System.err.println("Failed to solve the puzzle, too many steps required!");
            System.err.println("Stopped at step: " + cost);
            System.err.println("Time taken: " + timeElapsed / 1000000000 + "." + (timeElapsed / 1000000 - (timeElapsed / 1000000000) * 1000) + " seconds");
            return null;
        }
        return currentStateStr;
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
