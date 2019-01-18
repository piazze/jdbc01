package test3;

import utils.DBUitls;

public class Test1 extends DBUitls {

    public void remove(){
        String sql = "delete from examstudent where flow_id = ?";

        int row = super.executeUpdate(sql,1);
        System.out.println(row);
    }

    public void insert(){
        String sql = "insert into examstudent (type,id_card,exam_card,student_name,location,grade) values (?,?,?,?,?,?)";
        Object[] params = {6,"342824195263214584","200523164754002","杨丽","北京",95};

        int row = super.executeUpdate(sql, params);
        System.out.println(row);
    }
    public static void main(String[] args) {
        //删除操作
        Test1 t = new Test1();
        //t.remove();
        t.insert();

    }
}
