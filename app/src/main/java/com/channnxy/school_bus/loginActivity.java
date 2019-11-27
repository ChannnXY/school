package com.channnxy.school_bus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class loginActivity extends AppCompatActivity {
    private Spinner msp_identity;
    private Button mBtn_login;
    private GlobalData app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //初始化全局变量

        //初始化身份选择spinner
        msp_identity = findViewById(R.id.sp_login_identity);
        initSpinnerAdapter();

        mBtn_login = findViewById(R.id.btn_login);
        mBtn_login.setOnClickListener(new loginOnclickListener());
    }

    //初始化身份选择spinner
    private void initSpinnerAdapter() {
        String[] identityArray = {"我是老师","我是学生","我是司机"};

        final ArrayAdapter<String> identityAdapter = new ArrayAdapter<String>(this,R.layout.item_index_spinner,identityArray);
        msp_identity.setAdapter(identityAdapter);
        msp_identity.setPrompt("请选择你的身份");
        msp_identity.setSelection(0);
        msp_identity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                app.loginMode = identityAdapter.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private class loginOnclickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(loginActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }
}
