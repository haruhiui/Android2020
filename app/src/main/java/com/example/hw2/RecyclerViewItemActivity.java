package com.example.hw2;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

//import com.example.hw2.recycler.MyAdapter;

public class RecyclerViewItemActivity extends AppCompatActivity {

    private static final String TAG = "TAG - item page";
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_itempage);
        Log.i(TAG, "RecyclerViewItemPageActivity onCreate");

        tv = findViewById(R.id.title);
        Intent recIntent = getIntent();
        tv.setText(recIntent.getStringExtra("title") + " " + recIntent.getStringExtra("value"));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "RecyclerViewItemPageActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "RecyclerViewItemPageActivity onResume");
    }
}
