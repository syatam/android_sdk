package com.adjust.sdk.threading;

import android.util.*;

import com.adjust.sdk.*;

import java.util.concurrent.*;

/**
 * Created by abdullah on 8/3/16.
 * <p/>
 * An all-purpose thread manager that starts daemon background threads equal to the number of
 * the devices cores
 */
@SuppressWarnings("unused")
public class ThreadManager {
    // Sets the amount of time an idle thread will wait for a task before terminating
    private static final int KEEP_ALIVE_TIME = 1;

    // Sets the Time Unit to seconds
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;

    // A managed thread pool of background threads
    private final ThreadPoolExecutor executor;

    // A single instance of ThreadManager, used to implement a singleton pattern
    private static ThreadManager sInstance = new ThreadManager();

    public static ThreadManager getInstance() {
        return sInstance;
    }

    public void execute(Runnable runnable) {
        executor.execute(runnable);
    }

    /**
     * Constructs the work queues and thread pools
     */
    private ThreadManager() {
        final int numOfCores = Util.getNumberOfCores();

        executor = new ThreadPoolExecutor(
                numOfCores,
                numOfCores,
                KEEP_ALIVE_TIME,
                KEEP_ALIVE_TIME_UNIT,
                new LinkedBlockingQueue<Runnable>(),
                new LoggingThreadFactory(),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                        AdjustFactory.getLogger().warn(runnable.toString() + " was rejected");
                    }
                });

        executor.allowCoreThreadTimeOut(true);

        //----------MONITORING_THREAD-----------
//        injectMonitorThread();
        //----------MONITORING_THREAD-----------
    }

    private void injectMonitorThread() {
        MonitorThread watch = new MonitorThread(executor, 5000);
        executor.execute(watch);
    }
}
