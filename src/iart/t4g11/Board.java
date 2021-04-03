package iart.t4g11;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

enum Difficulty {
    EASY,
    NORMAL,
    HARD
}

enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT
}

public class Board implements Serializable {
    public static final int FILLED = 1;
    public static final int EMPTY = 0;

    private final Difficulty difficulty;
    private final PuzzleSize puzzleSize;
    private final int[] rowsRules;
    private final int[] colsRules;
    private final int[][] pools;
    private final int[][] currentState;

    private final String name;
    private final int length;
    private final int poolsNum;

    // Function that returns the piece that is currently selected by the player
    public Pair getSelectedPiece() {
        return selectedPiece;
    }

    private Pair selectedPiece;

    public Board(String name, Difficulty difficulty, PuzzleSize puzzleSize, int[] rows, int[] cols, int[][] pools, int[][] currentState) {
        this.name = name;
        this.difficulty = difficulty;
        this.puzzleSize = puzzleSize;
        this.rowsRules = rows;
        this.colsRules = cols;
        this.pools = pools;
        this.currentState = currentState;
        this.length = rows.length;
        this.poolsNum = calculatePoolsNum();
        this.selectedPiece = new Pair(0, 0);
    }

    // Function that generates a clone of a board and returns it
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
            e.printStackTrace();
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

    // Function that returns the difficulty of the board
    public Difficulty getDifficulty() {
        return difficulty;
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

    // Function that return how many of the restrictions of the board have already been met
    public int rulesMet() {
        int rulesMet = 0, totalr = 0, totalc = 0;

        for(int r = 0; r < length; r++) {
            for(int c = 0; c < length; c++) {
                if(totalr != getRowsRules()[r]) {
                    totalr += getValue(r, c);
                }
                if(totalc != getColsRules()[r]) {
                    totalc += getValue(c, r);
                }
            }
            rulesMet += totalr;
            rulesMet += totalc;
            totalr = 0;
            totalc = 0;
        }

        return rulesMet;
    }

    // Function that returns the color of a column restriction, depending on if the restriction is being broken or not, or if it has already been met
    private String getColorForCol(int col) {
        int total = 0;
        for(int r = 0; r < length; r++) {
            total += getValue(r, col);
        }
        if(total > getColsRules()[col]) return Color.WRONG;
        else if(total == getColsRules()[col]) return Color.RIGHT;
        else return Color.TEXT;
    }

    // Function that returns the color of a row restriction, depending on if the restriction is being broken or not, or if it has already been met
    private String getColorForRow(int row) {
        int total = 0;
        for(int c = 0; c < length; c++) {
            total += getValue(row, c);
        }
        if(total > getRowsRules()[row]) return Color.WRONG;
        else if(total == getRowsRules()[row]) return Color.RIGHT;
        else return Color.TEXT;
    }

    // Function that executes the move of the selected piece
    public void moveSelected(Direction direction) {
        switch (direction) {
            case UP -> {
                if(selectedPiece.getA() > 0) selectedPiece.setA(selectedPiece.getA() - 1);
            }
            case DOWN -> {
                if(selectedPiece.getA() < length - 1) selectedPiece.setA(selectedPiece.getA() + 1);
            }
            case LEFT -> {
                if(selectedPiece.getB() > 0) selectedPiece.setB(selectedPiece.getB() - 1);
            }
            case RIGHT -> {
                if(selectedPiece.getB() < length - 1) selectedPiece.setB(selectedPiece.getB() + 1);
            }
        }
    }

    public void draw(TextGraphics graphics, boolean isHuman, boolean isGameWon, boolean isSolving, Search searchAI) {
        drawBackground(graphics);
        drawInfo(graphics, isHuman, isGameWon, isSolving, searchAI);
        drawRestrictions(graphics);
        drawBoard(graphics, isGameWon, isHuman);
    }

    private void drawBackground(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString(Color.BG));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Settings.WIDTH(puzzleSize), Settings.HEIGHT(puzzleSize)), ' ');
    }

    private void drawInfo(TextGraphics graphics, boolean isHuman, boolean isGameWon, boolean isSolving, Search searchAI) {
        if(isHuman) {
            if(isGameWon) {
                graphics.setForegroundColor(TextColor.Factory.fromString(Color.TEXT));
                graphics.putString(Settings.WIDTH(puzzleSize)/2 - 5, 1, "Aquarium");
                graphics.putString(Settings.WIDTH(puzzleSize)/2 - 8, 2, name + " (" + puzzleSize.toString() + ")");
                graphics.setForegroundColor(TextColor.Factory.fromString(Color.RIGHT));
                graphics.putString(Settings.WIDTH(puzzleSize)/2 - 13, 4, "Congratulations, you won!");
            } else {
                graphics.setForegroundColor(TextColor.Factory.fromString(Color.TEXT));
                graphics.putString(Settings.WIDTH(puzzleSize)/2 - 5, 1, "Aquarium");
                graphics.putString(Settings.WIDTH(puzzleSize)/2 - 8, 2, name + " (" + puzzleSize.toString() + ")");
                graphics.putString(Settings.WIDTH(puzzleSize)/2 - 9, 3, "Player type: Human");
            }
        } else {
            if(isGameWon) {
                graphics.setForegroundColor(TextColor.Factory.fromString(Color.TEXT));
                graphics.putString(Settings.WIDTH(puzzleSize)/2 - 5, 1, "Aquarium");
                graphics.putString(Settings.WIDTH(puzzleSize)/2 - 8, 2, name + " (" + puzzleSize.toString() + ")");
                if(searchAI.hasCrashed()) {
                    graphics.setForegroundColor(TextColor.Factory.fromString(Color.WRONG));
                    graphics.putString(Settings.WIDTH(puzzleSize)/2 - 30, 3, "Failed to solve the puzzle, hit maximum steps set at: " + searchAI.STEP_CAP);
                    graphics.setForegroundColor(TextColor.Factory.fromString(Color.TEXT));
                    graphics.putString(Settings.WIDTH(puzzleSize)/2 - 8, 4, "Steps taken: " + searchAI.getFinalCost());
                    graphics.putString(Settings.WIDTH(puzzleSize)/2 - 12, 5, "Time spent: " + searchAI.getFinalTime());
                } else {
                    graphics.putString(Settings.WIDTH(puzzleSize)/2 - 8, 3, "Steps taken: " + searchAI.getFinalCost());
                    graphics.putString(Settings.WIDTH(puzzleSize)/2 - 12, 4, "Time spent: " + searchAI.getFinalTime());
                }
            } else {
                graphics.setForegroundColor(TextColor.Factory.fromString(Color.TEXT));
                graphics.putString(Settings.WIDTH(puzzleSize)/2 - 5, 1, "Aquarium");
                graphics.putString(Settings.WIDTH(puzzleSize)/2 - 8, 2, name + " (" + puzzleSize.toString() + ")");
                graphics.putString(Settings.WIDTH(puzzleSize)/2 - 17, 3, "Search method: " + searchAI.getSearchMethod().toString());
                if(isSolving) graphics.putString(Settings.WIDTH(puzzleSize)/2 - 6, 4, "Solving...");
                else graphics.putString(Settings.WIDTH(puzzleSize)/2 - 17, 4, "Press \"ENTER\" to start solving");
            }
        }
    }

    private void drawRestrictions(TextGraphics graphics) {
        int size = 7, spacing = 0, i = 0;
        for(int rule : this.getColsRules()) { // COLUMN RESTRICTIONS
            graphics.setForegroundColor(TextColor.Factory.fromString(getColorForCol(i)));
            graphics.putString(Settings.INDENT_BOARD_LEFT + spacing + 2, Settings.INDENT_BOARD_TOP - 2, rule + "");
            spacing += size;
            i++;
        }
        size = 4;
        spacing = i = 0;
        for(int rule : this.getRowsRules()) { // ROW RESTRICTIONS
            graphics.setForegroundColor(TextColor.Factory.fromString(getColorForRow(i)));
            graphics.putString(Settings.INDENT_BOARD_LEFT - 3, Settings.INDENT_BOARD_TOP + spacing + 1, rule + "");
            spacing += size;
            i++;
        }
    }

    public void drawBoard(TextGraphics graphics, boolean isGameWon, boolean isHuman) {
        drawOuterBorder(graphics);
        drawInnerBorder(graphics);
        drawPoolValues(graphics, isGameWon, isHuman);
        drawPoolBorders(graphics);
    }

    private void drawOuterBorder(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString(Color.TANK_LINE));
        graphics.fillRectangle(new TerminalPosition(Settings.INDENT_BOARD_LEFT - 1, Settings.INDENT_BOARD_TOP - 1), new TerminalSize(length * (Settings.SQUARE_WIDTH + 1) + 1, 1), '█'); // TOP BORDER
        graphics.fillRectangle(new TerminalPosition(Settings.INDENT_BOARD_LEFT - 1, Settings.INDENT_BOARD_TOP + length * (Settings.SQUARE_HEIGHT + 1) - 1), new TerminalSize(length * (Settings.SQUARE_WIDTH + 1) + 1, 1), '█'); // BOTTOM BORDER
        graphics.fillRectangle(new TerminalPosition(Settings.INDENT_BOARD_LEFT - 1, Settings.INDENT_BOARD_TOP - 1), new TerminalSize(1, length * (Settings.SQUARE_HEIGHT + 1)), '█'); // LEFT BORDER
        graphics.fillRectangle(new TerminalPosition(Settings.INDENT_BOARD_LEFT + length * (Settings.SQUARE_WIDTH + 1) - 1, Settings.INDENT_BOARD_TOP - 1), new TerminalSize(1, length * (Settings.SQUARE_HEIGHT + 1)), '█'); // RIGHT BORDER
    }

    private void drawInnerBorder(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString(Color.NORMAL_LINE));
        for(int c = 0; c < length - 1; c++) { // INNER ROWS
            graphics.fillRectangle(new TerminalPosition(Settings.INDENT_BOARD_LEFT, Settings.INDENT_BOARD_TOP + (c + 1) * Settings.SQUARE_HEIGHT + c), new TerminalSize(length * (Settings.SQUARE_WIDTH + 1) - 1, 1), '█');
        }
        for(int r = 0; r < length - 1; r++) { // INNER COLUMNS
            graphics.fillRectangle(new TerminalPosition(Settings.INDENT_BOARD_LEFT + (r + 1) * Settings.SQUARE_WIDTH + r, Settings.INDENT_BOARD_TOP), new TerminalSize(1, length * (Settings.SQUARE_HEIGHT + 1) - 1), '█');
        }
    }

    private void drawPoolBorders(TextGraphics graphics) {
        int pool;
        for(int r = 0; r < length; r++) {
            for(int c = 0; c < length; c++) {
                pool = getPoolOfPosition(r, c);
                if(r > 0)
                    if(getPoolOfPosition(r - 1, c) != pool)
                        drawTopTankLine(graphics, new Pair(c, r));
                if(r < length - 1)
                    if(getPoolOfPosition(r + 1, c) != pool)
                        drawBottomTankLine(graphics, new Pair(c, r));
                if(c > 0)
                    if(getPoolOfPosition(r, c - 1) != pool)
                        drawLeftTankLine(graphics, new Pair(c, r));
                if(c < length - 1)
                    if(getPoolOfPosition(r, c + 1) != pool)
                        drawRightTankLine(graphics, new Pair(c, r));
            }
        }
    }

    private void drawTopTankLine(TextGraphics graphics, Pair position) {
        graphics.setForegroundColor(TextColor.Factory.fromString(Color.TANK_LINE));
        graphics.fillRectangle(new TerminalPosition(Settings.INDENT_BOARD_LEFT - 1 + (position.getA() * Settings.SQUARE_WIDTH) + position.getA(), Settings.INDENT_BOARD_TOP - 1 + (position.getB() * Settings.SQUARE_HEIGHT) + position.getB()), new TerminalSize(Settings.SQUARE_WIDTH + 2, 1), '█');
    }

    private void drawBottomTankLine(TextGraphics graphics, Pair position) {
        graphics.setForegroundColor(TextColor.Factory.fromString(Color.TANK_LINE));
        graphics.fillRectangle(new TerminalPosition(Settings.INDENT_BOARD_LEFT - 1 + (position.getA() * Settings.SQUARE_WIDTH) + position.getA(), Settings.INDENT_BOARD_TOP + ((position.getB() + 1) * Settings.SQUARE_HEIGHT) + position.getB()), new TerminalSize(Settings.SQUARE_WIDTH + 2, 1), '█');
    }

    private void drawLeftTankLine(TextGraphics graphics, Pair position) {
        graphics.setForegroundColor(TextColor.Factory.fromString(Color.TANK_LINE));
        graphics.fillRectangle(new TerminalPosition(Settings.INDENT_BOARD_LEFT - 1 + (position.getA() * Settings.SQUARE_WIDTH) + position.getA(), Settings.INDENT_BOARD_TOP - 1 + (position.getB() * Settings.SQUARE_HEIGHT) + position.getB()), new TerminalSize(1, Settings.SQUARE_HEIGHT + 2), '█');
    }

    private void drawRightTankLine(TextGraphics graphics, Pair position) {
        graphics.setForegroundColor(TextColor.Factory.fromString(Color.TANK_LINE));
        graphics.fillRectangle(new TerminalPosition(Settings.INDENT_BOARD_LEFT + ((position.getA() + 1) * Settings.SQUARE_WIDTH) + position.getA(), Settings.INDENT_BOARD_TOP - 1 + (position.getB() * Settings.SQUARE_HEIGHT) + position.getB()), new TerminalSize(1, Settings.SQUARE_HEIGHT + 2), '█');
    }

    private void drawPoolValues(TextGraphics graphics, boolean isGameWon, boolean isHuman) {
        int spacing_w = 0, spacing_h = 0;
        for(int r = 0; r < length; r++) { // ACTUAL SQUARES
            for(int c = 0; c < length; c++) {
                String color;
                if(!isGameWon && isHuman && (selectedPiece.getA() == r && selectedPiece.getB() == c)) color = Color.SELECTED;
                else if(getValue(r, c) == FILLED) color = Color.FILLED;
                else color = Color.EMPTY;
                graphics.setForegroundColor(TextColor.Factory.fromString(color));
                graphics.fillRectangle(new TerminalPosition(c + Settings.INDENT_BOARD_LEFT + spacing_w, r + Settings.INDENT_BOARD_TOP + spacing_h), new TerminalSize(Settings.SQUARE_WIDTH, Settings.SQUARE_HEIGHT), '█');
                spacing_w += Settings.SQUARE_WIDTH;
            }
            spacing_w = 0;
            spacing_h += Settings.SQUARE_HEIGHT;
        }
    }
}
