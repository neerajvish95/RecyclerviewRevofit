package com.neerajvishwakarma.revofit.di.module;

import com.neerajvishwakarma.revofit.di.scopes.ActivityScope;
import com.neerajvishwakarma.revofit.presenter.MainActivityContract;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityMvpModule {
    private final MainActivityContract.View mView;


    public MainActivityMvpModule(MainActivityContract.View mView) {
        this.mView = mView;
    }

    @Provides
    @ActivityScope
    MainActivityContract.View provideView() {
        return mView;
    }


}
