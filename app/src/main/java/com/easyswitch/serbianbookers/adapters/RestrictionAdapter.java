package com.easyswitch.serbianbookers.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.models.Restriction;
import com.easyswitch.serbianbookers.models.Restrictions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RestrictionAdapter extends RecyclerView.Adapter<RestrictionAdapter.RestrictionHolder> {

    private Context context;
    private List<Restrictions> restrictionList;
    private OnRestrictionClickListener onRestrictionClickListener;

    public RestrictionAdapter(Context context, List<Restrictions> restrictionList) {
        this.context = context;
        this.restrictionList = restrictionList;
    }

    public interface OnRestrictionClickListener {
        void onRestrictionPlanClick(View view, int position, Restrictions r);
    }

    public void setOnRestrictionClickListener(OnRestrictionClickListener onRestrictionClickListener) {
        this.onRestrictionClickListener = onRestrictionClickListener;
    }

    @NonNull
    @Override
    public RestrictionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RestrictionHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_price_restrictions, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RestrictionHolder holder, int position) {

        Restrictions restriction = restrictionList.get(position);

        holder.rbRoom.setText(restriction.getName());
    }

    @Override
    public int getItemCount() {
        return restrictionList.size();
    }

    class RestrictionHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.rbRoom)
        RadioButton rbRoom;

        public RestrictionHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.rbRoom)
        public  void click() {
            if (onRestrictionClickListener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                onRestrictionClickListener.onRestrictionPlanClick(itemView, getAdapterPosition(), restrictionList.get(getAdapterPosition()));
            }
        }
    }
}
