package com.fekrah.my4sale.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fekrah.my4sale.R;
import com.fekrah.my4sale.adapters.AdsAdapter;
import com.fekrah.my4sale.models.Ad;
import com.fekrah.my4sale.models.SlidShow;
import com.fekrah.my4sale.server.BaseClient;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

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
public class MainFragment extends Fragment {

    SliderLayout sliderLayout;
    View mainView;
    private int imagesNum = 3;
    List<String> imagesList;

    @BindView(R.id.ads_recycler_view)
    RecyclerView myAdsRecyclerView;

    List<Ad.AdData> adList = new ArrayList<>();
    AdsAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    @BindView(R.id.searchEdt)
    EditText searchInput;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, mainView);

        return mainView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sliderLayout = mainView.findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(SliderLayout.Animations.WORM); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setScrollTimeInSec(2); //set scroll delay in seconds :
        imagesList = new ArrayList<>();
        BaseClient.getApi().slidShowCall().enqueue(new Callback<SlidShow>() {
            @Override
            public void onResponse(Call<SlidShow> call, Response<SlidShow> response) {

                if (response.body() != null) {
                    imagesNum = response.body().getSlide_show().size();
                    for (SlidShow.SlidImages image : response.body().getSlide_show()) {
                        imagesList.add(image.getImage());
                    }
                    setSliderViews();
                }
            }

            @Override
            public void onFailure(Call<SlidShow> call, Throwable t) {

            }
        });
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        adapter = new AdsAdapter(getActivity(), adList,null);
        myAdsRecyclerView.setLayoutManager(linearLayoutManager);
        myAdsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        myAdsRecyclerView.setNestedScrollingEnabled(false);
        myAdsRecyclerView.stopNestedScroll();
        myAdsRecyclerView.setAdapter(adapter);

        BaseClient.getApi().lastAds().enqueue(new Callback<Ad>() {
            @Override
            public void onResponse(Call<Ad> call, Response<Ad> response) {
                if (response.body() != null) {
                    if (response.body().getAdvertisements() != null) {
                        if (response.body().getAdvertisements().size() >= 1)
                            adapter.addAll(response.body().getAdvertisements());
                    }

                }
            }

            @Override
            public void onFailure(Call<Ad> call, Throwable t) {

            }
        });
        searchInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Toast.makeText(getContext(), "بحث", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
    }

    private void setSliderViews() {

        for (int i = 0; i < imagesNum; i++) {

            SliderView sliderView = new SliderView(getActivity());
            sliderView.setImageUrl(imagesList.get(i));
//            switch (i) {
//                case 0:
//                    sliderView.setImageUrl("https://images.pexels.com/photos/547114/pexels-photo-547114.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
//                    break;
//                case 1:
//                    sliderView.setImageUrl("https://images.pexels.com/photos/218983/pexels-photo-218983.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
//                    break;
//                case 2:
//                    sliderView.setImageUrl("https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260");
//                    break;
//                case 3:
//                    sliderView.setImageUrl("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
//                    break;
//            }


            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            // sliderView.setDescription("setDescription " + (i + 1));
            final int finalI = i;
            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
                    Toast.makeText(getActivity(), "This is slider " + (finalI + 1), Toast.LENGTH_SHORT).show();
                }
            });

            //at last add this view in your layout :
            sliderLayout.addSliderView(sliderView);
        }
    }

    private ChangeNewsListener mListener;


    public void onButtonPressed( ) {
        if (mListener != null) {
            mListener.goToNews();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ChangeNewsListener) {
            mListener = (ChangeNewsListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface ChangeNewsListener {
        void goToNews( );
    }

    @OnClick(R.id.ad_image)
    void adImage() {
        onButtonPressed();
    }
}
