package GUIManager.MyFrame.Summary;


import GUIManager.AllDialog.OutOrNotDialog;
import GUIManager.MyFrame.ChoiceFrame;
import JDBCUtils.EmployeeUtils;
import JDBCUtils.SGUtils;
import JDBCUtils.VWUtils;
import UserData.Employees;
import UserData.SalaryGrade;
import UserData.UserBean;
import UserData.VariableWage;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;

public class AllFrame extends JFrame {

    private final JComboBox comboBox;
    private JButton btnBack;
    private JButton btnOut;
    private JTextArea textArea;
    private JRadioButton salaryHigh;
    private JRadioButton salaryLow;
    private JButton btnConfirm;
    private JPanel contentPane;

    private List<Employees> empList;
    private List<UserBean> salary;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        new AllFrame();
    }

    /**
     * Create the frame.
     */
    public AllFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon=new ImageIcon("Mission1/pic/all.jpg");
        JLabel label = new JLabel(icon);//往一个标签中加入图片
        label.setBounds(0, 0, icon.getIconWidth(),icon.getIconHeight());//设置标签位置大小为图片大小
        this.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));//标签添加到第二层面板
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel imPanel = (JPanel)getContentPane();
        imPanel.setOpaque(false);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setOpaque(false);

        ButtonGroup b = new ButtonGroup();

        salaryHigh = new JRadioButton("工资从高到低");
        salaryHigh.setOpaque(false);
        b.add(salaryHigh);
        salaryHigh.setBounds(6, 6, 113, 23);
        contentPane.add(salaryHigh);

        salaryLow = new JRadioButton("工资从低到高");
        salaryLow.setOpaque(false);
        b.add(salaryLow);
        salaryLow.setBounds(121, 6, 105, 23);
        contentPane.add(salaryLow);

        JLabel lblNewLabel = new JLabel("月份");
        lblNewLabel.setOpaque(false);
        lblNewLabel.setBounds(451, 10, 35, 15);
        contentPane.add(lblNewLabel);

        comboBox = new JComboBox();
        comboBox.setOpaque(false);
        comboBox.setBounds(496, 5, 91, 24);
        contentPane.add(comboBox);
        for (int i = 1; i <= 12; i++) {
            comboBox.addItem(i + "月");
        }
        comboBox.setSelectedItem(getTime());

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setOpaque(false);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(20, 50, 580, 250);
        contentPane.add(scrollPane);

        textArea = new JTextArea();
        textArea.setOpaque(false);
        scrollPane.setViewportView(textArea);

        btnBack = new JButton("返回");
        btnBack.setOpaque(false);
        btnBack.setBounds(80, 325, 60, 23);
        contentPane.add(btnBack);

        btnConfirm = new JButton("查看");
        btnConfirm.setOpaque(false);
        btnConfirm.setBounds(270, 325, 60, 23);
        contentPane.add(btnConfirm);

        btnOut = new JButton("退出");
        btnOut.setOpaque(false);
        btnOut.setBounds(450, 325, 60, 23);
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
            new ChoiceFrame();
        });

        btnOut.addActionListener(e -> {
            new OutOrNotDialog("确定退出？");
        });

        btnConfirm.addActionListener(e -> {
            textArea.setText("id\t姓名\t性别\t初始年月\t工资等级\t基础工资\t岗位工资\t交通补贴\t月份\t奖励\t罚款\t总和\n");
            showMyInfo();
        });

    }

    public String getTime() {
        GregorianCalendar g = new GregorianCalendar();
        g.setTime(new Date());
        int month;
        month = g.get(GregorianCalendar.MONTH) + 1;
        return month + "月";
    }

    public void getInfo() {
        List<Employees> empList = EmployeeUtils.getEmployees();
        this.empList = new ArrayList<>();
        salary = new ArrayList<>();
        for (int i = 0; i < empList.size(); i++) {
            VariableWage v = VWUtils.SearchSal(empList.get(i).getId(), comboBox.getSelectedIndex()+1);
            SalaryGrade s = SGUtils.SearchSLel(empList.get(i).getSalaryLevel());
            UserBean ub = new UserBean();
            ub.setFinalSalary(v.getRewardSalary() - v.getFine() + s.getTrafficSalary() + s.getJobSalary() + s.getBasicSalary());
            ub.setId(empList.get(i).getId());
            salary.add(ub);
        }
        ChoiceWay();
        /*经过总工资的对比，把对比好的salary中的Id对应的信息add中empList中*/
        for (UserBean b : salary) {
            this.empList.add(EmployeeUtils.SearchEmployee(b.getId()));
        }
    }

    public void showMyInfo(){
        getInfo();
        double averageSalary = 0;
        for(Employees e:empList){
            SalaryGrade level = SGUtils.SearchSLel(e.getSalaryLevel());
            VariableWage vw = VWUtils.SearchSal(e.getId(),comboBox.getSelectedIndex()+1);
            double finalSalary = level.getBasicSalary()+level.getJobSalary()+level.getTrafficSalary()+vw.getRewardSalary()-vw.getFine();
            averageSalary+=finalSalary;
            textArea.append(e.getId()+"\t"+
                    e.getName()+"\t"+
                    e.getSex()+"\t"+
                    e.getStartDate()+"\t"+
                    e.getSalaryLevel()+"\t"+
                    level.getBasicSalary()+"\t"+
                    level.getJobSalary()+"\t"+
                    level.getTrafficSalary()+"\t"+
                    (comboBox.getSelectedIndex()+1)+"月"+"\t"+
                    vw.getRewardSalary()+"\t"+
                    vw.getFine()+"\t"+
                    finalSalary+"\n");
        }
        DecimalFormat df = new DecimalFormat("#.00");
        textArea.append("\n"+(comboBox.getSelectedIndex()+1)+"月份所有员工总工资的平均工资为"+df.format(averageSalary/empList.size())+"元");
    }

    /**
     * 此方法给工资排序，从高到底或者从低到高.
     */
    public void ChoiceWay() {
        /*工资从高到底排序*/
        if (salaryHigh.isSelected()) {
            Collections.sort(salary, (u1, u2) -> {
                if (u1.getFinalSalary() > u2.getFinalSalary()) {
                    return -1;
                }
                if (u1.getFinalSalary() == u2.getFinalSalary()) {
                    return 0;
                }
                return 1;
            });
        }
        /*工资从低到高*/
        if(salaryLow.isSelected()){
            Collections.sort(salary,(u1,u2)->{
                if (u1.getFinalSalary() > u2.getFinalSalary()) {
                    return 1;
                }
                if (u1.getFinalSalary() == u2.getFinalSalary()) {
                    return 0;
                }
                return -1;
            });
        }
    }


}
