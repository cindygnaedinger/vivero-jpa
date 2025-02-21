package com.vivero.servicios;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import com.vivero.entidades.Pago;
import com.vivero.persistencia.PagoDAO;

public class PagoServicio {
    private final PagoDAO daoPago;

    public PagoServicio() {
        this.daoPago = new PagoDAO();
    }

    public void crearPago(int idPago, LocalDate fechaPago, String formaPago, String idTransaccion, BigDecimal total) {
        try {
            Objects.requireNonNull(fechaPago, "La fecha de pago no puede ser nula");
            if (fechaPago.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("La fecha de pago no puede estar en el futuro");
            }
            if (formaPago == null || formaPago.trim().isEmpty()) {
                throw new IllegalArgumentException("La forma de pago no puede estar vacía.");
            }
            if (idTransaccion == null || idTransaccion.trim().isEmpty()) {
                throw new IllegalArgumentException("El ID de transacción no puede estar vacío.");
            }
            if (total.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("El total debe ser mayor que cero");
            }

            Pago nuevoPago = new Pago();
            nuevoPago.setIdPago(idPago);
            nuevoPago.setFechaPago(fechaPago);
            nuevoPago.setFormaPago(formaPago);
            nuevoPago.setIdTransaccion(idTransaccion);
            nuevoPago.setTotal(total);

            daoPago.guardarPago(nuevoPago);
            System.out.println("Pago creado éxitosamente.");

        } catch (IllegalArgumentException e) {
            System.err.println("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("No se guardó el nuevo pago de manera correcta: " + e.getMessage());
        }
    }

    public List<Pago> buscarTodosLosPagos() {
        try {
            return daoPago.buscarTodosLosPagos();
        } catch (Exception e) {
            System.err.println("Error al listar pagos: " + e.getMessage());
            return null;
        }
    }

    public Pago buscarPagoPorId(int idPago) {
        try {
            Pago pago = daoPago.buscarPagoPorId(idPago);
            if (pago == null) {
                throw new Exception("No se encontró el pago con ID: " + idPago);
            }
            return pago;
        } catch (Exception e) {
            System.err.println("Error al buscar el pago: " + e.getMessage());
            return null;
        }
    }

    public void actualizarPago(Pago pago) {
        try {
            if (pago == null) {
                throw new IllegalArgumentException("El pago no puede ser nulo.");
            }
            if (pago.getIdPago() == 0) {
                throw new IllegalArgumentException("El ID de pago es obligatorio para actualizar.");
            }

            daoPago.actualizarPago(pago);
            System.out.println("Pago actualizado correctamente.");

        } catch (IllegalArgumentException e) {
            System.err.println("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al actualizar el pago: " + e.getMessage());
        }
    }

    public void eliminarPago(int idPago){
        try {
            daoPago.eliminarPago(idPago);
            System.out.println("Pago eliminado correctamente.");
        } catch (Exception e) {
            System.err.println("Error al eliminar la oficina: " + e.getMessage());
        }
    }


}