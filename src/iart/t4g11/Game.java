package iart.t4g11;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;

public class Game {
    public static final int FILLED = 1;
    public static final int EMPTY = 0;

    private final Board board;
    private Screen screen;
    private UI ui;

    public Game(Board board) {
        this.board = board;
    }

    public void draw() throws Exception {
        ui = new UI();
        ui.awaitStart();

        try {
            Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(Settings.WIDTH,Settings.HEIGHT)).createTerminal();
            screen = new TerminalScreen(terminal);
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary

        } catch (IOException e) {
            e.printStackTrace();
        }

        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        ui.draw(graphics);
        screen.refresh();
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
