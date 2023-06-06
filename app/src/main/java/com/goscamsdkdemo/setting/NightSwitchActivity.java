package com.goscamsdkdemo.setting;

import static com.gos.platform.api.devparam.DevParam.DevParamCmdType.NightSetting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.gos.platform.api.contact.ResultCode;
import com.gos.platform.api.devparam.DevParamResult;
import com.gos.platform.api.devparam.NightSettingParam;
import com.gos.platform.device.contact.OnOff;
import com.gos.platform.device.inter.OnDevEventCallback;
import com.gos.platform.device.result.DevResult;
import com.gos.platform.device.result.GetDevNightSwitchResult;
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

public class NightSwitchActivity extends BaseActivity implements OnDevEventCallback {
    TextView mTvTitle;
    String mDevId;
    Device mDevice;
    RadioButton mRbOpen;
    RadioButton mRbClose;
    RadioButton mRbAuto;
    private int nightSwitch = OnOff.Off;
    private int auto = OnOff.Off;



    public static void startActivity(Activity activity, String deviceId) {
        Intent intent = new Intent(activity, NightSwitchActivity.class);
        intent.putExtra("DEV_ID", deviceId);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night_switch);
        mTvTitle = findViewById(R.id.text_title);
        mTvTitle.setText(R.string.night_switch);
        mRbOpen = findViewById(R.id.rb_open);
        mRbClose = findViewById(R.id.rb_close);
        mRbAuto = findViewById(R.id.rb_auto);

        mDevId = getIntent().getStringExtra("DEV_ID");
        mDevice = DeviceManager.getInstance().findDeviceById(mDevId);
        mDevice.getConnection().addOnEventCallbackListener(this);
        showLoading();
        requestData();
    }

    private void requestData() {

        AppGetDeviceParamObservableOnSubscribe subscribe = new AppGetDeviceParamObservableOnSubscribe(mDevId, NightSetting);
        Observable<DevParamResult> observable = Observable.create(subscribe);
        observable = observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread());
        Disposable disposable = observable.subscribe(new AppDeviceParamConsumer<NightSwitchActivity>(this) {
            @Override
            public void accept(NightSwitchActivity pr, DevParamResult result) {
                if (pr == null)
                    return;
                dismissLoading();
                if (result.code != 0) {
                    showToast(ErrorCodeUtil.handlePlatResultCode(result.code));
                    return;
                }
                if (result.devParams != null && result.devParams.size() > 0) {
                    NightSettingParam settingParam = (NightSettingParam) result.devParams.get(0);
                    nightSwitch = settingParam.nightSwitch;//range(0:day 1:night)
                    auto = settingParam.auto;//range (0:off 1:on)
                    mRbOpen.setChecked(nightSwitch == OnOff.On);
                    mRbClose.setChecked(nightSwitch == OnOff.Off && auto == OnOff.Off);
                    mRbAuto.setChecked(auto == OnOff.On);
                    mRbOpen.setOnCheckedChangeListener(onCheckedChangeListener);
                    mRbClose.setOnCheckedChangeListener(onCheckedChangeListener);
                    mRbAuto.setOnCheckedChangeListener(onCheckedChangeListener);
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
        public void onCheckedChanged(CompoundButton buttonView, boolean b) {
            mRbAuto.setOnCheckedChangeListener(null);
            mRbClose.setOnCheckedChangeListener(null);
            mRbOpen.setOnCheckedChangeListener(null);
            switch (buttonView.getId()) {
                case R.id.rb_open:
                    mRbAuto.setChecked(false);
                    mRbClose.setChecked(false);
                    mRbOpen.setChecked(true);
                    showLoading();
                    setDevParam( OnOff.On, OnOff.Off);
                    break;
                case R.id.rb_close:
                    mRbAuto.setChecked(false);
                    mRbOpen.setChecked(false);
                    mRbClose.setChecked(true);
                    showLoading();
                    setDevParam( OnOff.Off, OnOff.Off);
                    break;
                case R.id.rb_auto:
                    mRbClose.setChecked(false);
                    mRbOpen.setChecked(false);
                    mRbAuto.setChecked(true);
                    showLoading();

                    setDevParam( OnOff.Off, OnOff.On);
                    break;
            }
            mRbAuto.setOnCheckedChangeListener(onCheckedChangeListener);
            mRbClose.setOnCheckedChangeListener(onCheckedChangeListener);
            mRbOpen.setOnCheckedChangeListener(onCheckedChangeListener);
        }
    };


    private void setDevParam(int reqSwitch,int auto){
        NightSettingParam param = new NightSettingParam(auto,reqSwitch);
        Observable<DevParamResult> observable = Observable.create(new AppSetDeviceParamObservableOnSubscribe(mDevId, param));
        observable = observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread());
        Disposable disposable = observable.subscribe(new AppDeviceParamConsumer<NightSwitchActivity>(this) {
            @Override
            public void accept(NightSwitchActivity pr, DevParamResult result) {
                if(pr == null )
                    return;
                dismissLoading();
                if (result.code != ResultCode.SUCCESS) {
                    showToast(ErrorCodeUtil.handlePlatResultCode(result.code));
                } else {
                }
            }
        });
    }
}
