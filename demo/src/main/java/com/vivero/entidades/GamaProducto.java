package com.vivero.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "gama_producto")
public class GamaProducto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gama")
    private int idGama;

    @Column(name = "descripcion_html")
    private String descripcionHmtl;

    @Column(name = "descripcion_texto")
    private String descripcionTexto;

    @Column(name = "gama")
    private String gama;

    @Column(name = "imagen")
    private String imagen;

    public GamaProducto() {
    }

    public int getIdGama() {
        return idGama;
    }

    public void setIdGama(int idGama) {
        this.idGama = idGama;
    }

    public String getDescripcionHmtl() {
        return descripcionHmtl;
    }

    public void setDescripcionHmtl(String descripcionHmtl) {
        this.descripcionHmtl = descripcionHmtl;
    }

    public String getDescripcionTexto() {
        return descripcionTexto;
    }

    public void setDescripcionTexto(String descripcionTexto) {
        this.descripcionTexto = descripcionTexto;
    }

    public String getGama() {
        return gama;
    }

    public void setGama(String gama) {
        this.gama = gama;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
