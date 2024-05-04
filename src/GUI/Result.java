package GUI;

import javax.swing.*;
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

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Word Ladder Solver", SwingConstants.CENTER);
        titleLabel.setFont(titleLabel.getFont().deriveFont(24.0f));
        mainPanel.add(titleLabel);

        List <String> pathResult = new ArrayList<>();

        List<JLabel> path = new ArrayList<>();
        if (method == 1) {
            pathResult = UCS.findLadder(start, second);
        } else if (method == 2) {
            pathResult = GBFS.findLadder(start, second);
        } else if (method == 3) {
            pathResult = A_Star.findLadder(start, second);
        }

        if (pathResult == null || pathResult.isEmpty()) {
            JLabel label = new JLabel("Tidak ada jalur yang ditemukan.", SwingConstants.CENTER);
            label.setFont(label.getFont().deriveFont(16.0f));
            mainPanel.add(label);
        } else {
            for (String word : pathResult) {
                JLabel label = new JLabel(word, SwingConstants.CENTER);
                label.setFont(label.getFont().deriveFont(16.0f));
                path.add(label);
                mainPanel.add(label);
            }
        }

//        JLabel resultLabel = new JLabel("Result: " + Result, SwingConstants.CENTER);
//        resultLabel.setFont(resultLabel.getFont().deriveFont(16.0f));
//        mainPanel.add(resultLabel);

        this.add(mainPanel);
    }
}
