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



public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {


    private Context mCtx;
    private List<Product> productList;
    private OnItemClickListener mLister;

    public interface OnItemClickListener {
        void OnItemClick(int position);


    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mLister=listener;

    }

    public ProductAdapter( Context mCtx, List<Product> productList) {

        this.mCtx = mCtx;
        this.productList = productList;

    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recycleview_product, parent, false);
        return new ProductViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder holder, final int position) {
        final Product prod =productList.get(position);

        holder.productname.setText(prod.getProductName());
        holder.productcategory.setText(prod.getProductCategory());
        holder.prodURL.setText(prod.getProductDescription());
        holder.pricer.setText(prod.getPrice());

        holder.productupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pass object using intent
                Intent intent = new Intent(holder.productupdate.getContext(),EditProductDetails.class);
                Product prod= productList.get(position);
                intent.putExtra("prod",prod);
                holder.productupdate.getContext().startActivity(intent);
                //end pass object intent


            }
        });
        holder.productdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference dbRef= FirebaseDatabase.getInstance().getReference().child("Product Details").child(String.valueOf(prod.getProductID()));
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        dbRef.removeValue();
                        Intent intent=new Intent(holder.productdelete.getContext(),ShowProductDetals.class);
                        holder.productdelete.getContext().startActivity(intent);

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
        return productList.size();
    }



    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView productname,productcategory,prodURL,pricer;
        ImageButton productupdate,productdelete;


        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productname = itemView.findViewById(R.id.productnameR);
            productcategory = itemView.findViewById(R.id.prodcategoryR);
            prodURL = itemView.findViewById(R.id.prodURLR);
            pricer = itemView.findViewById(R.id.priceR);

            productupdate=itemView.findViewById(R.id.productupdate);
            productdelete=itemView.findViewById(R.id.productdelete);

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
