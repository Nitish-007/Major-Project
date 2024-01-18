package com.acmegrade.majorproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class cartActivity extends AppCompatActivity {

    private ListView cartListView;
    private CartAdapter cartAdapter;
    private Button placeOrderButton;
    private List<Product> cartlist;
    DrawerLayout drawer;

    TextView name,email_name;
    ImageView menu;
    LinearLayout home,cart,accountinfo,signout;
    Intent i;
    databasehelper database;
    user User;

    private MyApplication myApp; // Add this


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        i=getIntent();
        drawer=(DrawerLayout) findViewById(R.id.drawerLayout);
        name=(TextView)findViewById(R.id.name);
        email_name=(TextView)findViewById(R.id.email_name);
        menu=(ImageView) findViewById(R.id.menu);
        home=(LinearLayout) findViewById(R.id.home);
        cart=(LinearLayout)findViewById(R.id.cart);
        accountinfo=(LinearLayout)findViewById(R.id.Accountinfo);
        signout=(LinearLayout)findViewById(R.id.signout);
        // Initialize views
        cartListView = (ListView) findViewById(R.id.cartListView);
        placeOrderButton = (Button)findViewById(R.id.placeOrderButton);
        myApp = (MyApplication) getApplication();

        database = new databasehelper(cartActivity.this);
        Intent i=getIntent();
        String email=i.getStringExtra("Email");

        User = database.getUserData(email);
        name.setText(User.getName());
        email_name.setText(User.getEmail());



        // Initialize the cart
        cartlist = new ArrayList<>();

        // Retrieve cart data from MyApplication
        List<Product> cartData = myApp.getCart();


        if (cartData != null) {
            cartlist.addAll(cartData);
        }

        // Set up the adapter
        cartAdapter = new CartAdapter(cartActivity.this, cartlist);
        cartListView.setAdapter(cartAdapter);

        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cartlist.isEmpty()) {

                    Toast.makeText(cartActivity.this, "Cart is empty", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(cartActivity.this, "Order Placed", Toast.LENGTH_SHORT).show();

                    myApp.clearCart();

                    // Clear the cart list in this activity
                    cartlist.clear();
                    cartAdapter.notifyDataSetChanged();
                }
            }
        });


        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDrawer(drawer);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent homeIntent = new Intent(cartActivity.this, Navdrawer.class);
                homeIntent.putExtra("Email",email); // Pass the email to cartActivity
                startActivity(homeIntent);
            }
        });

        accountinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent accountinfoIntent = new Intent(cartActivity.this,accountinfoactivity.class);
                accountinfoIntent.putExtra("Email",email); // Pass the email to cartActivity
                startActivity(accountinfoIntent);
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
            }
        });
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(cartActivity.this,"Logged out", Toast.LENGTH_LONG).show();
                redirectActivity(cartActivity.this, MainActivity2.class);
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

    public static void redirectActivity(Activity activity, Class secondActivity ){
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