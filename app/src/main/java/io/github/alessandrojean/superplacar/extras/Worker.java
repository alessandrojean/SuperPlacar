package io.github.alessandrojean.superplacar.extras;

import android.os.SystemClock;

import java.lang.ref.WeakReference;

import io.github.alessandrojean.superplacar.MainActivity;

/**
 * Created by Desktop on 14/12/2017.
 */

public class Worker extends Thread {
    private WeakReference<MainActivity> activity;

    public Worker(MainActivity mainActivity) {
        this.activity = new WeakReference<>(mainActivity);
    }

    @Override
    public void run() {
        super.run();

        while (activity.get() != null) {
            SystemClock.sleep(20000);
            new SuperPlacarRequest(activity.get()).execute();
        }
    }
}
