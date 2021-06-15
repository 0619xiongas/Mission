package MySong;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.player.Player;
public class MP3Player implements Runnable{
    public MP3Player(String filename) {
        this.filename = filename;
        Thread thread=new Thread(this);
        mp3=this;
        thread.start();
    }
    public void play() {
        try {
            BufferedInputStream buffer = new BufferedInputStream(
                    new FileInputStream(filename));
            player = new Player(buffer);
            player.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void run(){
        mp3.play();
    }

    public static void main(String[] args) {
        new MP3Player("G:\\Mission\\Mission1\\song\\lucky.mp3");
    }
    static MP3Player mp3;
    private String filename;
    private Player player;
}