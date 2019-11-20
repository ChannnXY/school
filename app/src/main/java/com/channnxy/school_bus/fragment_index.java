package com.channnxy.school_bus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fragment_index extends Fragment {
    //    fragment内部listView配置
    private ListView mListView_journey;
    private ArrayList<Journey> mJourneyArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View index_view = inflater.inflate(R.layout.item_nav_index,container,false);
    //  初始化行程列表
        mListView_journey = index_view.findViewById(R.id.lv_index_journey);
        initJourneyAdapter();

        return index_view;
    }

    private void initJourneyAdapter() {
        mJourneyArrayList = Journey.getDefaultData();
        final journeyAdapter adapter = new journeyAdapter(getActivity(),mJourneyArrayList);

        mListView_journey.setAdapter(adapter);
        mListView_journey.setSelection(0);
        mListView_journey.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}
