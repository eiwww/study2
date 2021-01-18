package com.sd.study2;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.sql.*;

public class Detail_Activity extends AppCompatActivity {
    TextView tvn,tvs,tvm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detail);
        tvn = findViewById(R.id.tn);
        tvm = findViewById(R.id.te);
        tvn = findViewById(R.id.ts);

        Bundle b = getIntent().getExtras();

        tvn.setText(b.getString("name"));
//        tvm.setText(b.getString("mail"));
//        tvs.setText(b.getString("salary"));

    }
}
