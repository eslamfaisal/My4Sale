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
import com.fekrah.my4sale.adapters.MyAdsAdapter;
import com.fekrah.my4sale.models.Ad;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyAdsFragment extends Fragment {

    @BindView(R.id.my_ads_recycler_view)
    RecyclerView myAdsRecyclerView;

    List<Ad> adList = new ArrayList<>();
    MyAdsAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    View mainView;

    public MyAdsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainView = inflater.inflate(R.layout.fragment_my_ads, container, false);
        ButterKnife.bind(this,mainView);
        return mainView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adList.add(new Ad("image","نيسان ","50.000 ريال ","عالية الكفاءة"));
        linearLayoutManager = new LinearLayoutManager(getActivity());
        adapter = new MyAdsAdapter(getActivity(),adList);
        myAdsRecyclerView.setLayoutManager(linearLayoutManager);
        myAdsRecyclerView.setAdapter(adapter);
    }
}
