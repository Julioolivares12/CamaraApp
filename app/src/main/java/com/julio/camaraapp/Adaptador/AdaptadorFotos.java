package com.julio.camaraapp.Adaptador;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.julio.camaraapp.Foto;
import com.julio.camaraapp.R;

import java.util.List;

public class AdaptadorFotos extends RecyclerView.Adapter<AdaptadorFotos.FotosHolder> {
    List<Foto> fotos;

   public AdaptadorFotos(List<Foto> fotos){
        this.fotos = fotos;

    }
    @NonNull
    @Override
    public AdaptadorFotos.FotosHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater =LayoutInflater.from(viewGroup.getContext());
        return new FotosHolder(layoutInflater.inflate(R.layout.fila,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorFotos.FotosHolder fotosHolder, int i) {
        Foto foto = fotos.get(i);
        fotosHolder.image_item.setImageURI(foto.getPath());
        fotosHolder.tv_nombre.setText(foto.getNombre());
    }

    @Override
    public int getItemCount() {
        return fotos.size();
    }

    class FotosHolder extends RecyclerView.ViewHolder {
        TextView tv_nombre;
        ImageView image_item;
        FotosHolder(@NonNull View itemView) {
            super(itemView);
            tv_nombre = itemView.findViewById(R.id.tvNombreImagen);
            image_item = itemView.findViewById(R.id.image_item);
        }
    }
}
