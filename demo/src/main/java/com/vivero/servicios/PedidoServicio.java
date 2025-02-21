package com.vivero.servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import com.vivero.entidades.Pedido;
import com.vivero.persistencia.PedidoDAO;

public class PedidoServicio {
    private final PedidoDAO daoPedido;

    public PedidoServicio(){
        this.daoPedido = new PedidoDAO();
    }

    public void crearPedido(int idPedido, int codigoPedido, String comentario, String estado, LocalDate fechaEntrega, LocalDate fechaEsperada, LocalDate fechaPedido){
        try {
            if (comentario == null || comentario.trim().isEmpty()) {
                throw new IllegalArgumentException("El comentario no puede estar vacío.");
            }
            if (estado == null || estado.trim().isEmpty()) {
                throw new IllegalArgumentException("El estado no puede estar vacío.");
            }
            Objects.requireNonNull(fechaEntrega, "La fecha de entrega no puede ser nula");
            if (fechaEntrega.isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("La fecha de entrega no puede ser una fecha pasada.");
            }
            Objects.requireNonNull(fechaEsperada, "La fecha esperada no puede ser nula");
            if (fechaEsperada.isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("La fecha esperada no puede ser una fecha pasada.");
            }
            Objects.requireNonNull(fechaPedido, "La fecha de pedido no puede ser nula");
            if (fechaPedido.isAfter(fechaEntrega)) {
                throw new IllegalArgumentException("La fecha de pedido no puede ser posterior a la fecha de entrega.");
            }

            Pedido nuevoPedido = new Pedido();
            nuevoPedido.setIdPedido(idPedido);
            nuevoPedido.setCodigoPedido(codigoPedido);
            nuevoPedido.setComentario(comentario);
            nuevoPedido.setEstado(estado);
            nuevoPedido.setFechaEntrega(fechaEntrega);
            nuevoPedido.setFechaEsperada(fechaEsperada);
            nuevoPedido.setFechaPedido(fechaPedido);

            daoPedido.guardarPedido(nuevoPedido);
            System.out.println("Pedido creado éxitosamente.");
        } catch (IllegalArgumentException e) {
            System.err.println("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("No se guardó la nueva oficina de manera correcta: " + e.getMessage());
        }
    }

    public List<Pedido> buscarTodosLosPedidos(){
        try {
            return daoPedido.buscarTodosLosPedidos();
        } catch (Exception e) {
            System.err.println("Error al listar pedidos: " + e.getMessage());
            return null;
        }
    }

    public Pedido buscarPedidoPorId(int idPedido){
        try {
            Pedido pedido = daoPedido.buscarPedidoPorId(idPedido);
            if(pedido == null){
                throw new Exception("No se encontró la oficina con ID: " + idPedido);
            }
            return pedido;
        } catch (Exception e) {
            System.err.println("Error al buscar el pedido: " + e.getMessage());
            return null;
        }
    }

    public void actualizarPedido(Pedido pedido){
        try {
            if (pedido == null) {
                throw new IllegalArgumentException("El pedido no puede ser nulo.");
            }
            if (pedido.getIdPedido() == 0) {
                throw new IllegalArgumentException("El ID de pedido es obligatorio para actualizar.");
            }
            daoPedido.actualizarPedido(pedido);
            System.out.println("Pedido actualizado correctamente.");
        } catch (IllegalArgumentException e) {
            System.err.println("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al actualizar el pedido: " + e.getMessage());
        }
    }

    public void eliminarPedido(int idPedido) {
        try {
            daoPedido.eliminarPedido(idPedido);
            System.out.println("Pedido eliminado correctamente.");
        } catch (Exception e) {
            System.err.println("Error al eliminar el pedido: " + e.getMessage());
        }
    }
}
