package com.vivero.entidades;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "gama_producto")
public class GamaProducto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gama")
    private int idGama;

    @Column(name = "descripcion_html")
    private String descripcionHtml; 

    @Column(name = "descripcion_texto")
    private String descripcionTexto;

    @Column(name = "gama")
    private String gama;

    @Column(name = "imagen")
    private String imagen;

    @OneToMany(mappedBy = "gama", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Producto> productos;

    public GamaProducto() {
    }

    public int getIdGama() {
        return idGama;
    }

    public void setIdGama(int idGama) {
        this.idGama = idGama;
    }

    public String getDescripcionHtml() {
        return descripcionHtml;
    }

    public void setDescripcionHtml(String descripcionHtml) {
        this.descripcionHtml = descripcionHtml;
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

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}

