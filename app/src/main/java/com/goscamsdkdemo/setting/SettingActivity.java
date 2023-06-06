package com.goscamsdkdemo.setting;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.gos.platform.api.GosSession;
import com.gos.platform.api.contact.PlatCode;
import com.gos.platform.api.inter.OnPlatformEventCallback;
import com.gos.platform.api.result.PlatResult;
import com.goscamsdkdemo.BaseActivity;
import com.goscamsdkdemo.GApplication;
import com.goscamsdkdemo.R;
import com.goscamsdkdemo.entitiy.Device;
import com.goscamsdkdemo.entitiy.DeviceManager;

public class SettingActivity extends BaseActivity implements OnPlatformEventCallback {
    TextView mTvTitle;
    String mDevId;
    Device mDevice;
    GosSession mGosSession;

    public static void startActivity(Activity activity, String deviceId){
        Intent intent = new Intent(activity, SettingActivity.class);
        intent.putExtra("DEV_ID", deviceId);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mTvTitle = findViewById(R.id.text_title);
        mTvTitle.setText(R.string.setting);

        mDevId = getIntent().getStringExtra("DEV_ID");
        mDevice = DeviceManager.getInstance().findDeviceById(mDevId);
        mGosSession = GosSession.getSession();
        mGosSession.addOnPlatformEventCallback(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mGosSession.removeOnPlatformEventCallback(this);
    }

    public void click_Modify_DevName(View v){
        ModifyDevNameActivity.startActivity(this, mDevId);
    }

    public void click_Dev_Share(View v){
        DevShareActivity.startActivity(this, mDevId);
    }

    @Override
    public void OnPlatformEvent(PlatResult platResult) {
        if(platResult.getPlatCmd() == PlatResult.PlatCmd.unbindSmartDevice){
            dismissLoading();
            if(PlatCode.SUCCESS == platResult.getResponseCode()){
                DeviceManager.getInstance().deleteDevice(mDevice);
                mGosSession.getDeviceList(GApplication.app.user.userName);
                finish();
            }else{
                showToast("Delete failed, code="+platResult.getResponseCode());
            }
        }
    }

    public void click_Delete_Dev(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Notice");
        builder.setMessage(R.string.sure_delete);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                showLoading();
                mGosSession.unbindSmartDevice(GApplication.app.user.userName, mDevId, mDevice.isOwn);
            }
        });
        builder.show();
    }

    public void click_Move_Motion(View v){
        MotionDetectionActivity.startActivity(this, mDevId);
    }

    public void click_Pir_Detection(View v){
        PirDetectionActivity.startActivity(this, mDevId);
    }

    public void click_Time_Verify(View v){
        TimeVerifyActivity.startActivity(this, mDevId);
    }

    public void click_Night_Switch(View v){
        NightSwitchActivity.startActivity(this, mDevId);
    }

    public void click_Led_Switch(View v){
        LedSwitchActivity.startActivity(this, mDevId);
    }

    public void click_Camera_Switch(View v){
        CameraSwitchActivity.startActivity(this, mDevId);
    }

    public void click_Mic_Switch(View v){
        MicSwitchActivity.startActivity(this, mDevId);
    }

    public void click_Tf_Info(View v){
        TfInfoActivity.startActivity(this, mDevId);
    }

    public void click_Soft_Update(View v){
        DevSoftUpdateActivity.startActivity(this, mDevId);
    }
}
