package com.example.pc_house;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowPaymentDetails extends AppCompatActivity {

    RecyclerView recyclerView;
    PaymentAdapter paymentAdapter;
    List<Payment> paymentList;
    DatabaseReference dbRef;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_payment_details);
        recyclerView = findViewById(R.id.recyclerViewPayments);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        paymentList = new ArrayList<>();
        paymentAdapter = new PaymentAdapter( this, paymentList);
        recyclerView.setAdapter(paymentAdapter);
        btn = findViewById(R.id.addpay);

        dbRef= FirebaseDatabase.getInstance().getReference().child("Payment Details");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){

                    for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                        Payment pay = dataSnapshot.getValue(Payment.class);
                        paymentList.add(pay);


                    }
                    paymentAdapter.notifyDataSetChanged();


                }else{}


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ShowPaymentDetails.this,AddNewPayment.class);
                startActivity(intent);


            }
        });


    }
}