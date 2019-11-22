import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Level extends JPanel {
    private Game game;

    public Level(int x, int y, CardLayout card, JPanel pan, Game game) {
        super();
        this.game = game;
        setSize(new Dimension(x, y));
        setLayout(new GridLayout(4,1));
        JLabel wyb = new JLabel("Choose difficulty");
        wyb.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
        JButton law5 = new JButton("EASY");
        JButton nor5 = new JButton("NORMAL");
        JButton tru5 = new JButton("HARD");
        this.add(wyb);
        this.add(law5);
        this.add(nor5);
        this.add(tru5);
        law5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(pan,"Card8");
                game.setLevel(1);
            }
        });
        nor5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(pan,"Card8");
                game.setLevel(2);
            }
        });
        tru5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(pan,"Card8");
                game.setLevel(3);
            }
        });
    }

    public void paintComponent(Graphics g) {
        Rectangle r = this.getBounds();
        Settings.paintBackground(g,r);
    }
}
