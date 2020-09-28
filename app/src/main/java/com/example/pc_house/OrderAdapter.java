/**package com.example.pc_house;

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


public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrdersViewHolder> {


    private Context mCtx;
    private List<Orders> addList;
    private OnItemClickListener mLister;

    public interface OnItemClickListener {
        void OnItemClick(int position);


    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mLister=listener;

    }

    public OrderAdapter(Context mCtx, List<Orders> addList) {

        this.mCtx = mCtx;
        this.addList = addList;

    }


    @NonNull
    @Override
    public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.order_item, parent, false);
        return new OrdersViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final OrdersViewHolder holder, final int position) {
        final Orders add =addList.get(position);
       // holder.aa.setText(add.getPrice());
      //  holder.bb.setText(add.getId());
      //  holder.cc.setText(add.getName());
       // holder.dd.setText(add.getQty());
        //holder.ee.setText(String.valueOf(add.getUrl()));


        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pass object using intent
                Intent intent = new Intent(holder.btn.getContext(),MainActivity.class);
                Orders add= addList.get(position);
                intent.putExtra("id",add);
                holder.btn.getContext().startActivity(intent);
                //end pass object intent


            }
        });
        holder.btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference dbRef= FirebaseDatabase.getInstance().getReference().child("Item").child(String.valueOf(add.getId()));
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        dbRef.removeValue();
                        Intent intent=new Intent(holder.btndel.getContext(),MainActivity.class);
                        holder.btndel.getContext().startActivity(intent);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

    }


    @Override
    public int getItemCount() {
        return addList.size();
    }



    class OrdersViewHolder extends RecyclerView.ViewHolder {

        TextView aa,bb,cc,dd,ee;
        ImageButton btn,btndel;


        public OrdersViewHolder(@NonNull View itemView) {
            super(itemView);
            aa = itemView.findViewById(R.id.id);
            bb = itemView.findViewById(R.id.name);
            cc = itemView.findViewById(R.id.price);
            dd = itemView.findViewById(R.id.qty);
            ee = itemView.findViewById(R.id.url);

            btn=itemView.findViewById(R.id.update);
            btndel=itemView.findViewById(R.id.delete);

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

**/