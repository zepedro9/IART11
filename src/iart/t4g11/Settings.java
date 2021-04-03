package iart.t4g11;

public class Settings {
    static final int INDENT_BOARD_LEFT = 6;
    static final int INDENT_BOARD_TOP = 9;
    static final int SQUARE_HEIGHT = 3; //Size of the pool squares
    static final int SQUARE_WIDTH = SQUARE_HEIGHT * 2; //Size of the pool squares

    public static int WIDTH(PuzzleSize puzzleSize) {
        return switch (puzzleSize) {
            case TEST, SIX -> 54;
            case TEN -> 80;
            case FIFTEEN -> 120;
        };
    }

    public static int HEIGHT(PuzzleSize puzzleSize) {
        return switch (puzzleSize) {
            case TEST, SIX -> 34;
            case TEN -> 50;
            case FIFTEEN -> 80;
        };
    }
}
