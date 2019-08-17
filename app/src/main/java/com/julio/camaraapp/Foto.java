package com.julio.camaraapp;

import android.net.Uri;

public class Foto {
    private String nombre;
    private Uri path;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Uri getPath() {
        return path;
    }

    public void setPath(Uri path) {
        this.path = path;
    }
}
