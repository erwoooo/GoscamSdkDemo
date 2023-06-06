package com.goscamsdkdemo.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gos.platform.api.contact.ResultCode;
import com.gos.platform.api.devparam.DevParamResult;
import com.gos.platform.api.devparam.TimeZoneInfoParam;
import com.gos.platform.device.domain.DevTimeInfo;
import com.gos.platform.device.inter.OnDevEventCallback;
import com.gos.platform.device.result.DevResult;
import com.gos.platform.device.result.GetTimeInfoResult;
import com.gos.platform.device.result.ResetDevTimeResult;
import com.goscamsdkdemo.BaseActivity;
import com.goscamsdkdemo.R;
import com.goscamsdkdemo.entitiy.Device;
import com.goscamsdkdemo.entitiy.DeviceManager;
import com.goscamsdkdemo.util.AppDeviceParamConsumer;
import com.goscamsdkdemo.util.AppSetDeviceParamObservableOnSubscribe;
import com.goscamsdkdemo.util.ErrorCodeUtil;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TimeVerifyActivity extends BaseActivity implements OnDevEventCallback {
    TextView mTvTitle;
    String mDevId;
    Device mDevice;
    Button mBtnVerify;
    DevTimeInfo mDevTimeInfo;

    public static void startActivity(Activity activity, String deviceId){
        Intent intent = new Intent(activity, TimeVerifyActivity.class);
        intent.putExtra("DEV_ID", deviceId);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_verify);
        mTvTitle = findViewById(R.id.text_title);
        mTvTitle.setText(R.string.time_j);
        mBtnVerify = findViewById(R.id.btn_verify);
        mBtnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoading();
                resetTime();
            }
        });


        mDevId = getIntent().getStringExtra("DEV_ID");
        mDevice = DeviceManager.getInstance().findDeviceById(mDevId);
        mDevice.getConnection().addOnEventCallbackListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDevice.getConnection().removeOnEventCallbackListener(this);
    }

    @Override
    public void onDevEvent(String s, DevResult devResult) {

    }

    private void resetTime(){
        showLoading();
        int timeZone = mDevice.getVerifyTimezone();
        TimeZoneInfoParam param = new TimeZoneInfoParam((int)(System.currentTimeMillis()/1000), timeZone);
        Observable<DevParamResult> observable = Observable.create(new AppSetDeviceParamObservableOnSubscribe(mDevId, param));
        observable = observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread());
        Disposable disposable = observable.subscribe(new AppDeviceParamConsumer<TimeVerifyActivity>(this) {
            @Override
            public void accept(TimeVerifyActivity pr, DevParamResult result) {
                if(pr == null )
                    return;
                dismissLoading();
                if (result.code != ResultCode.SUCCESS) {
                    showToast(ErrorCodeUtil.handlePlatResultCode(result.code));
                } else {
                    showToast("verify success");
                }
            }
        });
    }
}
