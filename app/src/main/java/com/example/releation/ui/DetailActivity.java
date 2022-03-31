package com.example.releation.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.releation.R;
import com.example.releation.Util;
import com.example.releation.adapters.DetailAdapter;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {
    private TextView tvName, tvCategory;
    private RecyclerView rvDetail;
    private int sourceNodeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent != null) {
            sourceNodeId = intent.getIntExtra("id", 0);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvName = findViewById(R.id.tvName);
        tvCategory = findViewById(R.id.tvCategory);
        rvDetail = findViewById(R.id.rvDetails);
        rvDetail.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rvDetail.setLayoutManager(new LinearLayoutManager(this));
        setUpUi();
    }

    void setUpUi() {
        try {
            String category = Objects.requireNonNull(Util.getAllNodes().get(sourceNodeId)).data.categories.get(0);
            tvCategory.setText(category);
        } catch (Exception e) {
            tvCategory.setVisibility(View.GONE);
            e.printStackTrace();
        } finally {
            String name = Objects.requireNonNull(Util.getAllNodes().get(sourceNodeId)).data.properties.name;
            tvName.setText(name);
        }
        DetailAdapter mAdapter = new DetailAdapter(Util.getAllRelatedNodes(sourceNodeId));
        rvDetail.setAdapter(mAdapter);
    }
}