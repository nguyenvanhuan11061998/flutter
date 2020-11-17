package com.example.huannv_mutithread;

import android.util.Log;

public class SendRequestTask extends BaseWorker{
    private static String TAG = "SendRequestTask";

    public SendRequestTask(long priority, long delayTime) {
        this.name = TAG;
        this.priority = priority;
        this.delayTime = delayTime;
    }

}
