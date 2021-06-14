package GUIManager.GUIUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class FontHint implements FocusListener {
    private String hint;

    private JTextField jTextField;

    public FontHint(String hint, JTextField jTextField) {
        this.hint = hint;
        this.jTextField = jTextField;
        jTextField.setText(hint);
        jTextField.setForeground(Color.GRAY);
    }

    @Override
    public void focusGained(FocusEvent e) {
        String str = jTextField.getText();
        if(str.equals(hint)){
            jTextField.setText("");
            jTextField.setForeground(Color.BLACK);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        String str = jTextField.getText();
        if(str.equals("")){
            jTextField.setForeground(Color.GRAY);
            jTextField.setText(hint);
        }
    }
}
