import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnemyType extends JPanel {
    private Game game;

    public EnemyType(int x, int y, CardLayout card, JPanel pan, Game game) {
        super();
        this.game = game;
        setSize(new Dimension(x, y));
        setLayout(new GridLayout(5,1));
        JLabel wyb = new JLabel("Choose enemy shape");
        wyb.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
        JButton br = new JButton("BRICK");
        JButton sq = new JButton("SQUARE");
        JButton tr = new JButton("TRIANGLE");
        JButton ci = new JButton("CIRCLE");
        this.add(wyb);
        this.add(br);
        this.add(sq);
        this.add(tr);
        this.add(ci);
        br.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(pan,"Card5");
            }
        });
        sq.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Settings.objectFigure = "square";
                Settings.objectType = "figures";
                card.show(pan,"Card5");
            }
        });
        tr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Settings.objectFigure = "triangle";
                Settings.objectType = "figures";
                card.show(pan,"Card5");
            }
        });
        ci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Settings.objectFigure = "circle";
                Settings.objectType = "figures";
                card.show(pan,"Card5");
            }
        });
    }

    public void paintComponent(Graphics g) {
        Rectangle r = this.getBounds();
        Settings.paintBackground(g,r);
    }
}
