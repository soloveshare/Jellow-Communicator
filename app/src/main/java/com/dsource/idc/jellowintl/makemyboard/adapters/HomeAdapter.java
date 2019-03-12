package com.dsource.idc.jellowintl.makemyboard.adapters;


import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.dsource.idc.jellowintl.GlideApp;
import com.dsource.idc.jellowintl.R;
import com.dsource.idc.jellowintl.models.JellowIcon;
import com.dsource.idc.jellowintl.utility.SessionManager;

import java.io.File;
import java.util.List;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import static com.dsource.idc.jellowintl.MainActivity.isNotchDevice;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {


    public List<JellowIcon> data;
    public Context mContext;
    private onDoubleTapListener onDoubleTapListener;
    private OnItemSelectListener onItemSelectListener;
    int GridSize;
    public int selectedPosition=-1;
    public int expIconPos = -1;
    public int highlightedIcon = -1;

    public HomeAdapter(List<JellowIcon> data, Context context, int gridSize) {
        this.data = data;
        mContext=context;
        this.GridSize=gridSize;
    }

    @Override
    public void onBindViewHolder(HomeAdapter.ViewHolder holder, int position) {

        JellowIcon thisIcon = data.get(position);
        holder.iconTitle.setText(thisIcon.IconTitle);
        setMenuImageBorder(holder.backGround,false,-1);
        if(selectedPosition!=-1) highlightedIcon=-1;
        if(position==this.selectedPosition) setMenuImageBorder(holder.backGround,true,expIconPos);
        else if(highlightedIcon==position) setMenuImageBorder(holder.backGround,true,100);




        if(thisIcon.parent0==-1)
        {
            File en_dir = mContext.getDir(SessionManager.ENG_IN, Context.MODE_PRIVATE);
            String path = en_dir.getAbsolutePath() + "/boardicon";
            GlideApp.with(mContext)
                    .load(path+"/"+ thisIcon.IconDrawable+".png")
                    .apply(RequestOptions.
                            circleCropTransform())
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(false)
                    .centerCrop()
                    .dontAnimate()
                    .into(holder.iconImage);
            holder.iconImage.setBackground(mContext.getResources().getDrawable(R.drawable.icon_back_grey));
        }
        else
        {
            File en_dir = mContext.getDir(SessionManager.ENG_IN, Context.MODE_PRIVATE);
            String path = en_dir.getAbsolutePath() + "/drawables";
            GlideApp.with(mContext)
                    .load(path+"/"+ thisIcon.IconDrawable+".png")
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(false)
                    .centerCrop()
                    .dontAnimate()
                    .into(holder.iconImage);
        }





    }


    public interface onDoubleTapListener {
        void onItemDoubleTap(View view, int position);
    }
    public interface OnItemSelectListener {
        void onItemSelected(View view, int position);
    }



    public void setOnDoubleTapListner(final onDoubleTapListener mItemClickListener) {
        this.onDoubleTapListener = mItemClickListener;
    }

    public void setOnItemSelectListner(final HomeAdapter.OnItemSelectListener mItemClickListener) {
        this.onItemSelectListener = mItemClickListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final int GRID_1BY1 = 1, GRID_1BY2 = 2, GRID_1BY3 = 3;
        View rowView;
        if(isNotchDevice(mContext) && GridSize == GRID_1BY1) {
            rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_level_xadapter_1_icon_notch, parent, false);
        }else if(!isNotchDevice(mContext) && GridSize == GRID_1BY1){
            rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_level_xadapter_1_icon, parent, false);
        }else if(isNotchDevice(mContext) && GridSize == GRID_1BY2){
            rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_level_xadapter_2_icons_notch, parent, false);
        }else if(!isNotchDevice(mContext) && GridSize == GRID_1BY2){
            rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_level_xadapter_2_icons, parent, false);
        }else if(isNotchDevice(mContext) && GridSize == GRID_1BY3){
            rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_level_xadapter_3_icons_notch, parent, false);
        }else if(!isNotchDevice(mContext) && GridSize == GRID_1BY3){
            rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_level_xadapter_3_icons, parent, false);
        }else if(isNotchDevice(mContext)){
            rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_level_xadapter_9_icons_notch, parent, false);
        }else{
            rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_level_xadapter_9_icons, parent, false);
        }
        return new ViewHolder(rowView);

        /*switch (GridSize){
            case 1: //1 by 1
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.custom_layout_1x1_icons, parent, false);
                break;
            case 2: // 1 by 2
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.custom_layout_1x2_icons, parent, false);
                break;
            case 3: // 1 by 3
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.custom_layout_3_icons, parent, false);break;
            case 6:// 3 by 3
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.custom_layout_9_icons, parent, false);
            default:
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.custom_layout_9_icons, parent, false);
        }
        return new ViewHolder(itemView);*/
    }

    private void setMenuImageBorder(GradientDrawable gd, boolean setBorder,int pos) {

        if(setBorder){
            // mActionBtnClickCount = 0, brown color border is set.
                // mFlgImage define color of border.
            if(pos==-1) gd.setColor(ContextCompat.getColor(mContext,R.color.colorSelect));
            else switch (pos) {
                    case 0: gd.setColor(ContextCompat.getColor(mContext,R.color.colorLike)); break;
                    case 1: gd.setColor(ContextCompat.getColor(mContext,R.color.colorYes)); break;
                    case 2: gd.setColor(ContextCompat.getColor(mContext,R.color.colorMore)); break;
                    case 3: gd.setColor(ContextCompat.getColor(mContext,R.color.colorDontLike)); break;
                    case 4: gd.setColor(ContextCompat.getColor(mContext,R.color.colorNo)); break;
                    case 5: gd.setColor(ContextCompat.getColor(mContext,R.color.colorLess)); break;
                case 100:   gd.setColor(ContextCompat.getColor(mContext,R.color.search_highlight)); break;
                }
        } else
            gd.setColor(ContextCompat.getColor(mContext,android.R.color.transparent));
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public TextView iconTitle;
        public ImageView iconImage;
        public GradientDrawable backGround;

        public ViewHolder(final View v) {
            super(v);
            iconImage=v.findViewById(R.id.icon1);
            iconTitle=v.findViewById(R.id.te1);
            backGround = (GradientDrawable)v.findViewById(R.id.borderView).getBackground();
            v.findViewById(R.id.delete_icons).setVisibility(View.VISIBLE);
            v.findViewById(R.id.edit_icons).setVisibility(View.VISIBLE);
            v.findViewById(R.id.delete_icons).bringToFront();
            v.findViewById(R.id.edit_icons).bringToFront();
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(getAdapterPosition()!=selectedPosition)
                onItemSelectListener.onItemSelected(view,getAdapterPosition());
            else
                onDoubleTapListener.onItemDoubleTap(view,getAdapterPosition());

        }
    }


    @Override
    public int getItemCount() {
        return data.size();
    }



}
