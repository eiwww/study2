package com.sd.study2;
import java.sql.*;

public class DBConnect {
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String user="root";
            String pwd="ket123456";
            String url="jdbc:mysql://192.168.43.253/db1?&characterEncoding=UTF-8";

            return DriverManager.getConnection(url,user,pwd);
        }catch (Exception ex){
            return null;
        }
    }
}
