package utils;

import com.mysql.cj.protocol.Resultset;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 通用的操作数据的类
 * 包括对数据库的建立连接，关闭连接，释放资源
 * 通用的增、删、改及通用的查询的功能
 */
public class DBUitls {
    //连接数据库的驱动类
    private static String DRIVER;
    //连接数据库的地址
    private static String URL;
    //数据库登录用户名
    private static String USER;
    //数据库登录密码
    private static String PASSWORD;

    static{
        Properties prop = new Properties();
        //   /代表根目录（src）
        //InputStream inputStream = DBUitls.class.getResourceAsStream("/jdbc.properties");

        //从ClassLoader获取的流相当于从根(src)目录下直接获取
        InputStream inputStream = DBUitls.class.getClassLoader().getResourceAsStream("jdbc.properties");

        //加载jdbc.properties
        try {
            prop.load(inputStream);

            //通过键取值
            DRIVER = prop.getProperty("jdbc.mysql.driver");
            URL = prop.getProperty("jdbc.mysql.url");
            USER = prop.getProperty("jdbc.mysql.user");
            PASSWORD = prop.getProperty("jdbc.mysql.password");

            Class.forName(DRIVER); //加载驱动
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 打开数据库连接
     * @return
     */
    public Connection getConnection(){
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭连接，释放资源
     * @param conn
     * @param pstmt
     * @param rs
     */
    public void close(Connection conn, PreparedStatement pstmt, ResultSet rs){
        //注意关闭时的顺序
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
               pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通用的增、删、改的方法
     * @param sql
     * @param params 可变长度的参数列表，当成一个数组用
     * @return
     */
    protected int executeUpdate(String sql,Object...params){
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            //为sql语句的参数赋值
            if (params != null){
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1,params[i]);
                }
            }

            int row = pstmt.executeUpdate();
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{ //不管程序是否出现异常都会执行的代码块
            close(conn,pstmt,null);
        }
        return -1;
    }
}
