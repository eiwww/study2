package com.sd.study2;
import java.sql.ResultSet;

public interface ActionDB {
    public ResultSet searchData() throws Exception;
    public ResultSet selectData() throws Exception;
    public int insertData() throws Exception;
    public int updateData() throws Exception;
    public int deleteData() throws Exception;
}
