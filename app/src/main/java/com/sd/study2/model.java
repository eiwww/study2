package com.sd.study2;

import java.sql.*;

public class model implements ActionDB {

    private String empid;
    private String empname;
    private String empmail;
    private int salary;
    Connection c;

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public void setEmpmail(String empmail) {
        this.empmail = empmail;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public model(Connection c) {
        this.c = c;
    }

    public ResultSet searchData() throws Exception {
        String sql="select * from employee where empname like CONCAT(?)";
        PreparedStatement stm = c.prepareStatement(sql);
        stm.setString(1, empname);
        ResultSet rs = c.createStatement().executeQuery(sql);

        return rs;
    }

    @Override
    public ResultSet selectData() throws Exception {
        String sql="select * from employee";
        ResultSet rs = c.createStatement().executeQuery(sql);
        return rs;
    }

    @Override
    public int insertData() throws Exception {
        String sql="insert into employee values(?,?,?,?)";
        PreparedStatement stm = c.prepareStatement(sql);
        stm.setString(1,empid);
        stm.setString(2,empname);
        stm.setString(3,empmail);
        stm.setInt(4,salary);
        int r = stm.executeUpdate();
        return r;
    }

    @Override
    public int updateData() throws Exception {
        String sql="update employee set empname=?,email=?,salary=? where empid=?";
        PreparedStatement stm = c.prepareStatement(sql);
        stm.setString(4,empid);
        stm.setString(1,empname);
        stm.setString(2,empmail);
        stm.setInt(3,salary);
        int r = stm.executeUpdate();
        return r;
    }

    @Override
    public int deleteData() throws Exception {
        String sql="delete from employee where empid=?";
        PreparedStatement stm = c.prepareStatement(sql);
        stm.setString(1,empid);
        int r = stm.executeUpdate();
        return r;
    }
}
