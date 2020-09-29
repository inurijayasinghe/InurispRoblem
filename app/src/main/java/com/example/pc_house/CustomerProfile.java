package com.example.pc_house;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CustomerProfile extends AppCompatActivity {

    private Button paymentDetails, paymentHistory, shippingDetails, registerAsASeller, editProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);

        editProfile =findViewById(R.id.editProfile);
        paymentDetails =findViewById(R.id.paymentDetails);
        paymentHistory =findViewById(R.id.paymentHistory);
        shippingDetails =findViewById(R.id.shippingDetails);
        registerAsASeller =findViewById(R.id.registerAsASeller);

        paymentDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPayments();
            }
        });

        shippingDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddressDetails();
            }
        });

    }

    private void openAddressDetails() {
        Intent intent = new Intent(this, ShowAddressDetails.class );
        startActivity(intent);
    }

    private void openPayments() {
        Intent intent = new Intent(this, ShowPaymentDetails.class );
        startActivity(intent);
    }
}