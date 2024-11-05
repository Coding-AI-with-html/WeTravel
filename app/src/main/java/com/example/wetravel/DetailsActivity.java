package com.example.wetravel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.wetravel.adapter.DetailsAdapter;
import com.example.wetravel.adapter.RecentsAdapter;
import com.example.wetravel.model.DetailsData;
import com.example.wetravel.model.RecentsData;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {


    RecyclerView detailRecycler;
    DetailsAdapter detailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        List<DetailsData> DetailDataList = new ArrayList<>();
        DetailDataList.add(new DetailsData(R.drawable.img1));
        DetailDataList.add(new DetailsData(R.drawable.img2));
        DetailDataList.add(new DetailsData(R.drawable.img3));
        DetailDataList.add(new DetailsData(R.drawable.img4));

        setDetailRecycler(DetailDataList);
    }

    private void setDetailRecycler(List<DetailsData> detailDataList){

        detailRecycler = findViewById(R.id.image_gallery_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        detailRecycler.setLayoutManager(layoutManager);
        detailAdapter = new DetailsAdapter(this, detailDataList);
        detailRecycler.setAdapter(detailAdapter);
    }
}