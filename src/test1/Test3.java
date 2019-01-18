package test1;

import java.sql.*;

/**
 * 通过JDBC实现对数据库的查询
 */
public class Test3 {
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
            //1.建立连接
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            //2.创建statement
            Statement statement = connection.createStatement();

            //3.编写sql语句
            String sql = "select sid,sname,sage,phone,address from student";

            //4.执行sql语句,得到ResultSet
            ResultSet resultSet = statement.executeQuery(sql);

            //5.通过while循环及next方法依次获取每一行数据
            while (resultSet.next()){ //只要结果集中有数据行，就获取并进入该行
                //获取每行的数据
                //int sid = resultSet.getInt(1);  //根据列号获取第一列的值
                int sid = resultSet.getInt("sid"); //根据列名获取第一列的值
                String sname = resultSet.getString(2);
                int sage = resultSet.getInt(3);
                String phone = resultSet.getString(4);
                String address = resultSet.getString(5);

                System.out.println("sid:" + sid + ",sname:" + sname + ",sage:" + sage + ",phone:" + phone + ",address:" + address);
            }

            //6.关闭连接，释放资源
            if (resultSet != null )
                resultSet.close();
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
