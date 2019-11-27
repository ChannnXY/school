package com.channnxy.school_bus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class journeyDetail extends AppCompatActivity {
    private EditText mTv_name ,mTv_number, mTv_phone, mTv_reason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey_detail);

        initEditText();

    }

// 初始化edit框的状态
    private void initEditText() {
        Intent intent = getIntent();
        boolean mode = intent.getBooleanExtra("mode",true);
        mTv_name = findViewById(R.id.tv_set_name);
        mTv_number = findViewById(R.id.tv_set_number);
        mTv_reason = findViewById(R.id.tv_set_reason);
        mTv_phone = findViewById(R.id.tv_set_phone);
        if(!mode) {
            //如果mode=false即不可编辑的时候
            mTv_name.setFocusable(false);
            mTv_name.setEnabled(false);
            mTv_name.setText("陈同学");
            mTv_number.setFocusable(false);
            mTv_number.setEnabled(false);
            mTv_number.setText("1170299999");
            mTv_reason.setFocusable(false);
            mTv_reason.setEnabled(false);
            mTv_reason.setText("回学校打印相关证书，需要当天来回。");
            mTv_phone.setFocusable(false);
            mTv_phone.setEnabled(false);
            mTv_phone.setText("12444455555");
            //设置不可编辑状态
        }
    }
}
