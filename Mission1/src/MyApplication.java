import GUIManager.MyFrame.LoginFrame;

import ThreadUtils.MyThread;

/**
 * 工资管理系统
 * 系统主入口函数
 */
public class MyApplication {
    public static void main(String[] args) {
        new LoginFrame();
        MyThread t = new MyThread();
        Thread t1 = new Thread(t);
        t1.start();
    }
}
