package iart.t4g11;

import javax.swing.*;
import java.awt.*;

enum SearchMethod {
    DEPTH_FIRST,
    BREADTH_FIRST,
    ITERATIVE_DEEPENING,
    GREEDY,
    A_STAR;

    @Override
    public String toString() {
        return switch (this) {
            case DEPTH_FIRST -> "Depth-first";
            case BREADTH_FIRST -> "Breath-first";
            case ITERATIVE_DEEPENING -> "Iterative deepening";
            case GREEDY -> "Greedy search";
            case A_STAR -> "A* algorithm";
        };
    }
}

enum PuzzleSize {
    SIX,
    TEN,
    FIFTEEN,
    TEST;

    @Override
    public String toString() {
        return switch (this) {
            case SIX -> "6x6";
            case TEN -> "10x10";
            case FIFTEEN -> "15x15";
            case TEST -> "test";
        };
    }
}

public class UI {
    private int ready = 0;

    private boolean isHuman;

    private PuzzleSize puzzleSize;

    private SearchMethod searchMethod;

    private JFrame frame;
    private JComboBox puzzleSizeField, playerTypeField, searchMethodField;
    private JButton startGameButton;

    public UI() {
        setupGUI();

        startGameButton.addActionListener(actionEvent -> {
            switch (playerTypeField.getSelectedIndex()) {
                case 0 -> isHuman = true;
                case 1 -> isHuman = false;
            }
            switch (puzzleSizeField.getSelectedIndex()) {
                case 0 -> puzzleSize = PuzzleSize.SIX;
                case 1 -> puzzleSize = PuzzleSize.TEN;
            }
            switch (searchMethodField.getSelectedIndex()) {
                case 0 -> searchMethod = SearchMethod.DEPTH_FIRST;
                case 1 -> searchMethod = SearchMethod.BREADTH_FIRST;
                case 2 -> searchMethod = SearchMethod.ITERATIVE_DEEPENING;
                case 3 -> searchMethod = SearchMethod.GREEDY;
                case 4 -> searchMethod = SearchMethod.A_STAR;
            }
            ready = 1;
        });
    }

    public boolean isHuman() {
        return isHuman;
    }

    public PuzzleSize getPuzzleSize() {
        return puzzleSize;
    }

    public SearchMethod getSearchMethod() {
        return searchMethod;
    }

    public void awaitStart() throws InterruptedException {
        while(ready == 0) {
            frame.validate();
            Thread.sleep(1000);
        }
        frame.setVisible(false);
    }

    private void setupGUI() {
        frame = new JFrame("Aquarium");
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 1, new Insets(0, 25, 0, 25), -1, -1));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(10, 0, 10, 0), -1, -1));
        panel1.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Aquarium");
        panel2.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 20, 0), -1, -1));
        panel1.add(panel3, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        startGameButton = new JButton();
        startGameButton.setText("Start game!");
        panel3.add(startGameButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 2, new Insets(10, 0, 10, 0), -1, -1));
        panel1.add(panel4, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        puzzleSizeField = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("6x6");
        defaultComboBoxModel1.addElement("10x10");
        puzzleSizeField.setModel(defaultComboBoxModel1);
        panel4.add(puzzleSizeField, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        playerTypeField = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("Human");
        defaultComboBoxModel2.addElement("AI");
        playerTypeField.setModel(defaultComboBoxModel2);
        panel4.add(playerTypeField, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        searchMethodField = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel3 = new DefaultComboBoxModel();
        defaultComboBoxModel3.addElement("Depth-first search");
        defaultComboBoxModel3.addElement("Breadth-first search");
        defaultComboBoxModel3.addElement("Iterative Deepening");
        defaultComboBoxModel3.addElement("Greedy search");
        defaultComboBoxModel3.addElement("A* algorithm");
        searchMethodField.setModel(defaultComboBoxModel3);
        panel4.add(searchMethodField, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        final JLabel label3 = new JLabel();
        label3.setText("Choose puzzle size:");
        panel4.add(label3, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        final JLabel label4 = new JLabel();
        label4.setText("Choose type of player:");
        panel4.add(label4, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        final JLabel label5 = new JLabel();
        label5.setText("(AI only) Choose search algorithm:");
        panel4.add(label5, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        frame.getContentPane().add(panel2);
        frame.getContentPane().add(panel1);
        frame.pack();
        frame.setVisible(true);
    }
}
