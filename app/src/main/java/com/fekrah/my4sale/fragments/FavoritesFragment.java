package com.fekrah.my4sale.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fekrah.my4sale.R;
import com.fekrah.my4sale.activities.LoginActivity;
import com.fekrah.my4sale.adapters.AdsAdapter;
import com.fekrah.my4sale.helper.SharedHelper;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment {

    @BindView(R.id.my_ads_recycler_view)
    RecyclerView myAdsRecyclerView;

    List<Ad.AdData> adList = new ArrayList<>();
    AdsAdapter adapter;
    LinearLayoutManager linearLayoutManager;



    public FavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        adapter = new AdsAdapter(getActivity(),adList,null);
        myAdsRecyclerView.setLayoutManager(linearLayoutManager);
        myAdsRecyclerView.setAdapter(adapter);

        BaseClient.getApi().favoriteCall(SharedHelper.getKey(getActivity(), LoginActivity.USER_ID)).enqueue(new Callback<Ad>() {
            @Override
            public void onResponse(Call<Ad> call, Response<Ad> response) {
                if (response.body()!=null){
                    if (response.body().getAdvertisements()!=null){
                        if (response.body().getAdvertisements().size()>=1)
                            adapter.addAll(response.body().getAdvertisements());
                    }

                }
            }

            @Override
            public void onFailure(Call<Ad> call, Throwable t) {

            }
        });

    }
}
