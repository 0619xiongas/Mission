package JDBCUtils;

import GUIManager.AllDialog.MyDialog;
import UserData.*;

import javax.print.DocFlavor;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 此类是关于工资等级的数据的管理，表名为salaryLel;
 */
public class SGUtils {
    /**
     * 添加新的工资等级
     * @param sg
     */
    public static void AddNewLevel(SalaryGrade sg){
        String sql = "insert into salaryLel(levels,basicSalary,jobSalary,trafficSalary) values(?,?,?,?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,sg.getSalaryLevel());
            pstmt.setDouble(2,sg.getBasicSalary());
            pstmt.setDouble(3,sg.getJobSalary());
            pstmt.setDouble(4,sg.getTrafficSalary());
            int count = pstmt.executeUpdate();
            if(count > 0){
                new MyDialog("添加成功！");
            }else{
                new MyDialog("添加失败！");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCUtils.preFree(conn,pstmt,null);
        }
    }

    /**
     * 查询工资等级的数据
     * @param level
     * @return 返回一个SalaryGrade的对象，里面封装了所需要的数据。
     */
    public static SalaryGrade SearchSLel(String level){
        String sql = "select * from salaryLel where levels="+level;
        SalaryGrade sg = new SalaryGrade();
        Connection conn = null;
        Statement stmt = null;
        ResultSet res = null;
        try{
            conn = JDBCUtils.getConnection();
            stmt = conn.createStatement();
            res = stmt.executeQuery(sql);
            while(res.next()) {
                sg.setSalaryLevel(res.getString("levels"));
                sg.setBasicSalary(res.getDouble("basicSalary"));
                sg.setJobSalary(res.getDouble("jobSalary"));
                sg.setTrafficSalary(res.getDouble("trafficSalary"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCUtils.free(conn,stmt,res);
        }
        return sg;
    }

    public static void updateDate(String key,String fieldName,double value){
        String sql = "update salaryLel set "+fieldName+"=? where levels=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1,value);
            pstmt.setString(2,key);
            int count = pstmt.executeUpdate();
            if(count > 0){
                //todo
            }else{
                // todo
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static List<SalaryGrade> getSgList(){
        List<SalaryGrade> list = new ArrayList<>();
        String sql = "select * from salaryLel";
        Connection conn = null;
        Statement stmt = null;
        ResultSet res = null;
        try{
            conn = JDBCUtils.getConnection();
            stmt = conn.createStatement();
            res = stmt.executeQuery(sql);
            while(res.next()){
                SalaryGrade s = new SalaryGrade();
                s.setSalaryLevel(res.getString(1));
                s.setBasicSalary(res.getDouble(2));
                s.setJobSalary(res.getDouble(3));
                s.setTrafficSalary(res.getDouble(4));
                list.add(s);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public static List<String> getSize(){
        List<String> list = new ArrayList<>();
        String sql = "select levels from salaryLel";
        Connection conn = null;
        Statement stmt = null;
        ResultSet res = null;
        try{
            conn = JDBCUtils.getConnection();
            stmt = conn.createStatement();
            res = stmt.executeQuery(sql);
            while(res.next()){
                list.add(res.getString(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCUtils.free(conn,stmt,res);
        }
        Collections.sort(list,(o1,o2)->{
            if(Integer.parseInt(o1) > Integer.parseInt(o2)){
                return 1;
            }else if(Integer.parseInt(o1) == Integer.parseInt((o2))){
                return 0;
            }else{
                return -1;
            }
        });
        return list;
    }

}
