package com.goscamsdkdemo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.gos.platform.api.GosSession;
import com.gos.platform.api.contact.PlatCode;
import com.gos.platform.api.contact.ServerType;
import com.gos.platform.api.inter.OnPlatformEventCallback;
import com.gos.platform.api.result.PlatResult;
import com.goscamsdkdemo.add.WifiSelectActivity;
import com.goscamsdkdemo.cloud.CloudDayActivity;
import com.goscamsdkdemo.entitiy.Device;
import com.goscamsdkdemo.entitiy.DeviceManager;
import com.goscamsdkdemo.setting.SettingActivity;
import com.goscamsdkdemo.tf.TfDayActivity;

import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, OnPlatformEventCallback {
    ImageView mIvBack;
    ImageView mIvRight;
    ImageView mIvLeft;
    TextView mTvTitle;
    TextView mTvUserName;
    SwipeRefreshLayout mSwipeRefresh;
    RecyclerView mRecycleView;
    DrawerLayout mDrawerLayout;


    GosSession mGosSession;
    DeviceAdapter mDeviceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvTitle = findViewById(R.id.text_title);
        mIvBack = findViewById(R.id.back_img);
        mIvBack.setImageResource(R.drawable.ic_main_title_user_tag);
        mIvBack.setOnClickListener(this);
        mIvRight = findViewById(R.id.right_img);
        mTvUserName = findViewById(R.id.tv_username);
        mTvUserName.setText(GApplication.app.user.userName);
        mDrawerLayout = findViewById(R.id.drawer_layout);

        mTvTitle.setText(R.string.list);
        mIvRight.setImageResource(R.drawable.icon_add);
        mIvRight.setVisibility(View.VISIBLE);
        mIvRight.setOnClickListener(this);
        findViewById(R.id.tv_modify_psw).setOnClickListener(this);
        findViewById(R.id.tv_logout).setOnClickListener(this);
        findViewById(R.id.tv_web_socket).setOnClickListener(this);

        mSwipeRefresh = findViewById(R.id.swipe_refresh);
        mRecycleView = findViewById(R.id.recycle_view);
        mSwipeRefresh.setOnRefreshListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(layoutManager);
        mDeviceAdapter = new DeviceAdapter();
        mRecycleView.setAdapter(mDeviceAdapter);

        mGosSession = GosSession.getSession();
        mGosSession.addOnPlatformEventCallback(this);
        mSwipeRefresh.setRefreshing(true);
        onRefresh();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mGosSession.removeOnPlatformEventCallback(this);
        DeviceManager.getInstance().clear();
    }

    long mExitTime;
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - mExitTime > 2000) {
            mExitTime = System.currentTimeMillis();
            showToast(getString(R.string.exit_tip));
        } else {
            logout();
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_img:
                mDrawerLayout.openDrawer(Gravity.START);
                break;
            case R.id.right_img:
                startActivity(new Intent(this, WifiSelectActivity.class));
                break;
            case R.id.tv_modify_psw:
                startActivity(new Intent(this, ModifyPswActivity.class));
                break;
            case R.id.tv_web_socket:
                //startActivity(new Intent(this, WsActivity.class));
                break;
            case R.id.tv_logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Notice");
                builder.setMessage(R.string.exit_account);
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton(R.string.logout, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        logout();
                    }
                });
                builder.show();
                break;
        }
    }

    private void logout(){
        DeviceManager.getInstance().clear();
        GosSession.getSession().stopHeartBeat();
        GosSession.getSession().netClosse(ServerType.CGSA);
        finish();
    }

    @Override
    public void onRefresh() {
        mGosSession.getDeviceList(GApplication.app.user.userName);
    }

    @Override
    public void OnPlatformEvent(PlatResult platResult) {
        PlatResult.PlatCmd platCmd = platResult.getPlatCmd();
        int code = platResult.getResponseCode();
        if(PlatResult.PlatCmd.getDeviceList == platCmd){
            mSwipeRefresh.setRefreshing(false);
            if(PlatCode.SUCCESS == code){
                deviceList = DeviceManager.getInstance().getDeviceList();
                mDeviceAdapter.notifyDataSetChanged();
            }else {
                showToast("getDeviceList failed,code="+code);
            }
        }
    }

    private List<Device> deviceList;
    class DeviceAdapter extends RecyclerView.Adapter<ViewHolder>{

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
            if(type == 2){
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_device, viewGroup, false);
                return new Vh(view);
            }else{
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_device_empty, viewGroup, false);
                return new EmptyVh(view);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder vh, int i) {
            if(vh instanceof Vh){
                final Device device = deviceList.get(i);
                ((Vh) vh).tv.setText("DevName:"+device.devName
                        +"\nDevId:"+device.devId+
                        "\nDevType:"+device.devType+
                        "\nStatus:"+(device.isOnline?"Online":"Offline"));
                vh.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PlayActivity.startActivity(MainActivity.this, device.devId);
                    }
                });
                ((Vh) vh).ibtnTf.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TfDayActivity.startActivity(MainActivity.this, device.devId);
                    }
                });
                ((Vh) vh).ibtnCloud.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CloudDayActivity.startActivity(MainActivity.this, device.devId);
                    }
                });
                ((Vh) vh).ibtnSetting.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SettingActivity.startActivity(MainActivity.this, device.devId);
                    }
                });

            }
        }

        @Override
        public int getItemViewType(int position) {
            return deviceList == null || deviceList.size() == 0 ? 1 : 2;
        }

        @Override
        public int getItemCount() {
            return deviceList == null || deviceList.size() == 0 ? 1 : deviceList.size();
        }

        class Vh extends ViewHolder{
            TextView tv;
            ImageButton ibtnTf;
            ImageButton ibtnCloud;
            ImageButton ibtnSetting;
            public Vh(@NonNull View itemView) {
                super(itemView);
                tv = itemView.findViewById(R.id.tv);
                ibtnTf = itemView.findViewById(R.id.ibtn_tf);
                ibtnCloud = itemView.findViewById(R.id.ibtn_cloud);
                ibtnSetting = itemView.findViewById(R.id.ibtn_setting);
            }
        }

        class EmptyVh extends ViewHolder{
            public EmptyVh(@NonNull View itemView) {
                super(itemView);
            }
        }
    }
}