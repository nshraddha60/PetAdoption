package com.exp.android_user.canteenautomation;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FoodItemDetail extends AppCompatActivity {

public static List<Contact_FoodList> productDetails=new ArrayList<Contact_FoodList>();

    Contact_FoodList contact_foodList;
    CollapsingToolbarLayout collapsingToolbarLayout;

    TextView petDescription, petCategory, petQuantity;
    String petName, quantity, petId,email;
    ImageView ngoImage;
    EditText email_username;
    Button adoptButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_item_detail);


        email_username=(EditText) findViewById(R.id.et_login_name);
        petDescription = (TextView) findViewById(R.id.pet_desc);
        petCategory = (TextView) findViewById(R.id.pet_category);
        petQuantity = findViewById(R.id.quantity);
        ngoImage = (ImageView) findViewById(R.id.ngo_image);


        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);

        contact_foodList = getIntent().getParcelableExtra("petdetail");


        petDescription.setText(contact_foodList.getPetDescription());
        petCategory.setText(contact_foodList.getPetCategory());

        Picasso.with(getApplicationContext()).load(contact_foodList.getPetImage())
                .into(ngoImage);

        collapsingToolbarLayout.setTitle(contact_foodList.getPetCategory());

        //email=email_username.getText().toString();
        petName = petCategory.getText().toString();
        petId =contact_foodList.getPetId();
//Log.e("df",petId);

        adoptButton = findViewById(R.id.adopt_button);
        adoptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact_FoodList p_detail=new Contact_FoodList(petId, petName,quantity);
                productDetails.add(p_detail);
                Toast.makeText(FoodItemDetail.this, "Added to cart", Toast.LENGTH_SHORT).show();
            }
        });


    }

}