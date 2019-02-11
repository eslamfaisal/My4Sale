package com.fekrah.my4sale.adapters;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fekrah.my4sale.R;
import com.fekrah.my4sale.activities.ChatActivity;
import com.fekrah.my4sale.activities.LoginActivity;
import com.fekrah.my4sale.activities.MainActivity;
import com.fekrah.my4sale.helper.SharedHelper;
import com.fekrah.my4sale.models.Ad;
import com.fekrah.my4sale.models.AdFavoriteResponse;
import com.fekrah.my4sale.models.DeleteResponse;
import com.fekrah.my4sale.server.BaseClient;
import com.rafakob.drawme.DrawMeButton;
import com.rafakob.drawme.DrawMeImageButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdsAdapter extends RecyclerView.Adapter {

    // View Types
    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private boolean isLoadingAdded = false;



    Activity context;
    List<Ad.AdData> adList;
    UpdateListener updateListener;

    public AdsAdapter(Activity context, List<Ad.AdData> adList, UpdateListener updateListener) {
        this.context = context;
        this.adList = adList;
        this.updateListener = updateListener;
    }

    public interface UpdateListener{
        void goUpdate(Ad.AdData ad);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                View viewItem = inflater.inflate(R.layout.layout_ad_item, parent, false);
                viewHolder = new MyAdsViewHolder(viewItem);
                break;
            case LOADING:
                View viewLoading = inflater.inflate(R.layout.layout_item_progress, parent, false);
                viewHolder = new LoadingVH(viewLoading);
                break;

        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {

        final Ad.AdData ad = adList.get(position);
        switch (getItemViewType(position)) {

            case ITEM:
                final MyAdsViewHolder holder = (MyAdsViewHolder) viewHolder;

                if (MainActivity.myAdsFragment.isVisible()){
                    holder.call2.setVisibility(View.GONE);
                    holder.call.setVisibility(View.GONE);
                    holder.message.setVisibility(View.GONE);
                    holder.message2.setVisibility(View.GONE);
                    holder.edit.setVisibility(View.VISIBLE);
                    holder.edit2.setVisibility(View.VISIBLE);
                    holder.delete.setVisibility(View.VISIBLE);
                    holder.delete2.setVisibility(View.VISIBLE);
                }else {
                    holder.call2.setVisibility(View.VISIBLE);
                    holder.call.setVisibility(View.VISIBLE);
                    holder.message.setVisibility(View.VISIBLE);
                    holder.message2.setVisibility(View.VISIBLE);
                    holder.edit.setVisibility(View.GONE);
                    holder.edit2.setVisibility(View.GONE);
                    holder.delete.setVisibility(View.GONE);
                    holder.delete2.setVisibility(View.GONE);
                }
                holder.name.setText(ad.getTitle());
                holder.price.setText(context.getString(R.string.Prise) +" "+ad.getPrice());
                holder.image.setImageURI(ad.getImage());
                holder.description.setText(ad.getDes());
                holder.mainView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(holder.descriptionView.getVisibility()==View.VISIBLE){
                            holder.descriptionView.setVisibility(View.GONE);
                            holder.editView.setVisibility(View.VISIBLE);
                            holder.mainRelative.setBackgroundResource(R.drawable.ic_layer_list_ad);
                        }else {
                            holder.descriptionView.setVisibility(View.VISIBLE);
                            holder.editView.setVisibility(View.GONE);
                            holder.mainRelative.setBackgroundResource(R.drawable.ic_layer_list_ad);
                        }

                    }
                });

                holder.call2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        callClient(ad.getMobile());
                    }
                });

                holder.call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        callClient(ad.getMobile());
                    }
                });

                holder.message.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (SharedHelper.getKey(context,LoginActivity.IS_LOGIN).equals("no")){
                            startLoginActivity();
                        }else {
                            Intent intent = new Intent(context, ChatActivity.class);
                            intent.putExtra("receiverId", ad.getUser_id());
                            intent.putExtra("receiverName", ad.getUsername());
                            context.startActivity(intent);
                        }
                    }
                });

                holder.message2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (SharedHelper.getKey(context,LoginActivity.IS_LOGIN).equals("no")){
                            startLoginActivity();
                        }else {
                            Intent intent = new Intent(context, ChatActivity.class);
                            intent.putExtra("receiverId", ad.getUser_id());
                            intent.putExtra("receiverName", ad.getUsername());
                            context.startActivity(intent);
                        }
                    }
                });

                holder.favorite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BaseClient.getApi().setFavorite(SharedHelper.getKey(context, LoginActivity.USER_ID), ad.getId())
                                .enqueue(new Callback<AdFavoriteResponse>() {
                                    @Override
                                    public void onResponse(Call<AdFavoriteResponse> call, Response<AdFavoriteResponse> response) {
                                        if (response.body().isSuccess()) {
                                            Toast.makeText(context, "تم اضافة الاعلان الى المفضلة", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<AdFavoriteResponse> call, Throwable t) {

                                    }
                                });
                    }
                });
                holder.favorite2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BaseClient.getApi().setFavorite(SharedHelper.getKey(context, LoginActivity.USER_ID), ad.getId())
                                .enqueue(new Callback<AdFavoriteResponse>() {
                                    @Override
                                    public void onResponse(Call<AdFavoriteResponse> call, Response<AdFavoriteResponse> response) {
                                        if (response.body().isSuccess()) {
                                            Toast.makeText(context, "تم اضافة الاعلان الى المفضلة", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<AdFavoriteResponse> call, Throwable t) {

                                    }
                                });
                    }
                });

                holder.report.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showProblemNoteDialog(ad.getId());
                    }
                });
                holder.report2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showProblemNoteDialog(ad.getId());
                    }
                });
                holder.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDeleteDialog(ad.getId(),position);
                    }
                });
                holder.delete2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDeleteDialog(ad.getId(),position);
                    }
                });

                holder.edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateListener.goUpdate(ad);
                    }
                });

                break;

            case LOADING:
                final LoadingVH progressHolder = (LoadingVH) viewHolder;
                break;
        }
    }

    @Override
    public int getItemCount() {
        return adList.size();
    }


    @Override
    public int getItemViewType(int position) {

        return (position == adList.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }


    class MyAdsViewHolder extends RecyclerView.ViewHolder {

        View mainView;
        SimpleDraweeView image;
        TextView price, name, description;
        DrawMeImageButton call, call2, message, message2, favorite, favorite2, report, report2,edit ,edit2,delete,delete2;
        LinearLayout descriptionView;
        LinearLayout editView;
        RelativeLayout mainRelative;

        public MyAdsViewHolder(@NonNull View itemView) {
            super(itemView);
            mainView = itemView;
            image = mainView.findViewById(R.id.ad_image);
            name = mainView.findViewById(R.id.ad_name);
            price = mainView.findViewById(R.id.ad_price);
            description = mainView.findViewById(R.id.ad_description);
            call = mainView.findViewById(R.id.call);
            call2 = mainView.findViewById(R.id.call2);
            message = mainView.findViewById(R.id.message);
            message2 = mainView.findViewById(R.id.message2);
            favorite = mainView.findViewById(R.id.favorite);
            favorite2 = mainView.findViewById(R.id.favorite2);
            report = mainView.findViewById(R.id.report);
            report2 = mainView.findViewById(R.id.report2);
            descriptionView = mainView.findViewById(R.id.description_view);
            editView = mainView.findViewById(R.id.edit_view);
            mainRelative = mainView.findViewById(R.id.main_relative);
            edit = mainView.findViewById(R.id.edit);
            edit2 = mainView.findViewById(R.id.edit2);
            delete = mainView.findViewById(R.id.delete);
            delete2 = mainView.findViewById(R.id.delete2);
        }
    }

    public void callClient(String phone) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phone));
        Log.d("xxxxxxxxxx", "callClient: " + phone);
        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                context.requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1232);
            }
        } else {
            context.startActivity(callIntent);
        }
    }

    void showProblemNoteDialog(final String id) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("مشكلة");
        //  alertDialog.setMessage("اكتب المشكلة");

        final EditText input = new EditText(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        input.setHint("اكتب المشكلة");
        alertDialog.setView(input);

        alertDialog.setPositiveButton("ارسال",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        BaseClient.getApi().setReport(SharedHelper.getKey(context, LoginActivity.USER_ID), id,input.getText().toString())
                                .enqueue(new Callback<AdFavoriteResponse>() {
                                    @Override
                                    public void onResponse(Call<AdFavoriteResponse> call, Response<AdFavoriteResponse> response) {
                                        if (response.body().isSuccess()) {
                                            Toast.makeText(context, "تم ارسال التقرير", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<AdFavoriteResponse> call, Throwable t) {

                                    }
                                });
                    }
                });

        alertDialog.setNegativeButton("الغاء",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }

    protected class LoadingVH extends RecyclerView.ViewHolder {
        private ProgressBar mProgressBar;

        public LoadingVH(View itemView) {
            super(itemView);

            mProgressBar = (ProgressBar) itemView.findViewById(R.id.loadmore_progress);

        }
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        adList.add(new Ad.AdData());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = adList.size() - 1;
        Ad.AdData result = getItem(position);

        if (result != null) {
            adList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Ad.AdData getItem(int position) {
        return adList.get(position);
    }

    public void addAll(List<Ad.AdData> newfishList) {
        adList.addAll(newfishList);
        notifyDataSetChanged();
    }
    private void startLoginActivity(){
        Intent intent = new Intent(context,LoginActivity.class);
        context.startActivityForResult(intent,12587);
    }

    private void showDeleteDialog(final String ad_id, final int position){
      final Dialog  warningDialog = new Dialog(context);
        warningDialog.setContentView(R.layout.layout_delete_dialog);
        DrawMeButton ok = warningDialog.findViewById(R.id.ok_warning);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseClient.getApi().deleteMyAd(SharedHelper.getKey(context,LoginActivity.USER_ID),ad_id)
                        .enqueue(new Callback<DeleteResponse>() {
                            @Override
                            public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
                                notifyItemRemoved(position);
                                Toast.makeText(context, "تم مسح الاعلان", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onFailure(Call<DeleteResponse> call, Throwable t) {

                            }
                        });
                warningDialog.dismiss();
            }
        });
        warningDialog.show();
    }

}
