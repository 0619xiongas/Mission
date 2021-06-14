package GUIManager.MyFrame.SalaryLel;

import GUIManager.AllDialog.MyDialog;
import GUIManager.AllDialog.OutOrNotDialog;
import GUIManager.MyFrame.SalaryLelFrame;
import JDBCUtils.SGUtils;
import UserData.SalaryGrade;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SalaryLevelUpdateFrame extends JFrame {

    private JPanel contentPane;
    private JButton btnBack;
    private JButton btnOut;
    private JButton btnConfirm;
    private JButton btnUpdate;
    private JComboBox salaryLel;
    private JTextField basicSalary;
    private JTextField jobSalary;
    private JTextField trafficSalary;
    private int levelSize;

    private SalaryGrade sgBefore;
    private SalaryGrade sgAfter;

    public SalaryLevelUpdateFrame() {
        levelSize = SGUtils.getSize();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon=new ImageIcon("Mission1/pic/p4.jpg");
        JLabel label = new JLabel(icon);//往一个标签中加入图片
        label.setBounds(0, 0, icon.getIconWidth(),icon.getIconHeight());//设置标签位置大小为图片大小
        this.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));//标签添加到第二层面板
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel imPanel = (JPanel)getContentPane();
        imPanel.setOpaque(false);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setOpaque(false);

        JLabel note = new JLabel("请输入要更改的等级级别:");
        note.setOpaque(false);
        note.setBounds(140, 20, 150, 15);
        contentPane.add(note);

        JLabel lblNewLabel = new JLabel("工资等级:");
        lblNewLabel.setOpaque(false);
        lblNewLabel.setBounds(80, 60, 58, 15);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("基础工资:");
        lblNewLabel_1.setOpaque(false);
        lblNewLabel_1.setBounds(80, 140, 58, 15);
        contentPane.add(lblNewLabel_1);


        JLabel lblNewLabel_3 = new JLabel("岗位工资:");
        lblNewLabel_3.setOpaque(false);
        lblNewLabel_3.setBounds(80, 220, 58, 15);
        contentPane.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("交通补贴:");
        lblNewLabel_4.setOpaque(false);
        lblNewLabel_4.setBounds(80, 300, 58, 15);
        contentPane.add(lblNewLabel_4);

        salaryLel = new JComboBox();
        salaryLel.setOpaque(false);
        for(int i=0;i<=levelSize;i++){
            if(i==0){
                salaryLel.addItem("选择等级");
            }else {
                salaryLel.addItem(i+"");
            }
        }
        salaryLel.setBounds(160, 60, 155, 21);
        contentPane.add(salaryLel);

        basicSalary = new JTextField();
        basicSalary.setOpaque(false);
        basicSalary.setBounds(160, 140, 155, 21);
        contentPane.add(basicSalary);
        basicSalary.setColumns(10);


        jobSalary = new JTextField();
        jobSalary.setOpaque(false);
        jobSalary.setBounds(160, 220, 155, 21);
        contentPane.add(jobSalary);
        jobSalary.setColumns(10);

        trafficSalary = new JTextField();
        trafficSalary.setOpaque(false);
        trafficSalary.setBounds(160, 300, 155, 21);
        contentPane.add(trafficSalary);
        trafficSalary.setColumns(10);

        btnBack= new JButton("返回");
        btnBack.setBounds(30, 353, 60, 23);
        contentPane.add(btnBack);

        btnConfirm = new JButton("确定");
        btnConfirm.setBounds(130, 353, 60, 23);
        contentPane.add(btnConfirm);

        btnOut = new JButton("退出");
        btnOut.setBounds(330, 353, 60, 23);
        contentPane.add(btnOut);

        btnUpdate = new JButton("修改");
        btnUpdate.setEnabled(false);
        btnUpdate.setBounds(230, 353, 60, 23);
        contentPane.add(btnUpdate);

        imPanel.add(contentPane,BorderLayout.CENTER);

        this.setResizable(false);
        this.setSize(icon.getIconWidth(),icon.getIconHeight());
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation( (int) (width - this.getWidth()) / 2,(int) (height - this.getHeight()) / 2);
        this.setVisible(true);

        btnBack.addActionListener(e->{
            dispose();
            new SalaryLelFrame();
        });
        btnOut.addActionListener(e->{
           new OutOrNotDialog("是否退出？");
        });
        btnConfirm.addActionListener(e->{
            salaryLel.setEditable(false);
            sgBefore = getLevel();
            if(sgBefore.getSalaryLevel() != null){
                new MyDialog("可以进行修改了");
                btnUpdate.setEnabled(true);
            }else {
                btnUpdate.setEnabled(false);
            }
        });

        btnUpdate.addActionListener(e->{
            showFun();
            boolean f = true;
            if(sgBefore.equals(sgAfter)){
                new MyDialog("你尚未修改信息");
                f = false;
            }
            if(f){
                UpdateLevel();
                SalaryGrade s = SGUtils.SearchSLel(salaryLel.getSelectedIndex()+"");
                if(s.equals(sgAfter)){
                    new MyDialog("修改成功");
                    salaryLel.setEditable(true);
                }
            }
        });
    }
    public SalaryGrade getLevel(){
        SalaryGrade s = new SalaryGrade();
        if(salaryLel.getSelectedIndex() == 0){
            new MyDialog("请选择等级");
        }else {
            s = SGUtils.SearchSLel(salaryLel.getSelectedIndex()+"");
            if(s.getSalaryLevel() == null){
                new MyDialog("无此工资等级的信息，请重新输入");
            }else {
                jobSalary.setText(String.valueOf(s.getJobSalary()));
                basicSalary.setText(String.valueOf(s.getBasicSalary()));
                trafficSalary.setText(String.valueOf(s.getTrafficSalary()));
            }
            return s;
        }
        return s;
    }

    public void UpdateLevel(){
        double job,basic,traffic;
        job = Double.parseDouble(jobSalary.getText().trim());
        basic = Double.parseDouble(basicSalary.getText().trim());
        traffic = Double.parseDouble(trafficSalary.getText().trim());
        if(!(job==sgBefore.getJobSalary())){
            SGUtils.updateDate(sgBefore.getSalaryLevel(),"jobSalary",job);
        }
        if(!(basic==sgBefore.getBasicSalary())){
            SGUtils.updateDate(sgBefore.getSalaryLevel(),"basicSalary",basic);
        }
        if(!(traffic == sgBefore.getTrafficSalary())){
            SGUtils.updateDate(sgBefore.getSalaryLevel(),"trafficSalary",traffic);
        }
    }

    public void showFun(){
        sgAfter = new SalaryGrade();
        sgAfter.setSalaryLevel(salaryLel.getSelectedIndex()+"");
        sgAfter.setJobSalary(Double.parseDouble(jobSalary.getText().trim()));
        sgAfter.setBasicSalary(Double.parseDouble(basicSalary.getText().trim()));
        sgAfter.setTrafficSalary(Double.parseDouble(trafficSalary.getText().trim()));

    }

    public static void main(String[] args) {
        new SalaryLevelUpdateFrame();
    }

}
