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

public class ShowOrderDetails extends AppCompatActivity {

    RecyclerView recyclerView;
    OrderAdapter adapter;
    List<Order> orderList;
    DatabaseReference dbRef;
    //Button showOrderbtn;
    FirebaseAuth firebaseAuthh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_order_details);
firebaseAuthh=FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.recyclerViewOrd);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        orderList = new ArrayList<>();
        adapter = new OrderAdapter( this, orderList);
        recyclerView.setAdapter(adapter);
        //showOrderbtn = findViewById(R.id.addOrder);



        dbRef= FirebaseDatabase.getInstance().getReference().child("Order").child(firebaseAuthh.getCurrentUser().getUid());
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChildren()){
                    for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){

                        Order order=dataSnapshot1.getValue(Order.class);
                        orderList.add(order);
                    }
                    adapter.notifyDataSetChanged();

                }
                else{}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        /*btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ShowAddressDetails.this,AddNewAddress.class);
                startActivity(intent);

            }
        });*/




    }
}























