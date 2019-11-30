package com.channnxy.school_bus;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
    private LinearLayout mLinearLayout_feedback;
    private LinearLayout mLinearLayout_identify;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View users_view = inflater.inflate(R.layout.item_nav_users,container,false);
        mLinearLayout_edit = users_view.findViewById(R.id.linear_user_editPassword);
        mLinearLayout_exit = users_view.findViewById(R.id.linear_user_exit);
        mLinearLayout_feedback = users_view.findViewById(R.id.linear_feedback);
        mLinearLayout_identify = users_view.findViewById(R.id.linear_user_identify);
        mLinearLayout_identify.setOnClickListener(this);
        mLinearLayout_edit.setOnClickListener(this);
        mLinearLayout_exit.setOnClickListener(this);
        mLinearLayout_feedback.setOnClickListener(this);
        return users_view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.linear_user_editPassword:{
                Intent intent = new Intent(getContext(),editPasswordActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.linear_user_exit:{
                app.loginType = -1;
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setPositiveButton("确定退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getContext(),loginActivity.class);
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("不退出了",null);
                alert.setMessage("退出之后将清空登录状态，您确定要退出吗");
                alert.show();

                break;
            }
            case R.id.linear_feedback:{
                Intent intent = new Intent(getContext(),feedbackActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.linear_user_identify:{
                Intent intent = new Intent(getContext(),identifyActivity.class);
                startActivity(intent);
                break;
            }
            default:break;
        }
    }
}
