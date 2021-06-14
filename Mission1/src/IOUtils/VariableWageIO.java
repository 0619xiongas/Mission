package IOUtils;

import JDBCUtils.VWUtils;
import UserData.VariableWage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class VariableWageIO {
    public static void InfoToTxt(){
        List<VariableWage> mlist;
        mlist = VWUtils.Search(null);
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("libs/IO/VariableWage.txt"));
            for (VariableWage v:mlist) {
                bw.write(v.getEmployee_id()+"   "+
                        v.getMonth()+"   "+
                        v.getRewardSalary()+"   "+
                        v.getFine());
                bw.newLine();
            }
            bw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
