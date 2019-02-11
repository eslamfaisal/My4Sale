package com.fekrah.my4sale.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fekrah.my4sale.R;
import com.fekrah.my4sale.adapters.CategoriesAdapter;
import com.fekrah.my4sale.adapters.SubCategoryAdapter;
import com.fekrah.my4sale.models.SubCategory;
import com.fekrah.my4sale.server.BaseClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubCategoryActivity extends AppCompatActivity {

    @BindView(R.id.partition_recycler_view)
    RecyclerView partitionsRecyclerView;

    List<SubCategory.SubCategoryData> partitionList;
    LinearLayoutManager linearLayoutManager;
    SubCategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partitions);
        ButterKnife.bind(this);

        partitionList = new ArrayList<>();

        linearLayoutManager = new LinearLayoutManager(this);
        adapter = new SubCategoryAdapter(partitionList, this);
        partitionsRecyclerView.setAdapter(adapter);
        partitionsRecyclerView.setLayoutManager(linearLayoutManager);

        Intent intent = getIntent();
        String categoryID = intent.getStringExtra(CategoriesAdapter.SUB_CATEGORY_ID);
        if (intent.getStringExtra(CategoriesAdapter.SUB_CATEGORY_NAME)!=null)
        setTitle(intent.getStringExtra(CategoriesAdapter.SUB_CATEGORY_NAME));
        BaseClient.getApi().subCategoryCall(Integer.parseInt(categoryID)).enqueue(new Callback<SubCategory>() {
            @Override
            public void onResponse(Call<SubCategory> call, Response<SubCategory> response) {
                if (response.body()!=null){
                    adapter.add(response.body().getSub_categories());
                }
            }

            @Override
            public void onFailure(Call<SubCategory> call, Throwable t) {

            }
        });
    }
}
