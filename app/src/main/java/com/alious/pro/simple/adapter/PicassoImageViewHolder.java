package com.alious.pro.simple.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alious.pro.photo.library.widget.NinePicassoGridViewGroup;
import com.alious.pro.simple.R;

/**
 * Created by aliouswang on 16/9/8.
 */
public class PicassoImageViewHolder extends RecyclerView.ViewHolder{

    public NinePicassoGridViewGroup mNinePicassoGridViewGroup;

    public PicassoImageViewHolder(View itemView) {
        super(itemView);
        initView(itemView);
    }

    private void initView(View itemView) {
        mNinePicassoGridViewGroup = (NinePicassoGridViewGroup) itemView.findViewById(R.id.nine_picasso_gridview);
    }

}
