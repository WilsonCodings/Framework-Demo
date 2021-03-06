package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_Demo {

    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://101.201.212.204:3306/ground_monitoring_new";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "wanda";
    static final String PASS = "123wanda";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();

            String sql;
            sql = "SELECT * FROM server";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(rs);

            // 展开结果集数据库
            while (rs.next()) {
                // 通过字段检索
                int id = rs.getInt("id");
                String warehouse_name = rs.getString("warehouse_name");
                String slack_group = rs.getString("slack_group");
                String recipient_slackId = rs.getString("recipient_slackId");
                String email_address = rs.getString("email_address");
                String state = rs.getString("state");

                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", warehouse_name: " + warehouse_name);
                System.out.print(", slack_group: " + slack_group);
                System.out.print(", recipient_slackId: " + recipient_slackId);
                System.out.print(", email_address: " + email_address);
                System.out.print(", state: " + state);
                System.out.print("\n");
            }

            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            } // 什么都不做

            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}
