package com.goscamsdkdemo.add;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gos.platform.api.ConfigManager;
import com.gos.platform.api.GosSession;
import com.gos.platform.api.contact.PlatCode;
import com.gos.platform.api.inter.OnPlatformEventCallback;
import com.gos.platform.api.result.GetBindTokenResult;
import com.gos.platform.api.result.PlatResult;
import com.goscamsdkdemo.BaseActivity;
import com.goscamsdkdemo.GApplication;
import com.goscamsdkdemo.R;
import com.goscamsdkdemo.util.QRBitmapCreater;

public class QrCodeActivity extends BaseActivity implements View.OnClickListener, OnPlatformEventCallback, QRBitmapCreater.QRBitmapCallback {
    TextView mTvTitle;
    Button mBtnNext;
    ImageView mIvQrcode;

    String mSsid;
    String mPsw;
    String mBindToken;

    GosSession mGosSession;

    public static void startActivity(Activity activity, String ssid, String psw){
        Intent intent = new Intent(activity,QrCodeActivity.class);
        intent.putExtra("SSID",ssid);
        intent.putExtra("PSW",psw);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        mTvTitle = findViewById(R.id.text_title);
        mTvTitle.setText(R.string.add_device);
        mBtnNext = findViewById(R.id.btn_next);
        mBtnNext.setEnabled(false);

        mIvQrcode = findViewById(R.id.qr_image);

        mBtnNext.setOnClickListener(this);

        mSsid = getIntent().getStringExtra("SSID");
        mPsw = getIntent().getStringExtra("PSW");

        mGosSession = GosSession.getSession();
        mGosSession.addOnPlatformEventCallback(this);
        showLoading();
        mGosSession.getBindToken(GApplication.app.user.userName,null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mGosSession.removeOnPlatformEventCallback(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_next){
            CheckBindStatusActivity.startActivity(this, mBindToken);
            finish();
        }
    }

    @Override
    public void OnPlatformEvent(PlatResult platResult) {
        if(PlatResult.PlatCmd.getBindToken == platResult.getPlatCmd()){
            dismissLoading();
            int code = platResult.getResponseCode();
            if(PlatCode.SUCCESS == code){
                GetBindTokenResult tokenResult = (GetBindTokenResult) platResult;
                createQr(tokenResult.bindToken);
            }else{
                showToast("getBindToken failed, code = " + code);
                finish();
            }
        }
    }

    private void createQr(String bindToken){
        mBindToken = bindToken;
        String appName = getResources().getString(R.string.app_name);
        String sS = ConfigManager.getInstance().getCurServer();
        String server = "";
        if(TextUtils.equals(sS,ConfigManager.S_CN_URL)){
            server = "CN";
        }else if(TextUtils.equals(sS,ConfigManager.S_EN_URL)){
            server = "US";
        }
        String qrText = appName + "\n" +
                mSsid + "\n" +
                mPsw + "\n" +
                bindToken + "\n" +
                server + "\n" +
                0;
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Point p = new Point();
        wm.getDefaultDisplay().getSize(p);
        int mWidth = Math.min(p.x, p.y);
        new QRBitmapCreater(qrText, mWidth, mWidth, this).execute();
    }

    @Override
    public void onQRBitmapCreated(Bitmap bmp, boolean created) {
        if(created && bmp != null){
            mIvQrcode.setImageBitmap(bmp);
            mBtnNext.setEnabled(true);
        }
    }
}
