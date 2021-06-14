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
public class OutOrNotDialog extends MyDialog{

    public OutOrNotDialog(String str) {
        super(str);
    }

    @Override
    public void OkButton(){
        this.getOkButton().addActionListener(e->{
            System.exit(0);
        });
    }
    @Override
    public  void CancelButton(){
        this.getCancelButton().addActionListener(e->{
            dispose();
        });
    }
}
