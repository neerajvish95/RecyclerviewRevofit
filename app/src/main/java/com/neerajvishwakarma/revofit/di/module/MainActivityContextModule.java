package com.neerajvishwakarma.revofit.di.module;

import android.content.Context;


import com.neerajvishwakarma.revofit.MainActivity;
import com.neerajvishwakarma.revofit.di.qualifier.ActivityContext;
import com.neerajvishwakarma.revofit.di.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityContextModule {
    private MainActivity mainActivity;

    public Context context;

    public MainActivityContextModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        context = mainActivity;
    }

    @Provides
    @ActivityScope
    public MainActivity providesMainActivity() {
        return mainActivity;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context provideContext() {
        return context;
    }

}
