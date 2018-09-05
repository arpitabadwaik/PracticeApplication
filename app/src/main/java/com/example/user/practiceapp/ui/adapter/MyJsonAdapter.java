package com.example.user.practiceapp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.practiceapp.R;
import com.example.user.practiceapp.model.JsonGSonModel;
import com.example.user.practiceapp.model.JsonModel;

import java.util.ArrayList;

public class MyJsonAdapter extends RecyclerView.Adapter<MyJsonAdapter.MyJsonHolder> {

    private Context context;
    private ArrayList<JsonGSonModel> jsonGSonModels;

    public MyJsonAdapter(Context context, ArrayList<JsonGSonModel> jsonGSonModels) {
        this.context = context;
        this.jsonGSonModels = jsonGSonModels;
    }

    @NonNull
    @Override
    public MyJsonAdapter.MyJsonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.cell_sample, parent, false);
        MyJsonAdapter.MyJsonHolder viewHolder = new MyJsonAdapter.MyJsonHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyJsonAdapter.MyJsonHolder holder, int position) {

        holder.txtVillageId.setText(jsonGSonModels.get(position).getVillageId());
        holder.txtVillageName.setText(jsonGSonModels.get(position).getVillageName());
        holder.txtBlockPanchayatName.setText(jsonGSonModels.get(position).getBlockName() + ", " +jsonGSonModels.get(position).getPanchayatName());
    }

    @Override
    public int getItemCount() {
        if (jsonGSonModels != null || jsonGSonModels.size() > 0)
            return jsonGSonModels.size();
        else
            return 0;
    }

    protected class MyJsonHolder extends RecyclerView.ViewHolder {

        private TextView txtVillageId, txtVillageName, txtBlockPanchayatName, txtViewDetail, txtDelete;

        public MyJsonHolder(View itemView) {
            super(itemView);

            txtVillageId = itemView.findViewById(R.id.txt_id);
            txtVillageName = itemView.findViewById(R.id.txt_village_name);
            txtBlockPanchayatName = itemView.findViewById(R.id.txt_block_panchayat_name);
            txtViewDetail = itemView.findViewById(R.id.txt_view_details);
            txtDelete = itemView.findViewById(R.id.txt_delete);
        }
    }
}