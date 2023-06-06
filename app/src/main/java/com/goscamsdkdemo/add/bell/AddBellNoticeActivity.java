package com.goscamsdkdemo.add.bell;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.goscamsdkdemo.BaseActivity;
import com.goscamsdkdemo.R;

public class AddBellNoticeActivity extends BaseActivity implements View.OnClickListener {

    public static void startActivity(Activity activity, String wifi, String psw){
        Intent intent = new Intent(activity, AddBellNoticeActivity.class);
        intent.putExtra("wifi", wifi);
        intent.putExtra("psw", psw);
        activity.startActivity(intent);
    }

    TextView mTextTitle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bell_notice);
        mTextTitle = findViewById(R.id.text_title);
        mTextTitle.setText(R.string.open_dev);

        findViewById(R.id.rl).setOnClickListener(this);
        findViewById(R.id.btn_step_notice).setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl:
                final Dialog mDialog = new Dialog(this, R.style.DialogNoBackground);
                View view = LayoutInflater.from(this).inflate(R.layout.layout_notice_bell, null, false);
                mDialog.setContentView(view);
                view.findViewById(R.id.btn_yes).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                    }
                });
                mDialog.show();
                break;
            case R.id.btn_step_notice:
                AddBellVoiceActivity.startActivity(this,
                        getIntent().getStringExtra("wifi"),
                        getIntent().getStringExtra("psw"));
                break;
        }
    }

}
