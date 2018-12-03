package com.fekrah.my4sale.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fekrah.my4sale.R;
import com.fekrah.my4sale.adapters.MyAdsAdapter;
import com.fekrah.my4sale.models.Ad;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdsActivity extends AppCompatActivity {

    @BindView(R.id.my_ads_recycler_view)
    RecyclerView myAdsRecyclerView;

    List<Ad> adList = new ArrayList<>();
    MyAdsAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads);
        ButterKnife.bind(this);
        adList.add(new Ad("image","نيسان ","50.000 ريال ","عالية الكفاءة"));
        linearLayoutManager = new LinearLayoutManager(this);
        adapter = new MyAdsAdapter(this,adList);
        myAdsRecyclerView.setLayoutManager(linearLayoutManager);
        myAdsRecyclerView.setAdapter(adapter);
    }
}
