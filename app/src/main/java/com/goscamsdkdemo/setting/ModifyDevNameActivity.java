package com.goscamsdkdemo.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gos.platform.api.GosSession;
import com.gos.platform.api.contact.PlatCode;
import com.gos.platform.api.inter.OnPlatformEventCallback;
import com.gos.platform.api.result.PlatResult;
import com.goscamsdkdemo.BaseActivity;
import com.goscamsdkdemo.R;

public class ModifyDevNameActivity extends BaseActivity implements OnPlatformEventCallback {
    TextView mTvTitle;
    EditText mEtDevName;

    GosSession mGosSession;
    String mDevId;

    public static void startActivity(Activity activity, String deviceId){
        Intent intent = new Intent(activity, ModifyDevNameActivity.class);
        intent.putExtra("DEV_ID", deviceId);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mofiy_dev_name);
        mTvTitle = findViewById(R.id.text_title);
        mTvTitle.setText(R.string.modify_dev_name);
        mEtDevName = findViewById(R.id.et_devName);
        findViewById(R.id.btn_modify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoading();
                mGosSession.modifyDeviceAttr(mDevId, mEtDevName.getText().toString(),"","");
            }
        });

        mDevId = getIntent().getStringExtra("DEV_ID");
        mGosSession = GosSession.getSession();
        mGosSession.addOnPlatformEventCallback(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mGosSession.removeOnPlatformEventCallback(this);
    }

    @Override
    public void OnPlatformEvent(PlatResult platResult) {
        if(platResult.getPlatCmd() == PlatResult.PlatCmd.modifyDeviceAttr){
            dismissLoading();
            if(platResult.getResponseCode() == PlatCode.SUCCESS){
                showToast("Modify Success");
            } else{
                showToast("Modify failed,code="+platResult.getResponseCode());
            }
        }
    }
}
