package com.adjust.sdk.threading;

import android.util.*;

import java.util.concurrent.*;

/**
 * Created by abdullah on 8/3/16.
 * <p/>
 * A thread made for monitoring `ThreadManager`.
 * Prints out important info regarding the ThreadPoolExecutor every X delay
 */
public class MonitorThread implements Runnable {
    private static final String TAG = "MonitorThread";

    private ThreadPoolExecutor executor;
    private boolean run = true;
    private int delayInMilli;

    public MonitorThread(ThreadPoolExecutor threadPoolExecutor, int delayInMilli) {
        this.executor = threadPoolExecutor;
        this.delayInMilli = delayInMilli;
    }

    @Override
    public void run() {
        while (run) {
            Log.i(TAG, "<--------------------------Thread Info Start------------------------------->");
            Log.i(TAG, String.format("Monitoring Threads: [%d/%d] \n" +
                            "Active Threads: %d \n" +
                            "Number of Tasks Completed: %d \n" +
                            "Total Number of Tasks: %d \n" +
                            "Is it Shutdown: %s \n" +
                            "Is it Terminated: %s \n",
                    this.executor.getPoolSize(),
                    this.executor.getCorePoolSize(),
                    this.executor.getActiveCount(),
                    this.executor.getCompletedTaskCount(),
                    this.executor.getTaskCount(),
                    this.executor.isShutdown(),
                    this.executor.isTerminated())
            );
            Log.i(TAG, "<--------------------------Thread Info Ends------------------------------->");
            try {
                Thread.sleep(delayInMilli);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
