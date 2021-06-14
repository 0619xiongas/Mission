package JDBCUtils;

import GUIManager.AllDialog.MyDialog;
import UserData.VariableWage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 此类是进行变动工资的数据的处理
 */
public class VWUtils {
    /**
     * 变动工资新纪录的添加
     * @param vw
     */
    public static void AddNewVWSalary(VariableWage vw){
        String sql = "insert into salary(id,month,reward,fine) values(?,?,?,?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,vw.getEmployee_id());
            pstmt.setInt(2,vw.getMonth());
            pstmt.setDouble(3,vw.getRewardSalary());
            pstmt.setDouble(4,vw.getFine());
            int count = pstmt.executeUpdate();
            if(count > 0){
                new MyDialog("添加成功!");
            }else{
                new MyDialog("添加失败");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCUtils.preFree(conn,pstmt,null);
        }
    }

    /**
     * 删除一条数据
     * @param id
     */
    public static void SubVWSalary(String id,int month){
        String sql1 = "delete from salary where id=? and month=?";
        String sql = "delete from salary where id=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            conn = JDBCUtils.getConnection();
            if(month == 0) {
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, id);
            }
            if(month != 0){
                pstmt = conn.prepareStatement(sql1);
                pstmt.setString(1,id);
                pstmt.setInt(2,month);
            }
            int count = pstmt.executeUpdate();
            if(count > 0){
                new MyDialog("删除成功！");
            }else{
                new MyDialog("删除失败！");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCUtils.preFree(conn,pstmt,null);
        }
    }

    /**
     * 根据id查询他本月工资变动情况
     * @param id
     * @return VariableWage的对象，将数据封装在其中
     */
    public static VariableWage SearchSal(String id,int month){
        String sql = "select * from salary where id=?and month=?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet res = null;
        VariableWage vw = new VariableWage();
        try{
            conn = JDBCUtils.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,id);
            stmt.setInt(2,month);
            res = stmt.executeQuery();
            while(res.next()) {
                vw.setEmployee_id(res.getString("id"));
                vw.setMonth(res.getInt("month"));
                vw.setRewardSalary(res.getDouble("reward"));
                vw.setFine(res.getDouble("fine"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCUtils.free(conn,stmt,res);
        }
        return vw;
    }

    public static List<VariableWage> Search(String id){
        List<VariableWage> list = new ArrayList();
        String sql = "select * from salary where id="+id;
        String sql1 = "select * from salary";
        Connection conn = null;
        Statement stmt = null;
        ResultSet res = null;
        try{
            conn = JDBCUtils.getConnection();
            stmt = conn.createStatement();
            if(id!=null) {
                res = stmt.executeQuery(sql);
            }
            if(id == null){
                res = stmt.executeQuery(sql1);
            }
            while(res.next()){
                VariableWage vw = new VariableWage();
                vw.setEmployee_id(res.getString("id"));
                vw.setMonth(res.getInt("month"));
                vw.setRewardSalary(res.getDouble("reward"));
                vw.setFine(res.getDouble("fine"));
                list.add(vw);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCUtils.free(conn,stmt,res);
        }
        return list;
    }

    /**
     *  更新数据
     * @param id
     * @param fieldName
     * @param value
     */
    public static void updateDate(String id,String fieldName,double value,int month) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "update salary set "+fieldName+"=? where id=? and month = ?";
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1,value);
            pstmt.setString(2,id);
            pstmt.setInt(3,month);
            int count = pstmt.executeUpdate();
            if(count > 0){
                //todo
            }else {
                //todo
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCUtils.preFree(conn,pstmt,null);
        }
    }
}
