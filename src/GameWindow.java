import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow extends JFrame {
    public static JPanel cards = new JPanel();
    private JPanel card1 = new JPanel();
    private JPanel card2 = new JPanel();
    private JPanel card3 = new JPanel();
    private JPanel card4 = new JPanel();
    private JPanel card6 = new JPanel();
    private JPanel card7 = new JPanel();
    private JPanel card8 = new JPanel();
    public static CardLayout cardLayout = new CardLayout();
    private Rectangle windowSize;
    private String name;
    private int score;
    public static GameWindow thisGameWindow;

    public GameWindow() {
        super("Shooter");
        score = 0;
    }

    public String getEndName(){
        return name;
    }

    public int getEndScore(){
        return score;
    }

    public void setEndName(String s){
        name = s;
    }

    public void setEndScore(int value){
        score = value;
    }

    public void setEndgame(){
        card7 = new Endgame(windowSize.width, windowSize.height,this);
        cards.add(card7, "Card7");
    }

    public void launchWindow() {
        setSize(new Dimension(Settings.initialWidth, Settings.initialHeight));
        cards.setLayout(cardLayout);
        windowSize = this.getBounds();

        thisGameWindow = this;

        card1 = new Menu(windowSize.width, windowSize.height, cardLayout, cards);
        cards.add(card1, "Card1");
        card2 = new Scores(windowSize.width, windowSize.height, cardLayout, cards);
        cards.add(card2, "Card2");
        Game card5 = new Game(windowSize.width, windowSize.height, cardLayout, cards, this);
        cards.add(card5, "Card5");
        card3 = new NewPlayer(windowSize.width, windowSize.height, cardLayout, cards, card5);
        cards.add(card3, "Card3");
        card4 = new Level(windowSize.width, windowSize.height, cardLayout, cards, card5);
        cards.add(card4, "Card4");
        card8 = new EnemyType(windowSize.width, windowSize.height, cardLayout, cards, card5);
        cards.add(card8, "Card8");
        card6 = new WrongPlayer(windowSize.width, windowSize.height, cardLayout, cards);
        cards.add(card6, "Card6");
        cards.add(card7, "Card7");
        getContentPane().add(cards);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we)
            {
                System.exit(1);
            }
        });

        EventQueue.invokeLater(() -> setVisible(true));
    }
}