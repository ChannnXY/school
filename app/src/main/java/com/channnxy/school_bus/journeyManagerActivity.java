package com.channnxy.school_bus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class journeyManagerActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView mListView;
    private ArrayList<Passenger> mPassengerArrayList = new ArrayList<>();

    //计数器
    private Button mbtn_teacher_minus;
    private Button mbtn_teacher_add;
    private Button mbtn_student_minus;
    private Button mbtn_student_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey_manager);

        mListView = findViewById(R.id.lv_manager);
        initListView();

        mbtn_student_add = findViewById(R.id.btn_student_add);
        mbtn_student_minus = findViewById(R.id.btn_student_minus);
        mbtn_teacher_add = findViewById(R.id.btn_teacher_add);
        mbtn_teacher_minus = findViewById(R.id.btn_teacher_minus);
        mbtn_teacher_add.setOnClickListener(this);
        mbtn_student_minus.setOnClickListener(this);
        mbtn_student_add.setOnClickListener(this);
        mbtn_teacher_minus.setOnClickListener(this);
    }

    private void initListView() {
        mPassengerArrayList = Passenger.defaultData();
        passengerAdapter adapter = new passengerAdapter(this,mPassengerArrayList);
        Util.setListViewHeightBasedOnChildren(mListView);
        mListView.setAdapter(adapter);
        mListView.setSelection(0);
    }

    @Override
    public void onClick(View v) {
        EditText tv_student = findViewById(R.id.tv_student);
        EditText tv_teacher = findViewById(R.id.tv_teacher);
        switch (v.getId()){
            case R.id.btn_teacher_add:{
                countEdit(tv_teacher,1);
            }
            case R.id.btn_teacher_minus:{
                countEdit(tv_teacher,-1);
            }
            case R.id.btn_student_add:{
                countEdit(tv_student,1);
            }
            case R.id.btn_student_minus:{
                countEdit(tv_student,-1);
            }
            default:break;
        }
    }

    public void countEdit(EditText EditText,int count){
        int num = Integer.parseInt(EditText.getText().toString());
        if(num>0) EditText.setText(String.valueOf(num + count));
        else Toast.makeText(this, "不能再少了", Toast.LENGTH_SHORT).show();
    }
}
