import GUIManager.MyFrame.LoginFrame;

import JDBCUtils.SGUtils;
import MySong.MP3Player;

import java.util.List;

/**
 * 工资管理系统
 * 系统主入口函数
 */
public class MyApplication {
    public static void main(String[] args) {
        new MP3Player("G:\\Mission\\Mission1\\song\\mel.wav").run();
        new LoginFrame();
        List<String> list = SGUtils.getSize();
    }
}
