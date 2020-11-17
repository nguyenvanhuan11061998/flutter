package com.example.huannv_mutithread;

import android.util.Log;

public abstract class BaseWorker implements Runnable, Comparable<BaseWorker> {
    protected String id;
    protected String name;
    protected long priority;
    protected long delayTime;

    public long getPriority() {
        return priority;
    }

    public void setPriority(long priority) {
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        Log.e("Task "+ name, " - priority:" + priority +" start");
        try {
            Thread.sleep(delayTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e(name, " - finish");
    }

    @Override
    public String toString() {
        return "BaseWorker{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", priority=" + priority +
                ", delayTime=" + delayTime +
                '}';
    }

    @Override
    public int compareTo(BaseWorker baseWorker) {
        return String.valueOf(this.getPriority()).compareTo(String.valueOf(baseWorker.getPriority()));
    }
}
