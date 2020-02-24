package com.neerajvishwakarma.revofit;

import android.content.Context;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.neerajvishwakarma.revofit.model.Data;

import java.util.ArrayList;

public class ViewPagerAdapter extends PagerAdapter {

    public Context context;

    public ArrayList<Data> imageList;

    public ViewPagerAdapter(Context context, ArrayList<Data> imageList) {
        this.context = context;
        this.imageList = imageList;
    }


    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    public int getCount() {
        return imageList.size();
    }

    public int getItemPosition(Object obj) {
        return -2;
    }

    public View instantiateItem(ViewGroup viewGroup, final int i) {

        String stringURL = "";

        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.viewpager_item_layout, viewGroup, false);

        ImageView img_scrollable = (ImageView) inflate.findViewById(R.id.img_scrollable);


        stringURL = ((Data) this.imageList.get(i)).thumbnail;

        try {

            Glide.with(this.context)
                    .load(Uri.parse(stringURL))
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .skipMemoryCache(true)
                    .into(img_scrollable);

        } catch (Exception e) {
            e.printStackTrace();
        }

        viewGroup.addView(inflate);
        return inflate;
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
    }

    public Parcelable saveState() {
        return null;
    }
}
