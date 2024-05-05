package GUI;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import Dictionary.*;

public class MainGUI extends JFrame {
    JTextField t1 = new JTextField(40);
    JTextField t2 = new JTextField(40);
    JLabel errorLabel = new JLabel("", SwingConstants.CENTER);
    JButton b1 = new JButton("UCS");
    JButton b2 = new JButton("Greedy Best First Search");
    JButton b3 = new JButton("A*");

    public MainGUI(String title) {
        super(title);
        this.setSize(500, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // create title
        JLabel titleLabel = new JLabel("Word Ladder Solver", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Montserrat", Font.BOLD, 24));

        // create input panel
        JPanel inputGrid = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 15, 10);

        // description label
        JLabel descriptionLabel = new JLabel("Penyelesaian permainan Word Ladder", SwingConstants.CENTER);
        descriptionLabel.setFont(new Font("Montserrat", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        inputGrid.add(descriptionLabel, gbc);

        // add labels and text fields to the grid
        JLabel firstWordLabel = new JLabel("First Word:", SwingConstants.CENTER);
        firstWordLabel.setFont(new Font("Lato", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        inputGrid.add(firstWordLabel, gbc);

        JLabel secondWordLabel = new JLabel("Second Word:", SwingConstants.CENTER);
        secondWordLabel.setFont(new Font("Lato", Font.BOLD, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        inputGrid.add(secondWordLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        t1.setPreferredSize(new Dimension(40, 40));
        t1.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
        t1.setHorizontalAlignment(SwingConstants.CENTER);
        t1.setFont(new Font("Lato", Font.PLAIN, 12));
        inputGrid.add(t1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        t2.setPreferredSize(new Dimension(40, 40));
        t2.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
        t2.setHorizontalAlignment(SwingConstants.CENTER);
        t2.setFont(new Font("Lato", Font.PLAIN, 12));
        inputGrid.add(t2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        inputGrid.add(errorLabel, gbc);

        // panel button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));

        // add action listener to the buttons
        b1.addActionListener(e -> processUCS());
        b2.addActionListener(e -> processGBFS());
        b3.addActionListener(e -> processAStar());

        // add buttons to the button panel
        buttonPanel.add(b1);
        buttonPanel.add(b2);
        buttonPanel.add(b3);

        // add components to main panel
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(inputGrid, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);


        this.add(mainPanel);
    }

    public String getError(String first, String second) {
        if (!Word.isLengthSame(first, second)) {
            return "Kedua kata tidak memiliki panjang yang sama.";
        }

        if (first.equalsIgnoreCase(second)) {
            return "Kedua kata sama.";
        }

        boolean firstValid = true, secondValid = true;
        if (!Word.isWordExist(first)) {
            firstValid = false;
        }
        if (!Word.isWordExist(second)) {
            secondValid = false;
        }

        if (!firstValid && !secondValid) {
            return "Kedua kata tidak valid.";
        } else if (!firstValid) {
            return "Kata pertama tidak valid.";
        } else if (!secondValid){
            return "Kata kedua tidak valid.";
        }
        return "";
    }

    public void processUCS() {
        String start = t1.getText().trim().toLowerCase();
        String end = t2.getText().trim().toLowerCase();
        String err = getError(start, end);
        if (!err.isEmpty()) {
            errorLabel.setText(err);
            errorLabel.setForeground(Color.RED);
        } else {
            Result result = new Result("Penyelesaian dengan UCS", start, end, 0);
            result.setVisible(true);
        }
    }

    public void processGBFS() {
        String start = t1.getText().trim().toLowerCase();
        String end = t2.getText().trim().toLowerCase();
        String err = getError(start, end);
        if (!err.isEmpty()) {
            errorLabel.setText(err);
            errorLabel.setForeground(Color.RED);
        } else {
            Result result = new Result("Greedy Best First Search", start, end, 1);
            result.setVisible(true);
        }
    }

    public void processAStar() {
        String start = t1.getText().trim().toLowerCase();
        String end = t2.getText().trim().toLowerCase();
        String err = getError(start, end);
        if (!err.isEmpty()) {
            errorLabel.setText(err);
            errorLabel.setForeground(Color.RED);
        } else {
            Result result = new Result("A*", start, end, 2);
            result.setVisible(true);
        }
    }

}
