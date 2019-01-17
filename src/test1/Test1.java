package test1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test1 {

    //数据库驱动类的路径
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    /*
    * 连接数据库的url地址
    * 组成
    * jdbc:mysql://ip地址:端口号/数据库?参数=值&参数=值
    * */
    private static  final String URL = "jdbc:mysql://192.168.91.134:3306/myschool?useUnicode=true&characterEncoding=UTF8";

    //数据库登录名
    private static final String USER = "root";

    //数据库密码
    private static final String PASSWORD = "root";


    public static void main(String[] args) {
        /*
        * 1.通过Class.forname加载驱动类
        * */
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            /*
             * 2.通过DriverManager类建立与数据库的连接
             * */
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            if (connection != null){
                System.out.println("与数据库建立连接成功");
                int transactionIsolation = connection.getTransactionIsolation();
                System.out.println("当前数据库事务隔离级别：" + transactionIsolation);
            } else{
                System.out.println("建立数据库连接失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
