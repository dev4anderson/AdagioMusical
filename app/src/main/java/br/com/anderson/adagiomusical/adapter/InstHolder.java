package br.com.anderson.adagiomusical.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import br.com.anderson.adagiomusical.R;

/**
 * Created by andersonmacedo on 20/03/17.
 */

public class InstHolder extends RecyclerView.ViewHolder{

    final TextView modelo;
    final TextView marca;

    public InstHolder(View view) {
        super(view);
        modelo = (TextView) view.findViewById(R.id.tvModelo);
        marca = (TextView) view.findViewById(R.id.tvMarca);
    }

    public int getPos(){
        return getAdapterPosition();
    }

}
