package com.goscamsdkdemo;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gos.platform.api.ConfigManager;
import com.gos.platform.api.GosSession;
import com.gos.platform.api.contact.PlatCode;
import com.gos.platform.api.inter.OnPlatformEventCallback;
import com.gos.platform.api.jni.PlatformSession;
import com.gos.platform.api.result.PlatResult;
import com.goscamsdkdemo.util.PermissionUtil;
import com.goscamsdkdemo.util.RegexUtils;
import com.goscamsdkdemo.util.Util;

import static com.gos.platform.device.receiver.PushMessageBroadReceiver.EXTRA_PUSHMSG_ACTION;
import static com.gos.platform.device.receiver.PushMessageBroadReceiver.EXTRA_PUSH_MSG;

public class LoginActivity extends BaseActivity implements OnPlatformEventCallback, View.OnClickListener {


    boolean isLogin;
    boolean isGetSuc;
    GosSession mGosSession;

    EditText mEtUserName;
    EditText mEtPsw;
    Button mBtnLogin;
    TextView mTvSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEtUserName = findViewById(R.id.et_userName);
        mEtPsw = findViewById(R.id.et_password);
        mBtnLogin = findViewById(R.id.btn_login);
        mTvSignup = findViewById(R.id.tv_signUp);
        mBtnLogin.setOnClickListener(this);
        mTvSignup.setOnClickListener(this);
        findViewById(R.id.tv_forget_psw).setOnClickListener(this);

//        mEtUserName.setText("542957111@qq.com");
//        mEtPsw.setText("qwer2222");
        mEtUserName.setText("tianya202111@163.com");
        mEtPsw.setText("gg000000");

        PermissionUtil.verifyAppPermissions(this);

        mGosSession = GosSession.getSession();
        mGosSession.addOnPlatformEventCallback(this);
        mGosSession.appGetBSAddress(Util.getPhoneUUID(this),"");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mGosSession.removeOnPlatformEventCallback(this);
    }

    @Override
    public void OnPlatformEvent(PlatResult platResult) {
        int code = platResult.getResponseCode();
        if(PlatResult.PlatCmd.appGetBSAddress == platResult.getPlatCmd() && PlatCode.SUCCESS == code){
            isGetSuc = true;
            if(isLogin){ doLogin();}
        }else if(PlatResult.PlatCmd.login == platResult.getPlatCmd()){
            dismissLoading();
            if(PlatCode.SUCCESS == code){
                startActivity(new Intent(this,MainActivity.class));
                finish();
            }else{
                Toast.makeText(this, "login fail, code=" + code, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                if(isGetSuc){
                    doLogin();
                }else{
                    isLogin = true;
                    showLoading();
                    mGosSession.appGetBSAddress(Util.getPhoneUUID(this),"");
                }
                break;
            case R.id.tv_signUp:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.tv_forget_psw:
                startActivity(new Intent(this, ForgetPswActivity.class));
                break;
        }
    }

    private void doLogin(){
        String userName = mEtUserName.getText().toString();
        String psw = mEtPsw.getText().toString();
        if (RegexUtils.isMatch(RegexUtils.REGEX_MOBILE_EXACT, userName)) {
            if (checkPasswordPassed(psw)) {
                showLoading();
                login(userName,psw,"",true);
            }
        } else if (RegexUtils.isMatch(RegexUtils.REGEX_EMAIL, userName)) {
            if (userName.length() < 4 || userName.length() > 81) {
                showToast(getString(R.string.username_format_error_tip));
            } else if (checkPasswordPassed(psw)) {
                showLoading();
                login(userName,psw,"",true);
            }
        } else {
            showToast(getString(R.string.username_format_error_tip));
        }

    }

    /*
    * userName
    * pwd
    * country
    * isEncPsw
    * */
    private void login(String userName,String psw,String mobileCN, boolean isEncPsw){
       String mPsw = isEncPsw ? encodeData(psw) : psw;   //is need encryption?
        mGosSession.login(userName, psw, mobileCN, isEncPsw);
    }

    //Encryption
    public String encodeData(String data){
        return mGosSession.encodeData(data);
    }



    private boolean checkPasswordPassed(String pwd) {
        if (!(!RegexUtils.isMatch(RegexUtils.REGEX_SINGLE_TYPE_CHARACTER, pwd) && RegexUtils.isMatch(RegexUtils.REGEX_8_16_LETTER_NUMBER, pwd))) {
            showToast(getString(R.string.password_format_error_tip));
            return false;
        }
        return true;
    }
}