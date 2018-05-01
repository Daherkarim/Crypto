package com.example.karimdaher.crypto;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.karimdaher.crypto.fragments.CurrencyDetails;
import com.example.karimdaher.crypto.models.Currency;
import com.example.karimdaher.crypto.services.DeviceStorageManager;

import java.util.List;




public class
RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private static final String TAG = "recycler";
    private List<Currency> currencies;
    private RecyclerView.ViewHolder holder;
    private int position;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView currencyName;
        public TextView currecnySymbol;
        public TextView currencyPrice;
        public Button Favorites;

        public ViewHolder(View x) {
            super(x);
            currencyName = x.findViewById(R.id.currency_name);
            currecnySymbol = x.findViewById(R.id.currency_symbol);
            currencyPrice = x.findViewById(R.id.currency_price);
            Favorites = x.findViewById(R.id.favorites);
        }
    }
    public RecyclerAdapter(List<Currency> currencies) {
        this.currencies = currencies;
    }


    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.currency_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Currency currency = currencies.get(position);
        holder.currencyName.setText(currencies.get(position).getName());
        holder.currecnySymbol.setText(currencies.get(position).getSymbol());
        holder.currencyPrice.setText(currencies.get(position).getPrice_usd());
        holder.Favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeviceStorageManager deviceStorageManager = new DeviceStorageManager(view.getContext());
                deviceStorageManager.saveCurrency(currency);
                Intent i = new Intent(view.getContext(),CurrencyDetails.class);
                view.getContext().startActivity(i);
            }
        });

//        Log.d(TAG, "onBindViewHolder: called.");
    }

    @Override
    public int getItemCount() {
        return currencies.size();
    }
}