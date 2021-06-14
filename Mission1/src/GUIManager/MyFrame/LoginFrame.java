package GUIManager.MyFrame;

import GUIManager.AllDialog.MyDialog;
import java.awt.*;
import javax.swing.*;


public class LoginFrame extends JFrame {

    private static final String NAME = "admin";

    private static final String PWD = "123456";

    private JTextField textName;
    private JPasswordField textPwd;
    private JButton jbCancel;
    private JButton jbLogin;


    public LoginFrame() {
        this.setTitle("工资管理系统---登录");

        ImageIcon icon = new ImageIcon("Mission1/pic/login.jpg");
        JLabel label = new JLabel(icon);//往一个标签中加入图片
        label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());//设置标签位置大小为图片大小
        this.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));//标签添加到第二层面板

        JPanel imPanel = (JPanel) getContentPane();
        imPanel.setOpaque(false);

        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(null);
        displayPanel.setOpaque(false);

        JLabel jlabelName = new JLabel("用户:");
        jlabelName.setBounds(180, 60, 40, 30);
        jlabelName.setForeground(Color.BLACK);
        jlabelName.setOpaque(false);

        JLabel jlabelPwd = new JLabel("密码:");
        jlabelPwd.setBounds(180, 120, 40, 30);
        jlabelPwd.setOpaque(false);
        jlabelPwd.setForeground(Color.BLACK);

        textName = new JTextField();
        textName.setBounds(220, 60, 150, 30);

        textPwd = new JPasswordField();
        textPwd.setBounds(220, 120, 150, 30);

        jbLogin = new JButton("登录");
        jbLogin.setFocusPainted(false);
        jbLogin.setBorder(null);
        jbLogin.setOpaque(false);
        jbLogin.setBounds(220, 180, 150, 30);
        jbLogin.setForeground(Color.BLACK);

        jbCancel = new JButton("取消");
        jbCancel.setFocusPainted(false);
        jbCancel.setBorder(null);
        jbCancel.setOpaque(false);
        jbCancel.setBounds(220, 240, 150, 30);
        jbCancel.setForeground(Color.BLACK);

        displayPanel.add(jbLogin);
        displayPanel.add(jbCancel);
        displayPanel.add(jlabelName);
        displayPanel.add(jlabelPwd);
        displayPanel.add(textName);
        displayPanel.add(textPwd);

        imPanel.add(displayPanel, BorderLayout.CENTER);

        this.setSize(icon.getIconWidth(), icon.getIconHeight());
        this.setResizable(false);
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        jbLogin.addActionListener(e -> {
            this.jugde();
        });
        //点击取消按钮，退出程序
        jbCancel.addActionListener(e -> {
            System.exit(0);
        });
    }

    private void jugde(){
        if(textName.getText().trim().equals(NAME) && new String(textPwd.getPassword()).equals(PWD)){
            this.dispose();
            new ChoiceFrame();
        }else if(textName.getText().trim().equals("") || new String (textPwd.getPassword()).equals("")){
            //第一中情况，重新输入用户密码
            new MyDialog("请输入你的账户和密码");
        }else{
            new MyDialog("你的账户或者密码有误,请重新输入");
            textPwd.setText("");//密码清空
        }
    }
}
