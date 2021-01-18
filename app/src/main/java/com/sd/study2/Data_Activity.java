package com.sd.study2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.sql.*;
import java.util.ArrayList;

public class Data_Activity extends AppCompatActivity {

    ArrayAdapter adp;
    ListView lv;
    TextView txtsh;
    ArrayList<String> arrid = new ArrayList<String>();
    ArrayList<String> arrnm = new ArrayList<String>();
    ArrayList<String> arrmail = new ArrayList<String>();
    ArrayList<String> arrslr = new ArrayList<String>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_show);
        lv = findViewById(R.id.lvsh);
        txtsh = findViewById(R.id.txtse);
        new showData().execute("");
        txtsh.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                new SearchData().execute("");
            }
        });

    }

    private class showData extends AsyncTask<String,String,String>{


        @Override
        protected void onPreExecute() {
            Toast.makeText(Data_Activity.this, "Loading", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Connection c = DBConnect.getConnection();
                model m = new model(c);
                ResultSet rs = m.selectData();

                arrid.clear();
                arrnm.clear();
//                arrmail.clear();
//                arrslr.clear();
                while (rs.next()){
                    arrid.add(rs.getString("empid"));
                    arrnm.add(rs.getString("empname"));
//                    arrmail.add(rs.getString("empmail"));
//                    arrslr.add(rs.getString("empsalary"));
                }

                rs.close();

            }catch (Exception ex){
                ex.printStackTrace();
            }
                                                                                                                                                                                                                                                                                                                                                                                                            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            adp = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arrnm);
            lv.setAdapter(adp);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String stritm = arrid.get(i).toString();
                    String strnm = arrnm.get(i).toString();
//                    String strmail = arrmail.get(i).toString();
//                    String strslr = arrslr.get(i).toString();
                    Toast.makeText(Data_Activity.this, stritm, Toast.LENGTH_SHORT).show();
                    Intent obj = new Intent(Data_Activity.this,Detail_Activity.class);
                    obj.putExtra("name", strnm);
//                    obj.putExtra("mail", strmail);
//                    obj.putExtra("salary", strslr);
                    startActivity(obj);
                }
            });
        }
    }

    private class SearchData extends AsyncTask<String,String ,String>{

        @Override
        protected void onPreExecute() {
            Toast.makeText(Data_Activity.this, "Loading", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                Connection c = DBConnect.getConnection();
                String sql = "select * from employee where empname like '%"+ txtsh.getText() +"%'";
                ResultSet rs = c.createStatement().executeQuery(sql);
                arrid.clear();
                arrnm.clear();
//                arrmail.clear();
//                arrslr.clear();
                while (rs.next()){
                    arrid.add(rs.getString("empid"));
                    arrnm.add(rs.getString("empname"));
//                    arrmail.add(rs.getString("empmail"));
//                    arrslr.add(rs.getString("empsalary"));
                }

                rs.close();


            }catch (Exception ex){
                ex.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {

                Toast.makeText(getApplicationContext(), "CONNECT", Toast.LENGTH_SHORT).show();
                adp = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,arrnm);
                lv.setAdapter(adp);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String stritm = arrid.get(i).toString();
                        String strnm = arrnm.get(i).toString();
//                        String strmail = arrmail.get(i).toString();
//                        String strslr = arrslr.get(i).toString();
                        Toast.makeText(Data_Activity.this, stritm, Toast.LENGTH_SHORT).show();
                        Intent obj = new Intent(Data_Activity.this,Detail_Activity.class);
                        obj.putExtra("name", strnm);
//                        obj.putExtra("mail", strmail);
//                        obj.putExtra("salary", strslr);
                        startActivity(obj);
                    }
                });

        }
    }
}
