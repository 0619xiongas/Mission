package GUIManager.MyFrame.Salary;


import GUIManager.AllDialog.MyDialog;
import GUIManager.AllDialog.OutOrNotDialog;
import GUIManager.MyFrame.EmployeeFrame;
import GUIManager.MyFrame.SalaryFrame;
import JDBCUtils.EmployeeUtils;
import JDBCUtils.VWUtils;
import UserData.VariableWage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SalaryLookFrame extends JFrame {

    private JPanel contentPane;
    private JTextField id;
    private JButton btnOK;
    private JButton btnBack;
    private JButton btnOut;
    private JTextArea textArea;

    private List<VariableWage> list;

    public SalaryLookFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon=new ImageIcon("Mission1/pic/p3.jpg");
        JLabel label = new JLabel(icon);//往一个标签中加入图片
        label.setBounds(0, 0, icon.getIconWidth(),icon.getIconHeight());//设置标签位置大小为图片大小
        this.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));//标签添加到第二层面板
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel imPanel = (JPanel)getContentPane();
        imPanel.setOpaque(false);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setOpaque(false);

        JLabel lblNewLabel = new JLabel("请输入id查询对应的信息");
        lblNewLabel.setOpaque(false);
        lblNewLabel.setBounds(250, 10, 140, 31);
        contentPane.add(lblNewLabel);

        id = new JTextField();
        id.setOpaque(false);
        id.setBounds(250, 37, 140, 21);
        contentPane.add(id);
        id.setColumns(10);

        btnOK = new JButton("确定");
        btnOK.setOpaque(false);
        btnOK.setBounds(290, 68, 60, 23);
        contentPane.add(btnOK);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setOpaque(false);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(35,100,550,220);

        contentPane.add(scrollPane);
        textArea = new JTextArea();
        textArea.setOpaque(false);
        textArea.setText("\tid\t月份\t奖励\t罚款\n");
        scrollPane.setViewportView(textArea);

        btnBack = new JButton("返回");
        btnBack.setOpaque(false);
        btnBack.setBounds(65, 340, 60, 23);
        contentPane.add(btnBack);

        btnOut = new JButton("退出");
        btnOut.setOpaque(false);
        btnOut.setBounds(480, 340, 60, 23);
        contentPane.add(btnOut);

        imPanel.add(contentPane,BorderLayout.CENTER);

        this.setResizable(false);
        this.setSize(icon.getIconWidth(),icon.getIconHeight());
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation( (int) (width - this.getWidth()) / 2,(int) (height - this.getHeight()) / 2);
        this.setVisible(true);

        btnBack.addActionListener(e->{
            this.dispose();
            new SalaryFrame();
        });
        btnOut.addActionListener(e->{
            new OutOrNotDialog("是否退出？");
        });
        btnOK.addActionListener(e->{
            if(judge(id.getText().trim())) {
                getSalary();
            }else {
                if(id.getText().trim().equals("")){
                    new MyDialog("请输入id");
                }else {
                    new MyDialog("无此职工的信息，请检查你输入的id");
                }
            }
        });
    }

    public void getSalary(){
        list = VWUtils.Search(id.getText().trim());
        if(list.size() == 0){
            new MyDialog("此职员工资变动信息条数为0");
        }else {
            for (int i = 0; i < list.size(); i++) {
                VariableWage v = list.get(i);
                textArea.append("\t"+v.getEmployee_id() + "\t" + v.getMonth() + "\t" + v.getRewardSalary() + "\t" + v.getFine() + "\n");
            }
        }
    }
    public boolean judge(String id){
        String ID = EmployeeUtils.SearchEmployee(id).getId();
        if(ID == null){
            return false;
        }else {
            return true;
        }
    }

}
