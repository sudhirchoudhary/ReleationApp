package com.example.releation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.releation.R;
import com.example.releation.model.Node;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private final List<Node> mList;
    private final OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public MainAdapter(List<Node> mList, OnItemClickListener listener) {
        this.mList = mList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
        String typeCheck = mList.get(position).data.properties.permalink;
        if (typeCheck != null) {
            holder.tvTitle.setText(String.valueOf(mList.get(position).data.properties.name));
            if (typeCheck.contains("person")) {
                holder.tvType.setText("Person");
            } else {
                holder.tvType.setText("Organisation");
            }
        }

        holder.itemView.setOnClickListener(v -> listener.onItemClick(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public TextView tvType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvType = itemView.findViewById(R.id.tvType);
        }
    }
}
