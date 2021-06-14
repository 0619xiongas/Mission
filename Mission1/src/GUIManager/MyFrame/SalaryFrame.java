package GUIManager.MyFrame;

import GUIManager.AllDialog.OutOrNotDialog;
import GUIManager.MyFrame.Salary.SalaryAddFrame;
import GUIManager.MyFrame.Salary.SalaryLookFrame;
import GUIManager.MyFrame.Salary.SalaryRemoveFrame;
import GUIManager.MyFrame.Salary.SalaryUpdateFrame;
import UserData.SalaryGrade;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SalaryFrame extends JFrame {

    private JPanel contentPane;
    private JButton btnAdd;
    private JButton btnRemove;
    private JButton btnUpdate;
    private JButton btnLook;
    private JButton btnBack;
    private JButton btnOut;


    public SalaryFrame(){
        this.setTitle("工资管理系统---职工变动工资信息管理系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon=new ImageIcon("Mission1/pic/salary.jpg");
        JLabel label = new JLabel(icon);//往一个标签中加入图片
        label.setBounds(0, 0, icon.getIconWidth(),icon.getIconHeight());//设置标签位置大小为图片大小
        this.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));//标签添加到第二层面板
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel imPanel = (JPanel)getContentPane();
        imPanel.setOpaque(false);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setOpaque(false);

        btnBack = new JButton("返回");
        btnBack.setOpaque(false);
        btnBack.setBorder(null);
        btnBack.setForeground(Color.BLACK);
        btnBack.setBounds(60, 300, 60, 20);
        contentPane.add(btnBack);

        btnOut = new JButton("退出");
        btnOut.setOpaque(false);
        btnOut.setBorder(null);
        btnOut.setForeground(Color.BLACK);
        btnOut.setBounds(500, 300, 60, 20);
        contentPane.add(btnOut);

        btnLook = new JButton("查看变动信息");
        btnLook.setOpaque(false);
        btnLook.setBounds(250, 60, 140, 20);
        contentPane.add(btnLook);

        btnUpdate = new JButton("修改变动工资信息");
        btnUpdate.setOpaque(false);
        btnUpdate.setBounds(250, 120, 140, 20);
        contentPane.add(btnUpdate);

        btnRemove = new JButton("删除变动工资信息");
        btnRemove.setOpaque(false);
        btnRemove.setBounds(250, 180, 140, 20);
        contentPane.add(btnRemove);

        btnAdd = new JButton("添加变动工资信息");
        btnAdd.setOpaque(false);
        btnAdd.setBounds(250, 240, 140, 20);
        contentPane.add(btnAdd);

        imPanel.add(contentPane,BorderLayout.CENTER);
        this.setResizable(false);
        this.setSize(icon.getIconWidth(),icon.getIconHeight());
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);
        this.setVisible(true);

        //退出改程序，
        btnOut.addActionListener(e -> {
            new OutOrNotDialog("确认退出？");
        });

        //返回到之前的选择程序界面，但是要添加一个方法，即：用户在录入信息时，没有录入完整。将弹出窗口，提示，并不写入数据库当中
        btnBack.addActionListener(e -> {
            this.dispose();
            new ChoiceFrame();
        });

        btnAdd.addActionListener(e->{
            dispose();
            new SalaryAddFrame();
        });
        btnRemove.addActionListener(e->{
            dispose();
            new SalaryRemoveFrame();
        });
        btnUpdate.addActionListener(e->{
            dispose();
            new SalaryUpdateFrame();
        });
        btnLook.addActionListener(e->{
            dispose();
            new SalaryLookFrame();
        });
    }

    public static void main(String[] args) {
        new SalaryFrame();
    }

}

