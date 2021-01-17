package com.sd.study2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.*;

public class MainActivity extends AppCompatActivity {
    EditText txtid,txtname,txtemail,txtsalary;
    Button btnempsave,btnempedit,btnempdel,btnsh;
    TextView tv;
    Connection c = DBConnect.getConnection();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initial();
        new mydata().execute("");
        btnempsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new insertData().execute("");
            }
        });
        btnempedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new updateData().execute("");
            }
        });
        btnempdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new deleteData().execute("");
            }
        });
        btnsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent obj = new Intent(MainActivity.this,Data_Activity.class);
                startActivity(obj);
            }
        });
    }

    private void initial(){
        tv = findViewById(R.id.tvcon);
        txtid = findViewById(R.id.txtid);
        txtemail = findViewById(R.id.txtemail);
        txtname = findViewById(R.id.txtname);
        txtsalary = findViewById(R.id.txtsalary);
        btnempsave = findViewById(R.id.btnadd);
        btnempedit = findViewById(R.id.btnedit);
        btnempdel = findViewById(R.id.btndel);
        btnsh = findViewById(R.id.btndata);
    }
    private class mydata extends AsyncTask<String,String,String>{

        String smg="";
        String eid = txtid.getText().toString();
        String ename = txtname.getText().toString();
        String email = txtemail.getText().toString();
        String esalary = txtsalary.getText().toString();

        @Override
        protected void onPreExecute() {
            tv.setText("Connecting...");
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Connection c = DBConnect.getConnection();
                if(c==null){
                    smg="Connection fail...";
                }else{
                    smg="Connection COMPLETE";
                }
            }catch (Exception ex){
                smg="Connection Fail";
                ex.printStackTrace();
            }
            return smg;
        }

        @Override
        protected void onPostExecute(String s) {
            tv.setText(s);
        }
    }

    private class insertData extends AsyncTask<String,String,String>{

        String smg="";
        String eid = txtid.getText().toString();
        String ename = txtname.getText().toString();
        String email = txtemail.getText().toString();
        String esalary = txtsalary.getText().toString();

        @Override
        protected void onPreExecute() {
            tv.setText("Saving Data...");
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Connection c = DBConnect.getConnection();
                model m = new model(c);
                m.setEmpid(eid);
                m.setEmpname(ename);
                m.setEmpmail(email);
                m.setSalary(Integer.parseInt(esalary));
                int r = m.insertData();
                if(r>0){
                    smg = "Save COMPLETE";
                }else{
                    smg = "Save Fail";
                }
            }catch (Exception ex){
                smg = "Save Fail";
                ex.printStackTrace();
            }
            return smg;
        }

        @Override
        protected void onPostExecute(String s) {
            tv.setText(s);
        }
    }

    private class updateData extends AsyncTask<String,String,String>{

        String smg="";
        String eid = txtid.getText().toString();
        String ename = txtname.getText().toString();
        String email = txtemail.getText().toString();
        String esalary = txtsalary.getText().toString();

        @Override
        protected void onPreExecute() {
            tv.setText("Updating Data...");
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Connection c = DBConnect.getConnection();
                model m = new model(c);
                m.setEmpid(eid);
                m.setEmpname(ename);
                m.setEmpmail(email);
                m.setSalary(Integer.parseInt(esalary));
                int r = m.updateData();
                if(r>0){
                    smg = "Update COMPLETE";
                }else{
                    smg = "Update Fail";
                }
            }catch (Exception ex){
                smg = "Update Fail";
                ex.printStackTrace();
            }
            return smg;
        }

        @Override
        protected void onPostExecute(String s) {
            tv.setText(s);
        }
    }
    private class deleteData extends AsyncTask<String,String,String>{

        String smg="";
        String eid = txtid.getText().toString();
        @Override
        protected void onPreExecute() {
            tv.setText("Deleting Data...");
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Connection c = DBConnect.getConnection();
                model m = new model(c);
                m.setEmpid(eid);
                int r = m.deleteData();
                if(r>0){
                    smg = "Delete COMPLETE";
                }else{
                    smg = "Delete Fail";
                }
            }catch (Exception ex){
                smg = "Delete Fail";
                ex.printStackTrace();
            }
            return smg;
        }

        @Override
        protected void onPostExecute(String s) {
            tv.setText(s);
        }
    }
}