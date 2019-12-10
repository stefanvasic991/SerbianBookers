package com.easyswitch.serbianbookers.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.models.Guest;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuestAdapter extends RecyclerView.Adapter<GuestAdapter.GuestHolder> {

    Context context;
    ArrayList<Guest> guestList;

    public GuestAdapter(Context context, ArrayList<Guest> guestList) {
        this.context = context;
        this.guestList = guestList;
    }

    @NonNull
    @Override
    public GuestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GuestHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_guests, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GuestHolder holder, int position) {

        Guest guest = guestList.get(position);

        holder.tvName.setText(guest.getName());
        holder.tvEmail.setText(guest.getEmail());
    }

    @Override
    public int getItemCount() {
        return guestList.size();
    }

    class GuestHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvEmail)
        TextView tvEmail;

        public GuestHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
