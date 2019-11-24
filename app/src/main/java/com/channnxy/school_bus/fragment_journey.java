package com.channnxy.school_bus;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.channnxy.school_bus.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fragment_journey extends Fragment{
    //    fragment内部listView配置
    public ListView mListView_journey;
    public ArrayList<Journey> mJourneyArrayList;

    public Intent mIntent;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View journey_view = inflater.inflate(R.layout.item_nav_journey,container,false);

        mJourneyArrayList = Journey.getDefaultData();
        final journeyAdapter adapter = new journeyAdapter(getActivity(),mJourneyArrayList);
        mListView_journey = journey_view.findViewById(R.id.lv_journey);

        mListView_journey.setAdapter(adapter);
        mListView_journey.setSelection(0);
        mListView_journey.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mIntent = new Intent(getActivity(),journeyDetail.class);
                startActivity(mIntent);
            }
        });
        return journey_view;
    }
}
