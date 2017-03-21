package br.com.anderson.adagiomusical.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

import br.com.anderson.adagiomusical.R;
import br.com.anderson.adagiomusical.activity.AddActivity;
import br.com.anderson.adagiomusical.model.Instrument;


/**
 * Created by andersonmacedo on 20/03/17.
 */

public class InstAdapter extends RecyclerView.Adapter {

    private List<Instrument> instruments;
    private Context context;

    public InstAdapter(List<Instrument> instruments, Context context) {
        this.instruments = instruments;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.lista_item, parent, false);

        InstHolder holder = new InstHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final InstHolder instHolder = (InstHolder) holder;

        final Instrument instrument  = instruments.get(position) ;

        instHolder.modelo.setText(instrument.getModelo());
        instHolder.marca.setText(instrument.getMarca());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, AddActivity.class);
                intent.putExtra("instrument", instrument);
                context.startActivity(intent);

            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return instruments.size();
    }

    public Instrument getItem(int position){
        return instruments.get(position);
    }
}
