package GUIManager.MyFrame.SalaryLel;


import GUIManager.AllDialog.MyDialog;
import GUIManager.AllDialog.OutOrNotDialog;
import GUIManager.MyFrame.SalaryLelFrame;
import JDBCUtils.SGUtils;
import UserData.SalaryGrade;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class SalaryLevelLookFrame extends JFrame {

    private JPanel contentPane;
    private JButton btnBack;
    private JButton btnOut;
    private JButton btnConfirm;
    private JComboBox salaryLel;
    private JTextField basicSalary;
    private JTextField jobSalary;
    private JTextField trafficSalary;
    private List<String> list;

    public SalaryLevelLookFrame() {
      list = SGUtils.getSize();

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

        JLabel note = new JLabel("请输入要查看的等级级别:");
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
        salaryLel.addItem("选择等级");
        for(int i=0;i<list.size();i++){
                salaryLel.addItem(list.get(i));
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

        btnBack = new JButton("返回");
        btnBack.setOpaque(false);
        btnBack.setBounds(60, 380, 60, 23);
        contentPane.add(btnBack);

        btnConfirm = new JButton("查看");
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
            findLevel();
        });

    }
    public void findLevel(){
        SalaryGrade sg;
        if(salaryLel.getSelectedIndex() == 0){
            new MyDialog("请输入等级");
        }else{
            sg = SGUtils.SearchSLel(salaryLel.getSelectedIndex()+"");
            if(sg.getSalaryLevel() == null){
                MyDialog dialog = new MyDialog("没有此等级的详细信息，是否添加？");
                dialog.getOkButton().addActionListener(e1->{
                    this.dispose();
                    new SalaryLevelAddFrame();
                });
                dialog.getCancelButton().addActionListener(e2->{
                    dialog.dispose();
                });
            }else{
                jobSalary.setText(sg.getJobSalary()+"");
                basicSalary.setText(sg.getBasicSalary()+"");
                trafficSalary.setText(sg.getTrafficSalary()+"");
            }
        }
    }

    public static void main(String[] args) {
        new SalaryLevelLookFrame();
    }
}
