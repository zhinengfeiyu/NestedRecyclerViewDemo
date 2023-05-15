package com.zhinengfeiyu.nestedrecyclerviewdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TestOuterAdapter extends RecyclerView.Adapter<TestOuterAdapter.OuterViewHolder> {

    private final List<InnerData> mInnerDataList;
    private final RecyclerView mOuterRecyclerView;

    TestOuterAdapter(@NonNull List<InnerData> innerDataList, @NonNull RecyclerView recyclerView) {
        mInnerDataList = innerDataList;
        mOuterRecyclerView = recyclerView;
    }

    @NonNull
    @Override
    public OuterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_outer, parent, false);
        return new OuterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OuterViewHolder holder, int position) {
        RecyclerView rcvInner = holder.rcvNested;
        if (rcvInner.getLayoutManager() == null) {
            rcvInner.setLayoutManager(new LinearLayoutManager(rcvInner.getContext()));
            rcvInner.setAdapter(new TestInnerAdapter(mInnerDataList.get(position).innerDataList));
            rcvInner.getLayoutParams().height = mOuterRecyclerView.getMeasuredHeight();
        } else {
            ((TestInnerAdapter) rcvInner.getAdapter()).setData(mInnerDataList.get(position).innerDataList);
        }
    }

    @Override
    public int getItemCount() {
        return mInnerDataList.size();
    }

    static class OuterViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rcvNested;

        public OuterViewHolder(@NonNull View itemView) {
            super(itemView);
            rcvNested = (RecyclerView) itemView;
        }
    }
}
