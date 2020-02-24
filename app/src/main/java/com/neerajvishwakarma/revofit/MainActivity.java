package com.neerajvishwakarma.revofit;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.neerajvishwakarma.revofit.customview.AutoScrollViewPager;
import com.neerajvishwakarma.revofit.customview.CirclePageIndicator;
import com.neerajvishwakarma.revofit.di.component.ApplicationComponent;
import com.neerajvishwakarma.revofit.di.component.DaggerMainActivityComponent;
import com.neerajvishwakarma.revofit.di.component.MainActivityComponent;
import com.neerajvishwakarma.revofit.di.module.MainActivityContextModule;
import com.neerajvishwakarma.revofit.di.module.MainActivityMvpModule;
import com.neerajvishwakarma.revofit.di.qualifier.ActivityContext;
import com.neerajvishwakarma.revofit.di.qualifier.ApplicationContext;
import com.neerajvishwakarma.revofit.model.Data;
import com.neerajvishwakarma.revofit.model.RevofitResponseDataModel;
import com.neerajvishwakarma.revofit.presenter.MainActivityContract;
import com.neerajvishwakarma.revofit.presenter.PresenterImpl;

import java.util.ArrayList;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View, MyRecyclerAdapter.ClickListener {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    MainActivityComponent mainActivityComponent;
    TextView label;

    protected RelativeLayout main_layout;

    public CirclePageIndicator pageIndicator;

    protected AutoScrollViewPager viewpager;

    protected ViewPagerAdapter imagePagerAdapter;

    ArrayList<Data> movepopularlist = new ArrayList<>();
    final ArrayList<Data> movebannerdata = new ArrayList<>();
    ArrayList<Data> eatRecipe = new ArrayList<>();
    ArrayList<Object> mainlist = new ArrayList<>();

    @Inject
    @ApplicationContext
    public Context mContext;

    @Inject
    @ActivityContext
    public Context activityContext;

    @Inject
    PresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityContextModule(new MainActivityContextModule(this))
                .mainActivityMvpModule(new MainActivityMvpModule(this))
                .applicationComponent(applicationComponent)
                .build();

        mainActivityComponent.injectMainActivity(this);

        label = findViewById(R.id.label);
        recyclerView = findViewById(R.id.recyclerView);

        progressBar = findViewById(R.id.progressBar);

        pageIndicator = (CirclePageIndicator) findViewById(R.id.pageIndicator);
        main_layout = (RelativeLayout) findViewById(R.id.main_layout);
        viewpager = (AutoScrollViewPager) findViewById(R.id.viewpager);
        viewpager.startAutoScroll(4000);
        viewpager.setCycle(true);
        viewpager.setBorderAnimation(true);

        presenter.loadData();
        recyclerView.setNestedScrollingEnabled(false);

    }

    @Override
    public void showData(RevofitResponseDataModel data) {

        movepopularlist.addAll(data.main_content.list.data);
        movebannerdata.addAll(data.main_content.banner.data);
        eatRecipe.addAll(data.main_content.recipe.data);
        mainlist.addAll(movebannerdata);
        mainlist.addAll(eatRecipe);


        viewpager.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
                int width = viewpager.getWidth();
                float f = (float) (width / 2);
                layoutParams.width = width;
                layoutParams.height = (int) f;
                viewpager.setLayoutParams(layoutParams);
                viewpager.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        imagePagerAdapter = new ViewPagerAdapter(MainActivity.this, movepopularlist);
        viewpager.setAdapter(imagePagerAdapter);
        viewpager.destroyDrawingCache();
        pageIndicator.setViewPager(viewpager);

        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            public int getSpanSize(int i) {
                return (i > movebannerdata.size() - 1) ? 2 : 1;
            }
        });
        recyclerView.addItemDecoration(new ItemPaddingDecoration(20));
        recyclerView.setLayoutManager(gridLayoutManager);
        MyRecyclerAdapter myRecyclerAdapter = new MyRecyclerAdapter(MainActivity.this,MainActivity.this, mainlist, movebannerdata);
        recyclerView.setAdapter(myRecyclerAdapter);
        label.setVisibility(View.VISIBLE);

    }

    public class ItemPaddingDecoration extends RecyclerView.ItemDecoration {
        private int padding;

        ItemPaddingDecoration(int i) {
            this.padding = i;
        }

        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            int i;

            if (recyclerView.getChildAdapterPosition(view) % 2 == 0) {
                rect.right = padding / 2;
                i = padding;

            } else {

                rect.right = padding;
                i = padding / 2;
            }
            rect.left = i;
            rect.top = padding;
            if (recyclerView.getChildAdapterPosition(view) > movebannerdata.size() - 1) {
                rect.right = 0;
                rect.left = 0;
                rect.top = padding;
            }

        }
    }

    @Override
    public void showError(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showComplete() {

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void launchIntent(String name) {
        Toast.makeText(mContext, "Clicked on " + name, Toast.LENGTH_SHORT).show();
    }
}
