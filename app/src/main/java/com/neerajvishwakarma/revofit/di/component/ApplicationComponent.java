package com.neerajvishwakarma.revofit.di.component;

import android.content.Context;


import com.neerajvishwakarma.revofit.MyApplication;
import com.neerajvishwakarma.revofit.di.module.ContextModule;
import com.neerajvishwakarma.revofit.di.module.RetrofitModule;
import com.neerajvishwakarma.revofit.di.qualifier.ApplicationContext;
import com.neerajvishwakarma.revofit.di.scopes.ApplicationScope;
import com.neerajvishwakarma.revofit.retrofit.APIInterface;

import dagger.Component;

@ApplicationScope
@Component(modules = {ContextModule.class, RetrofitModule.class})
public interface ApplicationComponent {

    APIInterface getApiInterface();

    @ApplicationContext
    Context getContext();

    void injectApplication(MyApplication myApplication);
}
