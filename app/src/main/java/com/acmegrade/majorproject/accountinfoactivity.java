package com.acmegrade.majorproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.textfield.TextInputEditText;

public class accountinfoactivity extends AppCompatActivity {

    DrawerLayout drawer;
    ImageView menu;
    TextView name,email_name;

    LinearLayout home, cart, accountinfo, signout;
    TextInputEditText usernameEditText, emailEditText;
    databasehelper database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountinfoactivity);
        Intent g=getIntent();
        name=(TextView)findViewById(R.id.name);
        email_name=(TextView)findViewById(R.id.email_name);
        drawer=(DrawerLayout) findViewById(R.id.drawerLayout);
        menu=(ImageView) findViewById(R.id.menu);
        home=(LinearLayout) findViewById(R.id.home);
        cart=(LinearLayout) findViewById(R.id.cart);
        accountinfo=(LinearLayout) findViewById(R.id.Accountinfo);
        signout=(LinearLayout) findViewById(R.id.signout);;
        usernameEditText = (TextInputEditText) findViewById(R.id.usernameEditText);
        emailEditText = (TextInputEditText) findViewById(R.id.emailEditText);



        database = new databasehelper(accountinfoactivity.this);
        Intent i=getIntent();
        String email=i.getStringExtra("Email");

        user User = database.getUserData(email);
        name.setText(User.getName());
        email_name.setText(User.getEmail());

        // Set user data to TextInputEditText fields
        usernameEditText.setText(User.getName());
        emailEditText.setText(User.getEmail());

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDrawer(drawer);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(accountinfoactivity.this, Navdrawer.class);
                homeIntent.putExtra("Email",email); // Pass the email to cartActivity
                startActivity(homeIntent);
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent accountinfoIntent = new Intent(accountinfoactivity.this,cartActivity.class);
                accountinfoIntent.putExtra("Email",email); // Pass the email to cartActivity
                startActivity(accountinfoIntent);
            }
        });

        accountinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(accountinfoactivity.this, "Logged out", Toast.LENGTH_LONG).show();
                redirectActivity(accountinfoactivity.this, MainActivity2.class);
            }
        });
    }

    public static void openDrawer(DrawerLayout drawer) {
        drawer.openDrawer(GravityCompat.START);
    }

    public static void closeDrawer(DrawerLayout drawer) {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    public static void redirectActivity(Activity activity, Class secondActivity) {
        Intent intent = new Intent(activity, secondActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawer);
    }
}
