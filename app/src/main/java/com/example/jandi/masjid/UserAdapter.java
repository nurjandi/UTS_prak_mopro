package com.example.jandi.masjid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {


    private ArrayList<model_user> dataList;
    private Context context;

    public UserAdapter(ArrayList<model_user> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.users, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, final int position) {
        holder.txtNama.setText(dataList.get(position).getNama());
        holder.txtNpm.setText(dataList.get(position).getJabatan());
        Picasso.with(context).load(dataList.get(position).getGambar()).into(holder.gambar);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, detail_user.class);
                intent.putExtra("ID", dataList.get(position).getID());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNama, txtNpm;
        private ImageView gambar;
        private LinearLayout linearLayout;

        public UserViewHolder(View itemView) {
            super(itemView);
            txtNama = (TextView) itemView.findViewById(R.id.text1);
            txtNpm = (TextView) itemView.findViewById(R.id.text2);
            gambar = (ImageView) itemView.findViewById(R.id.gambar);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }
    }
}

