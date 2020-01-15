package com.easyswitch.serbianbookers.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.easyswitch.serbianbookers.R;
import com.easyswitch.serbianbookers.models.Price;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PriceAdapter extends RecyclerView.Adapter<PriceAdapter.PriceHolder> {

    private Context context;
    private List<Price> priceList;
    private OnPriceClickListener onPriceClickListener;

    public PriceAdapter(Context context, List<Price> priceList) {
        this.context = context;
        this.priceList = priceList;
    }

    public interface OnPriceClickListener {
        void onPricingPlanClick(View view, int position, Price price);
    }

    public void setOnPriceClickListener(OnPriceClickListener onPriceClickListener) {
        this.onPriceClickListener = onPriceClickListener;
    }

    @NonNull
    @Override
    public PriceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PriceHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_price_restrictions, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PriceHolder holder, int position) {

        Price price = priceList.get(position);

        holder.rbRoom.setText(price.getName());
    }

    @Override
    public int getItemCount() {
        return priceList.size();
    }

    class PriceHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rbRoom)
        RadioButton rbRoom;

        public PriceHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.llClick)
        public  void click() {
            if (onPriceClickListener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                onPriceClickListener.onPricingPlanClick(itemView, getAdapterPosition(), priceList.get(getAdapterPosition()));
            }
        }
    }
}
