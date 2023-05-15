package com.zhinengfeiyu.nestedrecyclerviewdemo;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RcvTestActivity extends AppCompatActivity {

    private RecyclerView mOuterRecyclerView;
    public static int OUT_RCV_HEIGHT;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rcv_test);
        mOuterRecyclerView = findViewById(R.id.rcv_outer);
        mOuterRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                OUT_RCV_HEIGHT = mOuterRecyclerView.getHeight();
                mOuterRecyclerView.setAdapter(new TestOuterAdapter(getData(), mOuterRecyclerView));
            }
        }, 1000);
    }

    private List<InnerData> getData() {
        List<InnerData> data = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            InnerData innerData = new InnerData();
            for (int j = 0; j < 15; j++) {
                innerData.innerDataList.add("这是第" + (i + 1) + "个列表的第" + (j + 1) + "个item");
            }
            innerData.innerDataList.add("这是第" + (i + 1) + "个列表的最后一个item");
            data.add(innerData);
        }
        return data;
    }
}
