package GUIManager.MyFrame.Employee;

import GUIManager.AllDialog.MyDialog;
import GUIManager.AllDialog.OutOrNotDialog;
import GUIManager.GUIUtils.FontHint;
import GUIManager.MyFrame.EmployeeFrame;
import JDBCUtils.EmployeeUtils;
import JDBCUtils.SGUtils;
import UserData.Employees;
import UserData.SalaryGrade;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class EmployeeAddFrame extends JFrame {

    private JPanel contentPane;
    private JButton btnBack;
    private JButton btnOut;
    private JButton btnConfirm;
    private JRadioButton btn_boy;
    private JRadioButton btn_girl;
    private JTextField id;
    private JTextField name;
    private JTextField startDate;
    private JComboBox salaryLevel;
    private Employees emp;

    private int levelSize;

    private static final String PROMPT_ID = "电话/邮箱...";
    private static final String PROMPT_NAME = "你的名字";
    private static final String PROMPT_LEVEL = "工资等级，例如'1','2'..";
    private static final String PROMPT_DATE = "初始年月";

    public EmployeeAddFrame() {
        levelSize = SGUtils.getSize();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon=new ImageIcon("Mission1/pic/p1.jpg");
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
        note.setForeground(Color.BLACK);
        note.setBounds(151, 20, 100, 15);
        contentPane.add(note);

        JLabel lblNewLabel = new JLabel("id:");
        lblNewLabel.setOpaque(false);
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setBounds(50, 54, 58, 15);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("姓名:");
        lblNewLabel_1.setOpaque(false);
        lblNewLabel_1.setForeground(Color.BLACK);
        lblNewLabel_1.setBounds(50, 103, 58, 15);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("性别:");
        lblNewLabel_2.setOpaque(false);
        lblNewLabel_2.setForeground(Color.BLACK);
        lblNewLabel_2.setBounds(50, 157, 58, 15);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("初始年月:");
        lblNewLabel_3.setOpaque(false);
        lblNewLabel_3.setForeground(Color.BLACK);
        lblNewLabel_3.setBounds(50, 208, 58, 15);
        contentPane.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("工资等级:");
        lblNewLabel_4.setOpaque(false);
        lblNewLabel_4.setForeground(Color.BLACK);
        lblNewLabel_4.setBounds(50, 278, 58, 15);
        contentPane.add(lblNewLabel_4);

        id = new JTextField();
        id.setOpaque(false);
        id.addFocusListener(new FontHint("电话/邮箱...", id));
        id.setBounds(139, 51, 155, 21);
        contentPane.add(id);
        id.setColumns(10);

        name = new JTextField();
        name.setOpaque(false);
        name.addFocusListener(new FontHint("你的名字", name));
        name.setBounds(139, 100, 155, 21);
        contentPane.add(name);
        name.setColumns(10);

        ButtonGroup bg = new ButtonGroup();

        btn_boy = new JRadioButton("男");
        btn_boy.setOpaque(false);
        bg.add(btn_boy);
        btn_boy.setBounds(139, 153, 50, 23);
        contentPane.add(btn_boy);

        btn_girl = new JRadioButton("女");
        btn_girl.setOpaque(false);
        bg.add(btn_girl);
        btn_girl.setBounds(191, 153, 50, 23);
        contentPane.add(btn_girl);

        startDate = new JTextField();
        startDate.setOpaque(false);
        startDate.setForeground(Color.BLACK);
        startDate.addFocusListener(new FontHint("初始年月", startDate));
        startDate.setBounds(139, 205, 155, 21);
        contentPane.add(startDate);
        startDate.setColumns(10);

        salaryLevel = new JComboBox();
        salaryLevel.setOpaque(false);
        salaryLevel.setBounds(139, 275, 155, 21);
        for (int i = 0; i <= levelSize; i++) {
            if (i == 0) {
                salaryLevel.addItem(PROMPT_LEVEL);
            } else {
                salaryLevel.addItem(i + "");
            }
        }
        contentPane.add(salaryLevel);

        btnBack = new JButton("返回");
        btnBack.setOpaque(false);
        btnBack.setBounds(45, 353, 60, 23);
        contentPane.add(btnBack);

        btnConfirm = new JButton("添加");
        btnConfirm.setOpaque(false);
        btnConfirm.setBounds(160, 353, 60, 23);
        contentPane.add(btnConfirm);

        btnOut = new JButton("退出");
        btnOut.setOpaque(false);
        btnOut.setBounds(275, 353, 60, 23);
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
            new EmployeeFrame();
        });
        btnOut.addActionListener(e -> {
            new OutOrNotDialog("是否退出？");
        });

        btnConfirm.addActionListener(e -> {
            if (!judgment()) {
                new MyDialog("请完善你的信息");
            } else {
                addUser();
            }
        });
    }

    public void addUser() {
        emp = new Employees();
        if (judge(id.getText().trim())) {
            this.emp.setId(id.getText().trim());
            this.emp.setName(name.getText().trim());
            if (btn_boy.isSelected()) {
                this.emp.setSex("男");
            }
            if (btn_girl.isSelected()) {
                this.emp.setSex("女");
            }
            this.emp.setStartDate(startDate.getText().trim());
            this.emp.setSalaryLevel(salaryLevel.getSelectedIndex()+ "");
            EmployeeUtils.AddEmployee(emp);
            Employees e = EmployeeUtils.SearchEmployee(id.getText().trim());
            if (e.equals(emp)) {
                new MyDialog("录入信息成功");
            } else {
                new MyDialog("录入信息失败,请重新录入");
            }
        } else {
            new MyDialog("此id已经被使用，请输入其他的id。");
        }
    }

    /**
     * @param id id.getText();
     * @return 没有返回true，有返回false
     */
    public boolean judge(String id) {
        Employees e = EmployeeUtils.SearchEmployee(id);
        if (e.getId() == null) {
            return true;
        } else {
            return false;
        }
    }


    public boolean judgment() {
        boolean flagId, flagName, flagSex, flagLevel, flagDate;
        if (id.getText().trim().equals(PROMPT_ID)) {
            flagId = false;
        } else {
            flagId = true;
        }
        if (name.getText().trim().equals(PROMPT_NAME)) {
            flagName = false;
        } else {
            flagName = true;
        }
        if (btn_boy.isSelected() || btn_girl.isSelected()) {
            flagSex = true;
        } else {
            flagSex = false;
        }
        if (startDate.getText().trim().equals(PROMPT_DATE)) {
            flagDate = false;
        } else {
            flagDate = true;
        }
        if (salaryLevel.getSelectedIndex() == 0) {
            flagLevel = false;
        } else {
            flagLevel = true;
        }

        if (flagId && flagName && flagLevel && flagDate && flagSex) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        new EmployeeAddFrame();
    }

}
