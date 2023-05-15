package com.zhinengfeiyu.nestedrecyclerviewdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class TestInnerAdapter extends RecyclerView.Adapter<TestInnerAdapter.InnerViewHolder> {

    private final List<String> mData;

    TestInnerAdapter(@NonNull List<String> data) {
        mData = data;
    }

    public void setData(List<String> newData) {
        mData.clear();
        if (newData != null) {
            mData.addAll(newData);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public InnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inner, parent, false);
        return new InnerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerViewHolder holder, int position) {
        holder.tvContent.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class InnerViewHolder extends RecyclerView.ViewHolder {
        TextView tvContent;

        public InnerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tv_content);
        }
    }
}
