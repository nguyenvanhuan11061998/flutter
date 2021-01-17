package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_time)
    TextView timeTextView;
    @BindView(R.id.btn_start)
    Button startButton;
    @BindView(R.id.btn_stop)
    Button stopButton;

    private Thread t;
    private Handler handler = new Handler();

    private long systemTime = 0;
    private long startTime = 0;
    private long pauseTime = 0;

    private boolean isRun = false;
    public int flag = 0;
    private long currentTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        timeTextView.setText("00:00:00");
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                if (flag != 2) {
                    systemTime = SystemClock.uptimeMillis() - startTime;
                    currentTime = pauseTime + systemTime;

                    if (flag == 0) {
                        long secs = (long) (currentTime / 1000);
                        long mins = secs / 60;
                        long milliseconds = (long) (currentTime % 1000);
                        if (isRun) {
                            timeTextView.setText("" + mins + ":" + String.format("%02d", secs) + ":" + String.format("%03d", milliseconds));
                        }
                        handler.post(this);
                    }
                } else {
                    timeTextView.setText("00:00:000");
                }
            }
        });
        t.start();
    }

    @OnClick({R.id.btn_start, R.id.btn_pause, R.id.btn_stop})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                startTime = SystemClock.uptimeMillis();
                currentTime = 0;
                if (flag != 1) {
                    pauseTime = 0;
                }
                flag = 0;
                handler.post(t);
                if (!isRun)
                    isRun = true;
                break;
            case R.id.btn_pause:
                if (flag == 1) {
                    return;
                }
                flag = 1;
                handler.post(t);
                pauseTime = currentTime;
                break;
            case R.id.btn_stop:
                if (flag == 2) {
                    return;
                }
                startTime = SystemClock.uptimeMillis();
                pauseTime = 0;
                currentTime = 0;
                flag = 2;
                handler.post(t);
                break;
        }
    }
}