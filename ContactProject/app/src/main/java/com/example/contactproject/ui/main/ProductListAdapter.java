package com.example.contactproject.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.contactproject.R;
import androidx.recyclerview.widget.RecyclerView;
import com.example.contactproject.Product;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private int productItemLayout;
    private List<Product> productList;
    onItemClickListener onItemClickListener;

    public void setOnItemClickListener(ProductListAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface onItemClickListener{
        void onClick(String str);//pass your object types.
    }

    public ProductListAdapter(int layoutId) {
        productItemLayout = layoutId;
    }

    public void setProductList(List<Product> products) {
        productList = products;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return productList == null ? 0 : productList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(productItemLayout, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {
        TextView item = holder.item;
        TextView number = holder.number;
        ImageView img = holder.img;
        item.setText(productList.get(listPosition).getName());
        number.setText(productList.get(listPosition).getQuantity());
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onItemClickListener.onClick(productList.get(listPosition).getName());
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView item;
        TextView number;
        ImageView img;
        ViewHolder(View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.contactName);
            number = itemView.findViewById(R.id.contactNumber);
            img = itemView.findViewById(R.id.item_image);

        }
    }
}
