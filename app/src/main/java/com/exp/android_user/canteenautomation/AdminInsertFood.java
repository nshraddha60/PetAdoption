package com.exp.android_user.canteenautomation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AdminInsertFood extends AppCompatActivity {
    private ProgressDialog progressDialog;
    EditText et_petCategory,et_categoryId, et_petId,et_desc, et_quantity;

    String petCategory, ngoId, petId, petDescription, petQuantity, petImagePath;
    String[] mediaColumns = {MediaStore.Images.Media._ID};


    Button btn_insertFood,btn_foodimage;
    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_insert_food);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Inserting");
        progressDialog.setMessage("Please wait ....");

        et_categoryId=(EditText)findViewById(R.id.etid_categoryId);
        et_desc=(EditText)findViewById(R.id.etid_desc);
        et_petId =(EditText)findViewById(R.id.pet_id);
        et_petCategory =(EditText)findViewById(R.id.pet_category);
        et_quantity = findViewById(R.id.no_of_pets_available);
        btn_foodimage=(Button)findViewById(R.id.pet_image_button);
        btn_insertFood=(Button)findViewById(R.id.insert_data_button);

        btn_insertFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initialize();
                if (validate()) {
                    insertFood();

                    Intent insertfood_intent = new Intent(AdminInsertFood.this, AdminPortal.class);
                    startActivity(insertfood_intent);
                    progressDialog.show();
                } else
                    Toast.makeText(AdminInsertFood.this, "insertion failed", Toast.LENGTH_LONG).show();
            }
        });

        btn_foodimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data){
            Uri selectImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectImage,filePathColumn,null,null,null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            petImagePath = cursor.getString(columnIndex);
            cursor.close();

           // ImageView imageView = findViewById(R.id.imageView);
            //imageView.setImageBitmap(BitmapFactory.decodeFile(petImagePath));
        }
    }

    public void initialize() {
        petCategory = et_petCategory.getText().toString();
        ngoId = et_categoryId.getText().toString();
        petId = et_petId.getText().toString();
        petDescription = et_desc.getText().toString();
        petQuantity = et_quantity.getText().toString();

    }
    public  boolean validate(){
        boolean valid=true;
        if(petCategory.isEmpty() && petCategory.length()>30){
            et_petCategory.setError("Please Enter Valid Pet Category. Pet Category should not empty.");
        }
        if(ngoId.isEmpty()  )
        {
            et_categoryId.setError("Please Enter Valid NGO Id. NGO Id  should not empty.");
            valid=false;
        }
        if(petId.isEmpty())
        {
            et_petId.setError("Please Enter Valid Pet Id. Pet Id should not empty");
            valid=false;
        }

        if(petDescription.isEmpty())
        {
            et_desc.setError("Please Enter Valid Description");
            valid=false;
        }
        if(petQuantity.isEmpty())
        {
            et_quantity.setError("Please Enter Valid pet no.");
            valid=false;
        }


        return  valid;
    }
    private void insertFood(){
        File file = new File(petImagePath);

        RequestBody requestBody =  RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        //RequestBody fileName = RequestBody.create(MediaType.parse("text/plain"),file.getName());
        RequestBody ngoIdOp = RequestBody.create(MediaType.parse("text/plain"), ngoId);
        RequestBody petIdOp = RequestBody.create(MediaType.parse("text/plain"), petId);
        RequestBody petCategoryOp = RequestBody.create(MediaType.parse("text/plain"), petCategory);
        RequestBody petDescriptionOp = RequestBody.create(MediaType.parse("text/plain"), petDescription);
        RequestBody petQuantityOp = RequestBody.create(MediaType.parse("text/plain"), petQuantity);


        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        Call<InsertResponseModel> call = apiService.insertFood(ngoIdOp, petIdOp, petCategoryOp, petDescriptionOp,petQuantityOp, fileToUpload);
        call.enqueue(new Callback<InsertResponseModel>() {
            @Override
            public void onResponse(Call<InsertResponseModel> call, Response<InsertResponseModel> response) {

                InsertResponseModel insertResponseModel = response.body();
                // Log.e("Result",response.body().getMessage()+"666");
                //check the status code
                if(insertResponseModel.getStatus()==1){
                    Toast.makeText(AdminInsertFood.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }else{
                    Toast.makeText(AdminInsertFood.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<InsertResponseModel> call, Throwable t) {

                Log.e("Result",t.getMessage()+"666");

                Toast.makeText(AdminInsertFood.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

}
