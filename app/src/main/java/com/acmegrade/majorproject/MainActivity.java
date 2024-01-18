package com.acmegrade.majorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    TextInputLayout tl1,tl2,pas,conpas;//t1=name,tl2=email,pas=password,conpas=confirm password

    TextView s1;
    EditText t1,t2,pas_reg,conpas_reg;
    Button b1;

    private Validation inputvalidation;
    private databasehelper database;
    user User;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tl1=(TextInputLayout) findViewById(R.id.personalname);
        tl2=(TextInputLayout) findViewById(R.id.personalemail);
        pas=(TextInputLayout)findViewById(R.id.password);
        conpas=(TextInputLayout)findViewById(R.id.confirmpassword);

        t1=(EditText) findViewById(R.id.editTextText2);
        t2=(EditText)findViewById(R.id.editTextText3);
        pas_reg=(EditText)findViewById(R.id.editTextTextPassword);
        conpas_reg=(EditText) findViewById(R.id.editTextTextPassword1);

        database=new databasehelper(MainActivity.this);
        inputvalidation=new Validation(MainActivity.this);
        User=new user();

        b1=(Button) findViewById(R.id.button3);
        s1=(TextView)findViewById(R.id.textView9);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!inputvalidation.isInputEditTextFilled(t1, tl1, "Enter Full Name")) {
                    return;
                }
                if(!inputvalidation.isInputEditTextFilled(t2, tl2, "Enter Valid Email")){
                    return;
                }
                if(!inputvalidation.isInputEditTextEmail(t2,tl2,"Enter Valid Email")){
                    return;
                }
                if(!inputvalidation.isInputEditTextFilled(pas_reg, pas, "Enter Password")){
                    return;
                }
                if(!inputvalidation.isInputEditTextMatches(pas_reg, conpas_reg, conpas, "Password doesn't match")){
                    return;
                }
               if(!database.checkUser(t2.getText().toString().trim())){

                User.setName(t1.getText().toString().trim());
                User.setEmail(t2.getText().toString().trim());
                User.setPassword(pas_reg.getText().toString().trim());
                database.adduser(User);
                   Toast.makeText(MainActivity.this, "Registration succesful", Toast.LENGTH_LONG).show();


                   Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                   startActivity(intent);
                }
               else{
                   Toast.makeText(getApplicationContext(), "Email Already exist", Toast.LENGTH_LONG).show();
               }
            }
        });

        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
            }
        });
        Intent i=getIntent();
    }


}