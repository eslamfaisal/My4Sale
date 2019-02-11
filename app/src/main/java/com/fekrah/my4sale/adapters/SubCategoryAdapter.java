package com.fekrah.my4sale.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fekrah.my4sale.R;
import com.fekrah.my4sale.activities.AdsActivity;
import com.fekrah.my4sale.models.SubCategory;
import com.rafakob.drawme.DrawMeTextView;

import java.util.List;

import static com.fekrah.my4sale.adapters.CategoriesAdapter.SUB_CATEGORY_ID;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.PartitionViewHolder> {

    List<SubCategory.SubCategoryData> partitionList;
    Activity context;
    public static String SUB_CATEGORY_ID2 = "SUB_CATEGORY_ID2";
    public static String SUB_CATEGORY_Name2 = "SUB_CATEGORY_Name2";

    public SubCategoryAdapter(List<SubCategory.SubCategoryData> partitionList, Activity context) {
        this.partitionList = partitionList;
        this.context = context;
    }

    @NonNull
    @Override
    public PartitionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_partition_item, viewGroup, false);
        return new PartitionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PartitionViewHolder holder, int i) {

        final SubCategory.SubCategoryData partition = partitionList.get(i);

        holder.textView.setText(partition.getSub_category());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AdsActivity.class);
                intent.putExtra(SUB_CATEGORY_ID2,""+partition.getSub_category_id());
                intent.putExtra(SUB_CATEGORY_Name2,""+partition.getSub_category());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return partitionList.size();
    }

    public void add(List<SubCategory.SubCategoryData> sub_categories) {
        partitionList.addAll(sub_categories);
        notifyDataSetChanged();
    }

    class PartitionViewHolder extends RecyclerView.ViewHolder {
        DrawMeTextView textView;

        public PartitionViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.partition_item);
        }
    }

}
