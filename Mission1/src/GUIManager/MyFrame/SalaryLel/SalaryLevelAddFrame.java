package GUIManager.MyFrame.SalaryLel;

import GUIManager.AllDialog.MyDialog;
import GUIManager.AllDialog.OutOrNotDialog;
import GUIManager.GUIUtils.FontHint;
import GUIManager.MyFrame.SalaryLelFrame;
import JDBCUtils.SGUtils;
import UserData.SalaryGrade;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SalaryLevelAddFrame extends JFrame {

    private JPanel contentPane;
    private JButton btnBack;
    private JButton btnOut;
    private JButton btnConfirm;
    private JTextField salaryLel;
    private JTextField basicSalary;
    private JTextField jobSalary;
    private JTextField trafficSalary;
    private SalaryGrade salaryGrade;

    private static final String PROMPT_LEVEL = "等级1,2,3....";
    private static final String PROMPT_SALARY = "...元";


    public SalaryLevelAddFrame() {
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

        JLabel note = new JLabel("请录入基本信息");
        note.setOpaque(false);
        note.setBounds(162, 20, 100, 15);
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

        salaryLel = new JTextField();
        salaryLel.setOpaque(false);
        salaryLel.addFocusListener(new FontHint("等级1,2,3....", salaryLel));
        salaryLel.setBounds(160, 60, 155, 21);
        contentPane.add(salaryLel);
        salaryLel.setColumns(10);

        basicSalary = new JTextField();
        basicSalary.setOpaque(false);
        basicSalary.addFocusListener(new FontHint("...元", basicSalary));
        basicSalary.setBounds(160, 140, 155, 21);
        contentPane.add(basicSalary);
        basicSalary.setColumns(10);


        jobSalary = new JTextField();
        jobSalary.setOpaque(false);
        jobSalary.addFocusListener(new FontHint("...元", jobSalary));
        jobSalary.setBounds(160, 220, 155, 21);
        contentPane.add(jobSalary);
        jobSalary.setColumns(10);

        trafficSalary = new JTextField();
        trafficSalary.setOpaque(false);
        trafficSalary.addFocusListener(new FontHint("...元", trafficSalary));
        trafficSalary.setBounds(160, 300, 155, 21);
        contentPane.add(trafficSalary);
        trafficSalary.setColumns(10);

        btnBack = new JButton("返回");
        btnBack.setOpaque(false);
        btnBack.setBounds(60, 380, 60, 23);
        contentPane.add(btnBack);

        btnConfirm = new JButton("添加");
        btnConfirm.setOpaque(false);
        btnConfirm.setBounds(180, 380, 60, 23);
        contentPane.add(btnConfirm);

        btnOut = new JButton("退出");
        btnOut.setOpaque(false);
        btnOut.setBounds(300, 380, 60, 23);
        contentPane.add(btnOut);

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
            if(!judgment()){
                new MyDialog("请完善信息");
            }else{
                addNewLevel();
                clean();
            }
        });

    }
    public void addNewLevel(){
        salaryGrade = new SalaryGrade();
        if(judge(salaryLel.getText().trim())){
            salaryGrade.setSalaryLevel(salaryLel.getText().trim());
            salaryGrade.setJobSalary(Double.parseDouble(jobSalary.getText().trim()));
            salaryGrade.setBasicSalary(Double.parseDouble(basicSalary.getText().trim()));
            salaryGrade.setTrafficSalary(Double.parseDouble(trafficSalary.getText().trim()));
            SGUtils.AddNewLevel(salaryGrade);
        }else {
            new MyDialog("此等级已经在库中,请重新录入");
        }
    }


    public boolean judge(String id){
        SalaryGrade s = SGUtils.SearchSLel(id);
        if(s.getSalaryLevel() == null){
            return true;
        }else {
            return false;
        }
    }
    /**
     * 判断是否完善了信息。
     * @return
     */
    public boolean judgment(){
        boolean level,basic,department,traffic;
        if(salaryLel.getText().trim().equals(PROMPT_LEVEL)){
            level = false;
        }else{
            level = true;
        }
        if(jobSalary.getText().trim().equals(PROMPT_SALARY)){
            department = false;
        }else {
            department = true;
        }
        if(basicSalary.getText().trim().equals(PROMPT_SALARY)){
            basic = false;
        }else{
            basic = true;
        }
        if(trafficSalary.getText().trim().equals(PROMPT_SALARY)){
            traffic = false;
        }else{
            traffic = true;
        }

        if(level && department && basic && traffic){
            return true;
        }else{
            return false;
        }
    }
    public void clean(){
        salaryLel.addFocusListener(new FontHint("等级1,2,3....", salaryLel));
        jobSalary.addFocusListener(new FontHint("...元", jobSalary));
        trafficSalary.addFocusListener(new FontHint("...元", trafficSalary));
        basicSalary.addFocusListener(new FontHint("...元",basicSalary));
    }

    public static void main(String[] args) {
        new SalaryLevelAddFrame();
    }
}
