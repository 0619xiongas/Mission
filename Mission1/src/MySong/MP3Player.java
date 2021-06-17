package MySong;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.*;

public class MP3Player implements Runnable{
    private String filename;
    private URI uri;
    private URL url;
    public MP3Player(String filename) {
        this.filename = filename;
    }

    @Override
    public void run(){
        new Thread(()->{
            try {
                File f = new File(filename);
                uri = f.toURI();
                url = uri.toURL(); // 解析路径
                AudioClip aau;
                aau = Applet.newAudioClip(url);
                aau.loop();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }).start();
    }

}