package com.example.helloworld;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class ContohJobScheduler extends JobService {
    private static final String TAG = "ExampleJobService";
    private boolean jobCancelled = false;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.d(TAG, "Job started");
        doBackground(jobParameters);
        return true;
    }
    private void doBackground(final JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    Log.d(TAG, "run: " + i);
                    final int counter = i;
                    Handler mHandler = new Handler(getMainLooper());
                    mHandler.post(new Runnable(){
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"Toast ke-"+counter,Toast.LENGTH_SHORT).show();
                        }
                    });
                    if (jobCancelled) {
                        return;
                    }
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Log.d(TAG, "Job finished");
                jobFinished(params, false);
            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.d(TAG, "Job cancelled before completion");
        jobCancelled = true;
        return true;
    }
}

