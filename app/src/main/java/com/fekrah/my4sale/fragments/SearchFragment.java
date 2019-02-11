package com.fekrah.my4sale.fragments;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.fekrah.my4sale.R;
import com.fekrah.my4sale.activities.AdsFromSearchActivity;
import com.fekrah.my4sale.models.Category;
import com.fekrah.my4sale.models.Cities;
import com.fekrah.my4sale.models.Partnerships;
import com.fekrah.my4sale.models.SubCategory;
import com.fekrah.my4sale.server.BaseClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    View mainView;

    @BindView(R.id.category_spinner)
    Spinner categorySpinner;

    @BindView(R.id.city_spinner)
    Spinner citiesSpinner;

    @BindView(R.id.partetion_spinner)
    Spinner subCategorySpinner;

    List<String> cities = new ArrayList<>();
    List<String> citiesId = new ArrayList<>();
    public static String CITY_ID = "";
    private List<Cities.CityData> citiesBody;

    List<String> categories = new ArrayList<>();
    List<String> categoriesId = new ArrayList<>();
    public static String CATEGORY_ID = "";
    private List<Category.CategoryData> categoriesBody;

    List<String> subCategories = new ArrayList<>();
    List<String> subCategoriesId = new ArrayList<>();
    public static String SUB_CATEGORY_ID = "";
    private List<SubCategory.SubCategoryData> subCategoriesBody;


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainView= inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this,mainView);
        return mainView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getcities();
        getCategories();

    }


    @OnClick(R.id.search)
    void serach(){

        if (CITY_ID.equals("")||SUB_CATEGORY_ID.equals("")||CATEGORY_ID.equals("")){
            Toast.makeText(getActivity(), "برجاء ادخال جميع البيانات", Toast.LENGTH_SHORT).show();
            return;
        }else {

            Intent intent = new Intent(getActivity(), AdsFromSearchActivity.class);
            intent.putExtra("city",CITY_ID);
            intent.putExtra("category",CATEGORY_ID);
            intent.putExtra("subCategory",SUB_CATEGORY_ID);
            getActivity().startActivity(intent);
        }
    }
    private void getcities() {
        if (cities != null && cities.size() > 1) {
            cities.clear();
        }
        Call<Cities> getCiriesCall = BaseClient.getApi().getCities();

        getCiriesCall.enqueue(new Callback<Cities>() {
            @Override
            public void onResponse(Call<Cities> call, Response<Cities> response) {
                if (response.body() != null) {
                    if (response.body().getCities().size() > 0) {

                        citiesBody = response.body().getCities();
                        for (Cities.CityData city : citiesBody) {
                            cities.add(city.getCity_name());
                            citiesId.add(city.getId());
                        }
                        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, cities);

                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        citiesSpinner.setAdapter(adapter);
                        citiesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                CITY_ID = String.valueOf(citiesBody.get(position).getId());
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<Cities> call, Throwable t) {

            }
        });
    }

    private void getCategories() {
        if (categories != null && categories.size() > 1) {
            categories.clear();
        }
        Call<Category> getCiriesCall = BaseClient.getApi().categoryCall();

        getCiriesCall.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if (response.body() != null) {
                    if (response.body().getCategories().size() > 0) {

                        categoriesBody = response.body().getCategories();
                        for (Category.CategoryData city : categoriesBody) {
                            categories.add(city.getName());
                            categoriesId.add(city.getId());
                        }

                        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, categories);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        categorySpinner.setAdapter(adapter);
                        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                CATEGORY_ID = String.valueOf(categoriesBody.get(position).getId());
                                subCategories = new ArrayList<>();
                                BaseClient.getApi().subCategoryCall(Integer.parseInt(CATEGORY_ID)).enqueue(new Callback<SubCategory>() {
                                    @Override
                                    public void onResponse(Call<SubCategory> call, Response<SubCategory> response) {
                                        if (response.body() != null) {
                                            if (response.body().getSub_categories().size() > 0) {

                                                subCategoriesBody = response.body().getSub_categories();
                                                for (SubCategory.SubCategoryData city : subCategoriesBody) {
                                                    subCategories.add(city.getSub_category());
                                                    subCategoriesId.add(city.getSub_category_id());
                                                }
                                                ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, subCategories);

                                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                subCategorySpinner.setAdapter(adapter);
                                                subCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        SUB_CATEGORY_ID = String.valueOf(subCategoriesBody.get(position).getSub_category_id());
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<SubCategory> call, Throwable t) {

                                    }
                                });

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {

            }
        });
    }
}
