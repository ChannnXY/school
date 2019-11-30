package com.channnxy.school_bus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class loginActivity extends AppCompatActivity {
    private CheckBox mCb_remember;
    private Button mBtn_login;
    private EditText mTv_username;
    private EditText mTv_password;
    private GlobalData app = new GlobalData();
    //是否记住密码
    private boolean isRemember;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //初始化记住的账号和密码
        mCb_remember = findViewById(R.id.cb_remember_Password);
        mCb_remember.setOnCheckedChangeListener(new checkboxListener());

        mTv_password = findViewById(R.id.tv_login_password);
        mTv_username = findViewById(R.id.tv_login_username);
        initPassword();
        //监听记住密码按钮
        mBtn_login = findViewById(R.id.btn_login);
        mBtn_login.setOnClickListener(new loginOnclickListener());
    }
    //初始化记住的账号和密码
    private void initPassword() {
        SharedPreferences sharedPreferences = getSharedPreferences("share",MODE_PRIVATE);
        CharSequence username = sharedPreferences.getString("username",null);
        CharSequence password = sharedPreferences.getString("password",null);
        boolean isRemember = sharedPreferences.getBoolean("isRemember",true);
        if(username!=null&&password!=null){
            mTv_username.setText(username);
            mTv_password.setText(password);
            mCb_remember.setChecked(isRemember);
        }
    }

    private class loginOnclickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String username = mTv_username.getText().toString();
            String password = mTv_password.getText().toString();
            JSONObject req = new JSONObject();
            try {
                req.put("url",app.url+"users/login?username="+username+"&password="+password);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            LoginAsyncTask loginAsyncTask = new LoginAsyncTask();
            loginAsyncTask.execute(req);
        }
    }

    //    异步请求，登录
    private class LoginAsyncTask extends AsyncTask<JSONObject,Void,JSONObject>{

        @Override
        protected JSONObject doInBackground(JSONObject... jsonObjects) {
            HttpReqData httpReqData = new HttpReqData();
            try {
                httpReqData.url=jsonObjects[0].getString("url");
                HttpRespData resp_data = HttpRequestUtil.getData(httpReqData);
                JSONObject res = new JSONObject(resp_data.content);
//                Log.i("___response_data___",res.toString());
                return res;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
//            Log.i("response_data",jsonObject.toString());
            if(!jsonObject.isNull("data")){
                try {
                    JSONObject res = jsonObject.getJSONObject("data");
                    int code = res.getInt("code");
                    String msg = res.getString("msg");
                    JSONObject data = res.getJSONObject("data");
                    int identity = data.getInt("identity");
                    String username = data.getString("username");
                    if(code == 200){
                        app.loginMode = identity;
                        app.username = username;
                        //如果点击了记住密码，将记住的账号密码放到共享参数里
                        SharedPreferences sharedPreferences = getSharedPreferences("share",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        if(isRemember){
                            String password = mTv_password.getText().toString();
                            editor.putString("username",username);
                            editor.putString("password",password);
                            editor.commit();
                        }else{
                            editor.putString("username",null);
                            editor.putString("password",null);
                            editor.commit();
                        }
                        editor.putBoolean("isRemember",isRemember);
                        //登录成功进行页面跳转
                        Intent intent = new Intent(loginActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                    Toast.makeText(loginActivity.this, msg, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //监听是否记住密码
    private class checkboxListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            isRemember = isChecked;
        }
    }
}
