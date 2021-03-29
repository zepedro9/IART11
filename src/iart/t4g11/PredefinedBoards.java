package iart.t4g11;

public class PredefinedBoards {

    // Function that given a board number (boardNum) return the respective board. Giving an unregistered value (or zero) return the default, already finished, board
    public Board getBoard(int boardNum) {
        return switch (boardNum) {
            case 1 -> board1;
            case 2 -> board2;
            case 3 -> board3;
            case 4 -> board4;
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
            "Puzzle 4 Hard (6x6)",
            Difficulty.HARD,
            new int[] {2, 4, 3, 3, 3, 2},
            new int[] {4, 5, 1, 1, 4, 2},
            new int[][] {new int[] {1, 2, 3, 3, 4, 5}, new int[] {1, 6, 6, 7, 7, 5}, new int[] {8, 9, 10, 10, 11, 12}, new int[] {13, 14, 15, 16, 11, 12}, new int[] {13, 14, 15, 16, 16, 16}, new int[] {17, 17, 18, 18, 16, 16}},
            new int[][] {new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}});

    private final Board emptyBoard = new Board(
            "Puzzle  (6x6)",
            Difficulty.NORMAL,
            new int[] {},
            new int[] {},
            new int[][] {new int[] {}, new int[] {}, new int[] {}, new int[] {}, new int[] {}, new int[] {}},
            new int[][] {new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}});

}