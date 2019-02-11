package com.fekrah.my4sale.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fekrah.my4sale.R;
import com.fekrah.my4sale.models.Ad;
import com.rafakob.drawme.DrawMeImageButton;

import java.util.List;

public class MyAdsAdapter extends RecyclerView.Adapter {

    // View Types
    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private boolean isLoadingAdded = false;

    Activity context;
    List<Ad> adList;

    public MyAdsAdapter(Activity context, List<Ad> adList) {
        this.context = context;
        this.adList = adList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                View viewItem = inflater.inflate(R.layout.layout_my_ad_item, parent, false);
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        Ad ad = adList.get(position);
        switch (getItemViewType(position)) {

            case ITEM:
                final MyAdsViewHolder holder = (MyAdsViewHolder) viewHolder;

//                holder.name.setText(ad.getName());
//                holder.price.setText(ad.getPrice());
//                holder.image.setImageURI(ad.getImage());
//                holder.description.setText(ad.getDescription());
                holder.mainView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.descriptionView.setVisibility(View.VISIBLE);
                        holder.editView.setVisibility(View.GONE);
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


    class MyAdsViewHolder extends RecyclerView.ViewHolder{

        View mainView;
        SimpleDraweeView image;
        TextView price,name,description;
        DrawMeImageButton edit1,edit2,delete1,delete2;
        LinearLayout descriptionView;
        LinearLayout editView;
        public MyAdsViewHolder(@NonNull View itemView) {
            super(itemView);
            mainView = itemView;
            image = mainView.findViewById(R.id.ad_image);
            name= mainView.findViewById(R.id.ad_name);
            price = mainView.findViewById(R.id.ad_price);
            description = mainView.findViewById(R.id.ad_description);
            edit1 = mainView.findViewById(R.id.edit_ad1);
            edit2 = mainView.findViewById(R.id.edit_ad2);
            delete1 = mainView.findViewById(R.id.dekete_ad1);
            delete2 = mainView.findViewById(R.id.delete_ad2);
            descriptionView = mainView.findViewById(R.id.description_view);
            editView = mainView.findViewById(R.id.edit_view);
        }
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
        adList.add(new Ad());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = adList.size() - 1;
        Ad result = getItem(position);

        if (result != null) {
            adList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Ad getItem(int position) {
        return adList.get(position);
    }

    public void addAll(List<Ad> newfishList) {
        adList.addAll(newfishList);
        notifyDataSetChanged();
    }

}
