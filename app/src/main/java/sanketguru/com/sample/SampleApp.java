package sanketguru.com.sample;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by Sanket Gurav on 12/11/2017.
 */

public class SampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

//        DaggerAppComponent
//                .builder()
//                .application(this)
//                .build()
//                .inject(this);
    }
}
