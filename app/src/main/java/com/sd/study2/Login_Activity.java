package com.sd.study2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
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
        lg = findViewById(R.id.login);

        lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Login().execute("");
            }
        });
    }

    private class Login extends AsyncTask<String,String,String>{

        String name = user.getText().toString();
        String pass = pwd.getText().toString();
        Boolean success = false;
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                Connection c = DBConnect.getConnection();
                String sql = "select * from account where user = '" + name.toString() + "' and password = '" + pass.toString() + "'";
                ResultSet rs = c.createStatement().executeQuery(sql);
                if(rs.next()){
                    success = true;
                    c.close();
                }else{
                    success = false;
                }

            }catch (Exception ex){
                success = false;
                ex.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(String s) {

            if(success){
                Toast.makeText(getApplicationContext(), "CONNECT", Toast.LENGTH_SHORT).show();
                Intent obj = new Intent(Login_Activity.this,MainActivity.class);
                startActivity(obj);
            }else{
                Toast.makeText(getApplicationContext(), "FAIL", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
