package com.fekrah.my4sale.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fekrah.my4sale.R;
import com.fekrah.my4sale.adapters.CategoriesAdapter;
import com.fekrah.my4sale.models.Category;
import com.fekrah.my4sale.server.BaseClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends Fragment {

    View mainView;

    @BindView(R.id.partition_recycler_view)
    RecyclerView partitionsRecyclerView;

    List<Category.CategoryData> partitionList;
    LinearLayoutManager linearLayoutManager;
    CategoriesAdapter adapter;

    public CategoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainView = inflater.inflate(R.layout.fragment_partition, container, false);
        ButterKnife.bind(this, mainView);
        return mainView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        partitionList = new ArrayList<>();

        linearLayoutManager = new LinearLayoutManager(getActivity());
        adapter = new CategoriesAdapter(partitionList, getActivity());
        partitionsRecyclerView.setLayoutManager(linearLayoutManager);
        partitionsRecyclerView.setAdapter(adapter);

        BaseClient.getApi().categoryCall().enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if (response.body()!=null){
                    adapter.add(response.body().getCategories());
                }
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {

            }
        });
    }
}
