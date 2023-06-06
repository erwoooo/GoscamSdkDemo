package com.goscamsdkdemo.add.bell;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.gos.platform.api.GosSession;
import com.gos.platform.api.inter.OnPlatformEventCallback;
import com.gos.platform.api.result.PlatResult;
import com.gos.platform.api.result.QueryUserBindResult;
import com.goscamsdkdemo.BaseActivity;
import com.goscamsdkdemo.GApplication;
import com.goscamsdkdemo.MainActivity;
import com.goscamsdkdemo.R;
import com.goscamsdkdemo.add.WifiSelectActivity;

//用于检查TOKEN
public class AddBellTokenActivity extends BaseActivity implements OnPlatformEventCallback, View.OnClickListener {

    public static void startActivity(Activity activity, String token){
        Intent intent = new Intent(activity, AddBellTokenActivity.class);
        intent.putExtra("TOKEN", token);
        activity.startActivity(intent);
    }

    TextView mTextTitle;
    TextView mTvAddStatus;

    Handler mHandler;
    String mToken;
    String mDevUid;
    private final int WHAT_TIMER = 10;
    private final int WHAT_QUERY = 11;
    private final int MAX_TIME = 120; //秒

    boolean isSuccess;
    boolean isTimeOut;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bell_token);
        mToken = getIntent().getStringExtra("TOKEN");

        mTextTitle = findViewById(R.id.text_title);
        mTvAddStatus = findViewById(R.id.tv_add_status);
        findViewById(R.id.rl).setOnClickListener(this);

        mTextTitle.setText(R.string.connect_wifi);

        GosSession.getSession().addOnPlatformEventCallback(this);
        mHandler = new TokenHandler();

        Message message = mHandler.obtainMessage();
        message.what = WHAT_TIMER;
        message.arg1 = 0;
        mHandler.sendMessage(message);
        mHandler.sendEmptyMessage(WHAT_QUERY);
    }

    class TokenHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(isSuccess || isTimeOut){return;}
            switch (msg.what){
                case WHAT_QUERY:
                    GosSession.getSession().queryUserBindResult(GApplication.app.user.userName, mToken);
                    break;
                case WHAT_TIMER:
                    int time = msg.arg1;
                    mTvAddStatus.setText(getResources().getString(R.string.connect_status_progress)+
                            ((int)(time*100.0f/MAX_TIME))+"%"+"...");
                    if(time<MAX_TIME){
                        Message message = mHandler.obtainMessage();
                        message.what = WHAT_TIMER;
                        message.arg1 = time+1;
                        mHandler.sendMessageDelayed(message,1000);
                    }else{
                        isTimeOut = true;
                        mTvAddStatus.setText(R.string.connect_time_out);
                        mHandler.removeCallbacksAndMessages(null);
                    }
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GosSession.getSession().removeOnPlatformEventCallback(this);
        mHandler.removeCallbacksAndMessages(null);
    }

    Dialog mAddDevDialog;
    public void onClick(View v) {
        if (mAddDevDialog == null) {
            mAddDevDialog = new Dialog(this, R.style.Dialog_FS);
            mAddDevDialog.setCancelable(false);
            mAddDevDialog.setCanceledOnTouchOutside(false);
            View view = View.inflate(this, R.layout.dialog_n_p_nor, null);
            mAddDevDialog.setContentView(view);
            TextView titleView = view.findViewById(R.id.tv_title);
            titleView.setText(R.string.add_fail_);
            titleView.setVisibility(View.VISIBLE);
            TextView msgView = view.findViewById(R.id.tv_msg);
            msgView.setText(R.string.red_light_msg);
            TextView cancelView = view.findViewById(R.id.cancel);
            cancelView.setText(R.string.retry_after);
            TextView okView = view.findViewById(R.id.ok);
            okView.setText(R.string.bind_retry);
            cancelView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAddDevDialog.dismiss();
                }
            });
            okView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAddDevDialog.dismiss();
                    startActivity(new Intent(AddBellTokenActivity.this, WifiSelectActivity.class));
                }
            });
        }
        mAddDevDialog.show();
    }

    @Override
    public void OnPlatformEvent(PlatResult platResult) {
        PlatResult.PlatCmd cmd = platResult.getPlatCmd();
        int code = platResult.getResponseCode();
        if(PlatResult.PlatCmd.queryUserBindResult == cmd){
            dismissLoading();
            switch (code){
                case 0:
                    //device=A99M82100000003&user=542957111@qq.com
                    QueryUserBindResult bResult = (QueryUserBindResult) platResult;
                    String deviceId = null;
                    try {
                        String des = bResult.resultDescribe;
                        String[] split = des.split("&");
                        for(int i=0; i<2; i++){
                            String[] split1 = split[i].split("=");
                            if(i==0){
                                deviceId = split1[1];
                            }else if(i==1){
                                //user = split1[1];
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    mDevUid = deviceId;
                    isSuccess = true;
                    mHandler.removeCallbacksAndMessages(null);
                    mTvAddStatus.setText(R.string.connect_success);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            GosSession.getSession().getDeviceList(GApplication.app.user.userName);
                        }
                    },2000);
                    break;
                case -10000://-10130 TOKEN 失效
                    mHandler.sendEmptyMessageDelayed(WHAT_QUERY, 2000);
                    break;
                case 10128:
                case -10128:
                    //device=A99M82100000001&user=18664983039
                    QueryUserBindResult bindResult = (QueryUserBindResult) platResult;
                    String user = null;
                    //String deviceId = null;
                    try {
                        String des = bindResult.resultDescribe;
                        String[] split = des.split("&");
                        for(int i=0; i<2; i++){
                            String[] split1 = split[i].split("=");
                            if(i==0){
                                //deviceId = split1[1];
                            }else if(i==1){
                                user = split1[1];
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    final Dialog dialog = new Dialog(this, R.style.Dialog_FS);
                    dialog.setCancelable(false);
                    dialog.setCanceledOnTouchOutside(false);
                    View v = View.inflate(this, R.layout.dialog_n_nor, null);
                    TextView tvTitle = v.findViewById(R.id.tv_title);
                    tvTitle.setVisibility(View.GONE);
                    TextView tvMsg = v.findViewById(R.id.tv_msg);
                    String s = TextUtils.isEmpty(user)?getResources().getString(R.string.dev_already_bind):
                            String.format(getResources().getString(R.string.dev_bind_by_other), user);
                    tvMsg.setText(s);
                    TextView tvOk = v.findViewById(R.id.ok);
                    tvOk.setText(R.string.hao_de);
                    dialog.setContentView(v);
                    tvOk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            startActivity(new Intent(AddBellTokenActivity.this, MainActivity.class));
                        }
                    });
                    dialog.show();
                    break;
                case -10130:
                    showToast(this.getString(R.string.token_invalid));
                    startActivity(new Intent(AddBellTokenActivity.this, WifiSelectActivity.class));
                    break;
                default:
                    showToast(getResources().getString(R.string.request_failed)+":"+code);
                    this.finish();
                    break;
            }
        }else if(cmd == PlatResult.PlatCmd.getDeviceList){
            if(isSuccess){
                AddBellChildActivity.startActivity(this, mDevUid);
                finish();
            }
        }
    }
}
