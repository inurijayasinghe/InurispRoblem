package com.example.pc_house;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyOrders extends AppCompatActivity {

    //Widgets

    RecyclerView recycleView;

    //Firebase
    private DatabaseReference myRef;

    //Variables
    private ArrayList<Orders> orderList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

        recycleView = findViewById(R.id.recycleView03);
        LinearLayoutManager layeroutManager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(layeroutManager);
        recycleView.setHasFixedSize(true);

        myRef = FirebaseDatabase.getInstance().getReference();

        //Arraylist
        orderList = new ArrayList<>();

        Clearall();

        GetDataFromFireBase();





    }

    private void GetDataFromFireBase() {


    }

    private void Clearall(){
        if(orderList!=null){
            orderList.clear();
        }
        orderList = new ArrayList<>();

    }
}