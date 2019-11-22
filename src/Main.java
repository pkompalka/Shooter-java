public class Main {
    public static void main(String[] args){
        Settings.loadSettings();
        Settings.playMusicLoop("menu.wav");
        GameWindow wind = new GameWindow();
        wind.launchWindow();
    }
}