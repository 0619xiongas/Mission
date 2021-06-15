package GUIManager.MyFrame.Salary;

import GUIManager.AllDialog.MyDialog;
import GUIManager.AllDialog.OutOrNotDialog;
import GUIManager.GUIUtils.FontHint;
import GUIManager.MyFrame.SalaryFrame;
import JDBCUtils.EmployeeUtils;
import JDBCUtils.VWUtils;
import UserData.VariableWage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class SalaryRemoveFrame extends JFrame {

    private final JTextArea textArea;
    private final JLabel note;
    private JPanel contentPane;
    private JTextField id;
    private JButton btnOk;
    private JTextField month;
    private JButton btnBack;
    private JButton btnOut;
    private JButton btnConfirm;
    private JButton btnRemove;
    private List<VariableWage> list;
    private int Month;

    public SalaryRemoveFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon=new ImageIcon("Mission1/pic/p3.jpg");
        JLabel label = new JLabel(icon);//往一个标签中加入图片
        label.setBounds(0, 0, icon.getIconWidth(),icon.getIconHeight());//设置标签位置大小为图片大小
        this.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));//标签添加到第二层面板
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel imPanel = (JPanel)getContentPane();
        imPanel.setOpaque(false);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setOpaque(false);

        JLabel lblNewLabel = new JLabel("请输入id删除对应的信息");
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
        scrollPane.setOpaque(false);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(35, 100, 400, 220);
        contentPane.add(scrollPane);
        textArea = new JTextArea();
        textArea.setOpaque(false);
        scrollPane.setViewportView(textArea);

        btnBack = new JButton("返回");
        btnBack.setOpaque(false);
        btnBack.setBounds(32, 340, 60, 23);
        contentPane.add(btnBack);

        btnOut = new JButton("退出");
        btnOut.setOpaque(false);
        btnOut.setBounds(367, 340, 60, 23);
        contentPane.add(btnOut);


        note = new JLabel("输入月份进行删除");
        note.setVisible(false);
        note.setOpaque(false);
        note.setBounds(480, 98, 124, 31);
        contentPane.add(note);

        btnConfirm = new JButton("确定");
        btnConfirm.setVisible(false);
        btnConfirm.setOpaque(false);
        btnConfirm.setBounds(480, 165, 60, 23);
        contentPane.add(btnConfirm);

        btnRemove = new JButton("删除");
        btnRemove.setVisible(false);
        btnRemove.setEnabled(false);
        btnRemove.setOpaque(false);
        btnRemove.setBounds(480, 210, 60, 23);
        contentPane.add(btnRemove);


        month = new JTextField();
        month.setOpaque(false);
        month.addFocusListener(new FontHint("月份数字", month));
        month.setVisible(false);
        month.setBounds(480, 125, 66, 21);
        contentPane.add(month);
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

        btnOk.addActionListener(e -> {
            if (judge(id.getText().trim())) {
                getSalary();
                myshow();
            } else {
                if (id.getText().trim().equals("")) {
                    new MyDialog("请输入id");
                } else {
                    new MyDialog("无此职工的信息，请检查你输入的id");
                }
            }
        });

        btnRemove.addActionListener(e -> {
            if(judgeMonth()) {
                RemoveSalary(id.getText().trim(), Month);
                getSalary();
            }
            else{
                new MyDialog("无此月的信息，无法删除");
            }
        });

        btnConfirm.addActionListener(e -> {
            if(month.getText().trim().equals("月份数字")){
                new MyDialog("请输入月份");
            }else {
                Month = Integer.parseInt(month.getText().trim());
                if (!(Month >= 1 && Month <= 12)) {
                    new MyDialog("请输入正确的月份数字");
                    btnRemove.setEnabled(false);
                } else {
                    btnRemove.setEnabled(true);
                }
            }
        });
    }


    public void RemoveSalary(String id, int month) {
        VWUtils.SubVWSalary(id, month);
    }

    public boolean judge(String id) {
        String ID = EmployeeUtils.SearchEmployee(id).getId();
        if (ID == null) {
            return false;
        } else {
            return true;
        }
    }

    public void getSalary() {
        list = VWUtils.Search(id.getText().trim());
        if (list.size() == 0) {
            new MyDialog("此职员的变动工资信息条数为0");
        } else {
            textArea.setText("id\t月份\t奖励\t罚款\n");
            for (int i = 0; i < list.size(); i++) {
                VariableWage v = list.get(i);
                textArea.append(v.getEmployee_id() + "\t" + v.getMonth() + "\t" + v.getRewardSalary() + "\t" + v.getFine() + "\n");
            }
        }
    }

    public boolean judgeMonth(){
        for(int i=0;i< list.size();i++){
            if(list.get(i).getMonth() == Month){
                return true;
            }
        }
        return false;
    }

    public void myshow() {
        note.setVisible(true);
        btnRemove.setVisible(true);
        btnConfirm.setVisible(true);
        month.setVisible(true);
    }

    public static void main(String[] args) {
        new SalaryRemoveFrame();
    }
}
