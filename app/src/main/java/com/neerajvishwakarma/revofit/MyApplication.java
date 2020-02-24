package com.neerajvishwakarma.revofit;

import android.app.Activity;
import android.app.Application;

import com.neerajvishwakarma.revofit.di.component.ApplicationComponent;
import com.neerajvishwakarma.revofit.di.component.DaggerApplicationComponent;
import com.neerajvishwakarma.revofit.di.module.ContextModule;


public class MyApplication extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder().contextModule(new ContextModule(this)).build();
        applicationComponent.injectApplication(this);

    }

    public static MyApplication get(Activity activity){
        return (MyApplication) activity.getApplication();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}

