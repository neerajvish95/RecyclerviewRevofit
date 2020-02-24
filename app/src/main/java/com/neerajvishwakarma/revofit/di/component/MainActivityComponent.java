package com.neerajvishwakarma.revofit.di.component;

import android.content.Context;

import com.neerajvishwakarma.revofit.MainActivity;
import com.neerajvishwakarma.revofit.di.module.AdapterModule;
import com.neerajvishwakarma.revofit.di.module.MainActivityMvpModule;
import com.neerajvishwakarma.revofit.di.qualifier.ActivityContext;
import com.neerajvishwakarma.revofit.di.scopes.ActivityScope;
import dagger.Component;


@ActivityScope
@Component(modules = {AdapterModule.class, MainActivityMvpModule.class}, dependencies = ApplicationComponent.class)
public interface MainActivityComponent {

    @ActivityContext
    Context getContext();
    void injectMainActivity(MainActivity mainActivity);
}
