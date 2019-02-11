package com.fekrah.my4sale.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.fekrah.my4sale.R;
import com.fekrah.my4sale.adapters.AdsAdapter;
import com.fekrah.my4sale.models.Ad;
import com.fekrah.my4sale.server.BaseClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.fekrah.my4sale.adapters.SubCategoryAdapter.SUB_CATEGORY_ID2;

public class AdsFromSearchActivity extends AppCompatActivity {

    @BindView(R.id.my_ads_recycler_view)
    RecyclerView myAdsRecyclerView;

    List<Ad.AdData> adList = new ArrayList<>();
    AdsAdapter adapter;
    LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads_from_search);
        ButterKnife.bind(this);
        linearLayoutManager = new LinearLayoutManager(this);
        adapter = new AdsAdapter(this,adList,null);
        myAdsRecyclerView.setLayoutManager(linearLayoutManager);
        myAdsRecyclerView.setAdapter(adapter);

        Intent intent = getIntent();
        String  subCategoryId = intent.getStringExtra("subCategory");
        String  categoryId = intent.getStringExtra("category");
        String  cityyId = intent.getStringExtra("city");
        Log.d("pppppp", "onCreate: "+subCategoryId+" "+categoryId+" "+cityyId);
        BaseClient.getApi().searchCall(cityyId,categoryId,subCategoryId).enqueue(new Callback<Ad>() {
            @Override
            public void onResponse(Call<Ad> call, Response<Ad> response) {
                Log.d("pppppp", "onResponse: "+response.body().getAds().get(0).getCat_id());
                if (response.body()!=null){
                    if (response.body().getAds()!=null){
                        if (response.body().getAds().size()>=1)
                            adapter.addAll(response.body().getAds());
                    }

                }
            }

            @Override
            public void onFailure(Call<Ad> call, Throwable t) {
                Log.d("pppppp", "onFailure: "+t.getMessage());
            }
        });

    }
}
