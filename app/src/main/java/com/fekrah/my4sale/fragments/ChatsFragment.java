package com.fekrah.my4sale.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fekrah.my4sale.R;
import com.fekrah.my4sale.activities.LoginActivity;
import com.fekrah.my4sale.adapters.ChatsAdapter;
import com.fekrah.my4sale.helper.SharedHelper;
import com.fekrah.my4sale.models.ChatsResponse;
import com.fekrah.my4sale.models.Room;
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
public class ChatsFragment extends Fragment {


    @BindView(R.id.chats_recycler_view)
    RecyclerView mSearchRecyclerView;

    List<Room> users;
    ChatsAdapter usersAdapter;
    LinearLayoutManager skillsLinearLayoutManager;


    View mainView;

    public ChatsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainView = inflater.inflate(R.layout.fragment_chats, container, false);
        ButterKnife.bind(this, mainView);
        return mainView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        users = new ArrayList<>();

        List<String> key = new ArrayList<>();
        skillsLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        usersAdapter = new ChatsAdapter(users, key, getActivity());
        mSearchRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // mSearchRecyclerView.addItemDecoration(new MyDividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL, 0));
        mSearchRecyclerView.setLayoutManager(skillsLinearLayoutManager);
        mSearchRecyclerView.setAdapter(usersAdapter);

        BaseClient.getApi().getChatsCall(SharedHelper.getKey(getActivity(), LoginActivity.USER_ID))
                .enqueue(new Callback<ChatsResponse>() {
                    @Override
                    public void onResponse(Call<ChatsResponse> call, Response<ChatsResponse> response) {
                        if (response.body().getRooms().size() > 0) {
                            Log.d("sssssssssssss", "onResponse: " + response.body().getRooms().get(0).getChatWithName());
                            usersAdapter.addRooms(response.body().getRooms());
                        }

                    }

                    @Override
                    public void onFailure(Call<ChatsResponse> call, Throwable t) {

                    }
                });

    }
}
