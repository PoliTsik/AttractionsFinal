package com.despol.attractions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class AT_recyclerview_adapter extends RecyclerView.Adapter<AT_recyclerview_adapter.MyViewHolder> {
    Context context;
    ArrayList<AttractionsModel> attractionsModels;
    com.despol.attractions.RecyclerViewInterface recyclerViewInterface;

    public AT_recyclerview_adapter(Context context, ArrayList<AttractionsModel> attractionsModels, com.despol.attractions.RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.attractionsModels = attractionsModels;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.mrecyclerviewrow, parent, false);
        return new MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvName.setText(attractionsModels.get(position).getAttractionName());
        holder.tvCountry.setText(attractionsModels.get(position).getAttractionCountry());
        holder.tvCity.setText(attractionsModels.get(position).getAttractionCity());

        Picasso.get()
                .load(attractionsModels.get(position).getCoverImage())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {

        return attractionsModels.size();
    }

    public void updateList(ArrayList<AttractionsModel>filteredList){
        attractionsModels= filteredList;
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvName;
        TextView tvCountry;
        TextView tvCity;

        public MyViewHolder(@NonNull View itemView, com.despol.attractions.RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            tvName = itemView.findViewById(R.id.textView);
            tvCountry = itemView.findViewById(R.id.textView2);
            tvCity = itemView.findViewById(R.id.textView3);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface != null) {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
