package com.channnxy.school_bus;

import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class fragment_index extends Fragment{
    //    fragment内部listView配置
    private ListView mListView_journey;
    private ArrayList<Journey> mJourneyArrayList;

    //spinner
    private Spinner msp_origin;
    private Spinner msp_destination;
    private ArrayAdapter<String> originAdapter;

    //    日历
    private TextView mtv_calendar;

//    搜索
    private Button mbtn_search;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View index_view = inflater.inflate(R.layout.item_nav_index,container,false);
    //  初始化行程列表
        mListView_journey = index_view.findViewById(R.id.lv_index_journey);
        initJourneyAdapter();
    //  初始化起始地和目的地两个spinner
        msp_destination = index_view.findViewById(R.id.sp_destination);
        msp_origin = index_view.findViewById(R.id.sp_origin);
        initSpinnerAdapter();
    //  初始化日历
        mtv_calendar = index_view.findViewById(R.id.tv_index_useDp);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date(System.currentTimeMillis());
        mtv_calendar.setText(simpleDateFormat.format(date));
        mtv_calendar.setOnClickListener(new CalendarListener());

    //  检索按钮监听
        mbtn_search = index_view.findViewById(R.id.btn_index_search);
        mbtn_search.setOnClickListener(new btnOnclickListener());

        return index_view;
    }

//    spinner初始化
    private void initSpinnerAdapter() {
        String[] originArray = {"小和山","安吉校区","五常大道"};
        String[] destinationArray={"小和山","安吉校区","五常大道"};

        originAdapter = new ArrayAdapter<String>(getActivity(),R.layout.item_index_spinner,originArray);
        msp_origin.setAdapter(originAdapter);
        msp_origin.setPrompt("请选择出发地");
        msp_origin.setSelection(0);
        msp_origin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {            }
        });

        ArrayAdapter<String> destinationAdapter = new ArrayAdapter<String>(getActivity(),R.layout.item_index_spinner,destinationArray);
        msp_destination.setAdapter(destinationAdapter);
        msp_destination.setPrompt("请选择目的地");
        msp_destination.setSelection(1);
    }

//    行程卡片适配器初始化
    private void initJourneyAdapter() {
        mJourneyArrayList = Journey.getDefaultData();
        final journeyAdapter adapter = new journeyAdapter(getActivity(),mJourneyArrayList);

        mListView_journey.setAdapter(adapter);
        Util.setListViewHeightBasedOnChildren(mListView_journey);
        mListView_journey.setSelection(0);
        mListView_journey.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),journeyDetail.class);
                startActivity(intent);
            }
        });
    }

//    新建日历对话框
    private class CalendarListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Calendar m_calendar = Calendar.getInstance();
            DatePickerDialog m_dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    String desc = String.format("%d年%d月%d日",year,month,dayOfMonth);
                    mtv_calendar.setText(desc);
                }
            },
                    m_calendar.get(Calendar.YEAR),
                    m_calendar.get(Calendar.MONTH),
                    m_calendar.get(Calendar.DAY_OF_MONTH));
            m_dialog.show();
        }
    }

    //搜索按钮监听
    private class btnOnclickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(),searchResultActivity.class);
            startActivity(intent);
        }
    }
}
