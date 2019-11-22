import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Graphics;
import java.io.FileWriter;
import java.util.*;
import java.lang.Math;

public class Game extends JPanel implements ActionListener, MouseListener, KeyListener{
    private int positionX;
    private int positionY;
    private int velocityX;
    private int velocityY;
    private int enemyWidth;
    private int enemyHeight;
    private JLabel scoreLabel;
    private JLabel timeLabel;
    private CardLayout myCard;
    private JPanel myPanel;
    private GameWindow myGameWindow;
    private BufferedImage myImage;
    private FileWriter myFileWriter;
    private int score = 0;
    private int currentLevel = 1;
    private String nick;
    private ArrayList<Enemy> targets = new ArrayList<>();
    private Timer myTimer = new Timer(1000/60, this);
    private double timeElapsedDouble = 0;
    private double timeElapsedDoubleToInt = 0;
    private int timeElapsedInt = 0;
    private boolean chooseBackground;
    private boolean checkFirstDraw = false;
    private boolean pause = false;
    private boolean checkFile = false;

    public Game(int x, int y, CardLayout card, JPanel pan, GameWindow gameW) {
        super();
        setSize(new Dimension(x, y));
        setLayout(new BorderLayout());
        myCard = card;
        myPanel = pan;
        myGameWindow = gameW;

        addKeyListener(this);
        addMouseListener(this);
        loadImg();
        setFocusable(true);
        requestFocus();

        targets.add(new Enemy(positionX - 50 , positionY - 50));
        enemyWidth = targets.get(0).getEnemyWidth();
        enemyHeight = targets.get(0).getEnemyHeight();

        scoreLabel = new JLabel(Integer.toString(score));
        scoreLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
        this.add(scoreLabel, BorderLayout.NORTH);
        timeLabel = new JLabel(Integer.toString(timeElapsedInt));
        timeLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
        this.add(timeLabel, BorderLayout.SOUTH);
        myKeyRequest();
    }

    public synchronized void paintComponent(Graphics g) {
            if (chooseBackground == false) {
                g.drawImage(myImage, 0, 0, null);
            }
            else {
                g.setColor(Color.getHSBColor(Settings.r, Settings.g, Settings.b));
                g.fillRect(0, 0, getWidth(), getHeight());
            }

        if(checkFirstDraw == false) {
            Rectangle r = this.getBounds();
            Random rand = new Random();
            positionX = rand.nextInt(r.width);
            positionY = rand.nextInt(r.height);

            if (Settings.objectType.equals("figures")) {
                if (Settings.objectFigure.equals("square")) {
                    if(positionX < Math.round(enemyWidth / 2)) {
                        positionX = positionX + Math.round(enemyWidth / 2);
                    }
                    if(positionX > r.width - Math.round(enemyWidth / 2)) {
                        positionX = positionX - Math.round(enemyWidth / 2);
                    }
                    if(positionY < Math.round(enemyWidth / 2)) {
                        positionY = positionY + Math.round(enemyWidth / 2);
                    }
                    if(positionY > r.height - Math.round(enemyWidth / 2)) {
                        positionY = positionY - Math.round(enemyWidth / 2);
                    }
                }
                else if (Settings.objectFigure.equals("circle")) {
                    if(positionX < 0){
                        positionX = positionX + enemyWidth;
                    }
                    if(positionX > r.width - enemyWidth){
                        positionX = positionX - enemyWidth;
                    }
                    if(positionY < 0){
                        positionY = positionY + enemyWidth;
                    }
                    if(positionY > r.height - enemyWidth){
                        positionY = positionY -enemyWidth;
                    }
                }
                else {
                    if(positionX < Math.round(enemyWidth/2)){
                        positionX = positionX + Math.round(enemyWidth/2);
                    }
                    if(positionX > r.width - Math.round(enemyWidth/2)){
                        positionX = positionX - Math.round(enemyWidth/2);
                    }
                    if(positionY < Math.round(enemyHeight/2)){
                        positionY = positionY + Math.round(enemyHeight/2);
                    }
                    if(positionY > r.height - Math.round(enemyHeight/2)){
                        positionY = positionY - Math.round(enemyHeight/2);
                    }
                }
            }
            else {
                if(positionX < 0){
                    positionX = positionX + enemyWidth;
                }
                if(positionX > r.width - enemyWidth){
                    positionX = positionX - enemyWidth;
                }
                if(positionY < 0){
                    positionY = positionY + enemyHeight;
                }
                if(positionY > r.height - enemyHeight){
                    positionY = positionY - enemyHeight;
                }
            }
            checkFirstDraw = true;
        }

        for (Enemy enemy : targets) {
            enemy.draw(g);
        }

        myTimer.start();

        scoreLabel.setText(score + "  level: " + currentLevel);
        timeLabel.setText(timeElapsedInt + " s");
    }

    public void actionPerformed(ActionEvent e) {
        if (pause != true) {
            timeElapsedDouble = timeElapsedDouble + 0.0166666667;
            timeElapsedDoubleToInt = Math.floor(timeElapsedDouble);
            timeElapsedInt = (int) timeElapsedDoubleToInt;

            if ((timeElapsedInt == 20) && (score >= 10) && (currentLevel == 1)) {
                currentLevel = 2;
                nextLevel();
            }
            if ((timeElapsedInt == 20) && (currentLevel == 1) &&(score < 10)) {
                myGameWindow.setEndScore(score);
                myGameWindow.setEndName(nick);
                myGameWindow.setEndgame();
                myCard.show(myPanel, "Card7");
                scoreSave();

            }
            if ((timeElapsedInt == 40) && (score >= 20) && currentLevel == 2) {
                currentLevel = 3;
                nextLevel();
            }
            if ((timeElapsedInt == 40) && (currentLevel == 2) &&(score < 20)) {
                myGameWindow.setEndScore(score);
                myGameWindow.setEndName(nick);
                myGameWindow.setEndgame();
                myCard.show(myPanel, "Card7");
                scoreSave();
            }
            if ((timeElapsedInt == 60)) {
                myGameWindow.setEndScore(score);
                myGameWindow.setEndName(nick);
                myGameWindow.setEndgame();
                myCard.show(myPanel, "Card7");
                scoreSave();
            }

            Rectangle ra = this.getBounds();

            if (Settings.objectType.equals("figures")) {
                if (Settings.objectFigure.equals("square")) {
                    if(((ra.width - Math.round(enemyWidth / 2) + 10)  < positionX) || ((ra.height - Math.round(enemyWidth / 2) + 10) < positionY)){
                        checkFirstDraw = false;
                        repaint();
                    }
                    else {
                        if (positionX < Math.round(enemyWidth / 2) || positionX > ra.width - Math.round(enemyWidth / 2)) {
                            velocityX = -velocityX;
                        }
                        if (positionY < Math.round(enemyWidth / 2) || positionY > ra.height - Math.round(enemyWidth / 2)) {
                            velocityY = -velocityY;
                        }
                        positionX += velocityX;
                        positionY += velocityY;
                        targets.get(0).setPositionX(positionX);
                        targets.get(0).setPositionY(positionY);
                        checkFirstDraw = true;
                        repaint();
                    }
                }
                else if (Settings.objectFigure.equals("circle")) {
                    if(((ra.width - enemyWidth + 10)  < positionX) || ((ra.height - enemyWidth + 10) < positionY)){
                        checkFirstDraw = false;
                        repaint();
                    }
                    else {
                        if (positionX < 0 || positionX > ra.width - enemyWidth) {
                            velocityX = -velocityX;
                        }
                        if (positionY < 0 || positionY > ra.height - enemyWidth) {
                            velocityY = -velocityY;
                        }
                        positionX += velocityX;
                        positionY += velocityY;
                        targets.get(0).setPositionX(positionX);
                        targets.get(0).setPositionY(positionY);
                        checkFirstDraw = true;
                        repaint();
                    }
                }
                else {
                    if(((ra.width - Math.round(enemyWidth / 2) + 10)  < positionX) || ((ra.height - Math.round(enemyHeight / 2) + 10) < positionY)){
                        checkFirstDraw = false;
                        repaint();
                    }
                    else {
                        if (positionX < Math.round(enemyWidth / 2) || positionX > ra.width - Math.round(enemyWidth / 2)) {
                            velocityX = -velocityX;
                        }
                        if (positionY < Math.round(enemyHeight / 2) || positionY > ra.height - Math.round(enemyHeight / 2)) {
                            velocityY = -velocityY;
                        }
                        positionX += velocityX;
                        positionY += velocityY;
                        targets.get(0).setPositionX(positionX);
                        targets.get(0).setPositionY(positionY);
                        checkFirstDraw = true;
                        repaint();
                    }
                }
            }
            else {
                if(((ra.width - enemyWidth + 10)  < positionX) || ((ra.height - enemyHeight + 10) < positionY)){
                    checkFirstDraw = false;
                    repaint();
                }
                else {
                    if (positionX < 0 || positionX > ra.width - enemyWidth) {
                        velocityX = -velocityX;
                    }
                    if (positionY < 0 || positionY > ra.height - enemyHeight) {
                        velocityY = -velocityY;
                    }
                    positionX += velocityX;
                    positionY += velocityY;
                    targets.get(0).setPositionX(positionX);
                    targets.get(0).setPositionY(positionY);
                    checkFirstDraw = true;
                    repaint();
                }
            }
        }
    }

    public void setLevel(int k){
        velocityX = k;
        velocityY = k;
    }

    public void nextLevel(){
        velocityX = velocityX + (velocityX * 2);
        velocityY = velocityY + (velocityY * 2);
    }

    public void myKeyRequest(){
        myGameWindow.addKeyListener(this);
        myGameWindow.requestFocus();
        myGameWindow.setFocusable(true);
    }

    public void setNick(String name){
        nick = name;
    }

    public void playEffect(String eff){
        try {
            String filename = eff;
            File fil;
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;
            Clip clip;

            fil = new File(filename);
            stream = AudioSystem.getAudioInputStream(fil);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();
        }
        catch (Exception e) {

        }
    }

    public static boolean fileExist(String nazwa) {
        File f = new File(nazwa);
        return f.exists() && f.isFile();
    }

    public void scoreSave(){
        if(!fileExist("scores.txt")){
            try{
                myFileWriter = new FileWriter("scores.txt",true);
                myFileWriter.write("Scores:\n");
                myFileWriter.write(nick + " " + score+ "\n");
                myFileWriter.close();
            }
            catch (Exception eee){

            }
        }
        else {
            try{
                if(checkFile == false){
                    myFileWriter = new FileWriter("scores.txt",true);
                    myFileWriter.write(nick + " " + score + "\n");
                    myFileWriter.close();
                    checkFile = true;
                }
            }
            catch (Exception e){

            }
        }
    }

    public void loadImg() {
        myImage = null;
        try {
            myImage = ImageIO.read(new File(Settings.imgBackground));
        }
        catch (Exception e) { }

        if (Settings.background.equals("file")) {
            chooseBackground = false;
        }
        else {
            chooseBackground = true;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e){

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (pause != true) {
            if (Settings.objectType.equals("figures")) {
                if (Settings.objectFigure.equals("circle")) {
                    int xx = e.getPoint().x;
                    int yy = e.getPoint().y;
                    if (((xx - targets.get(0).getPositionX() - enemyWidth / 2) * (xx - targets.get(0).getPositionX() - enemyWidth / 2) + (yy - targets.get(0).getPositionY() - enemyWidth / 2) * (yy - targets.get(0).getPositionY() - enemyWidth / 2)) < (enemyWidth * enemyWidth / 4)) {
                        checkFirstDraw = false;
                        targets.remove(0);
                        score++;
                        repaint();
                        targets.add(new Enemy(positionX, positionY));
                        playEffect("hit.wav");
                    }
                    else {
                        score--;
                        scoreLabel.setText(Integer.toString(score));
                        playEffect("miss.wav");
                    }
                }
                else {
                    if (targets.get(0).getPolygonEnemy().contains(e.getPoint())) {
                        checkFirstDraw = false;
                        targets.remove(0);
                        score++;
                        repaint();
                        targets.add(new Enemy(positionX, positionY));
                        playEffect("hit.wav");
                    }
                    else {
                        score--;
                        scoreLabel.setText(Integer.toString(score));
                        playEffect("miss.wav");
                    }
                }
            }
            else {
                int xx = e.getPoint().x;
                int yy = e.getPoint().y;

                if (xx > targets.get(0).getPositionX() && xx < targets.get(0).getPositionX() + enemyWidth && yy > targets.get(0).getPositionY() && yy < targets.get(0).getPositionY() + enemyHeight) {
                    checkFirstDraw = false;
                    targets.remove(0);
                    score++;
                    repaint();
                    targets.add(new Enemy(positionX, positionY));
                    playEffect("hit.wav");
                }
                else {
                    score--;
                    scoreLabel.setText(Integer.toString(score));
                    playEffect("miss.wav");
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e){
        pause = false;
    }

    @Override
    public void mouseExited(MouseEvent e){
        pause = true;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(pause==false){
            pause=true;
        }
        else {
            pause=false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
