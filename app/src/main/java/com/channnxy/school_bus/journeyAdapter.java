package com.channnxy.school_bus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class journeyAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {
    private ArrayList<Journey> mJourneyArrayList = new ArrayList<>();
    private Context mContext;

    public journeyAdapter(Context context,ArrayList<Journey> journeys){
        this.mContext=context;
        this.mJourneyArrayList = journeys;
    }

    @Override
    public int getCount() { return mJourneyArrayList.size(); }

    @Override
    public Object getItem(int position) { return mJourneyArrayList.get(position); }

    @Override
    public long getItemId(int position) { return 0; }

    //填充数据
    private final class ViewHolder{
        public TextView mOrigin; //出发点
        public TextView mDestination; //目的地
        public TextView mStart_place; //上车地点
        public TextView mStart_time; //上车时间

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder;
        if(convertView == null){
            mViewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_nav_journey_list,null);
            mViewHolder.mOrigin = convertView.findViewById(R.id.tv_journey_origin);
            mViewHolder.mDestination = convertView.findViewById(R.id.tv_journey_destination);
            mViewHolder.mStart_place = convertView.findViewById(R.id.tv_journey_place);
            mViewHolder.mStart_time = convertView.findViewById(R.id.tv_journey_time);

            convertView.setTag(mViewHolder);
        }else{
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        Journey journey = mJourneyArrayList.get(position);
        mViewHolder.mOrigin.setText(journey.origin);
        mViewHolder.mDestination.setText(journey.destination);
        mViewHolder.mStart_place.setText(journey.start_place);
        mViewHolder.mStart_time.setText(journey.start_time);
        return convertView;
    }

    //点击操作
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
    }
}
