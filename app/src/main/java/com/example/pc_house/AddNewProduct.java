package com.example.pc_house;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;



public class AddNewProduct extends AppCompatActivity {

    private static ArrayList list;
    EditText addProductname,addProductCategory,addDescription,addPrice;
    Button btnConfirmProduct;
    DatabaseReference dbRef;
    Product prod;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product);
        list=new ArrayList<Integer>();
        addProductname = findViewById(R.id.productname);
        addProductCategory = findViewById(R.id.prodcategory);
        addDescription = findViewById(R.id.proddescription);
        addPrice = findViewById(R.id.price);

        btnConfirmProduct = findViewById(R.id.confirmProduct);


        prod = new Product();
        dbRef= FirebaseDatabase.getInstance().getReference().child("Product Details");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChildren()){
                    for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){

                        Product prod=dataSnapshot1.getValue(Product.class);
                        list.add(prod.getProductID());


                    }


                }
                else{}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        btnConfirmProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String prodName = addProductname.getText().toString().trim();
                String prodCategory = addProductCategory.getText().toString().trim();
                String prodDescription = addDescription.getText().toString().trim();
                String prodPrice = addPrice.getText().toString().trim();


                try {
                    if (prodName.isEmpty()) {

                        addProductname.setError("Please enter your product name");
                        return;


                    }
                    if (prodCategory.isEmpty()) {

                        addProductCategory.setError("Please enter your product category");
                        return;

                    }
                    if (prodDescription.isEmpty()) {

                        addDescription.setError("Please enter your product description");
                        return;

                    }
                    if (prodPrice.isEmpty()) {

                        addPrice.setError("Please enter your product ");
                        return;

                    }

                    prod.setProductID(generateProductIDs());
                    prod.setProductName(addProductname.getText().toString().trim());
                    prod.setProductCategory(addProductCategory.getText().toString().trim());
                    prod.setProductDescription(addDescription.getText().toString().trim());
                    prod.setPrice(addPrice.getText().toString().trim());

                    dbRef.child(String.valueOf(prod.getProductID())).setValue(prod);

                    Toast.makeText(getApplicationContext(), "Successfully Added !!!", Toast.LENGTH_SHORT).show();
                    clearControls();
                    Intent intent = new Intent(AddNewProduct.this, ShowProductDetals.class);
                    startActivity(intent);

                }catch(NumberFormatException e){

                    Toast.makeText(getApplicationContext(), "Something Wrong, Please Check Details Again !!!", Toast.LENGTH_SHORT).show();

                }


            }

        });

    }


    private void clearControls(){


        addProductname.setText("");
        addProductCategory.setText("");
        addDescription.setText("");
        addPrice.setText("");

    }




    //=====================


    public static int generateProductIDs() {

        int id;
        int next =list.size();
        next++;
        id = CommonConstants.Product_ID_Prefix+ next;
        if (list.contains(id)) {
            next++;
            id = CommonConstants.Product_ID_Prefix + next;
        }
        return id;
    }
}