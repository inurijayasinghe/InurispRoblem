package com.example.pc_house;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class Category extends AppCompatActivity {

    private static final String TAG = "Category";
    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_category);
        CategoryAdapter adapter = new CategoryAdapter(mNames,mImageUrls,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mNames.add("Laptops");
        mImageUrls.add("https://sourcedigit.com/wp-content/uploads/2014/11/Hp-Laptops-Windows.jpg");
        mNames.add("Tablets");
        mImageUrls.add("https://static.giga.de/wp-content/uploads/2019/06/tablets-amazon.jpg");
        mNames.add("Mini pc");
        mImageUrls.add("https://cdn.windowsreport.com/wp-content/uploads/2018/05/mini-pcs.jpg");
        mNames.add("Desktops");
        mImageUrls.add("https://www.lenovo.com/medias/ww-desktops-thinkcentre-m720t-gallery-04-image.jpg?context=bWFzdGVyfHJvb3R8MTUxOTA3fGltYWdlL2pwZ3xoOGYvaDc4Lzk3NDcyOTU4NjI4MTQuanBnfDQ4ZGMxMmVjM2EwNGQzMjA1N2YyY2NhOWNjOTBiZjdlNTk5MTI0MTU1MmIxNjAzYmU0OTUzOWRhZDRiNDdjMWM");
        mNames.add("Mouse and Keyboard");
        mImageUrls.add("https://pic.made-in-china.com/4f0j00feaEuFLKbqcn/Gaming-Mouse-and-Keyboard-Combo.jpg");
        mNames.add("Servers");
        mImageUrls.add("http://www.solveit.ie/wp-content/uploads/2017/04/servers.png");
        mNames.add("Laptop Accessories");
        mImageUrls.add("https://images.techhive.com/images/article/2013/12/laptopaccessories_primary-100155352-large.jpg");
        mNames.add("Tablet Accessories");
        mImageUrls.add("https://www.techonthego.co.uk/wp-content/uploads/2011/02/galaxy_tab_accessories.jpg");
        mNames.add("Desktop Components");
        mImageUrls.add("http://www.offerzforyou.com/wp-content/uploads/2015/03/computer_accessories.jpg");


    }



}