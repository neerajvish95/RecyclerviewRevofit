package com.neerajvishwakarma.revofit.di.module;

import com.neerajvishwakarma.revofit.MainActivity;
import com.neerajvishwakarma.revofit.MyRecyclerAdapter;
import com.neerajvishwakarma.revofit.di.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module(includes = {MainActivityContextModule.class})
public class AdapterModule {

    @Provides
    @ActivityScope
    public MyRecyclerAdapter getCoinList(MyRecyclerAdapter.ClickListener clickListener) {
        return new MyRecyclerAdapter(clickListener);
    }


    @Provides
    @ActivityScope
    public MyRecyclerAdapter.ClickListener getClickListener(MainActivity mainActivity) {
        return mainActivity;
    }
}
