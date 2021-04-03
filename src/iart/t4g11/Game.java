package iart.t4g11;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;

enum GameStatus {
    ONGOING,
    GAME_WON;
}

public class Game {
    public static final int FILLED = 1;
    public static final int EMPTY = 0;

    private Board board;
    private Screen screen;
    private Search searchAI;
    private boolean isHuman;
    private SearchMethod searchMethod;
    private GameStatus gameStatus;
    private Terminal terminal;

    // Function called to start the execution of the main program
    public void run() throws Exception {
        UI ui = new UI();
        ui.awaitStart();

        try {
            terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(Settings.WIDTH(ui.getPuzzleSize()),Settings.HEIGHT(ui.getPuzzleSize()))).createTerminal();
            screen = new TerminalScreen(terminal);
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary
        } catch (IOException e) {
            e.printStackTrace();
        }

        board = new PredefinedBoards().getRandomBoard(ui.getPuzzleSize());
        searchMethod = ui.getSearchMethod();
        searchAI = new Search(searchMethod);
        isHuman = ui.isHuman();
        gameStatus = GameStatus.ONGOING;

        checkIfWon();
        while(gameStatus.equals(GameStatus.ONGOING)) {
            draw(false, false, searchAI);
            waitForAction(isHuman);
            checkIfWon();
        }
        draw(true, false, searchAI);
    }

    // Function that checks if the game has already hit a solution
    private void checkIfWon() {
        if(searchAI.isEndCondition(board)) {
            gameStatus = GameStatus.GAME_WON;
        }
    }

    // Function that awaits for a physical action by the user (click of a button) and evaluates what to do depending on it
    private void waitForAction(boolean isHuman) throws Exception {
        KeyStroke key = screen.readInput();
        if(isHuman) {
            switch (key.getKeyType()) {
                case ArrowUp -> board.moveSelected(Direction.UP);
                case ArrowDown -> board.moveSelected(Direction.DOWN);
                case ArrowLeft -> board.moveSelected(Direction.LEFT);
                case ArrowRight -> board.moveSelected(Direction.RIGHT);
                case Enter -> toggleFillPosition(board.getSelectedPiece());
                case EOF -> {
                    terminal.close();
                    System.exit(0);
                }
            }
        } else {
            if(key.getKeyType().equals(KeyType.Enter)) {
                draw(false, true, searchAI);
                switch (searchMethod) {
                    case DEPTH_FIRST -> {
                        searchAI.depth_first_search(board);
                    }
                    case BREADTH_FIRST -> {
                        searchAI.breadth_first_search(board);
                    }
                    case ITERATIVE_DEEPENING -> {
                        searchAI.iterative_deepening(board);
                    }
                    case GREEDY -> {
                        searchAI.greedy_search(board);
                    }
                    case A_STAR -> {
                        searchAI.a_star_search(board);
                    }
                }
                board = searchAI.getFinalBoard();
                draw(true, false, searchAI);
                if(searchAI.hasCrashed()) gameStatus = GameStatus.GAME_WON;
            }
        }
    }

    // Function that fills or empties a selected piece on the board (and all pieces next to it or below in the same pool)
    private void toggleFillPosition(Pair selectedPiece) {
        int r = selectedPiece.getA();
        int c = selectedPiece.getB();
        if(board.getValue(r, c) == Board.EMPTY) this.fillPoolRow(r, board.getPoolOfPosition(r, c));
        else this.emptyPoolRow(r, getBoard().getPoolOfPosition(r, c));
    }

    private void draw(boolean isGameWon, boolean isSolving, Search searchAI) throws Exception {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        board.draw(graphics, isHuman, isGameWon, isSolving, searchAI);
        screen.refresh();
    }

    // Function that returns the board
    public Board getBoard() {
        return board;
    }

    // Function that sets the board of the game
    public void setBoard(Board board) {
        this.board = board;
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
        ArrayList<Pair> positions = board.getPoolPositions(pool);

        for (Pair pos : positions) {
            if(pos.getA() == row) board.fillPosition(pos.getA(), pos.getB());
        }
    }

    // Function that empties a position on the board and also all the positions next to it and below, in the same pool
    public void emptyPoolRow(int row, int pool) {
        for(int r = row; r >= 0; r--) {
            emptyPoolRowAux(r, pool);
        }
    }

    // Auxiliary function used by emptyPoolRow() that fills a single row (r) of the a pool (pool)
    public void emptyPoolRowAux(int row, int pool) {
        ArrayList<Pair> positions = board.getPoolPositions(pool);

        for (Pair pos : positions) {
            if(pos.getA() == row) board.emptyPosition(pos.getA(), pos.getB());
        }
    }
}
