package com.acmegrade.majorproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ProductListAdapter extends BaseAdapter {

    private Context context;
    private List<Product> productList;

    public ProductListAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.product_list_item, null);
        }

        ImageButton addToCartButton;
        ImageView productImage = convertView.findViewById(R.id.productImage);
        TextView productName = convertView.findViewById(R.id.productName);
        TextView productPrice = convertView.findViewById(R.id.productPrice);
        addToCartButton = convertView.findViewById(R.id.addToCartButton);

        final Product product = productList.get(position);

        productImage.setImageResource(product.getImageResource());
        productName.setText(product.getName());
        productPrice.setText("$" + product.getPrice());

        // Handle click for the "Add to Cart" button
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApplication myApp = (MyApplication) context.getApplicationContext();
                myApp.addToCart(product);
                Toast.makeText(context, "Added to Cart: " + product.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}

