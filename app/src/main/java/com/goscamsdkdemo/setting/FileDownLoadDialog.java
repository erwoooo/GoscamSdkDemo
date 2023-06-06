package com.goscamsdkdemo.setting;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.goscamsdkdemo.R;

public class FileDownLoadDialog extends Dialog {
    private ProgressBar progressBar;
    private TextView textView;
    private Button btnCancel;

    public FileDownLoadDialog(Context context, int theme) {
        super(context, theme);
        init(context);
    }

    private void init(Context context) {
        setContentView(R.layout.item_down_load_dialog);
        progressBar = findViewById(R.id.pb_load_progress);
        textView = findViewById(R.id.tv_update_progress);
        btnCancel = findViewById(R.id.btn_cancel_update);
        setCanceledOnTouchOutside(false);
//        setCancelable(false);
    }

    public void update(int progress, int max) {
        progressBar.setProgress(progress);
        progressBar.setMax(max);
        textView.setText(progress + "/" + max);
    }

    public int getProgress() {
        return progressBar.getProgress();
    }

    public void setCancelUpdateListener(View.OnClickListener onClickListener) {
        btnCancel.setOnClickListener(onClickListener);
    }

    @Override
    public void dismiss() {
        progressBar.setProgress(0);
        super.dismiss();
    }
}
