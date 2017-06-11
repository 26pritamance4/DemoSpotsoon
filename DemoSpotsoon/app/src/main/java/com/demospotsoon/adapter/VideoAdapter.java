package com.demospotsoon.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demospotsoon.R;
import com.demospotsoon.model.DataItem;
import com.squareup.picasso.Picasso;

import java.util.List;


public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder>{

    Context _context;
    List<DataItem> dataList;

    public VideoAdapter(Context context, List<DataItem> list){
        this._context = context;
        this.dataList = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivIcon;
        TextView tvDescription, tvTitle, tvTime;


        public ViewHolder(View v) {
            super(v);
            ivIcon = (ImageView) v.findViewById(R.id.ivIcon);
            tvDescription = (TextView) v.findViewById(R.id.tvDesc);
            tvTitle = (TextView) v.findViewById(R.id.tvTitle);
            tvTime = (TextView) v.findViewById(R.id.tvTime);
        }
    }

    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView;
        ViewHolder vh;
        rowView = LayoutInflater.from(_context).inflate(R.layout.video_list_item, parent, false);
        vh = new ViewHolder(rowView);
        return vh;
    }

    @Override
    public void onBindViewHolder(VideoAdapter.ViewHolder holder, int position) {
        DataItem item = dataList.get(position);
        performOps(holder, item);
    }

    private void performOps(ViewHolder holder, DataItem item){
        Picasso.with(_context).load(item.icon).placeholder(R.drawable.placeholder).fit().into(holder.ivIcon);
        holder.tvTitle.setText(item.title);
        holder.tvDescription.setText(item.description);
        holder.tvTime.setText("18 HOURS AGO");
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
