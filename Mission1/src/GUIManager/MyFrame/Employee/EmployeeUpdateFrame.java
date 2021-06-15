package GUIManager.MyFrame.Employee;

import GUIManager.AllDialog.MyDialog;
import GUIManager.AllDialog.OutOrNotDialog;
import GUIManager.GUIUtils.FontHint;
import GUIManager.MyFrame.EmployeeFrame;
import JDBCUtils.EmployeeUtils;
import JDBCUtils.SGUtils;
import UserData.Employees;
import UserData.SalaryGrade;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class EmployeeUpdateFrame extends JFrame {
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
    private JButton btnUpdate;
    private int levelSize;

    private Employees empBefore;
    private Employees empAfter;


    public EmployeeUpdateFrame() {
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

        JLabel note = new JLabel("请输入id进行用户信息更改");
        note.setOpaque(false);
        note.setForeground(Color.BLACK);
        note.setBounds(111, 20, 180, 15);
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
        name.setForeground(Color.RED);
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
        for (int i = 1; i <= levelSize; i++) {
                salaryLevel.addItem(i + "");
        }
        contentPane.add(salaryLevel);

        btnBack = new JButton("返回");
        btnBack.setBounds(20, 353, 60, 23);
        contentPane.add(btnBack);

        btnUpdate = new JButton("修改");
        btnUpdate.setEnabled(false);
        btnUpdate.setBounds(112, 353, 60, 23);
        contentPane.add(btnUpdate);

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
            dispose();
            new EmployeeFrame();
        });

        btnOut.addActionListener(e -> {
            new OutOrNotDialog("是否退出？");
        });
        btnConfirm.addActionListener(e -> {
            empBefore = GetUser();
            if (empBefore.getId() != null) {
                new MyDialog("可以进行修改了");
                btnUpdate.setEnabled(true);
            } else {
                btnUpdate.setEnabled(false);
            }
        });
        btnUpdate.addActionListener(e -> {
            showFun();
            boolean flag = true;
            if(empBefore.equals(empAfter)){
                new MyDialog("你尚未修改信息");
                flag = false;
            }else if(name.getText().equals("你的名字")||startDate.getText().equals("初始年月")){
                new MyDialog("请完善你的信息");
                flag = false;
            }else if(!id.getText().equals(empBefore.getId())){
                new MyDialog("你无法更改你的id号");
                flag =false;
            }
            if(flag) {
                if (getNewId(id.getText().trim())) {
                        UpdateUser();
                    Employees emp = EmployeeUtils.SearchEmployee(id.getText().trim());
                    if (emp.equals(empAfter)) {
                        new MyDialog("修改成功!");
                    }
                }
            }
        });
    }

    public Employees GetUser() {
        Employees e = new Employees();
        if (id.getText().trim().equals("")) {
            new MyDialog("请输入id");
        } else {
            e = EmployeeUtils.SearchEmployee(id.getText().trim());
            if (e.getId() == null) {
                new MyDialog("无此人的消息，请重新输入或者退出");

            } else {
                name.setText(e.getName());
                salaryLevel.setSelectedItem(e.getSalaryLevel());
                startDate.setText(e.getStartDate());
                if (e.getSex().equals("男")) {
                    btn_boy.setSelected(true);
                } else {
                    btn_girl.setSelected(true);
                }
            }
            return e;
        }
        return e;
    }

    /**
     * 更新数据的方法，这里面的数据已经是经过判断了的，不必进行判断，只进行修改数据步骤即可，不必考虑数据的准确性
     */
    public void UpdateUser() {
        String id,name,sex,level,date;
        id = this.id.getText().trim();
        name = this.name.getText().trim();
        level = this.salaryLevel.getSelectedIndex()+1+"";
        date = this.startDate.getText().trim();
        if(btn_boy.isSelected()){
            sex = "男";
        }else{
            sex = "女";
        }
        if(!sex.equals(empBefore.getSex())){
            EmployeeUtils.updateData(empBefore.getId(),sex,"sex");
        }
        if(!name.equals(empBefore.getName())){
            EmployeeUtils.updateData(empBefore.getId(),name,"name");
        }
        if(!level.equals(empBefore.getSalaryLevel())){
            EmployeeUtils.updateData(empBefore.getId(),level,"salaryLevel");
        }
        if(!date.equals(empBefore.getStartDate())){
            EmployeeUtils.updateData(empBefore.getId(),date,"startDate");
        }
        if(!id.equals(empBefore.getId())){
            EmployeeUtils.updateData(empBefore.getId(),id,"ID");
        }
    }

    public void showFun(){
        empAfter = new Employees();
        empAfter.setId(id.getText().trim());
        empAfter.setName(name.getText().trim());
        empAfter.setSalaryLevel(salaryLevel.getSelectedIndex()+1+"");
        empAfter.setStartDate(startDate.getText().trim());
        if(btn_boy.isSelected()){
            empAfter.setSex("男");
        }else{
            empAfter.setSex("女");
        }
    }

    public boolean getNewId(String strid){
        Employees e = EmployeeUtils.SearchEmployee(strid);
        // 说明此时的id跟之前一样，或者压根不存在
        if(e.getId().equals(empBefore.getId()) || e.getId() == null){
            return true;
        }else{
            return false;
        }
    }


    public static void main(String[] args) {
        new EmployeeUpdateFrame();
    }
}
