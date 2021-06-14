package GUIManager.MyFrame.Salary;

import GUIManager.AllDialog.MyDialog;
import GUIManager.AllDialog.OutOrNotDialog;
import GUIManager.GUIUtils.FontHint;
import GUIManager.MyFrame.EmployeeFrame;
import GUIManager.MyFrame.SalaryFrame;
import JDBCUtils.EmployeeUtils;
import JDBCUtils.VWUtils;
import UserData.VariableWage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class SalaryUpdateFrame extends JFrame {

    private final JTextField month;
    private final JButton btnBack;
    private final JButton btnOut;
    private final JButton btnConfirm;
    private final JButton btnUpdate;
    private final JTextArea textArea;
    private final JPanel p1;
    private JPanel contentPane;
    private JTextField id;
    private JButton btnOk;
    private JTextField reward;
    private JTextField fine;

    private List<VariableWage> list ;
    private int Month;

    private VariableWage v1;
    private VariableWage v2;

    public SalaryUpdateFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon=new ImageIcon("G:\\Mission\\Mission1\\pic\\p3.jpg");
        JLabel label = new JLabel(icon);//往一个标签中加入图片
        label.setBounds(0, 0, icon.getIconWidth(),icon.getIconHeight());//设置标签位置大小为图片大小
        this.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));//标签添加到第二层面板
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel imPanel = (JPanel)getContentPane();
        imPanel.setOpaque(false);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setOpaque(false);

        JLabel lblNewLabel = new JLabel("请输入id修改对应的信息");
        lblNewLabel.setOpaque(false);
        lblNewLabel.setBounds(250, 10, 140, 31);
        contentPane.add(lblNewLabel);

        id = new JTextField();
        id.setOpaque(false);
        id.setBounds(250, 37, 150, 21);
        contentPane.add(id);
        id.setColumns(10);

        btnOk = new JButton("确定");
        btnOk.setOpaque(false);
        btnOk.setBounds(290, 68, 60, 23);
        contentPane.add(btnOk);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(22,103,400,200);
        contentPane.add(scrollPane);
        textArea = new JTextArea();
        scrollPane.setViewportView(textArea);
        textArea.setOpaque(false);

        btnBack = new JButton("返回");
        btnBack.setOpaque(false);
        btnBack.setBounds(32, 340, 60, 23);
        contentPane.add(btnBack);

        btnOut = new JButton("退出");
        btnOut.setOpaque(false);
        btnOut.setBounds(350, 340, 60, 23);
        contentPane.add(btnOut);

        p1 = new JPanel();
        p1.setOpaque(false);
        p1.setBounds(450, 97, 160, 238);
        p1.setVisible(false);
        contentPane.add(p1);
        p1.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("奖励");
        lblNewLabel_1.setOpaque(false);
        lblNewLabel_1.setBounds(10, 91, 58, 15);
        p1.add(lblNewLabel_1);

        reward = new JTextField();
        reward.setOpaque(false);
        reward.setBounds(78, 88, 66, 21);
        p1.add(reward);
        reward.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("罚款");
        lblNewLabel_2.setOpaque(false);
        lblNewLabel_2.setBounds(10, 131, 58, 15);
        p1.add(lblNewLabel_2);

        fine = new JTextField();
        fine.setOpaque(false);
        fine.setBounds(78, 128, 66, 21);
        p1.add(fine);
        fine.setColumns(10);

        btnConfirm = new JButton("确认");
        btnConfirm.setOpaque(false);
        btnConfirm.setBounds(28, 172, 97, 23);
        p1.add(btnConfirm);

        btnUpdate = new JButton("修改");
        btnUpdate.setOpaque(false);
        btnUpdate.setBounds(28, 205, 97, 23);
        p1.add(btnUpdate);

        JLabel lblNewLabel_3 = new JLabel("输入月份");
        lblNewLabel_3.setOpaque(false);
        lblNewLabel_3.setBounds(49, 10, 58, 15);
        p1.add(lblNewLabel_3);

        month = new JTextField();
        month.setOpaque(false);
        month.setBounds(49, 35, 58, 21);
        p1.add(month);
        month.setColumns(10);

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

        btnOk.addActionListener(e->{
            if (judge(id.getText().trim())) {
                textArea.setText("id\t月份\t奖励\t罚款\n");
                getSalary();
                p1.setVisible(true);
            } else {
                if (id.getText().trim().equals("")) {
                    new MyDialog("请输入id");
                } else {
                    new MyDialog("无此职工的信息，请检查你输入的id");
                }
            }
        });
        btnConfirm.addActionListener(e->{
            if(month.getText().trim().equals("")){
                new MyDialog("请输入月份");
            }else {
                Month = Integer.parseInt(month.getText().trim());
                if (!(Month >= 1 && Month <= 12)) {
                    new MyDialog("请输入正确的月份数字");
                } else {
                    if(getNow()) {
                        fine.setText(String.valueOf(v1.getFine()));
                        reward.setText(String.valueOf(v1.getRewardSalary()));
                    }else{
                        new MyDialog("请仔细检查有无此月份的信息");
                    }
                }
            }
        });
        btnUpdate.addActionListener(e->{
            if(judgeMonth()){
                if(judgeModify()) {
                    UpdateSalary();
                }
            }else{
                new MyDialog("无此月的信息，无法删除");
            }
        });
    }

    public void getSalary() {
        list = VWUtils.Search(id.getText().trim());
        if (list.size() == 0) {
            new MyDialog("此职员的变动工资信息条数为0");
        } else {
            for (int i = 0; i < list.size(); i++) {
                VariableWage v = list.get(i);
                textArea.append(v.getEmployee_id() + "\t" + v.getMonth() + "\t" + v.getRewardSalary() + "\t" + v.getFine() + "\n");
            }
        }
    }
    public boolean judge(String id) {
        String ID = EmployeeUtils.SearchEmployee(id).getId();
        if (ID == null) {
            return false;
        } else {
            return true;
        }
    }

    public void UpdateSalary(){
        double fine = Double.parseDouble(this.fine.getText().trim());
        double reward = Double.parseDouble(this.reward.getText().trim());

        VWUtils.updateDate(id.getText().trim(),"fine",fine,Month);
        VWUtils.updateDate(id.getText().trim(),"reward",reward,Month);

        new MyDialog("修改成功！");
    }

    public boolean judgeMonth(){
        for(int i=0;i< list.size();i++){
            if(list.get(i).getMonth() == Month){
                return true;
            }
        }
        return false;
    }

    public boolean getNow(){
        v1 = VWUtils.SearchSal(id.getText().trim(),Month);
        if(v1.getMonth() == 0){
            return false;
        }
        return true;
    }

    public boolean judgeModify(){
        for(int i=0;i<list.size();i++){
            if(Month == list.get(i).getMonth()){
                v2 = list.get(i);
            }
        }
        if(v2.getRewardSalary() == Double.parseDouble(reward.getText().trim())
                && v2.getFine() == Double.parseDouble(fine.getText().trim()
        )){
            new MyDialog("未改动信息,保存原有信息");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        new SalaryUpdateFrame();
    }

}
