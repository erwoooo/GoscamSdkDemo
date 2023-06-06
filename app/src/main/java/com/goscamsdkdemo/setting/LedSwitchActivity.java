package com.goscamsdkdemo.setting;

import static com.gos.platform.api.devparam.DevParam.DevParamCmdType.BatteryInfo;
import static com.gos.platform.api.devparam.DevParam.DevParamCmdType.DeviceSwitch;
import static com.gos.platform.api.devparam.DevParam.DevParamCmdType.DoorbellLedSwitch;
import static com.gos.platform.api.devparam.DevParam.DevParamCmdType.DoorbellVolume;
import static com.gos.platform.api.devparam.DevParam.DevParamCmdType.HumanTrackSetting;
import static com.gos.platform.api.devparam.DevParam.DevParamCmdType.LedSwitch;
import static com.gos.platform.api.devparam.DevParam.DevParamCmdType.LowpowerModeSetting;
import static com.gos.platform.api.devparam.DevParam.DevParamCmdType.MicSwitch;
import static com.gos.platform.api.devparam.DevParam.DevParamCmdType.MirrorModeSetting;
import static com.gos.platform.api.devparam.DevParam.DevParamCmdType.NightSetting;
import static com.gos.platform.api.devparam.DevParam.DevParamCmdType.ObjTrackSetting;
import static com.gos.platform.api.devparam.DevParam.DevParamCmdType.PirSetting;
import static com.gos.platform.api.devparam.DevParam.DevParamCmdType.RecordDuration;
import static com.gos.platform.api.devparam.DevParam.DevParamCmdType.RemoveAlarm;
import static com.gos.platform.api.devparam.DevParam.DevParamCmdType.ResetSetting;
import static com.gos.platform.api.devparam.DevParam.DevParamCmdType.SmdAlarmSetting;
import static com.gos.platform.api.devparam.DevParam.DevParamCmdType.TfRecordSetting;
import static com.gos.platform.api.devparam.DevParam.DevParamCmdType.VolumeSetting;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.gos.platform.api.contact.ResultCode;
import com.gos.platform.api.devparam.BatteryInfoParam;
import com.gos.platform.api.devparam.DevParam;
import com.gos.platform.api.devparam.DevParamResult;
import com.gos.platform.api.devparam.DeviceSwitchParam;
import com.gos.platform.api.devparam.DoorbellLedSwitchParam;
import com.gos.platform.api.devparam.DoorbellVolumeParam;
import com.gos.platform.api.devparam.HumanTrackSettingParam;
import com.gos.platform.api.devparam.LedSwitchParam;
import com.gos.platform.api.devparam.LowpowerModeSettingParam;
import com.gos.platform.api.devparam.MicSwitchParam;
import com.gos.platform.api.devparam.MirrorModeSettingParam;
import com.gos.platform.api.devparam.NightSettingParam;
import com.gos.platform.api.devparam.ObjTrackSettingParam;
import com.gos.platform.api.devparam.PirSettingParam;
import com.gos.platform.api.devparam.RecordDurationParam;
import com.gos.platform.api.devparam.RemoveAlarmParam;
import com.gos.platform.api.devparam.ResetSettingParam;
import com.gos.platform.api.devparam.SmdAlarmSettingParam;
import com.gos.platform.api.devparam.TfRecordSettingParam;
import com.gos.platform.api.devparam.VolumeSettingParam;
import com.gos.platform.device.contact.OnOff;
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

public class LedSwitchActivity extends BaseActivity implements OnDevEventCallback, CompoundButton.OnCheckedChangeListener {
    TextView mTvTitle;
    String mDevId;
    Device mDevice;
    Switch mSwitch;

    public static void startActivity(Activity activity, String deviceId){
        Intent intent = new Intent(activity, LedSwitchActivity.class);
        intent.putExtra("DEV_ID", deviceId);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led_switch);
        mTvTitle = findViewById(R.id.text_title);
        mTvTitle.setText(R.string.led_switch);
        mSwitch = findViewById(R.id.i_switch);

        mDevId = getIntent().getStringExtra("DEV_ID");
        mDevice = DeviceManager.getInstance().findDeviceById(mDevId);
        mDevice.getConnection().addOnEventCallbackListener(this);
        showLoading();

        requestData();
    }

    private void requestData() {
        AppGetDeviceParamObservableOnSubscribe subscribe = new AppGetDeviceParamObservableOnSubscribe(mDevId);
        subscribe.add(LedSwitch);
        Observable<DevParamResult> observable = Observable.create(subscribe);
        observable = observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread());
        Disposable disposable = observable.subscribe(new AppDeviceParamConsumer<LedSwitchActivity>(this) {
            @Override
            public void accept(LedSwitchActivity pr, DevParamResult result) {
                if(pr == null )
                    return;
                dismissLoading();
                if(result.code != 0){
                    showToast(ErrorCodeUtil.handlePlatResultCode(result.code));
                    return;
                }
                for(int i = 0; result.devParams != null && i < result.devParams.size(); i++){
                    DevParam devParam = result.devParams.get(i);
                    switch (devParam.CMDType){
                        case LedSwitch:
                            mSwitch.setChecked(((LedSwitchParam)devParam).ledSwitch == OnOff.On);
                            break;
                        default:
                            break;
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

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        showLoading();
        LedSwitchParam switchParam = new LedSwitchParam(isChecked ? OnOff.On : OnOff.Off);
        Observable<DevParamResult> observable = Observable.create(new AppSetDeviceParamObservableOnSubscribe(mDevId, switchParam));
        observable = observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread());
        Disposable disposable = observable.subscribe(new AppDeviceParamConsumer<LedSwitchActivity>(this) {
            @Override
            public void accept(LedSwitchActivity pr, DevParamResult result) {
                if(pr == null )
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
