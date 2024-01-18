package com.acmegrade.majorproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import java.util.ArrayList;
import java.util.List;

public class Navdrawer extends AppCompatActivity {

    TextView name,email_name;
    private ListView productListView;
    private List<Product> productList;
    private ProductListAdapter adapter;
    private MyApplication myApp;

    databasehelper database;
    DrawerLayout drawer;
    ImageView menu;
    LinearLayout home,cart,accountinfo,signout;


    ImageButton img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navdrawer);
        name=(TextView)findViewById(R.id.name);
        email_name=(TextView)findViewById(R.id.email_name);
        drawer=(DrawerLayout) findViewById(R.id.drawerLayout);
        menu=(ImageView) findViewById(R.id.menu);
        home=(LinearLayout) findViewById(R.id.home);
        cart=(LinearLayout) findViewById(R.id.cart);
        accountinfo=(LinearLayout) findViewById(R.id.Accountinfo);
        signout=(LinearLayout) findViewById(R.id.signout);
        productListView = (ListView) findViewById(R.id.productListView);
        productList = new ArrayList<>();
//        img=(ImageButton)findViewById(R.id.img);
        myApp = (MyApplication) getApplication();

        database = new databasehelper(Navdrawer.this);
        Intent i=getIntent();
        String email=i.getStringExtra("Email");

        user User = database.getUserData(email);
        name.setText(User.getName());
        email_name.setText(User.getEmail());






        productList.add(new Product("DATA COMMUNICATION", 500, R.drawable.book2));
        productList.add(new Product("GATE", 2500, R.drawable.book3));
        productList.add(new Product("GATE 2020", 750, R.drawable.book4));
        productList.add(new Product("SYSTEM SOFTWARE", 1200, R.drawable.book5));
        productList.add(new Product("GATE CSE", 1250, R.drawable.book6));
        productList.add(new Product("INTERVIEW QUESTIONS", 1520, R.drawable.book7));
        productList.add(new Product("COMPUTER GRAPHICS", 1575, R.drawable.book8));
        productList.add(new Product("IELTS", 500, R.drawable.book9));
        productList.add(new Product("CLOUD COMPUTING", 1750, R.drawable.book10));
        productList.add(new Product("CLRS", 1500, R.drawable.book1));




        productListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product product = productList.get(position);
                myApp.addToCart(product); // Add to the cart in MyApplication
                Toast.makeText(Navdrawer.this, "Added to Cart: " + product.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        adapter = new ProductListAdapter(this, productList);
        productListView.setAdapter(adapter);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDrawer(drawer);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cartIntent = new Intent(Navdrawer.this, cartActivity.class);
                cartIntent.putExtra("Email",email); // Pass the email to cartActivity
                startActivity(cartIntent);
            }
        });

        accountinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent accountInfoIntent = new Intent(Navdrawer.this, accountinfoactivity.class);
                accountInfoIntent.putExtra("Email", email);
                startActivity(accountInfoIntent);
            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Navdrawer.this, "Logged out", Toast.LENGTH_LONG).show();
                redirectActivity(Navdrawer.this, MainActivity2.class);
            }
        });
    }


    public static void openDrawer(DrawerLayout drawer){
        drawer.openDrawer(GravityCompat.START);
    }
    public static void closeDrawer(DrawerLayout drawer){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    public static void redirectActivity(Activity activity,Class secondActivity ){
        Intent intent=new Intent(activity,secondActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }
    @Override
    protected void  onPause(){
        super.onPause();
        closeDrawer(drawer);
    }
}