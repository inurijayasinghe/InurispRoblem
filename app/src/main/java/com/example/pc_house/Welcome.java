package com.example.pc_house;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Welcome extends AppCompatActivity {

    Button btnlogin,btnReg;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        firebaseAuth=FirebaseAuth.getInstance();
        btnlogin=findViewById(R.id.alreadymember);
        btnReg=findViewById(R.id.jjnow);

if(firebaseAuth.getCurrentUser()!=null){
    startActivity(new Intent(getApplicationContext(),MainActivity.class));
}

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Welcome.this,loging.class);
                startActivity(intent);

            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Welcome.this,Register.class);
                startActivity(intent);

            }
        });
    }
}