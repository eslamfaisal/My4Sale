package com.fekrah.my4sale.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.fekrah.my4sale.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddAdFragment extends Fragment {
    View mainView;

    @BindView(R.id.category_spinner)
    Spinner categorySpinner;

    @BindView(R.id.city_spinner)
    Spinner city_spinner;

    @BindView(R.id.country_spinner)
    Spinner country_spinner;

    @BindView(R.id.partetion_spinner)
    Spinner partetion_spinner;

    @BindView(R.id.type_spinner)
    Spinner type_spinner;
    public AddAdFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainView= inflater.inflate(R.layout.fragment_add_ad, container, false);
        ButterKnife.bind(this,mainView);
        return mainView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<String > categoryList = new ArrayList<>();
        categoryList.add(getString(R.string.categories));
        ArrayAdapter categortAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, categoryList);
        categortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categortAdapter);


        List<String > citiesList = new ArrayList<>();
        citiesList.add(getString(R.string.cities));
        ArrayAdapter citiesAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, citiesList);
        citiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city_spinner.setAdapter(citiesAdapter);

        List<String > typeList = new ArrayList<>();
        typeList.add(getString(R.string.type));
        ArrayAdapter typeAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, typeList);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type_spinner.setAdapter(typeAdapter);

        List<String > countryList = new ArrayList<>();
        countryList.add(getString(R.string.countries));
        ArrayAdapter countryAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, countryList);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country_spinner.setAdapter(countryAdapter);

        List<String> partitionList = new ArrayList<>();
        partitionList.add(getString(R.string.partitions));
        ArrayAdapter partitionAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, partitionList);
        partitionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        partetion_spinner.setAdapter(partitionAdapter);

    }
}
