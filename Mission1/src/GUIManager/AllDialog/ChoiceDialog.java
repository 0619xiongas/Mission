package GUIManager.AllDialog;

import GUIManager.MyFrame.Summary.AllFrame;
import GUIManager.MyFrame.Summary.PersonFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ChoiceDialog extends JDialog {

    private final JPanel contentPanel = new JPanel();

    public ChoiceDialog() {
        this.setTitle("选择一下吧");
        setBounds(100, 100, 306, 155);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JLabel lblNewLabel = new JLabel("选择查看个人还是全体?");
        lblNewLabel.setBounds(73, 27, 149, 29);
        contentPanel.add(lblNewLabel);
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        {
            JPanel buttonPane = new JPanel();
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
            {
                JButton okButton = new JButton("个人");
                buttonPane.add(okButton);
                okButton.addActionListener(e->{
                    dispose();
                    new PersonFrame();
                });
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("全体");
                cancelButton.addActionListener(e->{
                    dispose();
                    new AllFrame();
                });
                buttonPane.add(cancelButton);
            }
        }
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation( (int) (width - this.getWidth()) / 2,(int) (height - this.getHeight()) / 2);
        this.setVisible(true);
    }

}
