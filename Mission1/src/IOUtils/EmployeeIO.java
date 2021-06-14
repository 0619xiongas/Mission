package IOUtils;

import JDBCUtils.EmployeeUtils;
import UserData.Employees;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class EmployeeIO {

    public static void InfoToTxt(){
        List<Employees> mlist;
        mlist = EmployeeUtils.getEmployees();
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("libs/IO/employee.txt"));
            for(int i=0;i<mlist.size();i++){
                bw.write(mlist.get(i).getId()+"   "+
                        mlist.get(i).getName()+"   "+
                        mlist.get(i).getSex()+"   "+
                        mlist.get(i).getStartDate()+"  "+
                        mlist.get(i).getSalaryLevel());
                bw.newLine();
            }
            bw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
