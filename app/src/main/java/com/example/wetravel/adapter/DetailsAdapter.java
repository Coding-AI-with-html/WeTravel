package com.example.wetravel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wetravel.R;
import com.example.wetravel.model.DetailsData;
import com.example.wetravel.model.TopPlacesData;

import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder> {

    Context context;
    List<DetailsData> DetailsDataList;

    public DetailsAdapter(Context context, List<DetailsData> detailsDataList) {
        this.context = context;
        DetailsDataList = detailsDataList;
    }

    @NonNull
    @Override
    public DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_gallery_new_item, parent, false);
        return new DetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsViewHolder holder, int position) {

        holder.placeImg.setImageResource(DetailsDataList.get(position).getImageURL());


    }

    @Override
    public int getItemCount() {
        return DetailsDataList.size();
    }


    public static final class DetailsViewHolder extends RecyclerView.ViewHolder{

        ImageView placeImg;

        public DetailsViewHolder(@NonNull View itemView) {
            super(itemView);

            placeImg = itemView.findViewById(R.id.gallery_image);
        }
    }
}
