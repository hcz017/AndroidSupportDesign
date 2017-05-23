package com.hcz017.androidsupportdesign;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

public class SecActivity extends AppCompatActivity {

    TextInputLayout textInputLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textInputLayout = (TextInputLayout) findViewById(R.id.password);
        textInputLayout.setCounterEnabled(true);
        textInputLayout.setCounterMaxLength(16);
//        EditText editText = textInputLayout.getEditText();
        EditText editText = (EditText) findViewById(R.id.edt_input_pwd);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //s是单个字符/单词？
                if (s.length() <= 6) {
                    textInputLayout.setErrorEnabled(true);
                    textInputLayout.setError(getString(R.string.error));
                } else {
                    textInputLayout.setErrorEnabled(false);
                    textInputLayout.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        // 当FAB被点击的时候
        // 若要snackbar可以滑动消除，则父布局需为CoordinatorLayout，不一定是直接父布局。
        // 而且snackbar显示出来的位置就是CoordinatorLayout的底部
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Login failed", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
