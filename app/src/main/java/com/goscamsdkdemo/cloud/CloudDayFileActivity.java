package com.goscamsdkdemo.cloud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gos.platform.api.contact.PlatCode;
import com.gos.platform.api.inter.OnPlatformEventCallback;
import com.gos.platform.api.result.PlatResult;
import com.goscamsdkdemo.BaseActivity;
import com.goscamsdkdemo.GApplication;
import com.goscamsdkdemo.R;
import com.goscamsdkdemo.cloud.data.GosCloud;
import com.goscamsdkdemo.cloud.data.entity.CameraEvent;
import com.goscamsdkdemo.cloud.data.entity.DayTime;
import com.goscamsdkdemo.cloud.data.result.GetCloudAlarmVideoListResult;
import com.goscamsdkdemo.entitiy.User;

import java.util.ArrayList;
import java.util.List;

public class CloudDayFileActivity extends BaseActivity implements OnPlatformEventCallback {
    TextView mTvTitle;
    String mDeviceId;
    long mStartTime;
    long mEndTime;
    RecyclerView mRecycleView;
    CloudDayFileAdapter mCloudDayFileAdapter;

    public static void startActivity(Activity activity, String deviceId, DayTime dayTime){
        Intent intent = new Intent(activity, CloudDayFileActivity.class);
        intent.putExtra("DEV_ID", deviceId);
        intent.putExtra("START_TIME", dayTime.getStartTime());
        intent.putExtra("END_TIME", dayTime.getEndTime());
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_day);
        mTvTitle = findViewById(R.id.text_title);
        mTvTitle.setText(R.string.alarm_file);

        mRecycleView = findViewById(R.id.recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(layoutManager);
        mCloudDayFileAdapter = new CloudDayFileAdapter();
        mRecycleView.setAdapter(mCloudDayFileAdapter);

        mDeviceId = getIntent().getStringExtra("DEV_ID");
        mStartTime = getIntent().getLongExtra("START_TIME", 0);
        mEndTime = getIntent().getLongExtra("END_TIME", 0);

        User user = GApplication.app.user;
        GosCloud.getCloud().addOnPlatformEventCallback(this);
        showLoading();
        GosCloud.getCloud().getAllCloudAlarmVideoList(mDeviceId, mStartTime, mEndTime, user.token, user.userName, 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GosCloud.getCloud().removeOnPlatformEventCallback(this);
    }

    @Override
    public void OnPlatformEvent(PlatResult platResult) {
        if(PlatResult.PlatCmd.getAllCloudAlarmVideoList == platResult.getPlatCmd()){
            dismissLoading();
            if(PlatCode.SUCCESS == platResult.getResponseCode()){
                GetCloudAlarmVideoListResult listResult = (GetCloudAlarmVideoListResult) platResult;
                cameraEventList.addAll(listResult.getData());
                mCloudDayFileAdapter.notifyDataSetChanged();
            }else{
                showToast("request failed, code="+platResult.getResponseCode());
            }
        }
    }

    List<CameraEvent> cameraEventList = new ArrayList<>();
    class CloudDayFileAdapter extends RecyclerView.Adapter<CloudDayFileAdapter.Vh>{

        @NonNull
        @Override
        public CloudDayFileAdapter.Vh onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cloud_day, viewGroup, false);
            return new CloudDayFileAdapter.Vh(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CloudDayFileAdapter.Vh vh, int i) {
            final CameraEvent cameraEvent = cameraEventList.get(i);
            vh.tv.setText("StartTime:"+cameraEvent.getStartTextTime()+"\n"
                    +"EndTime:"+cameraEvent.getEndTextTime()+"\n"
                    +"Type="+cameraEvent.getEventType());

            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CloudFilePlayActivity.startActivity(CloudDayFileActivity.this, mDeviceId, cameraEvent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return cameraEventList.size();
        }

        class Vh extends RecyclerView.ViewHolder {
            TextView tv;
            public Vh(@NonNull View itemView) {
                super(itemView);
                tv = itemView.findViewById(R.id.tv);
            }
        }
    }
}
