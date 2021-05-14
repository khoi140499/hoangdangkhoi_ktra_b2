package com.example.hoangdangkhoi_ktra2_bai2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class ReAdapter extends RecyclerView.Adapter<ReAdapter.qgviewholder> {
    private Context context;
    private List<QuyenGop> qg;
    public ReAdapter(Context context) {
        this.context=context;
        this.qg=qg;
    }
    public void themList(List<QuyenGop> qg){
        this.qg=qg;
    }
    @NonNull
    @Override
    public qgviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View a=inflater.inflate(R.layout.cardview, parent, false);
        return new qgviewholder(a);
    }

    @Override
    public void onBindViewHolder(@NonNull  ReAdapter.qgviewholder holder, int position) {
        QuyenGop s = qg.get(position);
        holder.txtID.setText(s.getStt()+"");
        holder.txtName.setText(s.getHoten());
        holder.txtTP.setText(s.getThanhpho());
        holder.txtNgay.setText(s.getNgay());
        holder.txtSoTien.setText(s.getSotien()+"");
    }

    @Override
    public int getItemCount() {
        if(qg.size()>0){
            return qg.size();
        }
        return 0;
    }

    public class qgviewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView txtID;
        private TextView txtName;
        private TextView txtTP;
        private TextView txtNgay;
        private TextView txtSoTien;
        public qgviewholder(@NonNull  View itemView) {
            super(itemView);
            itemView.setOnClickListener((View.OnClickListener) this);
            txtID=itemView.findViewById(R.id.id_card);
            txtName=itemView.findViewById(R.id.hoten_card);
            txtTP=itemView.findViewById(R.id.tp_card);
            txtNgay=itemView.findViewById(R.id.date_card);
            txtSoTien=itemView.findViewById(R.id.sotien_card);
        }

        @Override
        public void onClick(View v) {
            int po=getLayoutPosition();
            Intent a=new Intent(context, UpdateQG.class);
            QuyenGop as=qg.get(po);
            a.putExtra("qg", as.toString());
            context.startActivity(a);
        }
    }
}
