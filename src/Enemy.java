import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Enemy{
    private int initialWidth = Settings.initialWidth;
    private int initialHeight = Settings.initialHeight;
    private float procentEnemy = Settings.initialObjectPerc;
    private float objectWidthFloat = (initialWidth * procentEnemy)/100;
    private float objectHeightFloat = (initialHeight * procentEnemy)/100;
    private int enemyWidth = Math.round(objectWidthFloat);
    private int enemyHeight = Math.round(objectHeightFloat);
    private int positionX;
    private int positionY;
    private Polygon polygonEnemy;

    public Enemy(int x, int y) {
        super();
        positionX = x;
        positionY = y;
    }

    public void draw(Graphics g) {
        if(Settings.objectType.equals("figures")) {
            if(Settings.objectFigure.equals("circle")) {
                g.setColor(Color.black);
                g.fillOval(positionX, positionY, enemyWidth, enemyHeight);
            }
            else if(Settings.objectFigure.equals("square")){
                g.setColor(Color.black);
                int x[] = {positionX - Math.round(enemyWidth/2), positionX + Math.round(enemyWidth/2), positionX + Math.round(enemyWidth/2), positionX - Math.round(enemyWidth/2)};
                int y[] = {positionY - Math.round(enemyWidth/2), positionY - Math.round(enemyWidth/2), positionY + Math.round(enemyWidth/2), positionY + Math.round(enemyWidth/2)};
                polygonEnemy = new Polygon(x, y, 4);
                g.fillPolygon(polygonEnemy);
                g.drawPolygon(polygonEnemy);
            }
            else {
                g.setColor(Color.black);
                int x[] = {positionX - Math.round(enemyWidth/2), positionX + Math.round(enemyWidth/2), positionX};
                int y[] = {positionY - Math.round(enemyHeight/3), positionY - Math.round(enemyHeight/3), positionY + Math.round(enemyHeight/2)};
                polygonEnemy = new Polygon(x, y, 3);
                g.fillPolygon(polygonEnemy);
                g.drawPolygon(polygonEnemy);
            }
        }
        else
        {
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File(Settings.imgObject));
            } catch (Exception err) {

            }
            g.drawImage(img, positionX, positionY, enemyWidth, enemyHeight, null);
        }
    }

    public Integer getEnemyWidth(){
        return enemyWidth;
    }

    public Integer getEnemyHeight(){
        return enemyHeight;
    }

    public Integer getPositionX(){
        return positionX;
    }

    public Integer getPositionY(){
        return positionY;
    }

    public Polygon getPolygonEnemy(){
        return polygonEnemy;
    }

    public void setPositionX(int x){
        positionX = x;
    }

    public void setPositionY(int y){
        positionY = y;
    }
}
