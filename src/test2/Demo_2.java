package test2;

import java.sql.*;
import java.util.Scanner;

/**
 * 通过PreparedStatement 预编译SQL语句的方式实现
 */
public class Demo_2 {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://192.168.91.134:3306/myschool?useUnicode=true&characterEncoding=UTF8";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static Scanner input = new Scanner(System.in);

    static {
        try {
            Class.forName(DRIVER); /*加载驱动*/
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        while(true){
            print();
            int choose = input.nextInt();
            switch (choose){
                case 1:
                    add();
                    break;
                case 2:
                    queryByID();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("谢谢使用，再见！");
                    return;
                default:
                    System.out.println("输入有误，请重新输入！");
            }
        }
    }

    private static void print(){
        System.out.println("1.新增学生");
        System.out.println("2.根据身份证查询");
        System.out.println("3.根据准考证查询");
        System.out.println("4.删除学生");
        System.out.println("5.退出系统");
    }

    private static void add(){
        System.out.println("请输入等级");
        Integer type = input.nextInt();
        System.out.println("请输入身份证");
        String idCard = input.next();
        System.out.println("请输入准考证");
        String examCard = input.next();
        System.out.println("请输入学生姓名");
        String studentName = input.next();
        System.out.println("请输入区域");
        String location = input.next();
        System.out.println("请输入成绩");
        Integer grade = input.nextInt();


        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "INSERT INTO EXAMSTUDENT (TYPE,ID_CARD,EXAM_CARD,STUDENT_NAME,LOCATION,GRADE) VALUES (?,?,?,?,?,?)";
            //System.out.println(sb);

            //通过预编译sql语句
            PreparedStatement pstmt = connection.prepareStatement(sql);

            //为sql语句中的问号赋值
            pstmt.setInt(1,type);
            pstmt.setString(2,idCard);
            pstmt.setString(3,examCard);
            pstmt.setString(4,studentName);
            pstmt.setString(5,location);
            pstmt.setInt(6,grade);

            //执行sql语句
            int row = pstmt.executeUpdate();

            if (row > 0)
                System.out.println("插入成功");
            else
                System.out.println("插入失败");

            //释放资源
            if (pstmt != null)
                pstmt.close();
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void queryByID(){
        System.out.println("请输入身份证");
        String idCard = input.next();

        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "select *  from examstudent t where t.id_card = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);

            //为问号赋值
            pstmt.setString(1,idCard);

            //执行sql语句
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                int flow_id = rs.getInt(1);
                int type = rs.getInt(2);
                String id_Card = rs.getString(3);
                String exam_card = rs.getString(4);
                String name = rs.getString(5);
                System.out.println(flow_id + "," + type + "," + id_Card + "," + exam_card + "," + name);
            }

            if (rs != null)
                rs.close();
            if (pstmt != null)
                pstmt.close();
            if (connection != null)
                connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
