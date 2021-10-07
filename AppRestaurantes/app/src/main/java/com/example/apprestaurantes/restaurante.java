package com.example.apprestaurantes;

public class restaurante {
    private String nombre,direccion,categoria,comentario,valoracion;
    private int telefono,precio;
    private boolean favorito;

    public restaurante(String nombre, String direccion, int telefono, String categoria, int precio, String comentario, String valoracion, boolean favorito) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.categoria = categoria;
        this.precio = precio;
        this.comentario = comentario;
        this.valoracion = valoracion;
        this.favorito = favorito;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getValoracion() {
        return valoracion;
    }

    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}// fin clase restaurante
