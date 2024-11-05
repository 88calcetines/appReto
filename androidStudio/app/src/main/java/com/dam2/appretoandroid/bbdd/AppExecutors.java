package com.dam2.appretoandroid.bbdd;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors
{
    private static final Object LOCK = new Object();
    private static AppExecutors sInstance;
    private final Executor diskIp;
    private final Executor mainThread;
    private final Executor networkIp;

    public AppExecutors(Executor diskIp, Executor mainThread, Executor networkIp)
    {
        this.diskIp = diskIp;
        this.mainThread = mainThread;
        this.networkIp = networkIp;
    }

    private static class MainThreadExecutor implements Executor{
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());
        @Override
        public void execute(Runnable command) {
            mainThreadHandler.post(command);
        }
    }

    public Executor getDiskIp() {
        return diskIp;
    }

    public Executor getMainThread() {
        return mainThread;
    }

    public Executor getNetworkIp() {
        return networkIp;
    }

    public static AppExecutors getInstance(){
        if(sInstance == null) {
            synchronized (LOCK)
            {
                sInstance = new AppExecutors(Executors.newSingleThreadExecutor(),
                        Executors.newFixedThreadPool(3), new MainThreadExecutor());
            }
        }
        return sInstance;
    }
}
