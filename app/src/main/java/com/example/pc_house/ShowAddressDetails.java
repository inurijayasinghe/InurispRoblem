package com.example.pc_house;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class ShowAddressDetails extends AppCompatActivity {

    RecyclerView recyclerView;
    AddressAdapter adapter;
    List<Address> addressList;
    DatabaseReference dbRef;
    FirebaseAuth firebaseAuth;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_address_details);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        addressList = new ArrayList<>();
        adapter = new AddressAdapter( this, addressList);
        recyclerView.setAdapter(adapter);
        btn = findViewById(R.id.addNew);
        firebaseAuth=FirebaseAuth.getInstance();


        dbRef= FirebaseDatabase.getInstance().getReference().child("Delivery Details").child(firebaseAuth.getCurrentUser().getUid());
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChildren()){
                    for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){

                        Address add=dataSnapshot1.getValue(Address.class);
                        addressList.add(add);

                    }
                    adapter.notifyDataSetChanged();

                }
                else{}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ShowAddressDetails.this,AddNewAddress.class);
                startActivity(intent);

            }
        });




    }
}