package com.example.jpr.dopecurrencyconverter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.myViewHolder> {
    private LayoutInflater inflater;
    private List<Countries> Country;
    private Context c;

    public CountryAdapter(Context c , List<Countries> Country) {
        inflater = LayoutInflater.from(c);
        this.c = c;
        this.Country = Country;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycle, null);
        myViewHolder holder = new myViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {

        holder.text.setText(Country.get(position).getCountryName());
    }

    @Override
    public int getItemCount() {
        return Country.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        TextView text;

        public myViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String str = text.getText().toString();
                    Intent vatsala = new Intent(c, flag_display.class);
                    String flag = Country.get(getAdapterPosition()).getFlag();
                    vatsala.putExtra("shemale", str);
                    vatsala.putExtra("flag", flag);
                    vatsala.setFlags(vatsala.FLAG_ACTIVITY_NEW_TASK);
                    c.startActivity(vatsala);
                }
            });
        }
    }
}
