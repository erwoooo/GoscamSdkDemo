package com.goscamsdkdemo.setting;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gos.platform.api.GosSession;
import com.gos.platform.api.contact.PlatCode;
import com.gos.platform.api.contact.ResultCode;
import com.gos.platform.api.inter.OnPlatformEventCallback;
import com.gos.platform.api.result.CheckNewerVerResult;
import com.gos.platform.api.result.PlatResult;
import com.gos.platform.device.contact.ConnectStatus;
import com.gos.platform.device.domain.DevInfo;
import com.gos.platform.device.inter.OnDevEventCallback;
import com.gos.platform.device.result.ConnectResult;
import com.gos.platform.device.result.DevResult;
import com.gos.platform.device.result.GetDevInfoResult;
import com.gos.platform.device.result.GetUpgradeStatusResult;
import com.goscamsdkdemo.BaseActivity;
import com.goscamsdkdemo.R;
import com.goscamsdkdemo.entitiy.Device;
import com.goscamsdkdemo.entitiy.DeviceManager;

public class DevSoftUpdateActivity extends BaseActivity implements OnDevEventCallback, OnPlatformEventCallback, View.OnClickListener {
    TextView mTvTitle;
    TextView mTvSoftInfo;
    TextView mTvSoftNew;
    Button mBtnUpdate;

    String mDevId;
    Device mDevice;
    GosSession mGosSession;
    DevInfo mDevInfo;
    CheckNewerVerResult mVerResult;
    public static final int CHECK_IPC = 1;
    public static final int CHECK_DOOR_STATE = 2;//中继
    public static final int CHECK_BATTERY_CAMERA = 3;//门铃/电池IP,低功耗设备

    public static void startActivity(Activity activity, String deviceId){
        Intent intent = new Intent(activity, DevSoftUpdateActivity.class);
        intent.putExtra("DEV_ID", deviceId);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev_soft_update);
        mTvTitle = findViewById(R.id.text_title);
        mTvTitle.setText(R.string.dev_soft_update);
        mTvSoftInfo = findViewById(R.id.tv_soft_info);
        mTvSoftNew = findViewById(R.id.tv_soft_new);
        mBtnUpdate = findViewById(R.id.btn_update);
        mBtnUpdate.setVisibility(View.INVISIBLE);
        mBtnUpdate.setOnClickListener(this);

        mGosSession = GosSession.getSession();
        mGosSession.addOnPlatformEventCallback(this);
        mDevId = getIntent().getStringExtra("DEV_ID");
        mDevice = DeviceManager.getInstance().findDeviceById(mDevId);
        mDevice.getConnection().addOnEventCallbackListener(this);

        showLoading();
        mDevice.getConnection().connect(0);

        mGosSession.checkNewerVer(mDevice.deviceHdType, CHECK_BATTERY_CAMERA);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mGosSession.removeOnPlatformEventCallback(this);
        mDevice.getConnection().removeOnEventCallbackListener(this);
    }

    @Override
    public void onDevEvent(String s, DevResult devResult) {
        int code = devResult.getResponseCode();
        switch (devResult.getDevCmd()){
            case connect:
                ConnectResult connectResult = (ConnectResult) devResult;
                if(connectResult.getConnectStatus() == ConnectStatus.CONNECT_SUCCESS){
                    mDevice.getConnection().getDevInfo(0);
                    showToast("connect success");
                }
                break;

            case getDevInfo:
                dismissLoading();
                if(ResultCode.SUCCESS == devResult.getResponseCode()){
                    GetDevInfoResult infoResult = (GetDevInfoResult) devResult;
                    mDevInfo = infoResult.getDevInfo();
                    mTvSoftInfo.setText("Current \ndevType:"+mDevInfo.devType+"\nsoftwareVer:"+mDevInfo.softwareVer);

                    mGosSession.checkNewerVer(mDevInfo.devType,0);
                }
                break;
            case getUpgradeInfo:
                dismissLoading();
                if (ResultCode.SUCCESS == code) {
                    mDevice.getConnection().getUpgradeStatus(0);
                } else {
                    showToast("getUpgradeInfo failed, code="+devResult.getResponseCode());
                }
                break;
            case getUpgradeStatus:
                if (ResultCode.SUCCESS == code) {
                    GetUpgradeStatusResult getUpgradeStatusResult = (GetUpgradeStatusResult) devResult;
                    showSetSoftUpdateSucc(getUpgradeStatusResult.upgradeProgress,getUpgradeStatusResult.upgradeStatus);
                } else {
                    showToast("getUpgradeStatus failed, code="+devResult.getResponseCode());
                }
                break;
        }
    }

    @Override
    public void OnPlatformEvent(PlatResult platResult) {
        dismissLoading();
        if(PlatResult.PlatCmd.checkNewerVer == platResult.getPlatCmd()){
            if(PlatCode.SUCCESS == platResult.getResponseCode()){
                mVerResult = (CheckNewerVerResult) platResult;
                int index2 = mVerResult.version.lastIndexOf(".");
                mTvSoftNew.setText("New\ndeviceType:"+mVerResult.deviceType
                        +"\nversion:"+mVerResult.version
                        +"\ndesc:"+mVerResult.desc
                        +"\nver:"+mVerResult.version.substring(index2)
                        +"\nfileSize:"+mVerResult.fileSize);
                if (mDevInfo != null && !TextUtils.isEmpty(mVerResult.version) && mDevInfo.gatewayVer.compareToIgnoreCase(mVerResult.version)<0){
                    mBtnUpdate.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        if(R.id.btn_update == view.getId()){
            final Dialog dialog = new Dialog(this, R.style.Dialog_FS);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            View v = View.inflate(this, R.layout.dialog_n_p_nor, null);
            dialog.setContentView(v);
            TextView titleView = v.findViewById(R.id.tv_title);
            titleView.setVisibility(View.GONE);
            TextView msgView = v.findViewById(R.id.tv_msg);
            msgView.setText(getResources().getString(R.string.update_to)+mVerResult.version);
            TextView cancelView = v.findViewById(R.id.cancel);
            cancelView.setText(R.string.next_time);
            TextView okView = v.findViewById(R.id.ok);
            okView.setText(R.string.update_now);

            cancelView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            okView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    showLoading();
                    isFileLoadDialogShowEnable = true;
                    mDevice.getConnection().getUpgradeInfo(0, 0, mVerResult.url,mVerResult.version, mVerResult.MD5, mVerResult.fileSize);
                }
            });
            dialog.show();
        }
    }

    Dialog mFileSuccDialog;
    FileDownLoadDialog mFileLoadDialog;
    private boolean isFileLoadDialogShowEnable = false;//dialog 是否可显示
    private final int REQEUST_UPDATE = 100;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == REQEUST_UPDATE && !isFinishing()) {
                mDevice.getConnection().getUpgradeStatus(0);
            }
        }
    };
    public void showSetSoftUpdateSucc(int code, int status) {
        if (mFileLoadDialog == null) {
            mFileLoadDialog = new FileDownLoadDialog(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar);
            mFileLoadDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    handler.removeMessages(REQEUST_UPDATE);
                    isFileLoadDialogShowEnable = false;
                }
            });
        }
        mFileLoadDialog.update(code, 100);
        if (isFileLoadDialogShowEnable) {
            mFileLoadDialog.show();
            handler.removeMessages(REQEUST_UPDATE);
            handler.sendEmptyMessageDelayed(REQEUST_UPDATE, 3000);
        }
        if (code == 100) {
            if(mFileLoadDialog!=null)
                mFileLoadDialog.dismiss();

            //设备升级成功，需要删除能力集
            if(mFileSuccDialog == null){
                mFileSuccDialog = new Dialog(this, R.style.Dialog_FS);
                mFileSuccDialog.setCancelable(false);
                mFileSuccDialog.setCanceledOnTouchOutside(false);
                View v = View.inflate(this, R.layout.dialog_n_nor,null);
                TextView tvTitle = v.findViewById(R.id.tv_title);
                tvTitle.setVisibility(View.GONE);
                TextView tvMsg = v.findViewById(R.id.tv_msg);
                tvMsg.setText(R.string.update_success);
                TextView tvOk = v.findViewById(R.id.ok);
                tvOk.setText(R.string.yes);
                mFileSuccDialog.setContentView(v);
                tvOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mFileSuccDialog.dismiss();
                        finish();
                    }
                });
            }
            mFileSuccDialog.show();
        } else {
            if (status != E_UPGRADE_ING && status != E_UPGRADE_OK && status != E_UPGRADE_SEND_COMPELETE) {
                if (mFileLoadDialog != null && mFileLoadDialog.isShowing()) {
                    mFileLoadDialog.dismiss();
                }
                String format = String.format(getString(R.string.upgrade_failed), status + "");
                showToast(format);
            }
        }
    }

    public static int E_UPGRADE_ING = 0;							//(0)正在升级
    public static int E_UPGRADE_FAILED=1;						//(1)升级失败
    public static int E_UPGRADE_OK=2;								//(2)升级成功
    public static int E_UPGRADE_SEND_COMPELETE=3;					//(3)发送文件完成
    public static int E_UPGRADE_NOEXIST=4;							//(4)升级文件不存在或校验失败
    public static int E_UPGRADE_TIMEOUT	= 780;					//(780)DSP升级超时
    public static int E_UPGRADE_SEND_ERR=781;						//(781)DSP升级发送失败
    public static int E_UPGRADE_WRITE_FLASH_ERR=782;					//(782)DSP升刷flash
    public static int E_UPGRADE_NOT_READY_ERR=783;					//(783)DSP设备没准备好
    public static int E_UPGRADE_REPORT_ERR=784;						//(784)DSP出错
}
