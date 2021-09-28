package com.example.dawaii;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Medicine;

import java.util.ArrayList;
import java.util.List;

public class MedAdapter extends RecyclerView.Adapter<MedAdapter.MedViewHolder> {

    List<Medicine> medsList ;

    public MedAdapter(List<Medicine> medsList) {
        this.medsList = medsList;
    }

    public static class MedViewHolder extends RecyclerView.ViewHolder {
        public Medicine medicine;
        View itemView;
        public MedViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }

    @NonNull
    @Override
    public MedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_blank, parent, false);
        MedViewHolder medViewHolder = new MedViewHolder(view);
        return medViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MedViewHolder holder, int position) {
        holder.medicine= medsList.get(position);
        TextView medName = holder.itemView.findViewById(R.id.medName);
        TextView medDosage = holder.itemView.findViewById(R.id.medDosage);
        TextView dosageTime = holder.itemView.findViewById(R.id.dosageTime);
        medName.setText(holder.medicine.getName());
        medDosage.setText(holder.medicine.getDosage().toString());
        String times = "";
        for (int i = 0; i < holder.medicine.getTimes().size(); i++) {
            times += holder.medicine.getTimes().get(i)+"\n";
        }
        dosageTime.setText(times);
    }

    @Override
    public int getItemCount() {
        return medsList.size();
    }

}
