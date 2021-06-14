package IOUtils;

import JDBCUtils.SGUtils;
import UserData.SalaryGrade;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class SalaryGradeIO {
    public static void InfoToTxt(){
        List<SalaryGrade> mlist;
        mlist = SGUtils.getSgList();
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("libs/IO/SalaryGrade.txt"));
            for(SalaryGrade s:mlist){
                bw.write(s.getSalaryLevel()+"   "+
                        s.getBasicSalary()+"   "+
                        s.getJobSalary()+"   "+
                        s.getTrafficSalary());
                bw.newLine();
            }
            bw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
