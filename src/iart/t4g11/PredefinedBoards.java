package iart.t4g11;

import java.util.ArrayList;
import java.util.Random;

public class PredefinedBoards {

    public PredefinedBoards() {
        boards6x6.add(board6_1);
        boards6x6.add(board6_2);
        boards6x6.add(board6_3);
        boards6x6.add(board6_4);
        boards6x6.add(board6_5);
        boards6x6.add(board6_6);
        boards6x6.add(board6_7);
        boards6x6.add(board6_8);
        boards6x6.add(board6_9);
        boards6x6.add(board6_10);

        boards10x10.add(board10_5);
        boards10x10.add(board10_2);
        boards10x10.add(board10_3);
        boards10x10.add(board10_4);
        boards10x10.add(board10_5);
        boards10x10.add(board10_6);
    }

    // Function that given a puzzle size, returns a random board from the list of the boards of that size
    public Board getRandomBoard(PuzzleSize puzzleSize) {
        return switch (puzzleSize) {
            case SIX -> boards6x6.get(new Random().nextInt(boards6x6.size()));
            case TEN -> boards10x10.get(new Random().nextInt(boards10x10.size()));
            case FIFTEEN -> boards15x15.get(new Random().nextInt(boards15x15.size()));
            case TEST -> boardComplete;
        };
    }

    private final ArrayList<Board> boards6x6 = new ArrayList<>(), boards10x10 = new ArrayList<>(), boards15x15 = new ArrayList<>();

    private final Board boardComplete = new Board(
            "Jogo Teste Terminado",
            Difficulty.EASY,
            PuzzleSize.SIX,
            new int[] {3, 2, 5, 4, 3, 3},
            new int[] {4, 3, 2, 3, 4, 4},
            new int[][] {new int[] {1, 1, 1, 2, 2, 2}, new int[] {3, 3, 1, 1, 2, 2}, new int[] {3, 3, 1, 4, 4, 2}, new int[] {3, 1, 1, 1, 5, 5}, new int[] {3, 1, 1, 5, 5, 5}, new int[] {3, 5, 5, 5, 6, 6}},
            new int[][] {new int[] {0, 0, 0, 1, 1, 1}, new int[] {0, 0, 0, 0, 1, 1}, new int[] {1, 1, 0, 1, 1, 1}, new int[] {1, 1, 1, 1, 0, 0}, new int[] {1, 1, 1, 0, 0, 0}, new int[] {1, 0, 0, 0, 1, 1}});

    private final Board board6_1 = new Board(
            "Puzzle #1",
            Difficulty.EASY,
            PuzzleSize.SIX,
            new int[] {3, 2, 5, 4, 3, 3},
            new int[] {4, 3, 2, 3, 4, 4},
            new int[][] {new int[] {1, 1, 1, 2, 2, 2}, new int[] {3, 3, 1, 1, 2, 2}, new int[] {3, 3, 1, 4, 4, 2}, new int[] {3, 1, 1, 1, 5, 5}, new int[] {3, 1, 1, 5, 5, 5}, new int[] {3, 5, 5, 5, 6, 6}},
            new int[][] {new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}});

    private final Board board6_2 = new Board(
            "Puzzle #2",
            Difficulty.EASY,
            PuzzleSize.SIX,
            new int[] {2, 4, 4, 5, 4, 4},
            new int[] {4, 1, 3, 5, 5, 5},
            new int[][] {new int[] {1, 1, 2, 2, 3, 4}, new int[] {1, 1, 5, 5, 3, 4}, new int[] {1, 5, 5, 3, 3, 4}, new int[] {1, 5, 3, 3, 3, 4}, new int[] {1, 5, 5, 4, 4, 4}, new int[] {5, 5, 6, 6, 6, 4}},
            new int[][] {new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}});

    private final Board board6_3 = new Board(
            "Puzzle #3",
            Difficulty.NORMAL,
            PuzzleSize.SIX,
            new int[] {5, 3, 1, 4, 3, 4},
            new int[] {2, 2, 4, 4, 5, 3},
            new int[][] {new int[] {1, 2, 2, 3, 4, 4}, new int[] {1, 1, 3, 3, 3, 5}, new int[] {6, 1, 1, 5, 5, 5}, new int[] {6, 6, 1, 7, 5, 8}, new int[] {9, 9, 9, 7, 7, 8}, new int[] {9, 9, 7, 7, 7, 8}},
            new int[][] {new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}});

    private final Board board6_4 = new Board(
            "Puzzle #4",
            Difficulty.NORMAL,
            PuzzleSize.SIX,
            new int[] {1, 3, 5, 4, 2, 3},
            new int[] {5, 1, 3, 3, 4, 2},
            new int[][] {new int[] {1, 2, 2, 2, 2, 2}, new int[] {1, 3, 4, 4, 5, 5}, new int[] {3, 3, 4, 5, 5, 6}, new int[] {3, 7, 4, 8, 8, 6}, new int[] {7, 7, 7, 7, 6, 6}, new int[] {7, 9, 9, 9, 6, 6}},
            new int[][] {new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}});

    private final Board board6_5 = new Board(
            "Puzzle #5",
            Difficulty.EASY,
            PuzzleSize.SIX,
            new int[] {2, 4, 5, 5, 1, 5},
            new int[] {3, 5, 4, 3, 3, 4},
            new int[][] {new int[] {1, 1, 2, 2, 2, 2}, new int[] {2, 2, 2, 3, 3, 2}, new int[] {2, 2, 3, 3, 3, 4}, new int[] {5, 2, 2, 3, 3, 4}, new int[] {5, 5, 6, 6, 6, 4}, new int[] {5, 6, 6, 6, 6, 4}},
            new int[][] {new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}});

    private final Board board6_6 = new Board(
            "Puzzle #6",
            Difficulty.NORMAL,
            PuzzleSize.SIX,
            new int[] {5,  1,  5,  3,  4,  3},
            new int[] {4,  2,  2,  3,  5,  5},
            new int[][] {new int[] {1,  1,  1,  2,  3,  3}, new int[] {1,  4,  4,  2,  2,  2}, new int[] {4,  4,  5,  2,  6,  6}, new int[] {4,  5,  5,  7,  8,  8}, new int[] {5,  5,  7,  7,  8,  9}, new int[] {5,  5,  5,  7,  9,  9}},
            new int[][] {new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}});

    private final Board board6_7 = new Board(
            "Puzzle #7",
            Difficulty.NORMAL,
            PuzzleSize.SIX,
            new int[] {3,  3,  4,  3,  4,  5},
            new int[] {1,  5,  5,  5,  5,  1},
            new int[][] {new int[] {1,  1,  2,  3,  3,  3}, new int[] {4,  4,  2,  3,  3,  5}, new int[] {4,  6,  6,  6,  7,  5}, new int[] {4,  6,  8,  6,  7,  5}, new int[] {9,  6,  8,  6,  6,  5}, new int[] {9,  6,  6,  6,  6,  5}},
            new int[][] {new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}});

    private final Board board6_8 = new Board(
            "Puzzle #8",
            Difficulty.EASY,
            PuzzleSize.SIX,
            new int[] {4, 2, 1, 3, 4, 1},
            new int[] {3, 1, 2, 3, 3, 3},
            new int[][] {new int[] {1, 1, 2, 2, 2, 2}, new int[] {1, 1, 1, 1, 3, 3}, new int[] {4, 1, 1, 1, 1, 1}, new int[] {4, 1, 1, 5, 5, 1}, new int[] {4, 4, 4, 4, 1, 1}, new int[] {6, 6, 6, 6, 6, 1}},
            new int[][] {new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}});

    private final Board board6_9 = new Board(
            "Puzzle #9",
            Difficulty.NORMAL,
            PuzzleSize.SIX,
            new int[] {2, 4, 4, 4, 2, 4},
            new int[] {5, 5, 3, 1, 1, 5},
            new int[][] {new int[] {1, 1, 2, 2, 2, 2}, new int[] {1, 2, 2, 3, 3, 3}, new int[] {1, 2, 4, 5, 5, 3}, new int[] {6, 6, 4, 7, 7, 3}, new int[] {7, 6, 7, 7, 8, 9}, new int[] {7, 7, 7, 8, 8, 9}},
            new int[][] {new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}});

    private final Board board6_10 = new Board(
            "Puzzle #10",
            Difficulty.NORMAL,
            PuzzleSize.SIX,
            new int[] {4, 4, 5, 1, 3, 2},
            new int[] {4, 5, 4, 1, 3, 2},
            new int[][] {new int[] {1, 1, 2, 2, 3, 3}, new int[] {4, 2, 2, 5, 3, 3}, new int[] {4, 2, 2, 5, 5, 6}, new int[] {7, 7, 8, 6, 6, 6}, new int[] {7, 7, 8, 9, 6, 6}, new int[] {7, 7, 9, 9, 9, 9}},
            new int[][] {new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}});

    private final Board board10_1 = new Board(
            "Puzzle #1",
            Difficulty.EASY,
            PuzzleSize.TEN,
            new int[] {4, 1, 4, 6, 2, 1, 2, 2, 6, 4},
            new int[] {2, 2, 3, 3, 3, 6, 4, 3, 4, 2},
            new int[][] {new int[] {1, 2, 2, 2, 2, 3, 4, 5, 5, 5}, new int[] {1, 6, 6, 6, 7, 3, 4, 5, 4, 5}, new int[] {1, 6, 7, 6, 7, 3, 4, 4, 4, 5}, new int[] {1, 6, 7, 7, 7, 3, 3, 3, 8, 5}, new int[] {9, 6, 6, 6, 10, 3, 3, 8, 8, 5}, new int[] {9, 10, 10, 10, 10, 3, 11, 8, 8, 5}, new int[] {9, 9, 10, 10, 10, 10, 11, 8, 5, 5}, new int[] {9, 12, 10, 10, 11, 11, 11, 13, 13, 13}, new int[] {9, 12, 12, 12, 11, 11, 11, 11, 11, 13}, new int[] {12, 12, 14, 14, 14, 14, 14, 14, 13, 13}},
            new int[][] {new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}});

    private final Board board10_2 = new Board(
            "Puzzle #2",
            Difficulty.EASY,
            PuzzleSize.TEN,
            new int[] {3, 5, 5, 9, 8, 8, 3, 9, 5, 8},
            new int[] {7, 5, 6, 9, 8, 6, 6, 4, 6, 6},
            new int[][] {new int[] {1, 1, 1, 2, 2, 3, 3, 3, 3, 4}, new int[] {1, 1, 1, 2, 2, 2, 2, 3, 3, 4}, new int[] {1, 1, 1, 2, 3, 2, 2, 3, 4, 4}, new int[] {1, 5, 1, 2, 3, 3, 3, 3, 3, 3}, new int[] {1, 5, 5, 2, 6, 6, 6, 3, 6, 6}, new int[] {1, 1, 5, 2, 6, 7, 6, 6, 6, 7}, new int[] {5, 5, 5, 7, 7, 7, 7, 7, 7, 7}, new int[] {8, 9, 5, 5, 5, 10, 10, 7, 11, 11}, new int[] {8, 9, 9, 9, 9, 12, 12, 12, 12, 12}, new int[] {8, 9, 9, 9, 13, 13, 12, 14, 14, 12}},
            new int[][] {new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}});

    private final Board board10_3 = new Board(
            "Puzzle #3",
            Difficulty.EASY,
            PuzzleSize.TEN,
            new int[] {4, 3, 3, 3, 5, 1, 7, 3, 1, 2},
            new int[] {3, 3, 2, 1, 4, 3, 2, 5, 4, 5},
            new int[][] {new int[] {1, 1, 1, 1, 2, 2, 3, 3, 4, 4}, new int[] {5, 5, 5, 5, 2, 6, 6, 3, 7, 4}, new int[] {5, 5, 5, 5, 2, 6, 6, 6, 7, 7}, new int[] {5, 8, 8, 8, 9, 6, 6, 10, 7, 7}, new int[] {5, 8, 8, 8, 9, 9, 6, 10, 7, 7}, new int[] {5, 5, 5, 8, 8, 11, 6, 12, 13, 13}, new int[] {5, 5, 5, 8, 8, 11, 6, 12, 12, 12}, new int[] {8, 8, 8, 8, 8, 11, 12, 12, 14, 14}, new int[] {8, 8, 14, 8, 8, 11, 14, 14, 14, 14}, new int[] {8, 8, 14, 14, 14, 14, 14, 14, 14, 14}},
            new int[][] {new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}});

    private final Board board10_4 = new Board(
            "Puzzle #4",
            Difficulty.EASY,
            PuzzleSize.TEN,
            new int[] {5, 6, 7, 8, 8, 3, 6, 5, 8, 9},
            new int[] {5, 8, 9, 5, 6, 8, 7, 6, 7, 4},
            new int[][] {new int[] {1, 1, 2, 2, 2, 3, 3, 3, 3, 3}, new int[] {1, 1, 1, 2, 4, 4, 3, 3, 3, 5}, new int[] {1, 1, 1, 6, 6, 6, 7, 3, 5, 5}, new int[] {8, 1, 1, 9, 9, 9, 7, 3, 3, 5}, new int[] {8, 9, 9, 9, 9, 7, 7, 7, 3, 5}, new int[] {8, 8, 9, 10, 10, 7, 5, 5, 3, 5}, new int[] {10, 8, 9, 10, 11, 11, 5, 5, 5, 5}, new int[] {10, 10, 10, 10, 11, 12, 12, 12, 12, 5}, new int[] {10, 10, 10, 11, 11, 13, 12, 12, 12, 13}, new int[] {10, 14, 14, 11, 13, 13, 13, 13, 13, 13}},
            new int[][] {new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}});

    private final Board board10_5 = new Board(
            "Puzzle #5",
            Difficulty.EASY,
            PuzzleSize.TEN,
            new int[] {3, 1, 1, 1, 1, 3, 3, 2, 7, 8},
            new int[] {2, 4, 3, 3, 2, 2, 6, 4, 3, 1},
            new int[][] {new int[] {1, 2, 2, 2, 3, 3, 3, 3, 4, 4}, new int[] {1, 5, 5, 5, 5, 5, 3, 3, 6, 4}, new int[] {1, 1, 7, 5, 5, 6, 6, 3, 6, 6}, new int[] {7, 1, 7, 7, 5, 5, 6, 6, 6, 6}, new int[] {7, 7, 7, 8, 8, 5, 6, 9, 9, 9}, new int[] {7, 7, 8, 8, 8, 5, 6, 9, 9, 9}, new int[] {8, 8, 8, 10, 8, 10, 11, 11, 11, 9}, new int[] {8, 8, 10, 10, 10, 10, 11, 10, 11, 9}, new int[] {12, 8, 13, 13, 13, 10, 10, 10, 10, 9}, new int[] {12, 12, 13, 13, 14, 14, 14, 14, 9, 9}},
            new int[][] {new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}});

    private final Board board10_6 = new Board(
            "Puzzle #6",
            Difficulty.EASY,
            PuzzleSize.TEN,
            new int[] {5, 7, 6, 7, 9, 7, 4, 2, 6, 6},
            new int[] {4, 8, 7, 8, 7, 8, 5, 3, 4, 5},
            new int[][] {new int[] {1, 2, 2, 2, 2, 2, 3, 3, 3, 3}, new int[] {1, 1, 4, 5, 5, 2, 6, 6, 6, 3}, new int[] {4, 4, 4, 2, 2, 2, 6, 7, 6, 7}, new int[] {8, 8, 8, 8, 9, 2, 6, 7, 6, 7}, new int[] {8, 8, 10, 8, 9, 2, 6, 7, 6, 7}, new int[] {11, 10, 10, 8, 9, 9, 9, 7, 6, 7}, new int[] {11, 10, 10, 10, 10, 10, 7, 7, 7, 7}, new int[] {11, 12, 12, 13, 10, 10, 10, 10, 10, 14}, new int[] {11, 12, 12, 13, 13, 13, 13, 10, 10, 14}, new int[] {11, 12, 12, 12, 12, 12, 10, 10, 10, 14}},
            new int[][] {new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}});

    /*private final Board emptyBoard1 = new Board(
            "Puzzle ",
            Difficulty.NORMAL,
            PuzzleSize.SIX,
            new int[] {},
            new int[] {},
            new int[][] {new int[] {}, new int[] {}, new int[] {}, new int[] {}, new int[] {}, new int[] {}},
            new int[][] {new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}});

    private final Board emptyBoard2 = new Board(
            "Puzzle ",
            Difficulty.EASY,
            PuzzleSize.TEN,
            new int[] {},
            new int[] {},
            new int[][] {new int[] {}, new int[] {}, new int[] {}, new int[] {}, new int[] {}, new int[] {}, new int[] {}, new int[] {}, new int[] {}, new int[] {}},
            new int[][] {new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}});
    */
}