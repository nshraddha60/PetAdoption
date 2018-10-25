package com.exp.android_user.canteenautomation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.content.SharedPreferences;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button login,register;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
login=(Button) findViewById(R.id.btn_login);
register=(Button) findViewById(R.id.btn_register);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login_intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(login_intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register_intent=new Intent(MainActivity.this,Register.class);
                startActivity(register_intent);
            }
        });
    }
}
