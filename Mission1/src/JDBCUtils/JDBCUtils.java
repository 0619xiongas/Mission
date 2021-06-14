package JDBCUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 此类为连接数据库的工具类
 */
public class JDBCUtils {
    private static String driver;

    private static String url;

    private static String username;

    private static String password;

    private static DataSource dataSource = null;

    static {
        try {
            Properties p = new Properties();
            InputStream is =  ClassLoader.getSystemClassLoader().getResourceAsStream("db.properties");
            p.load(is);
            driver = p.getProperty("driver");
            url = p.getProperty("url");
            username = p.getProperty("username");
            password = p.getProperty("password");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 获取连接数据库的对象Connection
     * @return
     */
    public static Connection getConnection(){
        Connection conn = null;
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url,username,password);
        }catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    /**
     *   此方法是释放Connection,Statement,ResultSet对象,
     * @param conn  连接对象
     * @param stmt  sql语句的实体
     */
    public static void free(Connection conn, Statement stmt, ResultSet res){
        if(conn != null){
            try{
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(stmt != null){
            try{
                stmt.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(res != null){
            try{
                res.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 此方法是释放Connection,PreparedStatement,ResultSet对象,
     * @param conn
     * @param pstmt
     * @param res
     */
    public static void preFree(Connection conn, PreparedStatement pstmt,ResultSet res){
        if(conn != null){
            try{
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(pstmt != null){
            try{
                pstmt.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(res != null){
            try{
                res.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }


}
