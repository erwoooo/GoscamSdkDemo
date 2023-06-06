package com.goscamsdkdemo.add;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.goscamsdkdemo.BaseActivity;
import com.goscamsdkdemo.R;
import com.goscamsdkdemo.add.bell.AddBellNoticeActivity;

public class WifiSelectActivity extends BaseActivity implements View.OnClickListener {
    TextView mTvTitle;
    EditText mEtSSID;
    EditText mEtPsw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_select);
        mTvTitle = findViewById(R.id.text_title);
        mTvTitle.setText(R.string.add_device);
        mEtSSID = findViewById(R.id.et_ssid);
        //mEtSSID.setText("abcd");
        mEtPsw = findViewById(R.id.et_password);
        //mEtPsw.setText("987654321111");

        findViewById(R.id.btn_ipc).setOnClickListener(this);
        findViewById(R.id.btn_doorbell).setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        String ssid = mEtSSID.getText().toString();
        String psw = mEtPsw.getText().toString();
        if(v.getId() == R.id.btn_ipc){
            QrCodeActivity.startActivity(this, ssid, psw);
            finish();
        }else if(v.getId() == R.id.btn_doorbell){
            AddBellNoticeActivity.startActivity(this, ssid, psw);
            finish();
        }
    }
}
