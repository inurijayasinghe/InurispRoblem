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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;


public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewHolder> {


    private Context mCtx;
    FirebaseAuth firebaseAuth;
    private List<Address> addList;
    private OnItemClickListener mLister;

    public interface OnItemClickListener {
        void OnItemClick(int position);


    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mLister=listener;

    }

    public AddressAdapter( Context mCtx, List<Address> addList) {

        this.mCtx = mCtx;
        this.addList = addList;

    }


    @NonNull
    @Override
    public AddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recycleview_address, parent, false);
        return new AddressViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final AddressViewHolder holder, final int position) {
        final Address add =addList.get(position);
        firebaseAuth=FirebaseAuth.getInstance();
        holder.street.setText(add.getStreet_Address());
        holder.city.setText(add.getCty());
        holder.province.setText(add.getProvince());
        holder.country.setText(add.getCountry());
        holder.postal.setText(String.valueOf(add.getPostalCode()));
        holder.telephone.setText(String.valueOf(add.getTelephone()));

        holder.btnEditAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pass object using intent
                Intent intent = new Intent(holder. btnEditAdd.getContext(),EditAddressDetails.class);
                Address add= addList.get(position);
                intent.putExtra("addrr",add);
                holder. btnEditAdd.getContext().startActivity(intent);
                //end pass object intent


            }
        });
        holder.btnDelAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference dbRef= FirebaseDatabase.getInstance().getReference().child("Delivery Details").child(firebaseAuth.getCurrentUser().getUid()).child(String.valueOf(add.getAddressId()));
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        dbRef.removeValue();
                        Intent intent=new Intent(holder.btnDelAdd.getContext(),ShowAddressDetails.class);
                        holder.btnDelAdd.getContext().startActivity(intent);

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



    class AddressViewHolder extends RecyclerView.ViewHolder {

        TextView street,city,province,postal,telephone,country;
        ImageButton btnEditAdd,btnDelAdd;


        public AddressViewHolder(@NonNull View itemView) {
            super(itemView);
            street = itemView.findViewById(R.id.streetr);
            city = itemView.findViewById(R.id.cityr);
            province = itemView.findViewById(R.id.provincer);
            country = itemView.findViewById(R.id.countryr);
            postal = itemView.findViewById(R.id.postalr);
            telephone = itemView.findViewById(R.id.telephoner);
            btnEditAdd=itemView.findViewById(R.id.update);
            btnDelAdd=itemView.findViewById(R.id.delete);

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

