package com.example.releation.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.releation.MyApplication;
import com.example.releation.R;
import com.example.releation.Util;
import com.example.releation.adapters.MainAdapter;
import com.example.releation.model.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements MainAdapter.OnItemClickListener {
    private RecyclerView rvMain;
    private MainAdapter adapter;
    private List<Node> mList;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        populateList();
        setTheme(R.style.Theme_Releation);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = findViewById(R.id.progressBar);
        showProgress();

        rvMain = findViewById(R.id.rvMain);

        rvMain.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rvMain.setLayoutManager(new LinearLayoutManager(this));
    }

    private void populateList() {
        MyApplication.getApplication();
        MyApplication.fetchData().observe(this, dataFetched -> {
            if(dataFetched) {
                hideProgress();
                mList = new ArrayList<>();
                for(Map.Entry<Integer, Node> it: Util.getAllNodes().entrySet()) {
                    mList.add(it.getValue());
                }
                adapter = new MainAdapter(mList, this);
                rvMain.setAdapter(adapter);
            }
        });
    }

    private void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        /*Node node = new Node();
        node = mList.get(position);*/
        intent.putExtra("id", mList.get(position).id);
        startActivity(intent);
    }
}