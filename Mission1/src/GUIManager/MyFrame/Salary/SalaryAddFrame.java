package GUIManager.MyFrame.Salary;

import GUIManager.AllDialog.MyDialog;
import GUIManager.AllDialog.OutOrNotDialog;
import GUIManager.GUIUtils.FontHint;
import GUIManager.MyFrame.EmployeeFrame;
import GUIManager.MyFrame.SalaryFrame;
import JDBCUtils.EmployeeUtils;
import JDBCUtils.VWUtils;
import UserData.Employees;
import UserData.VariableWage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SalaryAddFrame extends JFrame {

    private JPanel contentPane;
    private JButton btnBack;
    private JButton btnOut;
    private JButton btnConfirm;
    private JTextField id;
    private JComboBox month;
    private JTextField reward;
    private JTextField fine;
    private VariableWage vw;
    private int Month;

    private static final String PROMPT_ID = "电话/邮箱...";
    private static final String PROMPT_SALARY = "...元";


    public SalaryAddFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon=new ImageIcon("Mission1/pic/p2.jpg");
        JLabel label = new JLabel(icon);//往一个标签中加入图片
        label.setBounds(0, 0, icon.getIconWidth(),icon.getIconHeight());//设置标签位置大小为图片大小
        this.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));//标签添加到第二层面板
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel imPanel = (JPanel)getContentPane();
        imPanel.setOpaque(false);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setOpaque(false);

        JLabel note = new JLabel("请录入基本信息");
        note.setOpaque(false);
        note.setBounds(162, 20, 100, 15);
        contentPane.add(note);

        JLabel lblNewLabel = new JLabel("id:");
        lblNewLabel.setOpaque(false);
        lblNewLabel.setBounds(80, 60, 58, 15);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("月份:");
        lblNewLabel_1.setOpaque(false);
        lblNewLabel_1.setBounds(80, 140, 58, 15);
        contentPane.add(lblNewLabel_1);


        JLabel lblNewLabel_3 = new JLabel("奖励:");
        lblNewLabel_3.setOpaque(false);
        lblNewLabel_3.setBounds(80, 220, 58, 15);
        contentPane.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("罚款:");
        lblNewLabel_4.setOpaque(false);
        lblNewLabel_4.setBounds(80, 300, 58, 15);
        contentPane.add(lblNewLabel_4);

        id = new JTextField();
        id.setOpaque(false);
        id.addFocusListener(new FontHint("电话/邮箱...", id));
        id.setBounds(160, 60, 155, 21);
        contentPane.add(id);
        id.setColumns(10);

        month = new JComboBox();
        for(int i = 0;i<=12;i++){
            if(i == 0){
                month.addItem("选择月份");
            }else {
                month.addItem(i + "");
            }
        }
        month.setOpaque(false);
        month.setBounds(160, 140, 155, 21);
        contentPane.add(month);


        reward = new JTextField();
        reward.setOpaque(false);
        reward.addFocusListener(new FontHint("...元", reward));
        reward.setBounds(160, 220, 155, 21);
        contentPane.add(reward);
        reward.setColumns(10);

        fine = new JTextField();
        fine.setOpaque(false);
        fine.addFocusListener(new FontHint("...元", fine));
        fine.setBounds(160, 300, 155, 21);
        contentPane.add(fine);
        fine.setColumns(10);

        btnBack = new JButton("返回");
        btnBack.setOpaque(false);
        btnBack.setBounds(60, 353, 60, 23);
        contentPane.add(btnBack);

        btnConfirm = new JButton("添加");
        btnConfirm.setOpaque(false);
        btnConfirm.setBounds(180, 353, 60, 23);
        contentPane.add(btnConfirm);

        btnOut = new JButton("退出");
        btnOut.setOpaque(false);
        btnOut.setBounds(300, 353, 60, 23);
        contentPane.add(btnOut);

        imPanel.add(contentPane,BorderLayout.CENTER);

        this.setResizable(false);
        this.setSize(icon.getIconWidth(),icon.getIconHeight());
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation( (int) (width - this.getWidth()) / 2,(int) (height - this.getHeight()) / 2);
        this.setVisible(true);

        btnBack.addActionListener(e -> {
            this.dispose();
            new SalaryFrame();
        });
        btnOut.addActionListener(e -> {
            new OutOrNotDialog("是否退出？");
        });

        btnConfirm.addActionListener(e -> {
            if(!judgment()){
                new MyDialog("请完善信息");
            }else {
                if (judge(id.getText().trim()) == null) {
                    new MyDialog("职工信息库中无此id，请重新输入！");
                } else {
                    if (!judgeMonth()) {
                        if((Month>=1&&Month<=12)) {
                            addSalary();
                        }else{
                            new MyDialog("请输入正确的月份");
                        }
                    }else{
                        new MyDialog("此月份已经录入,无法录入！！");
                    }
                }
            }
        });

    }

    public void addSalary() {
        vw = new VariableWage();
        vw.setEmployee_id(id.getText().trim());
        vw.setMonth(Integer.parseInt(month.getSelectedIndex()+1+""));
        vw.setRewardSalary(Double.parseDouble(reward.getText().trim()));
        vw.setFine(Double.parseDouble(fine.getText().trim()));
        VWUtils.AddNewVWSalary(vw);
    }

    public boolean judgment() {
        if (!(id.getText().trim().equals(PROMPT_ID)) &&
                !(month.getSelectedIndex() == 0) &&
                !(fine.getText().trim().equals(PROMPT_SALARY)) &&
                !(reward.getText().trim().equals(PROMPT_SALARY))) {
            return true;
        } else {
            return false;
        }
    }

    public String  judge(String id){
        return EmployeeUtils.SearchEmployee(id).getId();
    }

    public boolean judgeMonth(){
        List<VariableWage> list;
        int month = this.month.getSelectedIndex();
        Month = this.month.getSelectedIndex();
        list = VWUtils.Search(id.getText().trim());
        for(int i=0;i<list.size();i++){
            if(list.get(i).getMonth() == month){
                return true;
            }
        }
        return false;
    }
}

