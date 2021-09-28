package com.example.dawaii;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Medicine;


import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.MedProfileViewHolder> {
    List<Medicine> meds ;

    public ProfileAdapter(List<Medicine> meds) {
        this.meds = meds;
    }

    public static class MedProfileViewHolder extends RecyclerView.ViewHolder {
        public Medicine medicine;
        View itemView;

        public MedProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }

    @NonNull
    @Override
    public MedProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println(1111111111);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_profile, parent, false);
        MedProfileViewHolder medProfileViewHolder = new MedProfileViewHolder(view);
        return medProfileViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MedProfileViewHolder holder, int position) {
        holder.medicine = meds.get(position);
        TextView medName = holder.itemView.findViewById(R.id.medNameProfile);
        TextView startToEnd = holder.itemView.findViewById(R.id.startToEndDate);
        TextView expirationDate = holder.itemView.findViewById(R.id.expirationDateProfile);
        TextView difference = holder.itemView.findViewById(R.id.pillsDiff);
        medName.setText(holder.medicine.getName());
        String startEnd = holder.medicine.getDates().get(0) + "TO" + holder.medicine.getDates().get(holder.medicine.getDates().size() - 1);
        startToEnd.setText(startEnd);
        expirationDate.setText(holder.medicine.getExpirationDate());
        int required = holder.medicine.getDosage()*holder.medicine.getDates().size()*holder.medicine.getTimes().size();
        String diff = "You have to take " + required + " pills from " + holder.medicine.getName()+ "\n you have "+ holder.medicine.getAvailableTablets();
        difference.setText(diff);
    }

    @Override
    public int getItemCount() {
        return meds.size();
    }
}
