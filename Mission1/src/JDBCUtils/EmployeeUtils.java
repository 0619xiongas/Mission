package JDBCUtils;

import GUIManager.AllDialog.MyDialog;
import UserData.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 此类是进行职工人员的数据操作
 */
public class EmployeeUtils {

    /**
     * 职员数据的录入。
     * @param emp
     */
    public static void AddEmployee(Employees emp){
        String sql = "insert into employees(ID,name,sex,startDate,salaryLevel)"
                +" values(?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,emp.getId());
            pstmt.setString(2,emp.getName());
            pstmt.setString(3,emp.getSex());
            pstmt.setString(4,emp.getStartDate());
            pstmt.setString(5,emp.getSalaryLevel());
            int count = pstmt.executeUpdate();
            if(count > 0){
                //todo 这里是弹出窗口，提示是否添加数据成功。
            }else{
                //todo 同上
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCUtils.preFree(conn,pstmt,null);
        }
    }

    /**
     *
     * @param id  根据传入的参数(id为主键)来删除数据
     */
    public static void SubEmployee(String id){
        String sql = "delete from employees where ID=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            int count = pstmt.executeUpdate();
            if(count > 0){
                // todo 弹出窗口
            }else{
                // todo 弹出窗口
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCUtils.preFree(conn,pstmt,null);
        }
    }

    /**
     *  查询数据
     * @param id 查询所要的参数
     * @return 将查询到的数据封装在Employees类中，返回其对象
     */
    public static Employees SearchEmployee(String id){
        Employees emp = new Employees();
        String sql = "select * from employees where ID=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try{
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            res = pstmt.executeQuery();
            if(res!=null) {
                while (res.next()) {
                    emp.setId(res.getString(1));
                    emp.setName(res.getString(2));
                    emp.setSex(res.getString(3));
                    emp.setStartDate(res.getString(4));
                    emp.setSalaryLevel(res.getString(5));
                }
            }else{
                emp = null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCUtils.preFree(conn,pstmt,res);
        }
        return emp;
    }

    /**
     *  此方法用来更新数据
     * @param id 根据唯一主键id确定数据
     * @param strValue 要更新的新数据
     */
    public static void updateData(String id,String strValue,String fieldName) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "update employees set "+fieldName+"=? where ID=?";
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,strValue);
            pstmt.setString(2,id);
            int count = pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCUtils.preFree(conn,pstmt,null);
        }
    }

    public static List<Employees> getEmployees(){
        List<Employees> list = new ArrayList<>();
        String sql = "select * from employees";
        Connection conn = null;
        Statement  stmt = null;
        ResultSet res = null;
        try{
            conn = JDBCUtils.getConnection();
            stmt = conn.createStatement();
            res = stmt.executeQuery(sql);
            while(res.next()){
                Employees e = new Employees();
                e.setId(res.getString("ID"));
                e.setName(res.getString("name"));
                e.setSex(res.getString("sex"));
                e.setStartDate(res.getString("startDate"));
                e.setSalaryLevel(res.getString("salaryLevel"));
                list.add(e);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.free(conn,stmt,res);
        }
        return list;
    }
}
