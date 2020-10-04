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

import java.util.ArrayList;


public class AddNewProduct extends AppCompatActivity {

    private static ArrayList list;
    EditText addProductname,addProductCategory,addProdURL,addPrice;
    Button btnConfirmProduct;
    DatabaseReference dbRef;
    Item item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product);
        list=new ArrayList<Integer>();
        addProductname = findViewById(R.id.productname);
        addProductCategory = findViewById(R.id.prodcategory);
        addProdURL = findViewById(R.id.prodURL);
        addPrice = findViewById(R.id.price);

        btnConfirmProduct = findViewById(R.id.confirmProduct);


        item = new Item();
        dbRef= FirebaseDatabase.getInstance().getReference().child("Item");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChildren()){
                    for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){

                        Item item=dataSnapshot1.getValue(Item.class);
                        list.add(item.getID());


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
                String prodURL = addProdURL.getText().toString().trim();
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
                    if (prodURL.isEmpty()) {

                        addProdURL.setError("Please enter your product URL");
                        return;

                    }
                    if (prodPrice.isEmpty()) {

                        addPrice.setError("Please enter your product price");
                        return;

                    }

                    item.setID(generateProductIDs());
                    item.setName(addProductname.getText().toString().trim());
                    item.setCategory(addProductCategory.getText().toString().trim());
                    item.setUrl(addProdURL.getText().toString().trim());
                    item.setPrice(Double.parseDouble(addPrice.getText().toString().trim()));

                    dbRef.child(String.valueOf(item.getID())).setValue(item);

                    Toast.makeText(getApplicationContext(), "Successfully Added !!!", Toast.LENGTH_SHORT).show();
                    clearControls();
                   // Intent intent = new Intent(AddNewProduct.this, ShowProductDetals.class);
                  //  startActivity(intent);

                }catch(NumberFormatException e){

                    Toast.makeText(getApplicationContext(), "Something Wrong, Please Check Details Again !!!", Toast.LENGTH_SHORT).show();

                }


            }

        });

    }


    private void clearControls(){


        addProductname.setText("");
        addProductCategory.setText("");
        addProdURL.setText("");
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
