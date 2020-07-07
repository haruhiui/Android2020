package com.example.hw2;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hw2.recycler.LinearItemDecoration;
import com.example.hw2.recycler.MyAdapter;
import com.example.hw2.recycler.TestData;
import com.example.hw2.recycler.TestDataSet;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

public class RecyclerViewActivity extends AppCompatActivity implements MyAdapter.IOnItemClickListener, View.OnClickListener {

    private static final String TAG = "TAG";

    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        Log.i(TAG, "RecyclerViewActivity onCreate");
        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recycler);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MyAdapter(TestDataSet.getData());
        mAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mAdapter);
        LinearItemDecoration itemDecoration = new LinearItemDecoration(Color.BLUE);
//        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//        DefaultItemAnimator animator = new DefaultItemAnimator();
//        animator.setAddDuration(3000);
//        recyclerView.setItemAnimator(animator);

        findViewById(R.id.btn_fensi).setOnClickListener(this);
        findViewById(R.id.btn_atwode).setOnClickListener(this);
        findViewById(R.id.btn_zan).setOnClickListener(this);
        findViewById(R.id.btn_pinglun).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "RecyclerViewActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "RecyclerViewActivity onResume");
    }


    @Override
    public void onItemCLick(int position, TestData data) {
//        Toast.makeText(RecyclerViewActivity.this, "点击了第" + position + "条", Toast.LENGTH_SHORT).show();
//        mAdapter.addData(position + 1, new TestData("新增头条", "0w"));
        Intent itemIntent = new Intent(this, RecyclerViewItemActivity.class);
        itemIntent.putExtra("title", data.getTitle());
        itemIntent.putExtra("value", data.getHot());
        startActivity(itemIntent);
    }

    @Override
    public void onItemLongCLick(int position, TestData data) {
//        Toast.makeText(RecyclerViewActivity.this, "长按了第" + position + "条", Toast.LENGTH_SHORT).show();
        int cnt = countView(findViewById(R.id.linear_recycler));
        Toast.makeText(RecyclerViewActivity.this, "共有" + String.valueOf(cnt) + "个view", Toast.LENGTH_SHORT).show();
        mAdapter.removeData(position);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, RecyclerViewItemActivity.class);

        switch(v.getId()) {
            case R.id.btn_fensi:
                intent.putExtra("title", "粉丝");
                intent.putExtra("value", "13");
                break;
            case R.id.btn_zan:
                intent.putExtra("title", "赞");
                intent.putExtra("value", "14");
                break;
            case R.id.btn_atwode:
                intent.putExtra("title", "@我的");
                intent.putExtra("value", "15");
                break;
            case R.id.btn_pinglun:
                intent.putExtra("title", "评论");
                intent.putExtra("value", "16");
                break;
        }

        startActivity(intent);
    }

    public int countView(View root) {
        int cnt = 0;

        if (null == root) {
            return 0;
        }
        if (root instanceof ViewGroup) {
            cnt++;
            for (int i = 0; i < ((ViewGroup)root).getChildCount(); i++) {
                View view = ((ViewGroup)root).getChildAt(i);
                if (view instanceof ViewGroup) {
                    cnt += countView(view);
                } else {
                    cnt++;
                }
            }
        }

        return cnt;
    }

}