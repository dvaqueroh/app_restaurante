package com.example.apprestaurantes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_LONG;

public class Adaptador extends BaseAdapter {
    private Context context;
    private ArrayList<restaurante> listaItems;

    public Adaptador(Context context, ArrayList<restaurante> listaItems) {
        this.context = context;
        this.listaItems = listaItems;
    }

    public Adaptador(MisRestaurantes misRestaurantes, int simple_list_item_1, ArrayList<restaurante> listaRest) {
    }

    @Override
    public int getCount() {

       return listaItems.size();
    }

    @Override
    public Object getItem(int position) {
        //return listaItems.get(position);
        return position;
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //restaurante item = (restaurante) getItem(position);

        restaurante item = listaItems.get(position);

        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_rest,null);
            //ImageView fotoREst = convertView.findViewById(R.id.imgItemRest);
            TextView nomRest = convertView.findViewById(R.id.etNombreRest);
            TextView catRest = convertView.findViewById(R.id.etCateRest);
            RatingBar rbVaRest = convertView.findViewById(R.id.rbValrRest);
            ImageView imvFav = convertView.findViewById(R.id.ivFav);

            //fotoREst.setImageResource();
            nomRest.setText(item.getNombre());
            catRest.setText(item.getCategoria());
            rbVaRest.setRating(Float.parseFloat(item.getValoracion()));
            boolean fav = item.isFavorito();
            if(fav){ // si el restaurante es favorito, marca la estrella
                imvFav.setActivated(true);
            }
            else{
                imvFav.setActivated(false);
            }
        }

        return  convertView;
    }// fin get view
}// fin clase adaptador
