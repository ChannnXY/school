package com.channnxy.school_bus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class journeyListActivity extends AppCompatActivity {
    private ListView lv_search_list;
    private ArrayList<Train> mTrainArrayList;

    private GlobalData app;

    //初始化日历
    private TextView mTv_calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey_list);

        //初始化适配器
        initSearchListAdapter();
        //初始化日历
        mTv_calendar = findViewById(R.id.tv_search_date);
        mTv_calendar.setOnClickListener(new initCalendar());
        initCalendarTime();
    }



    //初始化适配器
    private void initSearchListAdapter() {
        lv_search_list = findViewById(R.id.lv_search_result);
        mTrainArrayList = Train.getDefaultData();
        trainAdapter adapter = new trainAdapter(this,mTrainArrayList);
        lv_search_list.setAdapter(adapter);
        lv_search_list.setSelection(0);
        lv_search_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(app.loginMode.equals("我是司机")){
                    Intent intent = new Intent(journeyListActivity.this, journeyManagerActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(journeyListActivity.this, journeyDetailActivity.class);
                    intent.putExtra("mode",true);
                    startActivity(intent);
                }

            }
        });
    }

    //初始化日历

    private void initCalendarTime() {
        Calendar calendar = Calendar.getInstance();
        Date mDate = new Date();
        calendar.setTime(mDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String desc = year + "年" + month + "月" + day + "日";
        mTv_calendar.setText(desc);
    }

    private class initCalendar implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Calendar m_calendar = Calendar.getInstance();
            DatePickerDialog m_dialog = new DatePickerDialog(journeyListActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    String desc = year + "年" + month + "月" + dayOfMonth + "日";
                    mTv_calendar.setText(desc);
                }
            },
                    m_calendar.get(Calendar.YEAR),
                    m_calendar.get(Calendar.MONTH),
                    m_calendar.get(Calendar.DAY_OF_MONTH));
            m_dialog.show();
        }
    }
}
