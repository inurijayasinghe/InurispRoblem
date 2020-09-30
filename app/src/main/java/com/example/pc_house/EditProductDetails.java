package com.example.pc_house;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProductDetails extends AppCompatActivity {

    EditText editproductname,editproductcategory,editdescription,editprice;
    Button btnSaveProduct;
    DatabaseReference dbRef;
    Product product;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product_details);

        editproductname = findViewById(R.id.editproductname);
        editproductcategory = findViewById(R.id.editcategory);
        editdescription = findViewById(R.id.editdescription);
        editprice = findViewById(R.id.editprice);

        btnSaveProduct = findViewById(R.id.editconfirmProduct);

        product= new Product();


        //get passed object
        Intent intent = getIntent();
        product = intent.getParcelableExtra("prod");
        editproductname.setText(product.getProductName());
        editproductcategory.setText(product.getProductCategory());
        editdescription.setText(product.getProductDescription());
        editprice.setText(product.getPrice());



        btnSaveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                dbRef = FirebaseDatabase.getInstance().getReference().child("Product Details");
                dbRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(String.valueOf(product.getProductID()))){

                            product.setProductName(editproductname.getText().toString().trim());
                            product.setProductCategory(editproductcategory.getText().toString().trim());
                            product.setProductDescription(editdescription.getText().toString().trim());
                            product.setPrice(editprice.getText().toString().trim());

                            dbRef.child(String.valueOf(product.getProductID())).setValue(product);

                            Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(EditProductDetails.this,ShowProductDetals.class);
                            startActivity(intent1);

                        }else{

                            Toast.makeText(getApplicationContext(), "Data Updated Unsuccessfully", Toast.LENGTH_SHORT).show();

                        }



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

    }
}