package com.exp.android_user.canteenautomation;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Register extends AppCompatActivity {
    EditText et_name;
    EditText et_email;
    EditText et_contact;
    EditText et_address;
    EditText et_password;
    Button btn_register;
    Button btn_clear;

    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]";

    String name,contact,email,address,password;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//initializing....
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Inserting");
        progressDialog.setMessage("Please wait ....");

        et_name = (EditText) findViewById(R.id.edit_text_name);
        et_contact = (EditText) findViewById(R.id.edit_text_contact);
        et_email = (EditText) findViewById(R.id.edit_text_email);
        et_address = (EditText) findViewById(R.id.edit_text_address);
        et_password = (EditText) findViewById(R.id.edit_textpassword);
        btn_register = (Button) findViewById(R.id.button_insert);
//registration

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initialize();
                if (validate()) {

                    insertData(name, address, contact, email, password);

                    SharedPreferences sharedPreferences=getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("Username",et_email.getText().toString());
                    editor.putString("Password",et_password.getText().toString());
                    editor.apply();
                    Intent reg_intent = new Intent(Register.this, LoginActivity.class);
                    startActivity(reg_intent);
                    progressDialog.show();
                } else
                    Toast.makeText(Register.this, "Registration failed", Toast.LENGTH_LONG).show();
            }
        });


        //clear data
        btn_clear = (Button) findViewById(R.id.btn_clear);
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_name.getText().clear();
                et_email.getText().clear();
                et_password.getText().clear();
                et_contact.getText().clear();
                et_address.getText().clear();
            }
        });

    }
    //validating inserted data
    public  boolean validate(){
        boolean valid=true;
        if(name.isEmpty() || name.length()>30){
            et_name.setError("please Enter Valid Name");
        }
        if(contact.isEmpty() || contact.length()<10 )
        {
            et_contact.setError("please Enter Valid contact");
            valid=false;
        }
        /*if(!email.matches(emailpattern) || email.isEmpty())
        {
            et_email.setError("please Enter Valid Email");
            valid=false;
        }*/

        if(address.isEmpty())
        {
            et_address.setError("please Enter Valid Address");
            valid=false;
        }
        if(password.isEmpty())
        {
            et_password.setError("please Enter Valid password");
            valid=false;
        }

        return  valid;
    }
    public void initialize() {
        name = et_name.getText().toString();
        contact = et_contact.getText().toString();
        email = et_email.getText().toString();
        address = et_address.getText().toString();
        password = et_password.getText().toString();
    }

    // this method used to open popup
    /* this method used to send data to server or our local server
    */
    private void insertData(String name, String address, String contact, String email, String password){
        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        Call<InsertResponseModel> call = apiService.insertData(name,address,contact,email,password);

        call.enqueue(new Callback<InsertResponseModel>() {
            @Override
            public void onResponse(Call<InsertResponseModel> call, Response<InsertResponseModel> response) {

                InsertResponseModel insertResponseModel = response.body();
              // Log.e("Result",response.body().getMessage()+"666");
                //check the status code
                if(insertResponseModel.getStatus()==1){
                    Toast.makeText(Register.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }else{
                    Toast.makeText(Register.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    Toast.makeText(Register.this,"Registered Successfully !!",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<InsertResponseModel> call, Throwable t) {

                // Log.e("Result",t.getMessage()+"666");

                Toast.makeText(Register.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });
    }

}