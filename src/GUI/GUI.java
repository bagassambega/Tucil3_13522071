package GUI;
import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    public GUI(String title) {
        super(title);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void fillMainGUI() {
        JPanel panel = new JPanel();
        JLabel mainTitle = new JLabel("Word Ladder Solver");
        mainTitle.setFont(new Font("Calibri", Font.BOLD, 24));
        mainTitle.setBounds(300, 150, 200, 50);
        panel.add(mainTitle);
        this.add(panel);
    }
}
