package com.example.hw8;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhotoActivity extends AppCompatActivity {

    private String takeImagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takeImagePath = getOutputMediaPath();
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                FileProvider.getUriForFile(
                        PhotoActivity.this,
                        "com.example.hw8.fileprovider",
                        new File(takeImagePath)));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, Constants.REQUEST_CODE_TAKE_PHOTO);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.REQUEST_CODE_TAKE_PHOTO && resultCode == RESULT_OK) {

            ImageView imageView = findViewById(R.id.photo_image);
            int targetWidth = imageView.getWidth();
            int targetHeight = imageView.getHeight();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(takeImagePath, options);

            int photoWidth = options.outWidth;
            int photoHeight = options.outHeight;
            int scaleFactor = Math.min(photoWidth / targetWidth, photoHeight / targetHeight);
            options.inJustDecodeBounds = false;
            options.inSampleSize = scaleFactor;

            Bitmap bitmap = BitmapFactory.decodeFile(takeImagePath, options);
            imageView.setImageBitmap(bitmap);
        } else {
            finish();
        }
    }

    private String getOutputMediaPath() {
        File mediaStorageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile = new File(mediaStorageDir, "IMG_" + timeStamp + ".jpg");
        if (!mediaFile.exists()) {
            mediaFile.getParentFile().mkdirs();
        }
        return mediaFile.getAbsolutePath();
    }
}
