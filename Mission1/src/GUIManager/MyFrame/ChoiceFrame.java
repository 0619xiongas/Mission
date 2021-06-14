package GUIManager.MyFrame;


import GUIManager.AllDialog.ChoiceDialog;
import GUIManager.AllDialog.OutOrNotDialog;
import com.sun.corba.se.spi.ior.IdentifiableFactory;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ChoiceFrame extends JFrame {

    private JPanel contentPane;

    private JButton jb_Confirm;  //  确定按钮

    private JButton jb_Cancel;  //取消按钮

    private JRadioButton rb_employee;  // 单选按钮

    private JRadioButton rb_salaryLel;  //单选按钮

    private JRadioButton rb_salary;   //单选按钮

    private JRadioButton rb_sumSalary;  // 单选按钮


    //原来事677，446，现在是 640 ，426
    public ChoiceFrame() {
        this.setTitle("工资管理系统---选择");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon=new ImageIcon("Mission1/pic/choice.jpg");
        JLabel label = new JLabel(icon);//往一个标签中加入图片
        label.setBounds(0, 0, icon.getIconWidth(),icon.getIconHeight());//设置标签位置大小为图片大小
        this.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));//标签添加到第二层面板

        JPanel imPanel = (JPanel)getContentPane();
        imPanel.setOpaque(false);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setOpaque(false);

        JLabel lblNewLabel = new JLabel("请选择你要进入的管理系统");
        lblNewLabel.setVerticalAlignment(SwingConstants.CENTER);
        lblNewLabel.setOpaque(false);
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setBounds(241, 42, 200, 23);
        contentPane.add(lblNewLabel);

        ButtonGroup bg1 = new ButtonGroup();

        rb_employee = new JRadioButton("职工基本信息管理");
        rb_employee.setBounds(255, 100, 150, 23);
        rb_employee.setOpaque(false);
        bg1.add(rb_employee);
        contentPane.add(rb_employee);

        rb_salaryLel = new JRadioButton("工资等级情况管理");
        rb_salaryLel.setOpaque(false);
        rb_salaryLel.setBounds(255, 150, 150, 23);
        bg1.add(rb_salaryLel );
        contentPane.add(rb_salaryLel);

        rb_salary = new JRadioButton("职工变动工资管理");
        rb_salary.setOpaque(false);
        rb_salary.setBounds(255, 200, 150, 23);
        bg1.add(rb_salary);
        contentPane.add(rb_salary);


        rb_sumSalary = new JRadioButton("工资汇总查询");
        rb_sumSalary.setOpaque(false);
        rb_sumSalary.setBounds(255,250,150,23);
        bg1.add(rb_sumSalary);
        contentPane.add(rb_sumSalary);


        jb_Confirm = new JButton("确定");
        jb_Confirm.setOpaque(false);
        jb_Confirm.setBounds(160, 320, 97, 23);
        jb_Confirm.setBorder(null);
        jb_Confirm.setForeground(Color.black);
        contentPane.add(jb_Confirm);

        jb_Cancel = new JButton("取消");
        jb_Cancel.setOpaque(false);
        jb_Cancel.setBorder(null);
        jb_Cancel.setForeground(Color.BLACK);
        jb_Cancel.setBounds(400, 320, 97, 23);
        contentPane.add(jb_Cancel);

        imPanel.add(contentPane,BorderLayout.CENTER);

        this.setResizable(false);
        this.setSize(icon.getIconWidth(),icon.getIconHeight());
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation( (int) (width - this.getWidth()) / 2,(int) (height - this.getHeight()) / 2);
        this.setVisible(true);

        rb_employee.addActionListener(e->{
            lblNewLabel.setText("你选择了职工基本信息管理系统");
        });
        rb_salaryLel.addActionListener(e->{
            lblNewLabel.setText("你选择了工资等级情况管理系统");
        });
        rb_salary.addActionListener(e->{
            lblNewLabel.setText("你选择了职工变动工资管理系统");
        });
        rb_sumSalary.addActionListener(e->{
            lblNewLabel.setText("你选择了工资汇总查询");
        });

        jb_Confirm.addActionListener(e->{
            if(rb_employee.isSelected()){
                this.dispose();
                new EmployeeFrame();
            }
            if(rb_salaryLel.isSelected()){
                this.dispose();
                new SalaryLelFrame();
            }
            if(rb_salary.isSelected()){
                this.dispose();
                new SalaryFrame();
            }
            if(rb_sumSalary.isSelected()){
                this.dispose();
                new ChoiceDialog();
            }
        });
        jb_Cancel.addActionListener(e->{
        new OutOrNotDialog("确定要退出吗?");
        });
    }

}
