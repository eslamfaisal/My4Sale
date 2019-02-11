package com.fekrah.my4sale.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fekrah.my4sale.R;
import com.fekrah.my4sale.activities.SubCategoryActivity;
import com.fekrah.my4sale.models.Category;
import com.rafakob.drawme.DrawMeTextView;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.PartitionViewHolder>{

    public static String SUB_CATEGORY_NAME  = "SUB_CATEGORY_NAME" ;
    List<Category.CategoryData> partitionList;
    Activity context;
    public static String SUB_CATEGORY_ID = "SUB_CATEGORY_ID";

    public CategoriesAdapter(List<Category.CategoryData> partitionList, Activity context) {
        this.partitionList = partitionList;
        this.context = context;
    }

    @NonNull
    @Override
    public PartitionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_partition_item,viewGroup,false);
        return new PartitionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PartitionViewHolder holder, int i) {

        final Category.CategoryData partition = partitionList.get(i);

        holder.textView.setText(partition.getName());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context,SubCategoryActivity.class);
                intent.putExtra(SUB_CATEGORY_NAME,partition.getName());
                intent.putExtra(SUB_CATEGORY_ID,partition.getId());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return partitionList.size();
    }

    public void add(List<Category.CategoryData> categories) {
        partitionList.addAll(categories);
        notifyDataSetChanged();
    }

    class PartitionViewHolder extends RecyclerView.ViewHolder{
        DrawMeTextView textView;

        public PartitionViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.partition_item);
        }
    }

}
