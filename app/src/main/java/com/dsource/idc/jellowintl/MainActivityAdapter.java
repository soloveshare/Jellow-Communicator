package com.dsource.idc.jellowintl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.dsource.idc.jellowintl.TalkBack.TalkbackHints_RecyclerView;
import com.dsource.idc.jellowintl.utility.SessionManager;

import java.io.File;

import static com.dsource.idc.jellowintl.PathFactory.getIconDirectory;
import static com.dsource.idc.jellowintl.PathFactory.getIconPath;
import static com.dsource.idc.jellowintl.PathFactory.getJSONFile;

/**
 * Created by ekalpa on 4/19/2016.
 */
class MainActivityAdapter extends android.support.v7.widget.RecyclerView.Adapter<MainActivityAdapter.MyViewHolder> {
    private Context mContext;
    private SessionManager mSession;
    private String[] icons;
    private String[] mBelowTextArray;
    private RequestManager glide;

    public MainActivityAdapter(Context context) {
        mContext = context;
        glide = GlideApp.with(mContext);
        mSession = new SessionManager(mContext);

        icons = IconFactory.getL1Icons(
                getIconDirectory(context),
                LanguageFactory.getCurrentLanguageCode(context)
        );

        File map = getJSONFile(context);
        Icon[] iconObjects = TextFactory.getIconObjects(map, IconFactory.removeFileExtension(icons));
        mBelowTextArray = TextFactory.getDisplayText(iconObjects);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final int GRID_1BY3 = 0;
        View rowView;
        if (mSession.getGridSize() == GRID_1BY3)
            rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_level_xadapter_3_icons, parent, false);
        else
            rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_level_xadapter_9_icons, parent, false);
        return new MainActivityAdapter.MyViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(final MainActivityAdapter.MyViewHolder holder, final int position) {
        final int MODE_PICTURE_ONLY = 1;
        ViewCompat.setAccessibilityDelegate(holder.menuItemLinearLayout,
                new TalkbackHints_RecyclerView());


        if (mSession.getPictureViewMode() == MODE_PICTURE_ONLY)
            holder.menuItemBelowText.setVisibility(View.INVISIBLE);

        holder.menuItemBelowText.setAllCaps(true);
        holder.menuItemBelowText.setText(mBelowTextArray[position]);

        String path = getIconPath(mContext);
        Bitmap iconBitmap = MemoryCache.getBitmapFromMemCache(icons[position]);

        if (iconBitmap != null) {
            holder.menuItemImage.setImageBitmap(iconBitmap);
        } else {
            glide.load(path + icons[position])
                    .into(holder.menuItemImage);
        }

        holder.menuItemLinearLayout.setContentDescription(mBelowTextArray[position]);
        holder.menuItemLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) mContext).tappedCategoryItemEvent(holder.menuItemLinearLayout, v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return icons.length;
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
