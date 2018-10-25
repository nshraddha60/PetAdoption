package com.exp.android_user.canteenautomation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.SharedPreferences;

public class LoginActivity extends AppCompatActivity {

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        LoginActivity.name = name;
    }

    Button loginAct;
    EditText et_username, et_password;

String usrnm,pswd;
public static String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginAct = (Button) findViewById(R.id.btn_loginAct);
        et_username = (EditText) findViewById(R.id.et_login_name);
        et_password = (EditText) findViewById(R.id.et_login_password);


        loginAct.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                SharedPreferences sharedPreferences=getSharedPreferences("userInfo", Context.MODE_PRIVATE);

                usrnm=et_username.getText().toString();
                pswd=et_password.getText().toString();

               name=sharedPreferences.getString("Username","");
                String pass=sharedPreferences.getString("Password","");

                if (et_username.getText().toString().equals("admin") &&
                        et_password.getText().toString().equals("admin")) {
                    Toast.makeText(LoginActivity.this, "Admin Login Success", Toast.LENGTH_SHORT).show();

                    Intent intent_admin=new Intent(LoginActivity.this,AdminPortal.class);
                    startActivity(intent_admin);

                } else if (usrnm.equals(name) && pswd.equals(pass))
                {
                    Toast.makeText(LoginActivity.this, "User Login Success", Toast.LENGTH_SHORT).show();
                    Intent intent_user=new Intent(LoginActivity.this,Home.class);
                    startActivity(intent_user);
                }else
                    Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_LONG).show();
            }
        });

    }


}