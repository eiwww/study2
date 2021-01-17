package com.sd.study2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Login_Activity extends AppCompatActivity {

    EditText user,pwd;
    Button lg;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        user = findViewById(R.id.us);
        pwd = findViewById(R.id.pwd);

    }

    private class Login extends AsyncTask<String,String,String>{

        String name = user.getText().toString();
        String pass = pwd.getText().toString();
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Connection c = DBConnect.getConnection();
                String sql = "select * from account where user = ? and name = ?";
                PreparedStatement stm = c.prepareStatement(sql);
                stm.setString(1, name);
                stm.setString(2, pass);
                int r = stm.executeUpdate();
                if(r>0){
                    Intent obj = new Intent(Login_Activity.this,MainActivity.class);
                    startActivity(obj);
                }else{
                    Toast.makeText(getApplicationContext(), "FAIL", Toast.LENGTH_SHORT).show();
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {

        }
    }
}
