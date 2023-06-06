package com.goscamsdkdemo.cloud;

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
import android.widget.TextView;

import com.gos.platform.api.contact.PlatCode;
import com.gos.platform.api.inter.OnPlatformEventCallback;
import com.gos.platform.api.result.PlatResult;
import com.goscamsdkdemo.BaseActivity;
import com.goscamsdkdemo.GApplication;
import com.goscamsdkdemo.R;
import com.goscamsdkdemo.cloud.data.GosCloud;
import com.goscamsdkdemo.cloud.data.entity.CloudSetMenuInfo;
import com.goscamsdkdemo.cloud.data.entity.DayTime;
import com.goscamsdkdemo.cloud.data.result.GetCloudSetMenuInfoResult;
import com.goscamsdkdemo.entitiy.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class CloudDayActivity extends BaseActivity implements OnPlatformEventCallback {
    TextView mTvTitle;
    String mDevId;
    GosCloud mGosCloud;
    RecyclerView mRecycleView;
    CloudDayAdapter mCloudDayAdapter;

    public static void startActivity(Activity activity, String deviceId){
        Intent intent = new Intent(activity, CloudDayActivity.class);
        intent.putExtra("DEV_ID", deviceId);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_day);
        mTvTitle = findViewById(R.id.text_title);
        mTvTitle.setText(R.string.day_time);

        mRecycleView = findViewById(R.id.recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(layoutManager);
        mCloudDayAdapter = new CloudDayAdapter();
        mRecycleView.setAdapter(mCloudDayAdapter);

        mDevId = getIntent().getStringExtra("DEV_ID");
        mGosCloud = GosCloud.getCloud();
        mGosCloud.addOnPlatformEventCallback(this);

        showLoading();
        User user = GApplication.app.user;
        mGosCloud.getCloudSetMenuInfo(mDevId, user.token, user.userName, 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mGosCloud.removeOnPlatformEventCallback(this);
    }

    CloudSetMenuInfo setMenuInfo;
    private List<DayTime> mBackPlayDateList = new ArrayList<>();
    private void getDayTimeList(long currentTime) throws ParseException {
        mBackPlayDateList.clear();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //当前时间
        long currentDayStartTime = format.parse(format.format(new Date(currentTime))).getTime()/1000;
        //云服务起始时间
        long serviceDayStartTime = format.parse(format.format(new Date(setMenuInfo.getServiceStartTime()*1000l))).getTime()/1000;
        int days = (int) (currentDayStartTime - serviceDayStartTime) / (24 * 3600) + 1;
        //7天/30天循环覆盖
        for (int i = 0; i < Math.min(days, setMenuInfo.getDateLife() + 1); i++) {
            long mStartTime = currentDayStartTime - i * 24 * 3600;
            long mEndTime = mStartTime + 24 * 3600;
            mBackPlayDateList.add(new DayTime(mStartTime, mEndTime, format.format(mStartTime * 1000)));
        }

        //降序排列
        Collections.sort(mBackPlayDateList, new Comparator<DayTime>() {
            @Override
            public int compare(DayTime o1, DayTime o2) {
                return ((Long) o1.getStartTime()).compareTo(o2.getStartTime());
            }
        });
    }

    @Override
    public void OnPlatformEvent(PlatResult platResult) {
        if(PlatResult.PlatCmd.getCloudSetMenuInfo == platResult.getPlatCmd()){
            int code = platResult.getResponseCode();
            if(PlatCode.SUCCESS == code){
                dismissLoading();
                GetCloudSetMenuInfoResult infoResult = (GetCloudSetMenuInfoResult) platResult;
                setMenuInfo = infoResult.data;
                if (setMenuInfo != null && setMenuInfo.getDateLife() > 0
                        && !TextUtils.isEmpty(setMenuInfo.getStartTime())
                        && !TextUtils.isEmpty(setMenuInfo.getDataExpiredTime())) {
                    if (TextUtils.equals(setMenuInfo.getStatus(), "0")) {//云存储上传服务到期
                        showToast(getResources().getString(R.string.cloud_play_expiration_of_cloud_storage_service_tip));
                    }
                    try {
                        getDayTimeList(System.currentTimeMillis());
                        mCloudDayAdapter.notifyDataSetChanged();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    showToast(getResources().getString(R.string.error_code_80001));
                }
            }else{
                dismissLoading();
                if (code == 1200) {
                    //云存储播放服务到期
                    showToast(getResources().getString(R.string.cloud_play_expiration_of_cloud_storage_service_tip));
                } else if (code == 1204) {
                    //未开通云存储服务
                    showToast(getResources().getString(R.string.cloud_play_not_purchase_cloud_service_tip));
                } else {
                    showToast("Get menuInfo failed,code="+code);
                }
            }
        }
    }

    class CloudDayAdapter extends RecyclerView.Adapter<CloudDayAdapter.Vh>{

        @NonNull
        @Override
        public CloudDayAdapter.Vh onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cloud_day, viewGroup, false);
            return new CloudDayAdapter.Vh(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CloudDayAdapter.Vh vh, int i) {
            final DayTime dayTime = mBackPlayDateList.get(i);
            vh.tv.setText(dayTime.getFormatTime()
                    +"\n"+"StartTime="+dayTime.getStartTime()
                    +"\n"+"EndTime="+dayTime.getEndTime());
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CloudDayFileActivity.startActivity(CloudDayActivity.this, mDevId, dayTime);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mBackPlayDateList.size();
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
