package test1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Test2 {
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
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            //通过statement接口的对象访问数据库

            Statement statement = conn.createStatement();

            //插入数据的sql语句
            String insertSQL = "INSERT INTO STUDENT (sname,sage,address,phone) values ('王富贵',30,default,'1015')";
            String updateSQL = "UPDATE STUDENT SET ADDRESS = '渝中区中山三路劳动人民文化宫' WHERE SID=15";
            String deleteSQL = "DELETE FROM STUDENT WHERE SID = 9";

            //int row = statement.executeUpdate(insertSQL);
            //int row = statement.executeUpdate(updateSQL); //执行修改的SQL
            int row = statement.executeUpdate(deleteSQL); //执行删除的SQL


            if (row > 0){
                System.out.println("操作成功");
            } else {
                System.out.println("操作失败");
            }

            /*
                一定要记住关闭连接
                关闭的顺序是打开顺序的逆序
            */
            if(statement != null) statement.close();
            if (conn != null) conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
