package com.example.mei_v;

import android.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mei_v.api.model.maintenance.HistoryMaintenance;

import java.util.ArrayList;
import java.util.List;

public class ListTableAdapter extends RecyclerView.Adapter<ListTableAdapter.ViewHolder> {
private final List<HistoryMaintenance> listMaintenance = new ArrayList<>();

    void setListMaintenance(List<HistoryMaintenance> listMaintenance){
        if (listMaintenance == null)return;
        this.listMaintenance.clear();
        this.listMaintenance.addAll(listMaintenance);
    }

    @NonNull
    @Override
    public ListTableAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        HistoryMaintenance maintenance=listMaintenance.get(position);
            holder.tanggalTabel.setText(maintenance.getWaktu());
            holder.tanggalTabel.setBackgroundResource(R.drawable.table_content_cell_bg);
            holder.teknisiTabel.setText(maintenance.getUsers());
            holder.teknisiTabel.setBackgroundResource(R.drawable.table_content_cell_bg);
            holder.detailMaintenance.setText(maintenance.getCatatan());
            holder.detailMaintenance.setBackgroundResource(R.drawable.table_content_cell_bg);
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    AlertDialog.Builder builder=new AlertDialog.Builder(view.getRootView().getContext());
//                    View dialogView=LayoutInflater.from(view.getRootView().getContext()).inflate(R.layout.maintenance_dialog,null);
//                    TextView dialog_waktu = (TextView) dialogView.findViewById(R.id.waktu);
//                    TextView dialog_user = (TextView) dialogView.findViewById(R.id.user);
//                    TextView dialog_catatan = (TextView) dialogView.findViewById(R.id.catatan);
//                    dialog_waktu.setText("Waktu : "+maintenance.getWaktu());
//                    dialog_user.setText("User : " +maintenance.getUsers());
//                    dialog_catatan.setText(maintenance.getCatatan());
//                    builder.setView(dialogView);
//                    builder.setCancelable(true);
//                    builder.show();
//                }
//            });
        holder.detailpanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(view.getRootView().getContext());
                View dialogView=LayoutInflater.from(view.getRootView().getContext()).inflate(R.layout.maintenance_dialog,null);
                TextView dialog_waktu = (TextView) dialogView.findViewById(R.id.waktu);
                TextView dialog_user = (TextView) dialogView.findViewById(R.id.user);
                TextView dialog_catatan = (TextView) dialogView.findViewById(R.id.catatan);
                dialog_waktu.setText("Waktu : "+maintenance.getWaktu());
                dialog_user.setText("User : " +maintenance.getUsers());
                dialog_catatan.setText(maintenance.getCatatan());
                builder.setView(dialogView);
                builder.setCancelable(true);
                builder.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listMaintenance.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tanggalTabel;
        private TextView teknisiTabel;
        private TextView detailMaintenance;
        private Button detailpanel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tanggalTabel = itemView.findViewById(R.id.tanggaltabel);
            teknisiTabel = itemView.findViewById(R.id.teknisitabel);
            detailMaintenance = itemView.findViewById(R.id.detailmaintenancetabel);
            detailpanel = itemView.findViewById(R.id.detailpanel);
        }
    }
}
