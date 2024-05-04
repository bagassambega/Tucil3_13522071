package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import UCS.*;
import A_Star.*;
import GBFS.*;

public class Result extends JFrame {
    // Method 0: UCS, 1: Greedy Best First Search, 2: A*
    public Result(String title, String start, String second, int method) {
        super(title);
        this.setSize(500, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Word Ladder Result", SwingConstants.CENTER);
        titleLabel.setFont(titleLabel.getFont().deriveFont(24.0f));
        mainPanel.add(titleLabel);

        List <String> pathResult = new ArrayList<>();
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

        if (method == 0) {
            pathResult = UCS.findLadder(start, second);
        } else if (method == 1) {
            pathResult = GBFS.findLadder(start, second);
        } else if (method == 2) {
            pathResult = A_Star.findLadder(start, second);
        }
        runtime.gc();
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();

        if (pathResult == null || pathResult.isEmpty()) {
            JLabel label = new JLabel("Tidak ada jalur yang ditemukan.", SwingConstants.CENTER);
            label.setFont(label.getFont().deriveFont(16.0f));
            mainPanel.add(label);
        } else {
            for (String word : pathResult) {
                JLabel label = new JLabel(word, SwingConstants.CENTER);
                label.setFont(new Font("Calibri", Font.PLAIN, 14));
                mainPanel.add(label);
            }
        }

        // Show how many words it takes
        JLabel numberOfWords = new JLabel("", SwingConstants.CENTER);
        if (method == 0) {
            numberOfWords.setText(String.valueOf("Banyak artikel diperiksa: " + UCS.checkedNode));
        } else if (method == 1) {
            numberOfWords.setText(String.valueOf("Banyak artikel diperiksa: " + GBFS.checkedNode));
        } else {
            numberOfWords.setText(String.valueOf("Banyak artikel diperiksa: " + A_Star.checkedNode));
        }
        mainPanel.add(numberOfWords);

        JLabel memoryUsage = new JLabel("Memory usage: " + (memoryAfter - memoryBefore) / 1024 + " KB", SwingConstants.CENTER);
        mainPanel.add(memoryUsage);

        this.add(mainPanel);
    }
}
