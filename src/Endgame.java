import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Endgame extends JPanel {
    private JLabel endLabel;
    private JLabel scoreLabel;
    private JButton exitButton;
    private String nick;
    private String score;
    private String info;

    public Endgame(int x, int y, GameWindow gameW) {
        super();
        nick = gameW.getEndName();
        score = Integer.toString(gameW.getEndScore());
        info = nick + " scored " + score;
        setSize(new Dimension(x, y));
        setLayout(new GridLayout(3,1));
        endLabel = new JLabel("The end!");
        scoreLabel = new JLabel(info);
        endLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
        scoreLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        exitButton = new JButton("Exit");

        this.add(endLabel);
        this.add(scoreLabel);
        this.add(exitButton);

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(1);
            }
        });
    }

    public void paintComponent(Graphics g) {
        Rectangle r = this.getBounds();
        Settings.paintBackground(g,r);
    }
}