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
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();

        JLabel titleLabel = new JLabel("Word Ladder Result", SwingConstants.CENTER);
        titleLabel.setFont(titleLabel.getFont().deriveFont(24.0f));
        mainPanel.add(titleLabel);

        List <String> pathResult = new ArrayList<>();

        if (method == 0) {
            pathResult = UCS.findLadder(start, second);
        } else if (method == 1) {
            pathResult = GBFS.findLadder(start, second);
        } else if (method == 2) {
            pathResult = A_Star.findLadder(start, second);
        }

        if (pathResult == null || pathResult.isEmpty()) {
            JLabel label = new JLabel("Tidak ada jalur yang ditemukan.", SwingConstants.CENTER);
            label.setPreferredSize(new Dimension(500, 80));
            label.setFont(new Font("Calibri", Font.BOLD, 18));
            mainPanel.add(label);
        } else {
            JPanel resultPanel = new JPanel();
            resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
            for (String word : pathResult) {
                JLabel label = new JLabel(word, SwingConstants.CENTER);
                label.setFont(new Font("Calibri", Font.PLAIN, 14));
                resultPanel.add(label);
            }
            JScrollPane scrollPane = new JScrollPane(resultPanel);
            scrollPane.setPreferredSize(new Dimension(450, 180));
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setVisible(true);
            mainPanel.add(scrollPane);
            JLabel length = new JLabel("Kata dilalui: " + (pathResult.size() - 2));
            mainPanel.add(length);

        }

        // Tampilkan jumlah kata yang diperiksa
        JLabel numberOfWords = new JLabel("", SwingConstants.CENTER);
        JLabel memoryUsage = new JLabel("", SwingConstants.CENTER);
        if (method == 0) {
            numberOfWords.setText("Banyak kata diperiksa: " + UCS.checkedNode);
            memoryUsage.setText("Memory usage: " + UCS.memoryUsage/1024 + " KB");
            UCS.checkedNode = 0;
        } else if (method == 1) {
            numberOfWords.setText("Banyak kata diperiksa: " + GBFS.checkedNode);
            memoryUsage.setText("Memory usage: " + GBFS.memoryUsage/1024 + " KB");
            GBFS.checkedNode = 0;
        } else {
            numberOfWords.setText("Banyak kata diperiksa: " + A_Star.checkedNode);
            memoryUsage.setText("Memory usage: " + A_Star.memoryUsage/1024 + " KB");
            A_Star.checkedNode = 0;
        }
        mainPanel.add(numberOfWords);
        mainPanel.add(memoryUsage);


        this.add(mainPanel);
    }
}
