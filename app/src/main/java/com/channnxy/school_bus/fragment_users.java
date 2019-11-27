package com.channnxy.school_bus;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fragment_users extends Fragment implements View.OnClickListener {
    private GlobalData app;
    private LinearLayout mLinearLayout_edit;
    private LinearLayout mLinearLayout_exit;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View users_view = inflater.inflate(R.layout.item_nav_users,container,false);
        mLinearLayout_edit = users_view.findViewById(R.id.linear_user_editPassword);
        mLinearLayout_exit = users_view.findViewById(R.id.linear_user_exit);
        mLinearLayout_edit.setOnClickListener(this);
        mLinearLayout_exit.setOnClickListener(this);
        return users_view;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.linear_user_editPassword:{
                intent = new Intent(getContext(),editPasswordActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.linear_user_exit:{
                app.loginType = -1;
                intent = new Intent(getContext(),loginActivity.class);
                startActivity(intent);
                break;
            }
            default:break;
        }
    }
}
