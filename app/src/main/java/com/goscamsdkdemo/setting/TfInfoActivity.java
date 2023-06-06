package com.goscamsdkdemo.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.gos.platform.api.contact.ResultCode;
import com.gos.platform.device.contact.OnOff;
import com.gos.platform.device.domain.DevInfo;
import com.gos.platform.device.domain.DevParamInfo;
import com.gos.platform.device.inter.OnDevEventCallback;
import com.gos.platform.device.result.DevResult;
import com.gos.platform.device.result.FormatDevSdResult;
import com.gos.platform.device.result.GetDevInfoResult;
import com.gos.platform.device.result.GetDevParamInfoResult;
import com.gos.platform.device.result.GetDevSdInfoResult;
import com.goscamsdkdemo.BaseActivity;
import com.goscamsdkdemo.R;
import com.goscamsdkdemo.entitiy.Device;
import com.goscamsdkdemo.entitiy.DeviceManager;

//tf
public class TfInfoActivity extends BaseActivity implements OnDevEventCallback {
    TextView mTvTitle;
    String mDevId;
    Device mDevice;

    TextView mTvStatus;
    TextView mTvTotalSpace;
    TextView mTvUsedSpace;
    TextView mTvUnusedSpace;
    Button mBtnFormatSD;
    Handler mHandler = new Handler();

    public static void startActivity(Activity activity, String deviceId){
        Intent intent = new Intent(activity, TfInfoActivity.class);
        intent.putExtra("DEV_ID", deviceId);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tf_info);
        mTvTitle = findViewById(R.id.text_title);
        mTvTitle.setText(R.string.tf_info);
        mTvStatus = findViewById(R.id.tv_status);
        mTvTotalSpace = findViewById(R.id.tv_total_space);
        mTvUsedSpace = findViewById(R.id.tv_used_space);
        mTvUnusedSpace = findViewById(R.id.tv_unused_space);
        mBtnFormatSD = findViewById(R.id.btn_format);
        mBtnFormatSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoading();
                mDevice.getConnection().formatDevSdCard(0, 1);
            }
        });

        mDevId = getIntent().getStringExtra("DEV_ID");
        mDevice = DeviceManager.getInstance().findDeviceById(mDevId);
        mDevice.getConnection().addOnEventCallbackListener(this);
        showLoading();
        mDevice.getConnection().getDevSdInfo(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
        mDevice.getConnection().removeOnEventCallbackListener(this);
    }

    @Override
    public void onDevEvent(String s, DevResult devResult) {
        if(!TextUtils.equals(s,mDevId)){return;}
        if(DevResult.DevCmd.getDevSdInfo == devResult.getDevCmd()){
            dismissLoading();
            if(ResultCode.SUCCESS == devResult.getResponseCode()){
                GetDevSdInfoResult devInfo = (GetDevSdInfoResult) devResult;

                mTvStatus.setText(getString(R.string.tf_status)+devInfo.sdStatus);
                mTvTotalSpace.setText(getString(R.string.total_space)+devInfo.totalSize);
                mTvUsedSpace.setText(getString(R.string.used_space)+devInfo.usedSize);
                mTvUnusedSpace.setText(getString(R.string.unused_space)+devInfo.freeSize);
                mBtnFormatSD.setVisibility(devInfo.sdStatus>=0?View.VISIBLE:View.INVISIBLE);
            }
        }else if(DevResult.DevCmd.formatDevSdCard == devResult.getDevCmd()){
            if(ResultCode.SUCCESS == devResult.getResponseCode()){
                dismissLoading();
                showToast("format success");
                finish();
            }else if(1 == devResult.getResponseCode()){
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDevice.getConnection().formatDevSdCard(0, 0);
                    }
                },2000);
            }else {
                dismissLoading();
                showToast("format failed, code="+devResult.getResponseCode());
            }
        }
    }
}
