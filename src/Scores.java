import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.awt.Graphics;
import java.io.FileReader;
import java.util.*;
import java.util.List;

public class Scores extends JPanel {
    JLabel wyn1;
    JLabel wyn2;
    JLabel wyn3;
    JLabel wyn4;
    Button wroc;
    Map<String, Integer> mapOfResults = new HashMap<>();
    Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
    int checkwyp = -1;

    public Scores(int x, int y,  CardLayout card, JPanel pan) {
        super();
        wypiszDane("scores.txt");
        setSize(new Dimension(x, y));
        setLayout(new GridLayout(5,1));
        wyn1 = new JLabel(String.valueOf("1. " + sortedMap.keySet().toArray()[0]) + " " + String.valueOf(sortedMap.values().toArray()[0]));
        wyn2 = new JLabel(String.valueOf("2. " + sortedMap.keySet().toArray()[1]) + " " + String.valueOf(sortedMap.values().toArray()[1]));
        wyn3 = new JLabel(String.valueOf("3. " + sortedMap.keySet().toArray()[2]) + " " + String.valueOf(sortedMap.values().toArray()[2]));
        wyn4 = new JLabel(String.valueOf("4. " + sortedMap.keySet().toArray()[3]) + " " + String.valueOf(sortedMap.values().toArray()[3]));
        wyn1.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        wyn2.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        wyn3.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        wyn4.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        wroc = new Button("Back");
        this.add(wyn1);
        this.add(wyn2);
        this.add(wyn3);
        this.add(wyn4);
        this.add(wroc);

        wroc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(pan,"Card1");
            }
        });
    }

    public void wypiszDane(String nazwa){
        if(checkwyp == -1){
            BufferedReader plik2 = null;
            try {
                checkwyp = 1;
                plik2 = new BufferedReader(new FileReader(nazwa));
                String lin = plik2.readLine();
                while (lin != null) {
                    lin = plik2.readLine();
                    String[] spliting = lin.split(" ");
                    mapOfResults.put(spliting[0], Integer.parseInt(spliting[1]));

                }
            }
            catch (Exception e)
            {
            }

            List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(mapOfResults.entrySet());

            Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
                public int compare(Map.Entry<String, Integer> o1,
                                   Map.Entry<String, Integer> o2) {
                    return (o2.getValue()).compareTo(o1.getValue());
                }
            });

            for (Map.Entry<String, Integer> entry : list) {
                sortedMap.put(entry.getKey(), entry.getValue());
            }
        }
    }

    public void paintComponent(Graphics g) {
        Rectangle r = this.getBounds();
        Settings.paintBackground(g,r);
    }
}
