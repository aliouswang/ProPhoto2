package com.alious.pro.simple.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alious.pro.photo.library.adapter.DefaultNineImageAdapter;
import com.alious.pro.simple.model.Data;
import com.alious.pro.simple.model.Photo;
import com.alious.pro.simple.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by aliouswang on 16/9/8.
 */
public class FrescoRecyclerAdapter extends RecyclerView.Adapter<FrescoImageViewHolder>{
    private ArrayList<ArrayList<Data>> mMockList;

    public FrescoRecyclerAdapter() {
        mockData();
    }

    private void mockData() {
        mMockList = new ArrayList<>();
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 20; i ++) {
            final ArrayList<Data> datas = new ArrayList<>();
            int total = 3 + random.nextInt(7);
            if (i < 2) {
                total = 9;
            }else if (i == 10) {
                total = 1;
            }
            for (int j = 0; j < total; j ++) {
                Data data = new Data();
                data.setImage_url(Photo.images[j]);
                datas.add(data);
            }
            mMockList.add(datas);
        }
    }

    @Override
    public FrescoImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FrescoImageViewHolder holder = new FrescoImageViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_fresco, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(FrescoImageViewHolder holder, int position) {
        DefaultNineImageAdapter<Data> defaultNineImageAdapter =
                new DefaultNineImageAdapter(mMockList.get(position));
        holder.mNineFrescoGridViewGroup.setGridAdapter(defaultNineImageAdapter);
    }

    @Override
    public int getItemCount() {
        return mMockList.size();
    }
}
