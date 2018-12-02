package com.fekrah.my4sale.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fekrah.my4sale.R;
import com.fekrah.my4sale.activities.AdsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    View mainView;

    @OnClick(R.id.go_ads)
    void go(){
       getActivity().startActivity(new Intent(getActivity(),AdsActivity.class));
    }
    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainView= inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this,mainView);

        return mainView;
    }

}
