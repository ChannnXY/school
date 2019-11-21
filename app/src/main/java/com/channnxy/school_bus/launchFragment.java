package com.channnxy.school_bus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class launchFragment extends Fragment {
    protected View mView; // 声明一个视图对象
    protected Context mContext; // 声明一个上下文对象
    private int mPosition; // 位置序号
    private int mImageId; // 图片的资源编号
    private int mCount = 3; // 引导页的数量

    // 获取该碎片的一个实例,单例模式，控制他有且仅有一个
    public static launchFragment newInstance(int position,int image_id){
        launchFragment fragment = new launchFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position); // 往包裹存入位置序号
        bundle.putInt("image_id", image_id); // 往包裹存入图片的资源编号
        fragment.setArguments(bundle); // 把包裹塞给碎片
        return fragment; // 返回碎片实例
    }

    // 创建碎片视图
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity(); // 获取活动页面的上下文
        if (getArguments() != null) { // 如果碎片携带有包裹，则打开包裹获取参数信息
            mPosition = getArguments().getInt("position", 0);
            mImageId = getArguments().getInt("image_id", 0);
        }
        // 根据布局文件item_launch.xml生成视图对象
        mView = inflater.inflate(R.layout.item_launch, container, false);
        ImageView iv_launch = mView.findViewById(R.id.iv_launch);
        RadioGroup rg_indicate = mView.findViewById(R.id.rg_indicate);
        Button btn_start = mView.findViewById(R.id.btn_start);
        ImageView iv_start = mView.findViewById(R.id.iv_launch_text);
        // 设置引导页的全屏图片
        iv_launch.setImageResource(mImageId);
        // 每张图片都分配一个对应的单选按钮RadioButton
        for (int j = 0; j < mCount; j++) {
            RadioButton radio = new RadioButton(mContext);
            radio.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            if(j == mPosition){ radio.setButtonDrawable(R.drawable.icon_launch_selected); }
                else{radio.setButtonDrawable(R.drawable.icon_launch_unselected);}
            radio.setPadding(10, 10, 10, 10);
            // 把单选按钮添加到底部指示器的单选组
            rg_indicate.addView(radio);
        }
        // 当前位置的单选按钮要高亮显示，比如第二个引导页就高亮第二个单选按钮
        ((RadioButton) rg_indicate.getChildAt(mPosition)).setChecked(true);
        // 如果是最后一个引导页，则显示入口按钮，以便用户点击按钮进入首页
        if (mPosition == mCount - 1) {
            btn_start.setVisibility(View.VISIBLE);
            iv_start.setVisibility(View.GONE);
            btn_start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,"欢迎！",Toast.LENGTH_SHORT).show();
                    Intent mIntent = new Intent(getActivity(),loginActivity.class);
                    startActivity(mIntent);
                }
            });
        }
        return mView; // 返回该碎片的视图对象
    }
}
