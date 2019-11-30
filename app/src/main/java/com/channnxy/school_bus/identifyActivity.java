package com.channnxy.school_bus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class identifyActivity extends AppCompatActivity {
    private Spinner msp_identity;
    private int identity;
    private Button mBtn_confirm;
    private GlobalData app = new GlobalData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify);

        //初始化身份选择spinner
        msp_identity = findViewById(R.id.sp_login_identity);
        initSpinnerAdapter();

        mBtn_confirm = findViewById(R.id.btn_identify_confirm);
        mBtn_confirm.setOnClickListener(new confirmListener());
    }

    //初始化身份选择spinner
    private void initSpinnerAdapter() {
        String[] identityArray = {"我是老师","我是学生","我是司机"};

        final ArrayAdapter<String> identityAdapter = new ArrayAdapter<String>(this,R.layout.item_index_spinner,identityArray);
        msp_identity.setAdapter(identityAdapter);
        msp_identity.setPrompt("请选择你的身份");
        String url = app.url+"users/getIdentity?username="+app.username;
        GetInfoAsyncTask getInfoAsyncTask = new GetInfoAsyncTask();
        getInfoAsyncTask.execute(url);
        msp_identity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                identity = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //获得身份信息
    private class GetInfoAsyncTask extends AsyncTask<String,Void,JSONObject>{

        @Override
        protected JSONObject doInBackground(String... strings) {
            HttpReqData httpReqData = new HttpReqData(strings[0]);
            HttpRespData resp_data = HttpRequestUtil.getData(httpReqData);
            try {
                JSONObject res = new JSONObject(resp_data.content);
                return res;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            if(!jsonObject.isNull("data")){
                try {
                    JSONObject res = jsonObject.getJSONObject("data");
                    JSONObject data = res.getJSONObject("data");
                    msp_identity.setSelection(data.getInt("identity"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    更新监听按钮
    private class confirmListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            EditText tv_identity_passsword = findViewById(R.id.tv_identify_password);
            String password = tv_identity_passsword.getText().toString();
            String url = app.url+"users/updateIdentity";
            JSONObject req = new JSONObject();
            StringBuffer data = new StringBuffer("identity=");
            data.append(identity);
            data.append("&username=");
            data.append(app.username);
            try {
                req.put("url",url);
                req.put("data",data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(password.equals("123456")){
                UpdateIdentity updateIdentity = new UpdateIdentity();
                updateIdentity.execute(req);
            }else{
                Toast.makeText(identifyActivity.this, "管理员密码错误", Toast.LENGTH_SHORT).show();
            }
        }
    }
//    异步处理监听
    private class UpdateIdentity extends AsyncTask<JSONObject,Void,JSONObject>{

    @Override
    protected JSONObject doInBackground(JSONObject... jsonObjects) {
        HttpReqData httpReqData = new HttpReqData();
        try {
            httpReqData.url = jsonObjects[0].getString("url");
            StringBuffer data = new StringBuffer(jsonObjects[0].getString("data"));
            httpReqData.params = data;
//            Log.i("httpReqData.params",httpReqData.params.toString());
            HttpRespData resp_data = HttpRequestUtil.postUrl(httpReqData);
            JSONObject res = new JSONObject(resp_data.content);
            return res;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
        if(!jsonObject.isNull("data")){
            try {
                JSONObject res = jsonObject.getJSONObject("data");
                Toast.makeText(identifyActivity.this, res.getString("msg"), Toast.LENGTH_SHORT).show();
                if(res.getInt("code")==200){
                    Intent intent = new Intent(identifyActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
}
