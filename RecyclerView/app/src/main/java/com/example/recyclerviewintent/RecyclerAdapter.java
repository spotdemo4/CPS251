package com.example.recyclerviewintent;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.Random;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    Data data = new Data();

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemTitle;
        TextView itemDetail;

        ViewHolder(View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_image);
            itemTitle = itemView.findViewById(R.id.item_title);
            itemDetail = itemView.findViewById(R.id.item_detail);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(v.getContext(), ImageViewer.class);
                    intent.putExtra("title", itemTitle.getText());
                    intent.putExtra("detail", itemDetail.getText());
                    intent.putExtra("image", Integer.parseInt(itemImage.getContentDescription().toString()));
                    v.getContext().startActivity(intent);
                    //Snackbar.make(v, "Click detected on item " + position, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.itemTitle.setText(data.getTitle());
        viewHolder.itemDetail.setText(data.getDetails());
        viewHolder.itemImage.setImageResource(data.getImage());
        viewHolder.itemImage.setContentDescription(String.valueOf(data.getLastNum()));
    }

    @Override
    public int getItemCount() {
        return data.itemCount();
    }
}

class Data {

    Random rand = new Random();
    int lastNum = 0;
    private String[] titles = {"Chapter One", "Chapter Two", "Chapter Three", "Chapter Four", "Chapter Five", "Chapter Six", "Chapter Seven", "Chapter Eight"};
    private String[] details = {"Item one details", "Item two details", "Item three details", "Item four details", "Item five details", "Item six details", "Item seven details", "Item eight details"};
    private int[] images = {R.drawable.android_image_1, R.drawable.android_image_2, R.drawable.android_image_3, R.drawable.android_image_4, R.drawable.android_image_5, R.drawable.android_image_6, R.drawable.android_image_7, R.drawable.android_image_8};

    public String getTitle(){
        int num = rand.nextInt(titles.length);
        return titles[num];
    }

    public String getDetails(){
        int num = rand.nextInt(details.length);
        return details[num];
    }

    public int getImage(){
        int num = rand.nextInt(images.length);
        lastNum = num;
        return images[num];
    }

    public int getLastNum(){
        return lastNum;
    }

    public int itemCount(){
        return titles.length;
    }

}
