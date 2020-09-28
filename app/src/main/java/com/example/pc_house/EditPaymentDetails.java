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

public class EditPaymentDetails extends AppCompatActivity {

    EditText card,custName,cvv,exdate;
    Button save;
    Payment pay;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_payment_details);

        card=findViewById(R.id.editcardNo);
        custName=findViewById(R.id.editcustName);
        cvv=findViewById(R.id.editcvv);
        exdate=findViewById(R.id.editexpireDate);
        save=findViewById(R.id.saveconfirmAddPayment);

        pay= new Payment();

        //get passed object
        Intent intent = getIntent();
        pay = intent.getParcelableExtra("payyr");
        card.setText(String.valueOf(pay.getCardNo()));
        custName.setText(pay.getCustomer_name());
        cvv.setText(String.valueOf(pay.getCvv()));
        exdate.setText(pay.getExpireDate());


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dbRef = FirebaseDatabase.getInstance().getReference().child("Payment Details");
                dbRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(String.valueOf(pay.getPaymentId()))){

                            pay.setCardNo(card.getText().toString().trim());
                            pay.setCustomer_name(custName.getText().toString().trim());
                            pay.setCvv(Integer.parseInt(cvv.getText().toString().trim()));
                            pay.setExpireDate(exdate.getText().toString().trim());

                            dbRef.child(String.valueOf(pay.getPaymentId())).setValue(pay);

                            Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(EditPaymentDetails.this,ShowPaymentDetails.class);
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