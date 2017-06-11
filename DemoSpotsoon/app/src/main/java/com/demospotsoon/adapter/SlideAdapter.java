package com.demospotsoon.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demospotsoon.R;
import com.demospotsoon.model.DataItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SlideAdapter extends PagerAdapter {

    private List<DataItem> slideList;
    private Context _context;

    public SlideAdapter(Context context, List<DataItem> slideList) {
        this._context = context;
        this.slideList=slideList;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return slideList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View mainLayout = LayoutInflater.from(_context).inflate(R.layout.slide_item, view, false);
        ImageView myImage = (ImageView) mainLayout
                .findViewById(R.id.image);
        TextView tvTitle = (TextView) mainLayout.findViewById(R.id.tvTitle);
        TextView tvAuthor = (TextView) mainLayout.findViewById(R.id.tvAuthor);
        Picasso.with(_context).load(slideList.get(position).icon).placeholder(R.drawable.placeholder).fit().into(myImage);
        tvTitle.setText(slideList.get(position).title);
        tvAuthor.setText(slideList.get(position).author);
        view.addView(mainLayout, 0);
        return mainLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}