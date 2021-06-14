package GUIManager.MyFrame.Employee;

import GUIManager.AllDialog.MyDialog;
import GUIManager.AllDialog.OutOrNotDialog;
import GUIManager.MyFrame.EmployeeFrame;
import JDBCUtils.EmployeeUtils;
import JDBCUtils.VWUtils;
import UserData.Employees;
import UserData.VariableWage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class EmployeeRemoveFrame extends JFrame {
    private JPanel contentPane;
    private JButton btnBack;
    private JButton btnOut;
    private JButton btnConfirm;
    private JRadioButton btn_boy;
    private JRadioButton btn_girl;
    private JTextField id;
    private JTextField name;
    private JTextField startDate;
    private JTextField salaryLevel;
    private JButton btnRemove;


    public EmployeeRemoveFrame() {
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

        JLabel note = new JLabel("输入要查询的id号进行删除:");
        note.setOpaque(false);
        note.setForeground(Color.BLACK);
        note.setBounds(141, 20, 180, 15);
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
        id.setBounds(139, 51, 155, 21);
        contentPane.add(id);
        id.setColumns(10);

        name = new JTextField();
        name.setOpaque(false);
        name.setBounds(139, 100, 155, 21);
        contentPane.add(name);
        name.setColumns(10);

        ButtonGroup bg = new ButtonGroup();

        btn_boy = new JRadioButton("男");
        bg.add(btn_boy);
        btn_boy.setOpaque(false);
        btn_boy.setBounds(139, 153, 50, 23);
        contentPane.add(btn_boy);

        btn_girl = new JRadioButton("女");
        bg.add(btn_girl);
        btn_girl.setOpaque(false);
        btn_girl.setBounds(191, 153, 50, 23);
        contentPane.add(btn_girl);

        startDate = new JTextField();
        startDate.setOpaque(false);
        startDate.setBounds(139, 205, 155, 21);
        contentPane.add(startDate);
        startDate.setColumns(10);

        salaryLevel = new JTextField();
        salaryLevel.setOpaque(false);
        salaryLevel.setBounds(139, 275, 155, 21);
        contentPane.add(salaryLevel);
        salaryLevel.setColumns(10);

        btnBack = new JButton("返回");
        btnBack.setBounds(20, 353, 60, 23);
        contentPane.add(btnBack);

        btnRemove = new JButton("删除");
        btnRemove.setBounds(112, 353, 60, 23);
        contentPane.add(btnRemove);

        btnConfirm = new JButton("确定");
        btnConfirm.setBounds(204, 353, 60, 23);
        contentPane.add(btnConfirm);

        btnOut = new JButton("退出");
        btnOut.setBounds(296, 353, 60, 23);
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
            //todo  将要删除的人的信息显示出来,相当于查询的操作嘛
            findUser();
        });
        btnRemove.addActionListener(e->{
            MyDialog dialog = new MyDialog("此操作也会对变动工资信息进行相关删除，是否删除？");
            dialog.getOkButton().addActionListener(e1->{
                SubUser();
                clean();
            });
        });
    }

    public void SubUser() {
        List<VariableWage> list = VWUtils.Search(id.getText().trim());
        if(list.size() != 0){
            VWUtils.SubVWSalary(id.getText().trim(),0);
        }
        EmployeeUtils.SubEmployee(id.getText().trim());
        if(judge(id.getText().trim())){
            new MyDialog("删除成功！！！");
        }else {
            new MyDialog("删除失败！！！");
        }

    }
    public boolean judge(String id){
        Employees e = EmployeeUtils.SearchEmployee(id);
        if(e.getId() == null){
            return true;
        }else{
            return false;
        }
    }

    public void findUser() {
        Employees e;
        if (id.getText().trim().equals("")) {
            new MyDialog("请输入id");
        } else {
            e = EmployeeUtils.SearchEmployee(id.getText().trim());
            if (e.getId() == null) {
                new MyDialog("无此人的消息，请重新输入或者退出");
            } else {
                name.setText(e.getName());
                salaryLevel.setText(e.getSalaryLevel());
                startDate.setText(e.getStartDate());
                if (e.getSex().equals("男")) {
                    btn_boy.setSelected(true);
                } else {
                    btn_girl.setSelected(true);
                }
            }
        }
    }
    public void clean(){
        id.setText("");
        name.setText("");
        salaryLevel.setText("");
        startDate.setText("");
        btn_boy.setSelected(false);
        btn_girl.setSelected(false);
    }

    public static void main(String[] args) {
        new EmployeeRemoveFrame();
    }

}


