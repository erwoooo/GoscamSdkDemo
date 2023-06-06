package com.goscamsdkdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gos.platform.api.GosSession;
import com.gos.platform.api.contact.PlatCode;
import com.gos.platform.api.inter.OnPlatformEventCallback;
import com.gos.platform.api.result.PlatResult;
import com.goscamsdkdemo.util.RegexUtils;

public class ModifyPswActivity extends BaseActivity implements OnPlatformEventCallback, View.OnClickListener {
    TextView mTvTitle;
    EditText mEtOldPsw;
    EditText mEtFPsw;
    EditText mEtSPsw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_psw);
        mTvTitle = findViewById(R.id.text_title);
        mTvTitle.setText(R.string.modify_user_psw);
        mEtOldPsw = findViewById(R.id.et_old_psw);
        mEtFPsw = findViewById(R.id.et_password);
        mEtSPsw = findViewById(R.id.et_password_again);
        findViewById(R.id.btn_save).setOnClickListener(this);

        GosSession.getSession().addOnPlatformEventCallback(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GosSession.getSession().removeOnPlatformEventCallback(this);
    }

    @Override
    public void OnPlatformEvent(PlatResult platResult) {
        if(PlatResult.PlatCmd.modifyUserPassword == platResult.getPlatCmd()){
            dismissLoading();
            if(PlatCode.SUCCESS == platResult.getResponseCode()){
                showToast(getString(R.string.modify_success));
            }else{
                showToast("modifyUserPassword failed,code="+platResult.getResponseCode());
            }
        }
    }

    @Override
    public void onClick(View v) {
        if(R.id.btn_save == v.getId()){
            String oldPsw = mEtOldPsw.getText().toString();
            String fPsw = mEtFPsw.getText().toString();
            String sPsw = mEtSPsw.getText().toString();
            if(TextUtils.isEmpty(oldPsw) || TextUtils.isEmpty(fPsw) || TextUtils.isEmpty(sPsw)){
                showToast(getString(R.string.value_empty));
                return;
            }
            if(checkPasswordPassed(fPsw, sPsw)){
                showLoading();
                GosSession.getSession().modifyUserPassword(GApplication.app.user.userName, oldPsw, fPsw, true);
            }
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
}
