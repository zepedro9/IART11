package iart.t4g11;

public class PredefinedBoards {

    // Function that given a board number (boardNum) return the respective board. Giving an unregistered value (or zero) return the default, already finished, board
    public Board getBoard(int boardNum) {
        return switch (boardNum) {
            case 1 -> board1;
            case 2 -> board2;
            case 3 -> board3;
            case 4 -> board4;
            case 5 -> board5;
            case 6 -> board6;
            case 7 -> board7;
            case 8 -> board8;
            default -> boardComplete;
        };
    }

    private final Board boardComplete = new Board(
            "Jogo Terminado (6x6)",
            Difficulty.EASY,
            new int[] {3, 2, 5, 4, 3, 3},
            new int[] {4, 3, 2, 3, 4, 4},
            new int[][] {new int[] {1, 1, 1, 2, 2, 2}, new int[] {3, 3, 1, 1, 2, 2}, new int[] {3, 3, 1, 4, 4, 2}, new int[] {3, 1, 1, 1, 5, 5}, new int[] {3, 1, 1, 5, 5, 5}, new int[] {3, 5, 5, 5, 6, 6}},
            new int[][] {new int[] {0, 0, 0, 1, 1, 1}, new int[] {0, 0, 0, 0, 1, 1}, new int[] {1, 1, 0, 1, 1, 1}, new int[] {1, 1, 1, 1, 0, 0}, new int[] {1, 1, 1, 0, 0, 0}, new int[] {1, 0, 0, 0, 1, 1}});

    private final Board board1 = new Board(
            "Puzzle 1 Easy (6x6)",
            Difficulty.EASY,
            new int[] {3, 2, 5, 4, 3, 3},
            new int[] {4, 3, 2, 3, 4, 4},
            new int[][] {new int[] {1, 1, 1, 2, 2, 2}, new int[] {3, 3, 1, 1, 2, 2}, new int[] {3, 3, 1, 4, 4, 2}, new int[] {3, 1, 1, 1, 5, 5}, new int[] {3, 1, 1, 5, 5, 5}, new int[] {3, 5, 5, 5, 6, 6}},
            new int[][] {new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}});

    private final Board board2 = new Board(
            "Puzzle 2 Easy (6x6)",
            Difficulty.EASY,
            new int[] {2, 4, 4, 5, 4, 4},
            new int[] {4, 1, 3, 5, 5, 5},
            new int[][] {new int[] {1, 1, 2, 2, 3, 4}, new int[] {1, 1, 5, 5, 3, 4}, new int[] {1, 5, 5, 3, 3, 4}, new int[] {1, 5, 3, 3, 3, 4}, new int[] {1, 5, 5, 4, 4, 4}, new int[] {5, 5, 6, 6, 6, 4}},
            new int[][] {new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}});

    private final Board board3 = new Board(
            "Puzzle 3 Normal (6x6)",
            Difficulty.NORMAL,
            new int[] {5, 3, 1, 4, 3, 4},
            new int[] {2, 2, 4, 4, 5, 3},
            new int[][] {new int[] {1, 2, 2, 3, 4, 4}, new int[] {1, 1, 3, 3, 3, 5}, new int[] {6, 1, 1, 5, 5, 5}, new int[] {6, 6, 1, 7, 5, 8}, new int[] {9, 9, 9, 7, 7, 8}, new int[] {9, 9, 7, 7, 7, 8}},
            new int[][] {new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}});

    private final Board board4 = new Board(
            "Puzzle 4 Normal (6x6)",
            Difficulty.NORMAL,
            new int[] {1, 3, 5, 4, 2, 3},
            new int[] {5, 1, 3, 3, 4, 2},
            new int[][] {new int[] {1, 2, 2, 2, 2, 2}, new int[] {1, 3, 4, 4, 5, 5}, new int[] {3, 3, 4, 5, 5, 6}, new int[] {3, 7, 4, 8, 8, 6}, new int[] {7, 7, 7, 7, 6, 6}, new int[] {7, 9, 9, 9, 6, 6}},
            new int[][] {new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}});

    private final Board board5 = new Board(
            "Puzzle 5 Hard (6x6)",
            Difficulty.HARD,
            new int[] {2, 4, 3, 3, 3, 2},
            new int[] {4, 5, 1, 1, 4, 2},
            new int[][] {new int[] {1, 2, 3, 3, 4, 5}, new int[] {1, 6, 6, 7, 7, 5}, new int[] {8, 9, 10, 10, 11, 12}, new int[] {13, 14, 15, 16, 11, 12}, new int[] {13, 14, 15, 16, 16, 16}, new int[] {17, 17, 18, 18, 16, 16}},
            new int[][] {new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}});

    private final Board board6 = new Board(
            "Puzzle 6 Hard (6x6)",
            Difficulty.HARD,
            new int[] {5, 4, 4, 5, 1, 3},
            new int[] {4, 5, 1, 4, 5, 3},
            new int[][] {new int[] {1, 1, 2, 2, 2, 3}, new int[] {4, 4, 5, 5, 3, 3}, new int[] {6, 7, 7, 8, 9, 9}, new int[] {6, 10, 11, 8, 12, 12}, new int[] {13, 14, 15, 16, 16, 17}, new int[] {13, 14, 15, 16, 18, 17}},
            new int[][] {new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}});

    private final Board board7 = new Board(
            "Puzzle 7 Easy (10x10)",
            Difficulty.EASY,
            new int[] {4, 1, 4, 6, 2, 1, 2, 2, 6, 4},
            new int[] {2, 2, 3, 3, 3, 6, 4, 3, 4, 2},
            new int[][] {new int[] {1, 2, 2, 2, 2, 3, 4, 5, 5, 5}, new int[] {1, 6, 6, 6, 7, 3, 4, 5, 4, 5}, new int[] {1, 6, 7, 6, 7, 3, 4, 4, 4, 5}, new int[] {1, 6, 7, 7, 7, 3, 3, 3, 8, 5}, new int[] {9, 6, 6, 6, 10, 3, 3, 8, 8, 5}, new int[] {9, 10, 10, 10, 10, 3, 11, 8, 8, 5}, new int[] {9, 9, 10, 10, 10, 10, 11, 8, 5, 5}, new int[] {9, 12, 10, 10, 11, 11, 11, 13, 13, 13}, new int[] {9, 12, 12, 12, 11, 11, 11, 11, 11, 13}, new int[] {12, 12, 14, 14, 14, 14, 14, 14, 13, 13}},
            new int[][] {new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}});

    private final Board board8 = new Board( // Crashes due to space problems on depth-first search
            "Puzzle 8 Hard (10x10)",
            Difficulty.EASY,
            new int[] {3, 2, 2, 2, 1, 1, 1, 4, 8, 4},
            new int[] {1, 2, 3, 4, 3, 4, 4, 2, 1, 4},
            new int[][] {new int[] {1, 2, 2, 2, 3, 3, 4, 5, 6, 6}, new int[] {1, 7, 8, 8, 3, 3, 4, 5, 5, 9}, new int[] {1, 7, 7, 7, 3, 3, 10, 10, 5, 5}, new int[] {11, 12, 12, 13, 14, 14, 15, 15, 16, 17}, new int[] {11, 18, 19, 13, 20, 20, 20, 15, 16, 17}, new int[] {21, 18, 19, 19, 19, 19, 20, 15, 22, 22}, new int[] {21, 18, 23, 23, 24, 25, 22, 15, 22, 22}, new int[] {21, 26, 26, 23, 24, 25, 22, 15, 22, 27}, new int[] {28, 29, 30, 30, 31, 31, 22, 22, 22, 27}, new int[] {28, 29, 29, 29, 29, 32, 32, 33, 33, 27}},
            new int[][] {new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}});

    private final Board board9 = new Board( // Crashes due to space problems on depth-first search
            "Puzzle 9 Hard (15x15)",
            Difficulty.HARD,
            new int[] {4, 4, 4, 2, 2, 7, 6, 6, 7, 10, 5, 3, 7, 8, 14},
            new int[] {3, 2, 8, 8, 6, 5, 7, 9, 8, 5, 4, 7, 7, 4, 6},
            new int[][] {new int[] {1, 2, 2, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5}, new int[] {1, 6, 7, 3, 8, 9, 10, 10, 10, 11, 5, 5, 5, 12, 12}, new int[] {1, 6, 7, 8, 8, 9, 10, 9, 11, 11, 5, 5, 12, 12, 12}, new int[] {1, 6, 13, 8, 8, 9, 9, 9, 5, 5, 5, 5, 14, 12, 15}, new int[] {1, 6, 13, 16, 16, 16, 17, 9, 18, 5, 19, 5, 14, 12, 15}, new int[] {1, 6, 6, 6, 16, 17, 17, 9, 18, 18, 19, 15, 15, 15, 15}, new int[] {1, 6, 6, 6, 20, 17, 17, 21, 21, 18, 19, 22, 22, 22, 22}, new int[] {23, 23, 20, 20, 20, 24, 21, 21, 21, 19, 19, 19, 19, 22, 22}, new int[] {23, 25, 25, 25, 24, 24, 21, 21, 21, 21, 26, 26, 27, 27, 22}, new int[] {23, 28, 28, 25, 29, 30, 30, 30, 31, 31, 32, 26, 27, 33, 22}, new int[] {23, 23, 23, 29, 29, 29, 29, 34, 34, 35, 32, 27, 27, 33, 33}, new int[] {23, 36, 36, 29, 29, 37, 37, 35, 35, 35, 35, 27, 33, 33, 38}, new int[] {39, 39, 36, 29, 40, 37, 40, 40, 41, 35, 41, 27, 27, 38, 38}, new int[] {42, 39, 36, 36, 40, 40, 40, 43, 41, 35, 41, 27, 44, 44, 38}, new int[] {42, 39, 40, 40, 40, 40, 40, 43, 41, 41, 41, 45, 45, 44, 44}},
            new int[][] {new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}});

    private final Board emptyBoard1 = new Board(
            "Puzzle  (6x6)",
            Difficulty.NORMAL,
            new int[] {},
            new int[] {},
            new int[][] {new int[] {}, new int[] {}, new int[] {}, new int[] {}, new int[] {}, new int[] {}},
            new int[][] {new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}});

    private final Board emptyBoard2 = new Board(
            "Puzzle  (10x10)",
            Difficulty.EASY,
            new int[] {},
            new int[] {},
            new int[][] {new int[] {}, new int[] {}, new int[] {}, new int[] {}, new int[] {}, new int[] {}, new int[] {}, new int[] {}, new int[] {}, new int[] {}},
            new int[][] {new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}});

}