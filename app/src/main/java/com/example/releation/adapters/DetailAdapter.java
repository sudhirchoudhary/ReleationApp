package com.example.releation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.releation.R;
import com.example.releation.Util;
import com.example.releation.model.Detail;
import com.example.releation.model.Node;

import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {
    private final List<Detail> mList;

    public DetailAdapter(List<Detail> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public DetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_details_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DetailAdapter.ViewHolder holder, int position) {
        Node node = Util.getAllNodes().get(mList.get(position).targetNodeId);
        assert node != null;
        holder.tvDetailShowName.setText(node.data.properties.name);
        holder.tvDetailShowType.setText(mList.get(position).type);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvDetailShowType;
        public TextView tvDetailShowName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDetailShowName = itemView.findViewById(R.id.tvDetailShowName);
            tvDetailShowType = itemView.findViewById(R.id.tvDetailShowType);
        }
    }
}
