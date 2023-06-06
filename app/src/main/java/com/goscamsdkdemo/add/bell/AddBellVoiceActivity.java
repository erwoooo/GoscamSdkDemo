package com.goscamsdkdemo.add.bell;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
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

import voice.encoder.DataEncoder;
import voice.encoder.VoicePlayer;
import voice.encoder.VoicePlayerListener;

public class AddBellVoiceActivity extends BaseActivity implements OnPlatformEventCallback, View.OnClickListener {
    TextView mTextTitle;
    RelativeLayout mRl;
    Button mBtnSendVoice;
    Button mBtnStepNotice;

    VoicePlayer player;
    boolean isScanStart;
    String mToken;

    public static void startActivity(Activity activity, String wifi, String psw){
        Intent intent = new Intent(activity, AddBellVoiceActivity.class);
        intent.putExtra("wifi", wifi);
        intent.putExtra("psw", psw);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_voice_notice);
        mTextTitle = findViewById(R.id.text_title);
        mRl = findViewById(R.id.rl);
        mBtnSendVoice = findViewById(R.id.btn_send_voice);
        mBtnStepNotice = findViewById(R.id.btn_step_notice);
        mRl.setOnClickListener(this);
        mBtnSendVoice.setOnClickListener(this);
        mBtnStepNotice.setOnClickListener(this);


        mTextTitle.setText(R.string.voice_pei);
        mBtnSendVoice.setEnabled(false);
        mBtnStepNotice.setEnabled(false);

        AudioManager mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int max = mAudioManager.getStreamMaxVolume( AudioManager.STREAM_MUSIC );
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, (int)(max*0.8), 0);
        int freqs[] = new int[19];
        int baseFreq = 4000;
        for(int i = 0; i < freqs.length; i ++){
            freqs[i] = baseFreq + i * 150;
        }
        //创建声波通讯播放器
        player = new VoicePlayer();
        player.setFreqs(freqs);

        showLoading();
        GosSession.getSession().addOnPlatformEventCallback(this);
        GosSession.getSession().getBindToken(GApplication.app.user.userName,null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isScanStart){
            if(!player.isStopped())
                player.stop();
            isScanStart = false;
        }
        GosSession.getSession().removeOnPlatformEventCallback(this);
    }

    Dialog dialog;
    void showHelpDialog() {
        if (dialog == null) {
            if(dialog == null){
                dialog = new Dialog(this,R.style.DialogNoBackground);
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
                View v = View.inflate(this,R.layout.dialog_n_nor,null);
                TextView tvTitle = v.findViewById(R.id.tv_title);
                tvTitle.setText(R.string.no_hear_);
                TextView tvMsg = v.findViewById(R.id.tv_msg);
                tvMsg.setText(R.string.voice_notice_help);
                TextView tvOk = v.findViewById(R.id.ok);
                tvOk.setText(R.string.i_know);
                dialog.setContentView(v);
                tvOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
            dialog.show();
        }
        dialog.show();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl:
                showHelpDialog();
                break;
            case R.id.btn_send_voice:
                if(!isScanStart){
                    mBtnSendVoice.setEnabled(false);
                    String appName = "Goscom";
                    String ssid = getIntent().getStringExtra("wifi");
                    String psw = getIntent().getStringExtra("psw");
                    String sS = ConfigManager.getInstance().getCurServer();
                    String server = "";
                    String token = mToken;
                    if(TextUtils.equals(sS, ConfigManager.S_CN_URL)){
                        server = "CN";
                    }else if(TextUtils.equals(sS, ConfigManager.S_EN_URL)){
                        server = "US";
                    }
                    String qrText = appName + "\n" +
                            ssid + "\n" +
                            psw + "\n" +
                            token + "\n" +
                            server + "\n" +
                            0;
                    UtfEncoder utfEncoder=new UtfEncoder();
                    DataEncoder.setStringEncoder(utfEncoder);
                    String s = DataEncoder.encodeString(qrText);
                    player.setListener(new VoicePlayerListener() {
                        @Override
                        public void onPlayStart(VoicePlayer voicePlayer) {

                        }

                        @Override
                        public void onPlayEnd(VoicePlayer voicePlayer) {
                            isScanStart = false;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if(!isFinishing()){
                                        mBtnSendVoice.setEnabled(true);
                                        mBtnStepNotice.setEnabled(true);
                                    }
                                }
                            });
                        }
                    });
                    player.play(s,1,2000);
                }
                break;
            case R.id.btn_step_notice:
                AddBellTokenActivity.startActivity(this, mToken);
                break;
        }
    }

    @Override
    public void OnPlatformEvent(PlatResult platResult) {
        if(PlatResult.PlatCmd.getBindToken == platResult.getPlatCmd()){
            dismissLoading();
            if(platResult.getResponseCode() == PlatCode.SUCCESS){
                String token = ((GetBindTokenResult)platResult).bindToken;
                mToken = token;
                mBtnSendVoice.setEnabled(true);
            }else{
                finish();
                showToast(getResources().getString(R.string.request_failed));
            }
        }
    }
}
