import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;

public class WrongPlayer extends JPanel {
    private JLabel errorLabel;
    private JButton backButton;

    public WrongPlayer(int x, int y, CardLayout card, JPanel pan) {
        super();
        setSize(new Dimension(x, y));
        setLayout(new GridLayout(2,1));
        errorLabel = new JLabel("Incorrect nick!");
        errorLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        backButton = new JButton("Try again");

        this.add(errorLabel);
        this.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(pan,"Card3");
            }
        });
    }

    public void paintComponent(Graphics g) {
        Rectangle r = this.getBounds();
        Settings.paintBackground(g,r);
    }
}
