package com.goscamsdkdemo.add.bell;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.gos.platform.api.contact.ResultCode;
import com.gos.platform.device.contact.DoorCamStatus;
import com.gos.platform.device.inter.OnDevEventCallback;
import com.gos.platform.device.result.CheckDoorCamStatusResult;
import com.gos.platform.device.result.DevResult;
import com.goscamsdkdemo.BaseActivity;
import com.goscamsdkdemo.MainActivity;
import com.goscamsdkdemo.R;
import com.goscamsdkdemo.entitiy.Device;
import com.goscamsdkdemo.entitiy.DeviceManager;

//一拖一，中继与门铃配对
public class AddBellChildActivity extends BaseActivity implements OnDevEventCallback {
    TextView mTextTitle;
    TextView mTvAddStatus;

    String mDeviceId;
    Device mDevice;

    Handler mHandler = new Handler();

    public static void startActivity(Activity activity, String deviceId){
        Intent intent = new Intent(activity,AddBellChildActivity.class);
        intent.putExtra("DEV_ID", deviceId);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bell_child);
        mDeviceId = getIntent().getStringExtra("DEV_ID");
        mTextTitle = findViewById(R.id.text_title);
        mTvAddStatus = findViewById(R.id.tv_add_status);

        mTextTitle.setText(R.string.open_doorbell);

        mDevice = DeviceManager.getInstance().findDeviceById(mDeviceId);
        if(mDevice==null){
            showToast("未获取到中继设备");
            finish();
            return;
        }

        mDevice.getConnection().addOnEventCallbackListener(this);
        mDevice.getConnection().checkDoorCamStatus(0);

        //超时
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mHandler.removeCallbacksAndMessages(null);
                startActivity(new Intent(AddBellChildActivity.this, MainActivity.class));
                showToast(getResources().getString(R.string.time_out));
                finish();
            }
        }, 2 * 60 * 1000);
        //提示
        mTvAddStatus.setText(R.string.please_open_doorbell);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mTvAddStatus.setText(R.string.open_doorbell_ing);
            }
        }, 3 * 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
        if(mDevice != null)
            mDevice.getConnection().removeOnEventCallbackListener(this);
    }

    @Override
    public void onDevEvent(String s, DevResult devResult) {
        if(DevResult.DevCmd.checkDoorCamStatus == devResult.getDevCmd()){
            int code = devResult.getResponseCode();
            CheckDoorCamStatusResult result = (CheckDoorCamStatusResult) devResult;
            if(ResultCode.SUCCESS == code && result.getStatus() != DoorCamStatus.NO_PAIR){
                showToast(getResources().getString(R.string.bind_success));
                startActivity(new Intent(AddBellChildActivity.this, MainActivity.class));
                finish();
            }else{
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDevice.getConnection().checkDoorCamStatus(0);
                    }
                },2000);
            }
        }
    }
}
