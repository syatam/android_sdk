package com.adjust.sdk.threading;

import com.adjust.sdk.*;

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

/**
 * Created by abdullah on 8/3/16.
 * <p/>
 * Used for creating instantiating threads inside ThreadManager's ThreadPoolExecutor
 */
public class LoggingThreadFactory implements ThreadFactory {
    static final AtomicInteger poolNumber = new AtomicInteger(1);
    final AtomicInteger threadNumber = new AtomicInteger(1);
    final String namePrefix;
    final ThreadGroup threadGroup;

    public LoggingThreadFactory() {
        SecurityManager securityManager = System.getSecurityManager();
        threadGroup = (securityManager != null) ? securityManager.getThreadGroup() :
                Thread.currentThread().getThreadGroup();

        namePrefix = "pool-" +
                poolNumber.getAndIncrement() +
                "-thread-";
    }

    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(threadGroup, runnable,
                namePrefix + threadNumber.getAndIncrement(),
                0);

        if (!thread.isDaemon())
            thread.setDaemon(true);
        if (thread.getPriority() != Thread.MIN_PRIORITY)
            thread.setPriority(Thread.MIN_PRIORITY);

        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable throwable) {
                AdjustFactory.getLogger().error(throwable.getMessage(),
                        thread.toString() + ": " + throwable);
            }
        });

        return thread;
    }
}
