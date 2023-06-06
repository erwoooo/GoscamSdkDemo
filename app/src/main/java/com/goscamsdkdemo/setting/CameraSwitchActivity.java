package com.goscamsdkdemo.setting;


import static com.gos.platform.api.devparam.DevParam.DevParamCmdType.DeviceSwitch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.gos.platform.api.contact.ResultCode;
import com.gos.platform.api.devparam.DevParam;
import com.gos.platform.api.devparam.DevParamResult;
import com.gos.platform.api.devparam.DeviceSwitchParam;
import com.gos.platform.device.contact.OnOff;
import com.gos.platform.device.inter.OnDevEventCallback;
import com.gos.platform.device.result.DevResult;

import com.goscamsdkdemo.BaseActivity;
import com.goscamsdkdemo.R;
import com.goscamsdkdemo.entitiy.Device;
import com.goscamsdkdemo.entitiy.DeviceManager;
import com.goscamsdkdemo.util.AppDeviceParamConsumer;
import com.goscamsdkdemo.util.AppGetDeviceParamObservableOnSubscribe;
import com.goscamsdkdemo.util.AppSetDeviceParamObservableOnSubscribe;
import com.goscamsdkdemo.util.ErrorCodeUtil;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CameraSwitchActivity extends BaseActivity implements OnDevEventCallback {
    TextView mTvTitle;
    String mDevId;
    Device mDevice;
    Switch mSwitch;

    public static void startActivity(Activity activity, String deviceId){
        Intent intent = new Intent(activity, CameraSwitchActivity.class);
        intent.putExtra("DEV_ID", deviceId);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_switch);
        mTvTitle = findViewById(R.id.text_title);
        mTvTitle.setText(R.string.camera_switch);
        mSwitch = findViewById(R.id.i_switch);

        mDevId = getIntent().getStringExtra("DEV_ID");
        mDevice = DeviceManager.getInstance().findDeviceById(mDevId);
        mDevice.getConnection().addOnEventCallbackListener(this);
        showLoading();

        requestData();
    }

    private void requestData() {
        AppGetDeviceParamObservableOnSubscribe subscribe = new AppGetDeviceParamObservableOnSubscribe(mDevId);
        subscribe.add(DeviceSwitch);
        Observable<DevParamResult> observable = Observable.create(subscribe);
        observable = observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread());
        Disposable disposable = observable.subscribe(new AppDeviceParamConsumer<CameraSwitchActivity>(this) {
            @Override
            public void accept(CameraSwitchActivity activity, DevParamResult result) {
                if (activity == null || activity.isFinishing())
                    return;
                dismissLoading();
                if (result.code != 0) {
                    showToast(ErrorCodeUtil.handlePlatResultCode(result.code));
                    return;
                }
                if (result.devParams != null && result.devParams.size() > 0) {

                    for(int i = 0; result.devParams != null && i < result.devParams.size(); i++) {
                        DevParam devParam = result.devParams.get(i);
                        switch (devParam.CMDType) {
                            case DeviceSwitch:
                                DeviceSwitchParam deviceSwitchParam = (DeviceSwitchParam) devParam;
                                boolean isChecked = deviceSwitchParam.deviceSwitch == OnOff.On;
                                mSwitch.setChecked(isChecked);
                                mSwitch.setOnCheckedChangeListener(onCheckedChangeListener);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDevice.getConnection().removeOnEventCallbackListener(this);
    }

    @Override
    public void onDevEvent(String s, DevResult devResult) {

    }


    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            showLoading();
            sendCameraData( isChecked ? OnOff.On : OnOff.Off);
        }
    };

    private void sendCameraData(int status){
        DeviceSwitchParam switchParam = new DeviceSwitchParam(status);
        Observable<DevParamResult> observable = Observable.create(new AppSetDeviceParamObservableOnSubscribe(mDevId, switchParam));
        observable = observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread());
        Disposable disposable = observable.subscribe(new AppDeviceParamConsumer<CameraSwitchActivity>(this) {
            @Override
            public void accept(CameraSwitchActivity pr, DevParamResult result) {
                if(pr == null)
                    return;
                dismissLoading();

                if (result.code != ResultCode.SUCCESS) {
                    showToast(ErrorCodeUtil.handlePlatResultCode(result.code));
                } else {//保存开关状态
                    showToast("Success");
                }
            }
        });
    }
}
