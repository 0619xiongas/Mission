package GUIManager.MyFrame;

import GUIManager.AllDialog.OutOrNotDialog;
import GUIManager.MyFrame.Employee.EmployeeAddFrame;
import GUIManager.MyFrame.Employee.EmployeeLookFrame;
import GUIManager.MyFrame.Employee.EmployeeRemoveFrame;
import GUIManager.MyFrame.Employee.EmployeeUpdateFrame;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * 此类是职工的信息管理模块，
 * 在添加中，只添加id，姓名，性别，初始年月，工资等级，至于变动工资，暂且全都设置成默认值,月份0月，返款0.00 奖励0.00
 * 这里有个条件，必须得先存在工资等级才能添加。
 */
public class EmployeeFrame extends JFrame {

    private JPanel contentPane;
    private JButton btnBack;
    private JButton btnOut;
    private JButton btnAdd;
    private JButton btnRemove;
    private JButton btnUpdate;
    private JButton btnLook;

    public EmployeeFrame() {
        this.setTitle("工资管理系统---职工基本信息管理系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon=new ImageIcon("Mission1/pic/Employee.jpg");
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

        btnLook = new JButton("查看员工信息");
        btnLook.setOpaque(false);
        btnLook.setBounds(260, 60, 120, 20);
        contentPane.add(btnLook);

        btnUpdate = new JButton("修改员工信息");
        btnUpdate.setOpaque(false);
        btnUpdate.setBounds(260, 120, 120, 20);
        contentPane.add(btnUpdate);

        btnRemove = new JButton("删除员工信息");
        btnRemove.setOpaque(false);
        btnRemove.setBounds(260, 180, 120, 20);
        contentPane.add(btnRemove);

        btnAdd = new JButton("添加员工信息");
        btnAdd.setOpaque(false);
        btnAdd.setBounds(260, 240, 120, 20);
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
            this.dispose();
            new EmployeeAddFrame();
        });
        btnRemove.addActionListener(e->{
            this.dispose();
            new EmployeeRemoveFrame();
        });
        btnUpdate.addActionListener(e->{
            this.dispose();
            new EmployeeUpdateFrame();
        });
        btnLook.addActionListener(e->{
            this.dispose();
            new EmployeeLookFrame();
        });
    }


}
