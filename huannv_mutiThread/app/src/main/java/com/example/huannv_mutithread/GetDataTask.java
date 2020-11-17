package com.example.huannv_mutithread;

import android.util.Log;

public class GetDataTask extends BaseWorker{
    private static String TAG = "GetDataTask";

    public GetDataTask(long priority, long delayTime) {
        this.name = TAG;
        this.priority = priority;
        this.delayTime = delayTime;
    }
}
