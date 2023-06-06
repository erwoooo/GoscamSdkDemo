package com.goscamsdkdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.gos.platform.api.result.PlatResult;
import com.gos.platform.device.result.DevResult;
import com.goscamsdkdemo.ws.WsManager;

public class WsActivity extends BaseActivity implements WsManager.ICallback {
    TextView mTvTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ws);
        mTvTitle = findViewById(R.id.text_title);
        mTvTitle.setText(R.string.websocket);
        WsManager.getInstance().addICallback(this);
    }

    public void init(View view){
        WsManager.getInstance().init(GApplication.app);
    }

    public void uninit(View view){
        WsManager.getInstance().unInit();
    }

    public void reconnect(View view){
        WsManager.getInstance().reconnect();
    }

    public void update(View view){
        String sessionIdEx = GApplication.app.user.sessionIdEx;
        String sessionId = GApplication.app.user.sessionId;
        WsManager.getInstance().updateSession(sessionIdEx, sessionId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WsManager.getInstance().removeICallback(this);
    }

    @Override
    public void OnEvent(PlatResult platResult, DevResult devResult) {

    }
}
