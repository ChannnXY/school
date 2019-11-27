package com.channnxy.school_bus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class passengerAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<Passenger> mPassengersList = new ArrayList<>();

    public passengerAdapter(Context context,ArrayList<Passenger> passengers){
        mContext = context;
        mPassengersList = passengers;
    }

    @Override
    public int getCount() {
        return mPassengersList.size();
    }

    @Override
    public Object getItem(int position) {
        return mPassengersList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private final class ViewHolder{
        public TextView mtv_name;
        public TextView mtv_identity;//身份
        public TextView mtv_state;//是否上车
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewholder;
        if(convertView == null){
            mViewholder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_journey_manager_list,null);
            mViewholder.mtv_name = convertView.findViewById(R.id.tv_manager_name);
            mViewholder.mtv_identity = convertView.findViewById(R.id.tv_manager_identity);
            mViewholder.mtv_state = convertView.findViewById(R.id.tv_manager_state);

            convertView.setTag(mViewholder);
        }else{
            mViewholder = (ViewHolder) convertView.getTag();
        }
        Passenger passenger = mPassengersList.get(position);
        mViewholder.mtv_name.setText(passenger.name);
        if(passenger.state==1){
            mViewholder.mtv_state.setText("已签到");
            mViewholder.mtv_state.setBackgroundResource(R.drawable.shape_rec_green);
        }else {
            mViewholder.mtv_state.setText("未上车");
            mViewholder.mtv_state.setBackgroundResource(R.drawable.shape_rec_gray);
        }
        if(passenger.identity==1){
            mViewholder.mtv_identity.setText("学生");
            mViewholder.mtv_identity.setBackgroundResource(R.drawable.shape_rec_orange);
        }else {
            mViewholder.mtv_identity.setText("老师");
            mViewholder.mtv_identity.setBackgroundResource(R.drawable.shape_rec_brown);
        }

        return convertView;
    }
}
