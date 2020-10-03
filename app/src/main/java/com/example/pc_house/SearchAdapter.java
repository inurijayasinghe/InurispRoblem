package com.example.pc_house;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    Context mCtx;
    private List<Item> searchList;

    public SearchAdapter(Context mCtx,List<Item> searchList) {

        this.mCtx = mCtx;
        this.searchList=searchList;

    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recycleview_search, parent, false);
        return new SearchAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SearchAdapter.SearchViewHolder holder, final int position) {
        Item item=searchList.get(position);
        Glide.with(mCtx).load(item.getUrl()).into(holder.image);
        holder.text.setText(item.getName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.cardView.getContext(),Item_Display.class);
                Item item1=searchList.get(position);
                intent.putExtra("item",item1);
                holder.cardView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder{
ImageView image;
TextView text;
CardView cardView;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
image=itemView.findViewById(R.id.image_search);
text=itemView.findViewById(R.id.image_name_search);
cardView=itemView.findViewById(R.id.parent_55);


        }
    }
}
