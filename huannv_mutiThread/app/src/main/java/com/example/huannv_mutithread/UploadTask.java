package com.example.huannv_mutithread;

public class UploadTask extends BaseWorker{
    private static String TAG = "UploadTask";

    public UploadTask(long priority, long delayTime) {
        this.name = TAG;
        this.priority = priority;
        this.delayTime = delayTime;
    }

    @Override
    public void run() {
        super.run();
    }
}
