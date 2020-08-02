package com.example.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.EditText;

import android.content.Context;

// import java.lang.String;

public class MainActivity extends AppCompatActivity {

    int cnt = 0;
    String[] list={"a22", "a33", "aidang", "beierfasite", "beikaluolaina", "biaoqiang", "guanghui", "haiwangxing", "lafei", "lingbo"};

    public int  getResource(String imageName) {
        Context ctx = getBaseContext();
        int resId = getResources().getIdentifier(imageName, "drawable", ctx.getPackageName());
        return resId;
    }

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button find = findViewById(R.id.find);
        Button next = findViewById(R.id.next);
        final TextView title = findViewById(R.id.tv_title);
        final ImageView image = findViewById(R.id.imageView);
        final EditText findImg = findViewById(R.id.editText);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cnt += 1;
                title.setText(list[cnt % 10]);
                image.setImageResource(getResource(list[cnt % 10]));
                Log.i("MainActivity", "click next");
            }
        });
        
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = findImg.getText().toString();
                title.setText(str);
                image.setImageResource(getResource(str));
                Log.i("MainActivity", "click find");
            }
        });

    }


}
