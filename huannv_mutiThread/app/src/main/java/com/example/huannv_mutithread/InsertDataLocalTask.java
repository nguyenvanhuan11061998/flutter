package com.example.huannv_mutithread;

import android.util.Log;

public class InsertDataLocalTask extends BaseWorker{
    private static String TAG = "InsertDataLocalTask";

    public InsertDataLocalTask(long priority, long delayTime) {
        this.name = TAG;
        this.priority = priority;
        this.delayTime = delayTime;
    }
}
