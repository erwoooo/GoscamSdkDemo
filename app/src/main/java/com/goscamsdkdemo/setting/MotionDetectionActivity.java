package com.goscamsdkdemo.setting;

import static com.gos.platform.api.devparam.DevParam.DevParamCmdType.MotionDetection;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.gos.platform.api.contact.ResultCode;
import com.gos.platform.api.devparam.DevParamResult;
import com.gos.platform.api.devparam.MotionDetectionParam;
import com.gos.platform.device.contact.MotionAutoType;
import com.gos.platform.device.contact.MotionDetectLevel;
import com.gos.platform.device.contact.MotionDetectMode;
import com.gos.platform.device.contact.OnOff;
import com.gos.platform.device.domain.MotionDetectInfo;
import com.gos.platform.device.inter.OnDevEventCallback;
import com.gos.platform.device.result.DevResult;
import com.gos.platform.device.result.GetMotionDetectResult;
import com.goscamsdkdemo.BaseActivity;
import com.goscamsdkdemo.MainActivity;
import com.goscamsdkdemo.PlayActivity;
import com.goscamsdkdemo.R;
import com.goscamsdkdemo.entitiy.Device;
import com.goscamsdkdemo.entitiy.DeviceManager;
import com.goscamsdkdemo.util.AppDeviceParamConsumer;
import com.goscamsdkdemo.util.AppGetDeviceParamObservableOnSubscribe;
import com.goscamsdkdemo.util.AppSetDeviceParamObservableOnSubscribe;
import com.goscamsdkdemo.util.ErrorCodeUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MotionDetectionActivity extends BaseActivity implements OnDevEventCallback {
    TextView mTvTitle;
    String mDevId;
    Device mDevice;

    Switch mSwitch;
    RadioGroup mRg;
    RadioButton mRbHigh;
    RadioButton mRbDefault;
    RadioButton mRbLow;
    Button mBtnSave;

    RecyclerView mRecyclerView;
    AreaAdapter mAreaAdapter;
    MotionDetectInfo info;

    public static void startActivity(Activity activity, String deviceId) {
        Intent intent = new Intent(activity, MotionDetectionActivity.class);
        intent.putExtra("DEV_ID", deviceId);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_detection);
        mTvTitle = findViewById(R.id.text_title);
        mTvTitle.setText(R.string.move_motion);
        mSwitch = findViewById(R.id.i_switch);
        mRg = findViewById(R.id.rg);
        mRbHigh = findViewById(R.id.rb_high);
        mRbDefault = findViewById(R.id.rb_default);
        mRbLow = findViewById(R.id.rb_low);
        mBtnSave = findViewById(R.id.btn_save);
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoading();
                int lev = mRg.getCheckedRadioButtonId() == R.id.rb_high ? MotionDetectLevel.HEIGH : (mRg.getCheckedRadioButtonId() == R.id.rb_default ? MotionDetectLevel.MIDDEL : MotionDetectLevel.LOW);
                sendMotionData(mSwitch.isChecked() ? OnOff.On : OnOff.Off, lev, areaList);
            }
        });

        mRecyclerView = findViewById(R.id.recycle_view);
        GridLayoutManager manager = new GridLayoutManager(this, 4);
        mRecyclerView.setLayoutManager(manager);
        mAreaAdapter = new AreaAdapter();
        mRecyclerView.setAdapter(mAreaAdapter);

        mDevId = getIntent().getStringExtra("DEV_ID");
        mDevice = DeviceManager.getInstance().findDeviceById(mDevId);
        mDevice.getConnection().addOnEventCallbackListener(this);
        showLoading();
        requestData();
    }

    private void requestData() {
        AppGetDeviceParamObservableOnSubscribe subscribe = new AppGetDeviceParamObservableOnSubscribe(mDevId, MotionDetection);
        Observable<DevParamResult> observable = Observable.create(subscribe);
        observable = observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread());
        Disposable disposable = observable.subscribe(new AppDeviceParamConsumer<MotionDetectionActivity>(this) {
            @Override
            public void accept(MotionDetectionActivity pr, DevParamResult result) {
                if (pr == null)
                    return;
                dismissLoading();
                if (result.code != 0) {
                    showToast(ErrorCodeUtil.handlePlatResultCode(result.code));
                    finish();
                    return;
                }
                if (result.devParams != null && result.devParams.size() > 0) {
                    MotionDetectionParam mParam = (MotionDetectionParam) result.devParams.get(0);
                    pr.info = mParam.detecInfo;
                    mSwitch.setChecked(info.detectSwitch == OnOff.On);
                    int detectLevel = info.level;
                    mRg.check(detectLevel == MotionDetectLevel.HEIGH ? R.id.rb_high : (detectLevel == MotionDetectLevel.MIDDEL ? R.id.rb_default : R.id.rb_low));
                    if (info.selectedStatus != null) {
                        areaList = info.selectedStatus;
                        mAreaAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }


    private void sendMotionData(int status, int level, boolean[] reqStatus) {
        MotionDetectionParam switchParam = new MotionDetectionParam(status, level, MotionDetectMode.AUTO, MotionAutoType.H4_L4, reqStatus, null);
        Observable<DevParamResult> observable = Observable.create(new AppSetDeviceParamObservableOnSubscribe(mDevId, switchParam));
        observable = observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread());
        Disposable disposable = observable.subscribe(new AppDeviceParamConsumer<MotionDetectionActivity>(this) {
            @Override
            public void accept(MotionDetectionActivity pr, DevParamResult result) {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDevice.getConnection().removeOnEventCallbackListener(this);
    }

    @Override
    public void onDevEvent(String s, DevResult devResult) {


    }

    boolean[] areaList = new boolean[16];

    class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.Vh> {

        @NonNull
        @Override
        public AreaAdapter.Vh onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_area, viewGroup, false);
            return new AreaAdapter.Vh(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AreaAdapter.Vh vh, @SuppressLint("RecyclerView") final int i) {
            vh.rl.setBackgroundColor(areaList[i] ? Color.parseColor("#bbbbbb") : Color.parseColor("#dddddd"));
            vh.rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    areaList[i] = !areaList[i];
                    mAreaAdapter.notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            return areaList.length;
        }

        class Vh extends RecyclerView.ViewHolder {
            View rl;

            public Vh(@NonNull View itemView) {
                super(itemView);
                rl = itemView.findViewById(R.id.rl);
            }
        }
    }
}
