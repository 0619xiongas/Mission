package GUIManager.MyFrame.Summary;

import GUIManager.AllDialog.MyDialog;
import GUIManager.AllDialog.OutOrNotDialog;
import GUIManager.MyFrame.ChoiceFrame;
import JDBCUtils.EmployeeUtils;
import JDBCUtils.SGUtils;
import JDBCUtils.VWUtils;
import UserData.Employees;
import UserData.SalaryGrade;
import UserData.VariableWage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PersonFrame extends JFrame {

    private final JTextArea textArea;
    private final JButton btnBack;
    private final JButton btnConfirm;
    private final JButton btnOut;
    private JPanel contentPane;
    private JTextField id;

    private List<VariableWage> mlist;
    private Employees employee;
    private SalaryGrade level;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        new PersonFrame();
    }

    /**
     * Create the frame.
     */
    public PersonFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon=new ImageIcon("Mission1/pic/person.png");
        JLabel label = new JLabel(icon);//往一个标签中加入图片
        label.setBounds(0, 0, icon.getIconWidth(),icon.getIconHeight());//设置标签位置大小为图片大小
        this.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));//标签添加到第二层面板
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel imPanel = (JPanel)getContentPane();
        imPanel.setOpaque(false);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setOpaque(false);

        JLabel lblNewLabel = new JLabel("输入id即可查看所有信息哦");
        lblNewLabel.setOpaque(false);
        lblNewLabel.setBounds(250, 10, 210, 26);
        contentPane.add(lblNewLabel);

        id = new JTextField();
        id.setOpaque(false);
        id.setBounds(270, 33, 100, 23);
        contentPane.add(id);
        id.setColumns(10);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setOpaque(false);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(20, 80, 580, 207);
        contentPane.add(scrollPane);

        textArea = new JTextArea();
        textArea.setOpaque(false);
        scrollPane.setViewportView(textArea);

        btnBack = new JButton("返回");
        btnBack.setOpaque(false);
        btnBack.setBounds(70, 310, 60, 23);
        contentPane.add(btnBack);

        btnConfirm = new JButton("确定");
        btnConfirm.setOpaque(false);
        btnConfirm.setBounds(270, 310, 60, 23);
        contentPane.add(btnConfirm);

        btnOut = new JButton("退出");
        btnOut.setOpaque(false);
        btnOut.setBounds(470, 310, 60, 23);
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
            new ChoiceFrame();
        });

        btnOut.addActionListener(e->{
            new OutOrNotDialog("确定退出？");
        });


        btnConfirm.addActionListener(e->{
            if(judge()) {
                textArea.setText("id\t姓名\t性别\t初始年月\t工资等级\t基础工资\t岗位工资\t交通补贴\t月份\t奖励\t罚款\t总和\n");
                showInfo();
            }else {
                new MyDialog("输入的id不出存在，请检查");
            }

        });
    }

    public boolean judge(){
        String Id = EmployeeUtils.SearchEmployee(id.getText().trim()).getId();
        if(Id == null){
            return false;
        }
        else {
            return true;
        }
    }

    public void getSalary(){
        mlist = VWUtils.Search(id.getText().trim());
        Collections.sort(mlist, (o1, o2) -> {
            if(o1.getMonth() > o2.getMonth()){
                return 1;
            }
            if(o1.getMonth() == o2.getMonth()){
                return 0;
            }
            return -1;
        });
    }

    public void getLevel(){
        level = SGUtils.SearchSLel(employee.getSalaryLevel());
    }

    public Employees getEmployee(){
        employee = EmployeeUtils.SearchEmployee(id.getText().trim());
        return employee;
    }

    public void showInfo(){
        getEmployee();
        getLevel();
        getSalary();
        double averageSalary = 0;
        for(int i=0;i<mlist.size();i++){
            double finalSalary = level.getBasicSalary()+level.getJobSalary()+level.getTrafficSalary()+mlist.get(i).getRewardSalary()-mlist.get(i).getFine();
            averageSalary+=finalSalary;
            textArea.append(employee.getId()+"\t"+
                    employee.getName()+"\t"+
                    employee.getSex()+"\t"+
                    employee.getStartDate()+"\t"+
                    employee.getSalaryLevel()+"\t"+
                    level.getBasicSalary()+"\t"+
                    level.getJobSalary()+"\t"+
                    level.getTrafficSalary()+"\t"+
                    mlist.get(i).getMonth()+"月"+"\t"+
                    mlist.get(i).getRewardSalary()+"\t"+
                    mlist.get(i).getFine()+"\t"+
                    finalSalary+"\n");
        }
        DecimalFormat df = new DecimalFormat("#.00");

        textArea.append("\n"+"此员工"+mlist.size()+"个月的平均工资为"+df.format(averageSalary/mlist.size())+"元");
    }

}
