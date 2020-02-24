package com.neerajvishwakarma.revofit.di.module;

import android.content.Context;
import com.neerajvishwakarma.revofit.di.qualifier.ApplicationContext;
import com.neerajvishwakarma.revofit.di.scopes.ApplicationScope;
import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    public Context provideContext() {
        return context;
    }
}
