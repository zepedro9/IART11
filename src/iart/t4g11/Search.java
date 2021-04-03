package iart.t4g11;

import java.util.*;

public class Search {
    public Search(SearchMethod searchMethod) {
        this.search_method = searchMethod;
    }

    enum Heuristic {
        A_STAR,
        GREEDY
    }

    private final SearchMethod search_method;

    private boolean hasCrashed = false;
    private int final_cost;
    private String final_time_str;
    private Board final_board;

    private static final int FILLED = 1;
    private static final int EMPTY = 0;
    public final int STEP_CAP = 2000000;

    public boolean hasCrashed() {
        return hasCrashed;
    }

    public int getFinalCost() {
        return final_cost;
    }

    public String getFinalTime() {
        return final_time_str;
    }

    public Board getFinalBoard() {
        return final_board;
    }

    public SearchMethod getSearchMethod() {
        return search_method;
    }

    // Function that given a board, will search for a solution using the Depth First Search method, returning the solution, the search cost to get to it through this method, and the time taken by it
    public void depth_first_search(Board board) {
        //System.out.println("Search algorithm: Depth First");
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
        //System.out.println("Solving...");

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
                    if(isEndCondition(nextBoard)) {
                        searchComplete = true;
                        board = nextBoard.duplicateBoard();
                        break;
                    } else if(isFailCondition(nextBoard)) {
                        if(!it.hasNext()) {
                            wrongNode = route.pop();
                            board = goBackNode(board, wrongNode);
                        }
                    } else {
                        route.push(currentNode);
                        board = nextBoard.duplicateBoard();
                        break;
                    }
                } else if(!it.hasNext()) {
                    wrongNode = route.pop();
                    board = goBackNode(board, wrongNode);
                }
            }
        }

        finishSearch(board, cost, startTime);
    }



    // Function that given a board, will search for a solution using the Breadth First Search Method, returning the final cost of the solution
    public void breadth_first_search(Board board) {
        //System.out.println("Search algorithm: Breadth First");
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
        //System.out.println("Solving...");

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

    // Function that given a board, will search for a solution using the Iterative Deepening Search Method, returning the final cost of the solution
    public void iterative_deepening(Board board) {
        //System.out.println("Search algorithm: Iterative Deepening");
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
        //System.out.println("Solving...");

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
                        if(isEndCondition(nextBoard)) {
                            searchComplete = true;
                            saveBoard = nextBoard.duplicateBoard();
                            break;
                        } else if(depth >= maxDepth) {
                            depth--;
                            if(!it.hasNext()) {
                                if(route.empty()) {
                                    partialSearchComplete = true;
                                    break;
                                }
                                wrongNode = route.pop();
                                board = goBackNode(board, wrongNode);
                                depth--;
                            }
                        } else if(isFailCondition(nextBoard)) {
                            if(!it.hasNext()) {
                                wrongNode = route.pop();
                                board = goBackNode(board, wrongNode);
                                depth--;
                            }
                        } else {
                            route.push(currentNode);
                            board = nextBoard.duplicateBoard();
                            break;
                        }
                    } else if(!it.hasNext()) {
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

    // Function that given a board, will search for a solution using the Greedy Search Method, returning the final cost of the solution
    public void greedy_search(Board board) {
        //System.out.println("Search algorithm: Greedy");
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
        //System.out.println("Solving...");

        while(!searchComplete) {
            nextNodes = sortNodesByBest(board, getNextNodes(board), Heuristic.GREEDY, route.size());
            Iterator<Pair> it = nextNodes.iterator();
            while(it.hasNext()) {
                cost++;
                currentNode = it.next();
                nextBoard = expandNode(board, currentNode);
                currentStateStr = testForOutOfMemory(cost, nextBoard, startTime);
                if (currentStateStr == null) return;
                if(!visitedBoards.contains(currentStateStr)) {
                    visitedBoards.add(currentStateStr);
                    if(isEndCondition(nextBoard)) {
                        searchComplete = true;
                        board = nextBoard.duplicateBoard();
                        break;
                    } else if(isFailCondition(nextBoard)) {
                        if(!it.hasNext()) {
                            wrongNode = route.pop();
                            board = goBackNode(board, wrongNode);
                        }
                    } else {
                        route.push(currentNode);
                        board = nextBoard.duplicateBoard();
                        break;
                    }
                } else if(!it.hasNext()) {
                    wrongNode = route.pop();
                    board = goBackNode(board, wrongNode);
                }
            }
        }

        finishSearch(board, cost, startTime);
    }

    // Function that given a board, will search for a solution using the A* Search Method, returning the final cost of the solution
    public void a_star_search(Board board) {
        //System.out.println("Search algorithm: A*");
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
        //System.out.println("Solving...");



        while(!searchComplete) {
            nextNodes = sortNodesByBest(board, getNextNodes(board), Heuristic.A_STAR, route.size());

            Iterator<Pair> it = nextNodes.iterator();
            while(it.hasNext()) {
                cost++;
                currentNode = it.next();
                nextBoard = expandNode(board, currentNode);

                currentStateStr = testForOutOfMemory(cost, nextBoard, startTime);
                if (currentStateStr == null) return;
                if(!visitedBoards.contains(currentStateStr)) {
                    visitedBoards.add(currentStateStr);
                    if(isEndCondition(nextBoard)) {
                        searchComplete = true;
                        board = nextBoard.duplicateBoard();
                        break;
                    } else if(isFailCondition(nextBoard)) {
                        if(!it.hasNext()) {
                            wrongNode = route.pop();
                            board = goBackNode(board, wrongNode);
                        }
                    } else {
                        route.push(currentNode);
                        board = nextBoard.duplicateBoard();
                        break;
                    }
                } else if(!it.hasNext()) {
                    wrongNode = route.pop();
                    board = goBackNode(board, wrongNode);
                }
            }
        }

        finishSearch(board, cost, startTime);
    }


    // Function that given the final results of a puzzle solver, display the information neatly in the console
    private void finishSearch(Board board, int cost, long startTime) {
        long endTime;
        long timeElapsed;
        endTime = System.nanoTime();
        timeElapsed = endTime - startTime;
        String timeElapsedStr = timeElapsed / 1000000000 + "." + (timeElapsed / 1000000 - (timeElapsed / 1000000000) * 1000) + " seconds";

        final_cost = cost;
        final_time_str = timeElapsedStr;
        final_board = board.duplicateBoard();

        //System.out.println("Solution to puzzle found with a search cost of: " + cost + " steps");
        //System.out.println("Time taken to perform search: " + timeElapsed / 1000000000 + "." + (timeElapsed / 1000000 - (timeElapsed / 1000000000) * 1000) + " seconds");
    }

    // Function that will test if the puzzle solver has been running for too long and is at risk of running out of memory, stopping it if true
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
            String timeElapsedStr = timeElapsed / 1000000000 + "." + (timeElapsed / 1000000 - (timeElapsed / 1000000000) * 1000) + " seconds";

            final_cost = cost;
            final_time_str = timeElapsedStr;
            final_board = currentBoard.duplicateBoard();
            hasCrashed = true;
            return null;
        }
        return currentStateStr;
    }

    // Function that sorts the nodes using the evaluation function related to the search method applied
    private ArrayList<Pair> sortNodesByBest(Board board, ArrayList<Pair> nextNodes, Heuristic searchType, int size) {
        ArrayList<Pair> auxSorted = new ArrayList<>();

        while(!nextNodes.isEmpty()) {
            Pair tmp = getBestNode(board, nextNodes, searchType, size);
            auxSorted.add(tmp);
            nextNodes.removeIf(node -> node.equals(tmp));
        }

        return auxSorted;
    }

    // Function that in a list of nodes, returns the best one, depending on the evaluation function related to the search method applied
    private Pair getBestNode(Board board, ArrayList<Pair> nextNodes, Heuristic searchType, int size) {
        Pair bestNode = null;
        int max = 2000, tmp;

        for(Pair node : nextNodes) {
            tmp = evaluationFunction(board, node, searchType,size);
            if(tmp < max) {
                max = tmp;
                bestNode = node;
            }
        }

        assert bestNode != null;
        return bestNode;
    }

    // Function that returns the number of spots that are going to be filled in the current action
    private int getFilledBlocks(Board oldBoard, Board newBoard){
        int lenght  = oldBoard.getLength();
        int oldboardfilled = 0, newboardfilled = 0;
        for(int r = 0; r < lenght; r++) {
            for(int c = 0; c < lenght; c++) {
                if(oldBoard.getValue(r,c) == 1){
                    oldboardfilled++;
                }
                if(newBoard.getValue(r,c) == 1){
                    newboardfilled++;
                }
            }
        }

        return newboardfilled - oldboardfilled;
    }

    // Function that returns the value of a node depending on the evaluation function related to the search method applied
    private int evaluationFunction(Board board, Pair node, Heuristic searchType, int size) {
        Board boardToEvaluate = expandNode(board, node);
        int filled = getFilledBlocks(board, boardToEvaluate);


        int evalF, evalH, evalG, Heu1;
        Pair distances = calculateDistances(boardToEvaluate,size);
        Heu1 = filled;
        evalH = distances.getB();
        evalG = distances.getA();

        switch (searchType) {
            case A_STAR -> evalF = evalH + Heu1;
            case GREEDY -> evalF = evalH + evalG;
            default -> {
                System.err.println("Invalid Heuristic Search requested!");
                System.exit(-1);
                return -1;
            }
        }
        return evalF;
    }

    // Function that calculates the distances from the Start and to the Goal
    private Pair calculateDistances(Board boardToEvaluate, int size) {
        int totalDistance = 0;
        for (int value : boardToEvaluate.getRowsRules()) {
            totalDistance += value;
        }
        totalDistance *= 2;

        int rulesMet = boardToEvaluate.rulesMet();
        return new Pair(size, totalDistance - rulesMet );

    }

    // Function that given a board and the node to expand, expands the board with the node and returns it
    private Board expandNode(Board board, Pair node) {
        Game aux = new Game();
        aux.setBoard(board.duplicateBoard());
        aux.fillPoolRow(node.getA(), node.getB());
        return aux.getBoard();
    }

    // Function that given a board and the node, returns the board previous to the expansion of that node
    private Board goBackNode(Board board, Pair node) {
        Game aux = new Game();
        aux.setBoard(board.duplicateBoard());
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
    public boolean isEndCondition(Board board) {
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
