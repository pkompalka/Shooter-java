import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewPlayer extends JPanel {
    private Game game;
    private JLabel upLabel;
    private JTextField centrumText;
    private JLabel downLabel;
    private JButton backButton;
    private JButton nextButton;

    public NewPlayer(int x, int y,  CardLayout card, JPanel pan, Game game) {
        super();
        this.game = game;
        setSize(new Dimension(x, y));
        setLayout(new BorderLayout());
        upLabel = new JLabel("Enter nick");
        centrumText = new JTextField(1);
        downLabel = new JLabel();
        upLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
        backButton = new JButton("Back");
        nextButton = new JButton("Next");
        backButton.setPreferredSize(new Dimension(100,50));
        nextButton.setPreferredSize(new Dimension(100,50));
        downLabel.setPreferredSize(new Dimension(50,100));

        this.add(upLabel,BorderLayout.NORTH);
        this.add(centrumText,BorderLayout.CENTER);
        this.add(backButton,BorderLayout.WEST);
        this.add(nextButton,BorderLayout.EAST);
        this.add(downLabel,BorderLayout.SOUTH);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(pan,"Card1");
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameinput = centrumText.getText();
                if(nameinput.equals("") || nameinput.contains(" "))
                {
                    card.show(pan, "Card6");
                }
                else {
                    card.show(pan, "Card4");
                    game.setNick(nameinput);
                }
            }
        });
    }

    public void paintComponent(Graphics g) {
        Rectangle r = this.getBounds();
        Settings.paintBackground(g,r);
    }
}