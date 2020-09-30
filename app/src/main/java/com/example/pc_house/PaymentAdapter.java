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

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder> {


    private Context mCtx;
    private List<Payment> addList;
    private OnItemClickListener mLister;

    public interface OnItemClickListener {
        void OnItemClick(int position);


    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mLister=listener;

    }

    public PaymentAdapter(Context mCtx, List<Payment> addList) {

        this.mCtx = mCtx;
        this.addList = addList;

    }

    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recycleview_payment, parent, false);
        return new PaymentAdapter.PaymentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PaymentViewHolder holder, final int position) {
        final Payment pay=addList.get(position);
        holder.holderName.setText(pay.getCustomer_name());
        holder.cardNo.setText(pay.getCardNo());
        holder.cvv.setText(String.valueOf(pay.getCvv()));
        holder.expireDate.setText(pay.getExpireDate());

        holder.btnDelPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DatabaseReference dbRef= FirebaseDatabase.getInstance().getReference().child("Payment Details").child(String.valueOf(pay.getPaymentId()));
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        dbRef.removeValue();
                        Intent intent=new Intent(holder.btnDelPay.getContext(),ShowPaymentDetails.class);
                        holder.btnDelPay.getContext().startActivity(intent);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



            }
        });

        holder.btnEditPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //pass object using intent
                Intent intent = new Intent(holder.btnEditPay.getContext(),EditPaymentDetails.class);
                Payment pay1= addList.get(position);
                intent.putExtra("payyr",pay1);
                holder.btnEditPay.getContext().startActivity(intent);
                //end pass object intent

            }
        });




    }

    @Override
    public int getItemCount() {
        return addList.size();
    }

    class PaymentViewHolder extends RecyclerView.ViewHolder{

        TextView holderName,cardNo,expireDate,cvv;
        ImageButton btnEditPay,btnDelPay;



        public PaymentViewHolder(@NonNull View itemView) {
            super(itemView);
            holderName=itemView.findViewById(R.id.holdernamer);
            cardNo=itemView.findViewById(R.id.cardNor);
            cvv=itemView.findViewById(R.id.cvvr);
            expireDate=itemView.findViewById(R.id.expireDater);
            btnEditPay=itemView.findViewById(R.id.payeditr);
            btnDelPay=itemView.findViewById(R.id.paydeleter);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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

