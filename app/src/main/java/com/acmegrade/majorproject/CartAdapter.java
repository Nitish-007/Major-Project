package com.acmegrade.majorproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CartAdapter extends BaseAdapter {

    private Context context;
    private List<Product> cart;

    public CartAdapter(Context context, List<Product> cart) {
        this.context = context;
        this.cart = cart;
    }

    @Override
    public int getCount() {
        return cart.size();
    }

    @Override
    public Object getItem(int position) {
        return cart.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater;
           inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.cart_list_item, null);
        }
        ImageView imageView;
        TextView nameTextView;
        TextView priceTextView;

        imageView = convertView.findViewById(R.id.cartProductImage);
        nameTextView = convertView.findViewById(R.id.cartProductName);
        priceTextView = convertView.findViewById(R.id.cartProductPrice);

        Product product = cart.get(position);
        imageView.setImageResource(product.getImageResource());
        nameTextView.setText(product.getName());
        priceTextView.setText("$" + product.getPrice());

        return convertView;
    }
}