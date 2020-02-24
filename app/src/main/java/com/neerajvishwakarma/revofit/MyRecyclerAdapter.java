package com.neerajvishwakarma.revofit;

import android.app.Activity;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.neerajvishwakarma.revofit.model.Data;

import java.util.ArrayList;

import javax.inject.Inject;

public class MyRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Data> moveBannerDataList = new ArrayList<>();
    private ArrayList<Object> globalobjectarray = new ArrayList<>();

    private Activity activity;
    private ClickListener clickListener;

    @Inject
    public MyRecyclerAdapter(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    MyRecyclerAdapter(Activity activity,ClickListener clickListener, ArrayList<Object> globalobjectarray, ArrayList<Data> moveBannerDataList) {
        this.moveBannerDataList = moveBannerDataList;
        this.globalobjectarray = globalobjectarray;
        this.activity = activity;
        this.clickListener = clickListener;
    }


    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof BrowseFocusViewHolder) {

            final Data data = (Data) globalobjectarray.get(position);
            BrowseFocusViewHolder browseFocusViewHolder = (BrowseFocusViewHolder) viewHolder;
            Glide.with(activity).load(Uri.parse(data.thumbnail)).into(browseFocusViewHolder.img_image);
            if (data.headline != null && !data.headline.equals("")) {
                browseFocusViewHolder.tv_text.setText(data.headline);

            } else {
                browseFocusViewHolder.tv_text.setText(data.headline_1);

            }
            browseFocusViewHolder.ll_main.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {

                    if (data.headline != null && !data.headline.equals("")) {

                        clickListener.launchIntent(data.headline);

                    } else {
                        clickListener.launchIntent(data.headline_1);

                    }

                }
            });

        } else if (viewHolder instanceof PopularRecipeViewHolder) {

            PopularRecipeViewHolder popularRecipeViewHolder = (PopularRecipeViewHolder) viewHolder;
            popularRecipeViewHolder.main_title.setText("Popular recipes");

            final Data data = (Data) globalobjectarray.get(position);
            Glide.with(activity).load(Uri.parse(data.smallthumbnail)).into(popularRecipeViewHolder.img_image);

            if (position == moveBannerDataList.size()) {
                popularRecipeViewHolder.main_title.setVisibility(View.VISIBLE);
            } else {
                popularRecipeViewHolder.main_title.setVisibility(View.GONE);
            }
            popularRecipeViewHolder.tv_text.setText(data.name);
            popularRecipeViewHolder.tv_lunchtime.setText(data.meal_time);
            popularRecipeViewHolder.img_image.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {

                    clickListener.launchIntent(data.name);
                }
            });

        }
    }


    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        if (position == 1) {

            return new PopularRecipeViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.popular_recipe_item, viewGroup, false));

        } else if (position == 0) {

            return new BrowseFocusViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.browse_by_focus_item, viewGroup, false));

        } else {
            throw new RuntimeException("No Layout found");
        }
    }

    public class BrowseFocusViewHolder extends RecyclerView.ViewHolder {

        ImageView img_image;
        LinearLayout ll_main;
        TextView tv_text;

        BrowseFocusViewHolder(View view) {
            super(view);

            img_image = (ImageView) view.findViewById(R.id.img_image);
            tv_text = (TextView) view.findViewById(R.id.tv_text);
            ll_main = (LinearLayout) view.findViewById(R.id.ll_main);
        }
    }

    public class PopularRecipeViewHolder extends RecyclerView.ViewHolder {
        public View View;

        final TextView main_title;
        final ImageView img_image;
        final TextView tv_lunchtime;
        final TextView tv_text;

        PopularRecipeViewHolder(View view) {
            super(view);
            View = view;

            main_title = ((TextView) this.View.findViewById(R.id.main_title));
            img_image = ((ImageView) this.View.findViewById(R.id.img_image));
            tv_lunchtime = ((TextView) this.View.findViewById(R.id.tv_lunchtime));
            tv_text = ((TextView) this.View.findViewById(R.id.tv_text));

        }
    }

    public int getItemCount() {
        return globalobjectarray.size();
    }

    public int getItemViewType(int i) {
        if (i > moveBannerDataList.size() - 1) {
            return 1;
        } else {
            return 0;
        }
    }

    public interface ClickListener {
        void launchIntent(String name);
    }

}
