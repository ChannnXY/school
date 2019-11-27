package com.channnxy.school_bus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class feedbackActivity extends AppCompatActivity {
    private TextView tv_count;
    private EditText tv_content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        tv_content = findViewById(R.id.tv_feedback_content);
        tv_count = findViewById(R.id.tv_feedback_count);
        tv_content.addTextChangedListener(new EditTextListener());
    }

    private class EditTextListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            int count = tv_content.getText().length();
            String desc = count + "/500";
            tv_count.setText(desc);
        }
    }
}
