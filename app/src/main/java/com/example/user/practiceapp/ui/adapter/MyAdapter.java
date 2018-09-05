package com.example.user.practiceapp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.practiceapp.R;
import com.example.user.practiceapp.model.JsonModel;
import com.example.user.practiceapp.model.MyModel;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    private Context context;
    private ArrayList<JsonModel> myModels;

    public MyAdapter(Context context, ArrayList<JsonModel> myModels) {
        this.context = context;
        this.myModels = myModels;
    }

    @NonNull
    @Override
    public MyAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.cell_sample, parent, false);
        MyAdapter.MyHolder viewHolder = new MyAdapter.MyHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyHolder holder, int position) {
        
        holder.txtVillageId.setText(myModels.get(position).getVillageId());
        holder.txtVillageName.setText(myModels.get(position).getVillageName());
        holder.txtBlockPanchayatName.setText(myModels.get(position).getBlockName() + ", " +myModels.get(position).getPanchayatName());
    }

    @Override
    public int getItemCount() {
        if (myModels != null || myModels.size() > 0)
            return myModels.size();
        else
            return 0;
    }

    protected class MyHolder extends RecyclerView.ViewHolder {

        private TextView txtVillageId, txtVillageName, txtBlockPanchayatName, txtViewDetail, txtDelete;

        public MyHolder(View itemView) {
            super(itemView);
            
            txtVillageId = itemView.findViewById(R.id.txt_id);
            txtVillageName = itemView.findViewById(R.id.txt_village_name);
            txtBlockPanchayatName = itemView.findViewById(R.id.txt_block_panchayat_name);
            txtViewDetail = itemView.findViewById(R.id.txt_view_details);
            txtDelete = itemView.findViewById(R.id.txt_delete);
            
        }
    }
}
