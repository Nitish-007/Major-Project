package com.acmegrade.majorproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity2 extends AppCompatActivity {

    TextInputLayout tl1,tl2;
    TextView t;
    EditText t1,t2;
    Button b;

    private Validation inputvalidation;
    private databasehelper database;

    user User;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        b=(Button)findViewById(R.id.button7) ;
        Intent i=getIntent();
        t=(TextView) findViewById(R.id.textView11);
        t1=(EditText) findViewById(R.id.editTextText7);
        t2=(EditText)findViewById(R.id.editTextTextPassword2) ;
        tl1=(TextInputLayout)findViewById(R.id.personalemail2);
        tl2=(TextInputLayout)findViewById(R.id.password2);

        database=new databasehelper(MainActivity2.this);
        inputvalidation=new Validation(MainActivity2.this);
        User=new user();

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!inputvalidation.isInputEditTextFilled(t1, tl1, "Enter Valid Email")){
                    return;
                }
                if(!inputvalidation.isInputEditTextEmail(t1,tl1,"Enter Valid Email")){
                    return;
                }
                if(!inputvalidation.isInputEditTextFilled(t2, tl2, "Enter Password")){
                    return;
                }

                if(database.checkUser(t1.getText().toString().trim(),t2.getText().toString().trim())){

                Intent accountintent=new Intent(MainActivity2.this,Navdrawer.class);
                    accountintent.putExtra("Email",t1.getText().toString().trim());
                    emptyInputEditText();
                    startActivity(accountintent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Wrong Email or password", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void emptyInputEditText() {
        t1=null;
        t2=null;
    }
}