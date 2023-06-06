package com.goscamsdkdemo;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gos.platform.api.GosSession;
import com.gos.platform.api.contact.FindType;
import com.gos.platform.api.contact.PlatCode;
import com.gos.platform.api.contact.VerifyWay;
import com.gos.platform.api.inter.OnPlatformEventCallback;
import com.gos.platform.api.result.PlatResult;
import com.goscamsdkdemo.util.CountDownTimerUtil;
import com.goscamsdkdemo.util.RegexUtils;

public class ForgetPswActivity extends BaseActivity implements View.OnClickListener, OnPlatformEventCallback, CountDownTimerUtil.CountDownTimerListener {
    TextView mTvTitle;
    EditText mEtUserName;
    EditText mEtPsw;
    EditText mEtPswAgain;
    EditText mEtCode;
    Button mBtnGetCode;
    Button mBtnModify;

    GosSession mGosSession;
    String mUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_psw);
        mTvTitle = findViewById(R.id.text_title);
        mTvTitle.setText(R.string.forget_user_password);
        mEtUserName = findViewById(R.id.et_userName);
        mEtPsw = findViewById(R.id.et_password);
        mEtPswAgain = findViewById(R.id.et_password_again);
        mEtCode = findViewById(R.id.et_verification_code);
        mBtnGetCode = findViewById(R.id.btn_code);
        mBtnModify = findViewById(R.id.btn_modify);
        mBtnGetCode.setOnClickListener(this);
        mBtnModify.setOnClickListener(this);

        mGosSession = GosSession.getSession();
        mGosSession.addOnPlatformEventCallback(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mGosSession.removeOnPlatformEventCallback(this);
        CountDownTimerUtil.cancelCountDownTimer();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_code:
                mUserName = mEtUserName.getText().toString();
                if (TextUtils.isEmpty(mUserName)) {
                    showToast(getString(R.string.sign_up_input_phone_number_or_email));
                } else if (RegexUtils.isMatch(RegexUtils.REGEX_MOBILE_EXACT, mUserName)) {
                    showLoading();
                    mGosSession.getVerifyCode(FindType.PHONE, mUserName, VerifyWay.FORGET_PSW, getResources().getInteger(R.integer.user_type),"+86", null);
                } else if (RegexUtils.isMatch(RegexUtils.REGEX_EMAIL, mUserName)) {
                    if (mUserName.length() < 4 || mUserName.length() > 63) {
                        showToast(getString(R.string.account_format_error_tip));
                    } else {
                        showLoading();
                        mGosSession.getVerifyCode(FindType.EMAIL_ADDRESS, mUserName, VerifyWay.FORGET_PSW, getResources().getInteger(R.integer.user_type),null, null);
                    }
                } else {
                    showToast(getString(R.string.account_format_error_tip));
                }
                break;
            case R.id.btn_modify:
                String firstPwd = mEtPsw.getText().toString();
                String secondPwd = mEtPsw.getText().toString();
                String verifyCode = mEtCode.getText().toString();
                if (RegexUtils.isMatch(RegexUtils.REGEX_MOBILE_EXACT, mUserName)) {
                    if (checkPasswordPassed(firstPwd, secondPwd)) {
                        showLoading();
                        mGosSession.modifyPasswordByVerify(FindType.PHONE, mUserName, firstPwd, verifyCode, true);
                    }
                } else if (RegexUtils.isMatch(RegexUtils.REGEX_EMAIL, mUserName)) {
                    if (mUserName.length() < 4 || mUserName.length() > 63) {
                        showToast(getString(R.string.account_format_error_tip));
                    } else if (checkPasswordPassed(firstPwd, secondPwd)) {
                        showLoading();
                        mGosSession.modifyPasswordByVerify(FindType.EMAIL_ADDRESS, mUserName, firstPwd, verifyCode, true);
                    }
                } else {
                    showToast(getString(R.string.account_format_error_tip));
                }
                break;
        }
    }

    private boolean checkPasswordPassed(String firstPwd, String secondPwd) {
        if (!firstPwd.equals(secondPwd)) {
            showToast(getString(R.string.sign_up_input_password_again_error_tip));
            return false;
        } else if (!(!RegexUtils.isMatch(RegexUtils.REGEX_SINGLE_TYPE_CHARACTER, firstPwd) && RegexUtils.isMatch(RegexUtils.REGEX_8_16_LETTER_NUMBER, firstPwd))) {
            showToast(getString(R.string.password_format_error_tip));
            return false;
        }
        return true;
    }

    @Override
    public void OnPlatformEvent(PlatResult platResult) {
        int code = platResult.getResponseCode();
        if(PlatResult.PlatCmd.getVerifyCode == platResult.getPlatCmd()){
            dismissLoading();
            if(PlatCode.SUCCESS == code){
                String f = String.format(getResources().getString(R.string.send_verify_code_success_tip), mUserName, mUserName);
                showLToast(f);
                mBtnGetCode.setEnabled(false);
                CountDownTimerUtil.startCountDownTimer(60000, 1000, this);
            }else{
                showToast("getVerifyCode failed,code="+code);
            }
        }else if(PlatResult.PlatCmd.modifyPasswordByVerify == platResult.getPlatCmd()){
            dismissLoading();
            if(PlatCode.SUCCESS == code){
                showToast(getResources().getString(R.string.modify_success));
                finish();
            }else{
                showToast("modify failed,code="+code);
            }
        }
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mBtnGetCode.setText(millisUntilFinished / 1000 + "s");
    }

    @Override
    public void onFinish() {
        mBtnGetCode.setEnabled(true);
        mBtnGetCode.setText(R.string.get_verification_code);
    }
}