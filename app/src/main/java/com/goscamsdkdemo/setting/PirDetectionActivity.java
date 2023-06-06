package com.goscamsdkdemo.setting;

import static com.gos.platform.api.devparam.DevParam.DevParamCmdType.MicSwitch;
import static com.gos.platform.api.devparam.DevParam.DevParamCmdType.PirSetting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.gos.platform.api.contact.ResultCode;
import com.gos.platform.api.devparam.DevParamResult;
import com.gos.platform.api.devparam.MicSwitchParam;
import com.gos.platform.api.devparam.PirSettingParam;
import com.gos.platform.device.contact.OnOff;
import com.gos.platform.device.contact.PirDetectLevel;
import com.gos.platform.device.domain.DevParamInfo;
import com.gos.platform.device.inter.OnDevEventCallback;
import com.gos.platform.device.result.DevResult;
import com.gos.platform.device.result.GetDevParamInfoResult;
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

public class PirDetectionActivity extends BaseActivity implements OnDevEventCallback {
    TextView mTvTitle;
    String mDevId;
    Device mDevice;
    Switch mSwitch;

    public static void startActivity(Activity activity, String deviceId) {
        Intent intent = new Intent(activity, PirDetectionActivity.class);
        intent.putExtra("DEV_ID", deviceId);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pir_detection);
        mTvTitle = findViewById(R.id.text_title);
        mTvTitle.setText(R.string.pir_detection);
        mSwitch = findViewById(R.id.i_switch);

        mDevId = getIntent().getStringExtra("DEV_ID");
        mDevice = DeviceManager.getInstance().findDeviceById(mDevId);
        mDevice.getConnection().addOnEventCallbackListener(this);
        showLoading();

        requestData();
    }

    private void requestData() {
        AppGetDeviceParamObservableOnSubscribe subscribe = new AppGetDeviceParamObservableOnSubscribe(mDevId, PirSetting);
        Observable<DevParamResult> observable = Observable.create(subscribe);
        observable = observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread());
        Disposable disposable = observable.subscribe(new AppDeviceParamConsumer<PirDetectionActivity>(this) {
            @Override
            public void accept(PirDetectionActivity activity, DevParamResult result) {
                if (activity == null || activity.isFinishing())
                    return;
                dismissLoading();
                if (result.code != 0) {
                    showToast(ErrorCodeUtil.handlePlatResultCode(result.code));
                    return;
                }
                if (result.devParams != null && result.devParams.size() > 0) {
                    PirSettingParam warnSettingParam = (PirSettingParam) result.devParams.get(0);
                    mSwitch.setChecked(warnSettingParam.pirSwitch == OnOff.On);
                    mSwitch.setOnCheckedChangeListener(onCheckedChangeListener);
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
            sendPirData(isChecked ? OnOff.On : OnOff.Off);
        }
    };


    private void sendPirData(int status) {
        PirSettingParam switchParam = new PirSettingParam(status);
        Observable<DevParamResult> observable = Observable.create(new AppSetDeviceParamObservableOnSubscribe(mDevId, switchParam));
        observable = observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread());
        Disposable disposable = observable.subscribe(new AppDeviceParamConsumer<PirDetectionActivity>(this) {
            @Override
            public void accept(PirDetectionActivity pr, DevParamResult result) {
                if (pr == null)
                    return;
                dismissLoading();
                if (result.code != ResultCode.SUCCESS) {
                    showToast(ErrorCodeUtil.handlePlatResultCode(result.code));
                } else {
                    showToast("Success");
                }

            }
        });

    }
}
