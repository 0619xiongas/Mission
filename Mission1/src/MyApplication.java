import GUIManager.MyFrame.LoginFrame;

import MySong.MP3Player;
import ThreadUtils.MyThread;
/**
 * 工资管理系统
 * 系统主入口函数
 */
public class MyApplication {
    public static void main(String[] args) {
        new LoginFrame();
        //new MP3Player("G:\\Mission\\Mission1\\song\\lucky.mp3"); 增加音乐。
        MyThread t = new MyThread();
        Thread t1 = new Thread(t);
        t1.start();
    }
}
