package com.dsource.idc.jellowintl;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.dsource.idc.jellowintl.TalkBack.TalkbackHints_RecyclerView;
import com.dsource.idc.jellowintl.utility.SessionManager;

import static com.dsource.idc.jellowintl.PathFactory.getIconPath;

/**
 * Created by Sumeet on 19-04-2016.
 */
class LevelTwoAdapter extends android.support.v7.widget.RecyclerView.Adapter<LevelTwoAdapter.MyViewHolder> {
    private Context mContext;
    private SessionManager mSession;
    private String[] icons;
    private String[] mBelowTextArray;
    //private String path;

    LevelTwoAdapter(Context context, int levelTwoItemPos){
        mContext = context;
        mSession = new SessionManager(mContext);
        loadArraysFromResources(levelTwoItemPos);
        /*File en_dir = mContext.getDir(mSession.getLanguage(), Context.MODE_PRIVATE);
        path = en_dir.getAbsolutePath()+"/drawables";*/
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final int GRID_1BY3 = 0;
        View rowView;
        if (mSession.getGridSize() == GRID_1BY3)
            rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_level_xadapter_3_icons, parent, false);
        else
            rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_level_xadapter_9_icons, parent, false);
        return new LevelTwoAdapter.MyViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        ViewCompat.setAccessibilityDelegate(holder.menuItemLinearLayout,
                new TalkbackHints_RecyclerView());
        final int MODE_PICTURE_ONLY = 1;
        if (mSession.getPictureViewMode() == MODE_PICTURE_ONLY)
            holder.menuItemBelowText.setVisibility(View.INVISIBLE);
        holder.menuItemBelowText.setText(mBelowTextArray[position]);
        GlideApp.with(mContext)
                .load(getIconPath(mContext, icons[position]))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(false)
                .centerCrop()
                .dontAnimate()
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        GlideApp.with(mContext)
                                .load(getIconPath(mContext, icons[position]))
                                .diskCacheStrategy(DiskCacheStrategy.NONE)
                                .skipMemoryCache(false)
                                .centerCrop()
                                .dontAnimate()
                                .into(holder.menuItemImage);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(holder.menuItemImage);
        holder.menuItemLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                ((LevelTwoActivity)mContext).tappedCategoryItemEvent(holder.menuItemLinearLayout, v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return icons.length;
    }

    private void loadArraysFromResources(int levelTwoItemPos) {

           /* Log.d("level2", levelTwoItemPos+"");

            Log.d("level2", getLevel2IconCode(levelTwoItemPos));*/

        icons = IconFactory.getL2Icons(
                PathFactory.getIconDirectory(mContext),
                LanguageFactory.getCurrentLanguageCode(mContext),
                getLevel2IconCode(levelTwoItemPos)
        );


        /*for (String a : icons)
            Log.d("level2", a);*/


        Icon[] iconObjects = TextFactory.getIconObjects(
                PathFactory.getJSONFile(mContext),
                IconFactory.removeFileExtension(icons)
        );

        mBelowTextArray = TextFactory.getDisplayText(iconObjects);



        /*if (levelTwoItemPos == 0){
            icons = mContext.getResources().getStringArray(R.array.arrLevelTwoGreetFeelIconAdapter);
            mBelowTextArray = mContext.getResources().getStringArray(R.array.arrLevelTwoGreetFeelAdapterText);
        } else if (levelTwoItemPos == 1){
            icons = mContext.getResources().getStringArray(R.array.arrLevelTwoDailyActIconAdapter);
            mBelowTextArray = mContext.getResources().getStringArray(R.array.arrLevelTwoDailyActAdapterText);
        } else if (levelTwoItemPos == 2){
            icons = mContext.getResources().getStringArray(R.array.arrLevelTwoEatingIconAdapter);
            mBelowTextArray = mContext.getResources().getStringArray(R.array.arrLevelTwoEatAdapterText);
        } else if (levelTwoItemPos == 3){
            icons = mContext.getResources().getStringArray(R.array.arrLevelTwoFunIconAdapter);
            mBelowTextArray = mContext.getResources().getStringArray(R.array.arrLevelTwoFunAdapterText);
        } else if (levelTwoItemPos == 4) {
            icons = mContext.getResources().getStringArray(R.array.arrLevelTwoLearningIconAdapter);
            mBelowTextArray = mContext.getResources().getStringArray(R.array.arrLevelTwoLearningAdapterText);
        }  else if (levelTwoItemPos == 6) {
            icons = mContext.getResources().getStringArray(R.array.arrLevelTwoPlacesIcon);
            mBelowTextArray = mContext.getResources().getStringArray(R.array.arrLevelTwoPlacesAdapterText);
        }else if (levelTwoItemPos == 7) {
            icons = mContext.getResources().getStringArray(R.array.arrLevelTwoTimeIconAdapter);
            mBelowTextArray = mContext.getResources().getStringArray(R.array.arrLevelTwoTimeWeatherAdapterText);
        } else if (levelTwoItemPos == 8) {
            icons = mContext.getResources().getStringArray(R.array.arrLevelTwoHelpIconAdapter);
            mBelowTextArray = mContext.getResources().getStringArray(R.array.arrLevelTwoHelpAdapterText);
        }*/

    }

    private String getLevel2IconCode(int level1Position){
        if(level1Position+1 <= 9){
            return "0" + Integer.toString(level1Position+1);
        } else {
            return Integer.toString(level1Position+1);
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout menuItemLinearLayout;
        private ImageView menuItemImage;
        private TextView menuItemBelowText;

        MyViewHolder(final View view) {
            super(view);
            menuItemImage = view.findViewById(R.id.icon1);
            menuItemLinearLayout = view.findViewById(R.id.linearlayout_icon1);
            menuItemBelowText = view.findViewById(R.id.te1);
            menuItemBelowText.setTextColor(Color.rgb(64, 64, 64));
            GradientDrawable gd = (GradientDrawable) view.findViewById(R.id.borderView).getBackground();
            gd.setColor(ContextCompat.getColor(mContext, android.R.color.transparent));
        }
    }
}
