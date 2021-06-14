package IOUtils;



import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class SummaryIO {
    public static void InfoToTxt(JTextArea jTextArea){
        try{

            BufferedWriter bw = new BufferedWriter(new FileWriter("libs/IO/Summary.txt"));
            bw.write(jTextArea.getText());
            bw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
