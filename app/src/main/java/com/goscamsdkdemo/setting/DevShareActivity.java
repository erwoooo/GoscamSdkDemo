package com.goscamsdkdemo.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.gos.platform.api.GosSession;
import com.gos.platform.api.contact.PlatCode;
import com.gos.platform.api.inter.OnPlatformEventCallback;
import com.gos.platform.api.result.GetShareUserListResult;
import com.gos.platform.api.result.PlatResult;
import com.gos.platform.api.result.ShareSmartDeviceResult;
import com.goscamsdkdemo.BaseActivity;
import com.goscamsdkdemo.GApplication;
import com.goscamsdkdemo.MainActivity;
import com.goscamsdkdemo.PlayActivity;
import com.goscamsdkdemo.R;
import com.goscamsdkdemo.entitiy.Device;
import com.goscamsdkdemo.entitiy.DeviceManager;

import java.util.List;

public class DevShareActivity extends BaseActivity implements OnPlatformEventCallback {
    TextView mTvTitle;
    EditText mEtUserName;
    Button mBtnShare;
    RecyclerView mRecyclerView;
    ShareAdapter mShareAdapter;

    String mDevId;
    Device mDevice;
    GosSession mGosSession;


    public static void startActivity(Activity activity, String deviceId){
        Intent intent = new Intent(activity, DevShareActivity.class);
        intent.putExtra("DEV_ID", deviceId);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev_share);
        mTvTitle = findViewById(R.id.text_title);
        mTvTitle.setText(R.string.dev_share);
        mEtUserName = findViewById(R.id.et_userName);
        mBtnShare = findViewById(R.id.btn_share);
        mRecyclerView = findViewById(R.id.recycle_view);
        mBtnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shareName = mEtUserName.getText().toString();
                if(!TextUtils.isEmpty(shareName)){
                    showLoading();
                    mGosSession.shareSmartDevice(shareName, mDevice.devId, false, mDevice.devName,
                            mDevice.devType, "", "","",1);
                }
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mShareAdapter = new ShareAdapter();
        mRecyclerView.setAdapter(mShareAdapter);

        mDevId = getIntent().getStringExtra("DEV_ID");
        mDevice = DeviceManager.getInstance().findDeviceById(mDevId);
        mGosSession = GosSession.getSession();
        mGosSession.addOnPlatformEventCallback(this);

        showLoading();
        mGosSession.getShareUserList(mDevId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mGosSession.removeOnPlatformEventCallback(this);
    }

    @Override
    public void OnPlatformEvent(PlatResult platResult) {
        if(PlatResult.PlatCmd.getShareUserList == platResult.getPlatCmd()){
            dismissLoading();
            if(PlatCode.SUCCESS == platResult.getResponseCode()){
                GetShareUserListResult listResult = (GetShareUserListResult) platResult;
                mShareList = listResult.shareUserList;
                mShareAdapter.notifyDataSetChanged();
            }
        }else if(PlatResult.PlatCmd.shareSmartDevice == platResult.getPlatCmd()){
            dismissLoading();
            if(PlatCode.SUCCESS == platResult.getResponseCode()){
                showToast("Share Success");
                mGosSession.getShareUserList(mDevId);
            }else{
                showToast("Share Failed, code="+platResult.getResponseCode());
            }
        }else if(PlatResult.PlatCmd.unbindSharedSmartDevice == platResult.getPlatCmd()){
            dismissLoading();
            if(PlatCode.SUCCESS == platResult.getResponseCode()){
                showToast("unbind Success");
                mGosSession.getShareUserList(mDevId);
            }else{
                showToast("unbind Failed, code="+platResult.getResponseCode());
            }
        }
    }

    List<String > mShareList;
    class ShareAdapter extends RecyclerView.Adapter<ShareAdapter.Vh>{

        @NonNull
        @Override
        public Vh onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_share, viewGroup, false);
            return new Vh(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Vh vh, int i) {
            final String s = mShareList.get(i);
            vh.tv.setText(s);
            vh.ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showLoading();
                    mGosSession.unbindSharedSmartDevice(s, mDevId, false);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mShareList != null ? mShareList.size() : 0;
        }

        class Vh extends RecyclerView.ViewHolder {
            TextView tv;
            ImageView ivDelete;
            public Vh(@NonNull View itemView) {
                super(itemView);
                tv = itemView.findViewById(R.id.tv);
                ivDelete = itemView.findViewById(R.id.iv_delete);
            }
        }
    }
}
