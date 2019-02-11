package com.fekrah.my4sale.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fekrah.my4sale.R;
import com.fekrah.my4sale.activities.LoginActivity;
import com.fekrah.my4sale.helper.SharedHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    @BindView(R.id.user_image)
    SimpleDraweeView userImage;

    @BindView(R.id.user_name)
    TextView name;

    @BindView(R.id.phone)
    TextView phone;

    @BindView(R.id.email)
    TextView email;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        userImage.setImageURI(SharedHelper.getKey(getActivity(), LoginActivity.IMAGE));
        name.setText(SharedHelper.getKey(getActivity(), LoginActivity.USER_NAME));
        phone.setText(SharedHelper.getKey(getActivity(), LoginActivity.PHONE));
        email.setText(SharedHelper.getKey(getActivity(), LoginActivity.EMAIL));
    }


}
