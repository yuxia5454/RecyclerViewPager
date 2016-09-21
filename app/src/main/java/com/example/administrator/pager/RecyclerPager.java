package com.example.administrator.pager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weiyuxia on 2016/9/7.
 */

public class RecyclerPager extends ViewPager {
    List<View> mListViews = new ArrayList<>();
    RefreshListener refreshListener;
    int count;
    RecyclerViewPagerAdapter adapter;

    public RecyclerPager(Context context) {
        super(context);
    }

    public RecyclerPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    interface RefreshListener {
        void onRefresh(View v, int location);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item, false);
    }

    public void setItemCount(int count) {
        this.count = count;
    }

    public List<View> init(Context context, final int resId, RefreshListener refreshListener, int count) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        View v1 = inflater.inflate(resId, null);
        mListViews.add(v1);
        View v2 = inflater.inflate(resId, null);
        mListViews.add(v2);
        View v3 = inflater.inflate(resId, null);
        mListViews.add(v3);

        this.refreshListener = refreshListener;
        this.count = count;
        adapter = new RecyclerViewPagerAdapter();
        this.setAdapter(adapter);
        this.setCurrentItem(0);

        return mListViews;
    }


    class RecyclerViewPagerAdapter extends PagerAdapter {


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//           container.removeView(mListViews.get(position%3));//删除页卡
            Log.e("pager", "remove" + position + "");
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {  //这个方法用来实例化页卡
            Log.e("pager", position + "");

            container.removeView(mListViews.get(position % 3));
            container.addView(mListViews.get(position % 3), 0);//添加页卡
            refreshListener.onRefresh(mListViews.get(position % 3), position);
            return mListViews.get(position % 3);
        }

        @Override
        public int getCount() {
            return count;//返回页卡的数量
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;//官方提示这样写
        }

    }
}
