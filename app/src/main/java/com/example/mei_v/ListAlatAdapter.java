package com.example.mei_v;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mei_v.api.model.alat.DetailAlat;
import com.example.mei_v.api.model.maintenance.Maintenance;

import java.util.ArrayList;
import java.util.List;

public class ListAlatAdapter extends RecyclerView.Adapter<ListAlatAdapter.ViewHolder>{

    private final List<DetailAlat> listMaintenance = new ArrayList<>();
    void setListMaintenance(List<DetailAlat> listMaintenance){
        if (listMaintenance == null)return;
        this.listMaintenance.clear();
        this.listMaintenance.addAll(listMaintenance);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detailalat_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DetailAlat maintenance=listMaintenance.get(position);
        holder.kodealat.setText(maintenance.getBarcode());
        holder.kodealat.setBackgroundResource(R.drawable.table_content_cell_bg);
        holder.merkalat.setText(maintenance.getMerkalat());
        holder.merkalat.setBackgroundResource(R.drawable.table_content_cell_bg);
        holder.detaillokasialat.setText(maintenance.getNamaLokasi());
        holder.detaillokasialat.setBackgroundResource(R.drawable.table_content_cell_bg);

    }

    @Override
    public int getItemCount() {

        return listMaintenance.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView kodealat;
        private TextView merkalat;
        private TextView detaillokasialat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            kodealat = itemView.findViewById(R.id.kodealat);
            merkalat = itemView.findViewById(R.id.merkalat);
            detaillokasialat = itemView.findViewById(R.id.detaillokasialat);
        }
    }
}
