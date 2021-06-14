package GUIManager.AllDialog;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

/**
 * 此类是提示弹出窗口类，通过传入要提示的语句: str
 */
public class MyDialog extends JDialog{

    private JButton okButton;

    private JButton cancelButton;

    private final JPanel contentPanel = new JPanel();

    public MyDialog(String str) {
        this.setTitle("提示");
        setBounds(100, 100, 300, 150); /// 200 ， 150
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblNewLabel = new JLabel(str);
        lblNewLabel.setVerticalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(22, 24, 300, 31);
        contentPanel.add(lblNewLabel);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                okButton = new JButton("确定");
                buttonPane.add(okButton);
                this.OkButton();
                getRootPane().setDefaultButton(okButton);
            }
            {
                cancelButton = new JButton("取消");
                buttonPane.add(cancelButton);
                this.CancelButton();
                getRootPane().setDefaultButton(cancelButton);
            }
        }
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation( (int) (width - this.getWidth()) / 2,(int) (height - this.getHeight()) / 2);
        this.setVisible(true);
    }

    public void OkButton() {
        this.okButton.addActionListener(e->{
            this.dispose();
        });
    }

    public void CancelButton(){
        this.cancelButton.addActionListener(e-> {
            this.dispose();
        });
    }

    public JButton getOkButton() {
        return okButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }
}
