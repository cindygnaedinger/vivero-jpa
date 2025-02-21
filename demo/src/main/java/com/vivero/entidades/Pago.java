package com.vivero.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;


@Entity
@Table(name = "pago")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private int idPago;

    @Column(name = "fecha_pago")
    private LocalDate fechaPago; 

    @Column(name = "forma_pago")
    private String formaPago;

    @Column(name = "id_transaccion")
    private String idTransaccion; 

    @Column(name = "total", precision = 10, scale = 2)  // 10 dígitos en total, 2 decimales
    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pago() {
    }
}


