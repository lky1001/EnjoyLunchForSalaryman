package com.soundlly.enjoylaunchforsalaryman;

import java.io.File;

/**
 * Created by soundllydev on 2016. 3. 31..
 */
public class LunchApplication extends android.app.Application {

    private static LunchApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static File getCacheDirectory()  {
        return application.getCacheDir();
    }

    public static LunchApplication getInstance() {
        return application;
    }
}
