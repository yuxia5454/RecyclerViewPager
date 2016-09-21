package com.example.administrator.pager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerPager lastViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    void initViews() {
        lastViewPager = (RecyclerPager) findViewById(R.id.lastViewPager);
        List<View> tList = lastViewPager.init(this, R.layout.pager, new RecyclerPager.RefreshListener() {
            @Override
            public void onRefresh(View v, int location) {
                Log.e("pager", "recyclerview" + location);
//                ((RecyclerView)v).setText(location+"");
                if (location % 2 == 1)
                    v.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                else
                    v.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
        }, 10);

        //测试跳页
        tList.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastViewPager.setCurrentItem(9);
            }
        });

    }

}
