package com.goscamsdkdemo.add;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.gos.platform.api.GosSession;
import com.gos.platform.api.inter.OnPlatformEventCallback;
import com.gos.platform.api.result.PlatResult;
import com.gos.platform.api.result.QueryUserBindResult;
import com.goscamsdkdemo.BaseActivity;
import com.goscamsdkdemo.GApplication;
import com.goscamsdkdemo.R;

public class CheckBindStatusActivity extends BaseActivity implements OnPlatformEventCallback {
    TextView mTvTitle;
    TextView mTvAddStatus;

    String mBindToken;
    GosSession mGosSession;
    Handler mHandler;
    Runnable mRunnable;

    public static void startActivity(Activity activity, String bindToken){
        Intent intent = new Intent(activity, CheckBindStatusActivity.class);
        intent.putExtra("BindToken", bindToken);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_bind_status);
        mTvTitle = findViewById(R.id.text_title);
        mTvTitle.setText(R.string.add_device);
        mTvAddStatus = findViewById(R.id.tv);

        mBindToken = getIntent().getStringExtra("BindToken");
        mGosSession = GosSession.getSession();
        mGosSession.addOnPlatformEventCallback(this);
        mHandler = new Handler();
        mHandler.post(mRunnable = new Runnable() {
            @Override
            public void run() {
                if(!isFinishing())
                    mGosSession.queryUserBindResult(GApplication.app.user.userName, mBindToken);
            }
        });

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mHandler.removeCallbacks(mRunnable);
                showToast("time out");
                finish();
            }
        }, 120000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
        mGosSession.removeOnPlatformEventCallback(this);
    }

    @Override
    public void OnPlatformEvent(PlatResult platResult) {
        PlatResult.PlatCmd cmd = platResult.getPlatCmd();
        if(PlatResult.PlatCmd.queryUserBindResult == cmd){
            int code = platResult.getResponseCode();
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
                    showToast(getResources().getString(R.string.connect_success));
                    mGosSession.getDeviceList(GApplication.app.user.userName);
                    finish();
                    break;
                case -10000:
                    mHandler.postDelayed(mRunnable = new Runnable() {
                        @Override
                        public void run() {
                            if(!isFinishing())
                                mGosSession.queryUserBindResult(GApplication.app.user.userName, mBindToken);
                        }
                    },2000);
                    break;
                case -10130://-10130 TOKEN 失效
                    showToast("token invalid,code="+code);
                    finish();
                    break;
                case 10128:
                case -10128:
                    //device=A99M82100000001&user=18664983039
                    QueryUserBindResult bindResult = (QueryUserBindResult) platResult;
                    showToast(bindResult.resultDescribe + ",code="+code);
                    finish();
                    break;
                default:
                    finish();
                    break;
            }
        }
    }
}
