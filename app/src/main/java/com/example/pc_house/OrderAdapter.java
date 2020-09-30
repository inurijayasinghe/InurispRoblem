package com.example.pc_house;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;


public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {


    private Context mCtx;
    private List<Order> addOrderList;
    private OnItemClickListener mLister;

    public interface OnItemClickListener {
        void OnItemClick(int position);


    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mLister=listener;

    }

    public OrderAdapter( Context mCtx, List<Order> addOrderList) {

        this.mCtx = mCtx;
        this.addOrderList = addOrderList;

    }


    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recycleview_order, parent, false);
        return new OrderViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final OrderViewHolder holder, final int position) {
        final Order add =addOrderList.get(position);
        holder.txtv1.setText(add.getId());
        holder.txtv2.setText(add.getName());
        holder.txtv3.setText(add.getUrl());
        holder.txtv5.setText(String.valueOf(add.getPrice()));
        holder.txtv6.setText(String.valueOf(add.getQty()));


        /*holder.orderDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference dbRef= FirebaseDatabase.getInstance().getReference().child("Delivery Details").child(String.valueOf(add.getAddressId()));
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        dbRef.removeValue();
                        Intent intent=new Intent(holder.orderDel.getContext(),ShowAddressDetails.class);
                        holder.orderDel.getContext().startActivity(intent);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });*/

    }


    @Override
    public int getItemCount() {
        return addOrderList.size();
    }



    class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView txtv1, txtv2, txtv3, txtv4, txtv5, txtv6;
        ImageButton orderUpdate, orderDel;


        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            txtv1 = itemView.findViewById(R.id.tv1);
            txtv2 = itemView.findViewById(R.id.tv2);
            txtv3 = itemView.findViewById(R.id.tv3);
            txtv5 = itemView.findViewById(R.id.tv5);
            txtv6 = itemView.findViewById(R.id.tv6);
            orderUpdate =itemView.findViewById(R.id.orderUpdate);
            orderDel =itemView.findViewById(R.id.orderDelete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mLister!=null) {
                        int position = getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION) {
                            mLister.OnItemClick(position);
                        }
                    }
                }
            });

        }
    }
}

