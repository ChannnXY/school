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

public class trainAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {
    public ArrayList<Train> mTrainArrayList = new ArrayList<>();
    private Context mContext;

    public trainAdapter(Context context,ArrayList<Train> arrayList){
        this.mContext = context;
        this.mTrainArrayList = arrayList;
    }

    @Override
    public int getCount() {
        return mTrainArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mTrainArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
    }

    private final class ViewHolder{
        public TextView mOriginTime;
        public TextView mOriginPlace;
        public TextView mDestinationTime;
        public TextView mDestinationPlace;
        public TextView mLicense;
        public TextView mSeatForStudent;
        public TextView mSeatForTeacher;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder;
        if(convertView == null ){
            mViewHolder = new ViewHolder();
            convertView  = LayoutInflater.from(mContext).inflate(R.layout.item_search_result_list,null);
            mViewHolder.mOriginTime = convertView.findViewById(R.id.tv_search_originTime);
            mViewHolder.mOriginPlace = convertView.findViewById(R.id.tv_search_originPlace);
            mViewHolder.mDestinationTime = convertView.findViewById(R.id.tv_search_destinationTime);
            mViewHolder.mDestinationPlace = convertView.findViewById(R.id.tv_search_destinationPlace);
            mViewHolder.mLicense = convertView.findViewById(R.id.tv_search_license);
            mViewHolder.mSeatForStudent = convertView.findViewById(R.id.tv_search_seat_for_student);
            mViewHolder.mSeatForTeacher = convertView.findViewById(R.id.tv_search_seat_for_teacher);
            convertView.setTag(mViewHolder);
        }else{
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        Train train = mTrainArrayList.get(position);
        mViewHolder.mOriginTime.setText(train.originTime);
        mViewHolder.mOriginPlace.setText(train.originPlace);
        mViewHolder.mDestinationPlace.setText(train.destinationPlace);
        mViewHolder.mDestinationTime.setText(train.destinationTime);
        mViewHolder.mLicense.setText(train.license);
        mViewHolder.mSeatForStudent.setText("学生座位"+train.seatForStudent);
        mViewHolder.mSeatForTeacher.setText("老师座位"+train.seatForTeacher);
        return convertView;
    }
}
