import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Menu extends JPanel {
    private JLabel title;
    private JButton start;
    private JButton records;
    private JButton exit;

    public Menu(int x, int y, CardLayout card, JPanel pan) {
        super();
        setSize(new Dimension(x, y));
        setMinimumSize(new Dimension(200,800));
        setLayout(new GridBagLayout());
        JPanel pann = new JPanel();
        pann.setLayout(new GridLayout(4,3));
        start = new JButton("Start");
        start.setPreferredSize(new Dimension(120,120));
        records = new JButton("Scores");
        exit = new JButton("Exit");
        title = new JLabel("Java shooter");
        title.setForeground(Color.orange);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
        pann.add(title);
        pann.add(start);
        pann.add(records);
        pann.add(exit);
        GridBagConstraints c = new GridBagConstraints();
        this.add(pann, c);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(pan,"Card3");
            }
        });

        records.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(pan,"Card2");
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(1);
            }
        });
    }

    public void paintComponent(Graphics g) {
        Rectangle r = this.getBounds();
        Settings.paintBackground(g, r);
    }
}