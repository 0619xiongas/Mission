package ThreadUtils;

import GUIManager.MyFrame.SalaryLel.SalaryLevelLookFrame;

public class MyThread implements Runnable{
    @Override
    public void run() {
        SalaryLevelLookFrame s = new SalaryLevelLookFrame();
        s.setVisible(false);
        s.setBounds(10,10,10,10);
        new Thread(()->{
            try{
                Thread.sleep(2000);
                s.dispose();
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();
    }
}
