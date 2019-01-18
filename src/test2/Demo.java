package test2;

import java.sql.*;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * 通过Statement接口实现
 */
public class Demo {
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
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder("INSERT INTO EXAMSTUDENT (TYPE,ID_CARD,EXAM_CARD,STUDENT_NAME,LOCATION,GRADE) VALUES (");
            sb.append(type + ",")
                    .append( "'" + idCard + "',")
                    .append("'" + examCard + "',")
                    .append("'" + studentName + "',")
                    .append("'" + location + "',")
                    .append(grade)
                    .append(")");
            //System.out.println(sb);
            int row = statement.executeUpdate(sb.toString());
            if (row > 0)
                System.out.println("插入成功");
            else
                System.out.println("插入失败");


            if (statement != null)
                statement.close();
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

            Statement statement = connection.createStatement();

            String sql = "select *  from examstudent t where t.id_card = '" + idCard + "'";
            ResultSet rs = statement.executeQuery(sql);
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
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
