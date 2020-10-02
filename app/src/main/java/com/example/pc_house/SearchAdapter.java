package com.example.pc_house;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    Context mCtx;
    OnItemClickListener mLister;

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    public SearchAdapter(Context mCtx) {

        this.mCtx = mCtx;

    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recycleview_search, parent, false);
        return new SearchAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.SearchViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class SearchViewHolder extends RecyclerView.ViewHolder{


        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);


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
