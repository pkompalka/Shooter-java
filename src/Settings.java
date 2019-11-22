import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Settings {
    public static String background;
    public static String objectType;
    public static String objectFigure;
    public static String imgBackground;
    public static String imgObject;
    public static int initialWidth;
    public static int initialHeight;
    public static float initialObjectPerc;
    public static int r;
    public static int g;
    public static int b;

    public static void loadSettings() {
        background = "file";    //file   plain
        objectType = "file";  //file   figures
        objectFigure = "circle"; //"square", "triangle", "circle"
        imgBackground = "file.jpeg";
        imgObject = "fileObject.jpeg";
        initialWidth = 800;
        initialHeight = 800;
        initialObjectPerc = (float)6.5;
        r = 200;
        g = 200;
        b = 200;
    }

    public static void paintBackground(Graphics g, Rectangle r){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(Settings.imgBackground));
        } catch (Exception err) {

        }
        if (Settings.background.equals("file")) {
            g.drawImage(img, 0, 0, null);
        }
        else{
            g.setColor(Color.getHSBColor(Settings.r, Settings.g, Settings.b));
            g.fillRect(0, 0, r.width, r.height);
        }
    }

    public static void playMusicLoop(String title){
        try {
            String filename = title;
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
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        }
        catch (Exception e) {
        }
    }
}
