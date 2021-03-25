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
            new int[] {3, 2, 5, 4, 3, 3},
            new int[] {4, 3, 2, 3, 4, 4},
            new int[][] {new int[] {1, 1, 1, 2, 2, 2}, new int[] {3, 3, 1, 1, 2, 2}, new int[] {3, 3, 1, 4, 4, 2}, new int[] {3, 1, 1, 1, 5, 5}, new int[] {3, 1, 1, 5, 5, 5}, new int[] {3, 5, 5, 5, 6, 6}},
            new int[][] {new int[] {0, 0, 0, 1, 1, 1}, new int[] {0, 0, 0, 0, 1, 1}, new int[] {1, 1, 0, 1, 1, 1}, new int[] {1, 1, 1, 1, 0, 0}, new int[] {1, 1, 1, 0, 0, 0}, new int[] {1, 0, 0, 0, 1, 1}});

    private final Board board1 = new Board(
            "Puzzle 1 (6x6)",
            new int[] {3, 2, 4, 5, 4, 2},
            new int[] {5, 1, 2, 4, 3, 3},
            new int[][] {new int[] {1, 2, 2, 2, 2, 2}, new int[] {1, 3, 4, 4, 4, 2}, new int[] {1, 3, 3, 4, 5, 5}, new int[] {1, 3, 4, 4, 6, 5}, new int[] {3, 3, 4, 6, 6, 5}, new int[] {3, 4, 4, 6, 6, 5}},
            new int[][] {new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}});

    private final Board board2 = new Board(
            "Puzzle 2 (6x6)",
            new int[] {4, 3, 3, 1, 1, 3},
            new int[] {1, 2, 3, 3, 5, 1},
            new int[][] {new int[] {}, new int[] {}, new int[] {}, new int[] {}, new int[] {}, new int[] {}},
            new int[][] {new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}});

    private final Board board3 = new Board(
            "Puzzle 3 (6x6)",
            new int[] {4, 3, 3, 1, 1, 3},
            new int[] {1, 2, 3, 3, 5, 1},
            new int[][] {new int[] {1, 1, 1, 2, 2, 2}, new int[] {3, 3, 4, 2, 2, 2}, new int[] {4, 4, 4, 5, 5, 2}, new int[] {6, 7, 7, 8, 8, 9}, new int[] {6, 6, 7, 7, 7, 9}, new int[] {6, 6, 7, 9, 9, 9}},
            new int[][] {new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}});

    private final Board board4 = new Board(
            "Puzzle 4 (6x6)",
            new int[] {4, 3, 3, 1, 1, 3},
            new int[] {1, 2, 3, 3, 5, 1},
            new int[][] {new int[] {1, 1, 2, 3, 3, 3}, new int[] {4, 2, 2, 2, 3, 5}, new int[] {4, 2, 2, 6, 6, 5}, new int[] {2, 2, 7, 6, 5, 5}, new int[] {8, 2, 7, 6, 6, 5}, new int[] {8, 8, 7, 7, 6, 6}},
            new int[][] {new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0}});

}