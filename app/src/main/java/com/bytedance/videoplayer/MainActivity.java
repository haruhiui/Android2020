package com.bytedance.videoplayer;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = findViewById(R.id.imageView);
        String url = "https://s3.pstatp.com/toutiao/static/img/logo.271e845.png";

        Glide.with(this)
                .load(url)
//                .placeholder(R.drawable.loading_picture)
                .error(R.drawable.error_picture)
                .fallback(R.drawable.loading_picture)
//                .apply(new RequestOptions().centerCrop().bitmapTransform(new RoundedCorners(30)))
                .transition(withCrossFade())
                .into(imageView);

        findViewById(R.id.btn_video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, IJKPlayerActivity.class);
                startActivity(intent);
            }
        });
    }
}
