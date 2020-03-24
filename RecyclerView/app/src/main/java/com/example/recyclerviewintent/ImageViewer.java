package com.example.recyclerviewintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageViewer extends AppCompatActivity {

    private int[] images = {R.drawable.android_image_1, R.drawable.android_image_2, R.drawable.android_image_3, R.drawable.android_image_4, R.drawable.android_image_5, R.drawable.android_image_6, R.drawable.android_image_7, R.drawable.android_image_8};
    ImageView itemImage;
    TextView itemTitle;
    TextView itemDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageviewer);

        itemImage = findViewById(R.id.imageView);
        itemTitle = findViewById(R.id.textView);
        itemDetail = findViewById(R.id.desc);

        Intent intent = getIntent();
        itemTitle.setText(intent.getStringExtra("title"));
        itemDetail.setText(intent.getStringExtra("detail"));
        itemImage.setImageResource(images[intent.getIntExtra("image", 0)]);
    }
}
