package com.vivero.servicios;

import java.util.List;

import com.vivero.entidades.DetallePedido;
import com.vivero.persistencia.DetallePedidoDAO;

public class DetallePedidoServicio {
    
    private final DetallePedidoDAO daoDetallePedido;

    public DetallePedidoServicio() {
        this.daoDetallePedido = new DetallePedidoDAO();
    }

    public void crearDetallePedido(int idDetallePedido, int cantidad, int numeroLinea, double precioUnidad) {
        try {
            if(cantidad <= 0){
                throw new IllegalArgumentException("La cantidad debe ser mayor a 0.");
            }
            if(numeroLinea <= 0){
                throw new IllegalArgumentException("El número de línea debe ser mayor a 0.");
            }
            if(precioUnidad <= 0){
                throw new IllegalArgumentException("El precio por unidad debe ser mayor a 0.");
            }
    
            DetallePedido nuevoDetallePedido = new DetallePedido();
            nuevoDetallePedido.setIdDetallePedido(idDetallePedido);
            nuevoDetallePedido.setCantidad(cantidad);
            nuevoDetallePedido.setNumeroLinea(numeroLinea);
            nuevoDetallePedido.setPrecioUnidad(precioUnidad);
    
            daoDetallePedido.guardarDetallePedido(nuevoDetallePedido);
            System.out.println("Detalle del pedido creado éxitosamente.");
        } catch (IllegalArgumentException e) {
            System.err.println("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("No se guardó el detalle del pedido de forma correcta.");
        }
    }

    public List<DetallePedido> buscarTodosLosDetallePedidos(){
        try {
            return daoDetallePedido.buscarTodosLosDetallesPedidos();
        } catch (Exception e) {
          System.err.println("Error al listar los detalles de pedidos: "+e.getMessage());
          return null;
        }
    }

    public DetallePedido buscarDetallePedidoPorId(int idDetallePedido){
        try {
            DetallePedido detallePedido = daoDetallePedido.buscarDetallePedidoPorId(idDetallePedido);
            if(detallePedido == null){
                throw new Exception("No se encontró el detalle de pedido con ID: "+ idDetallePedido);
            }
            return detallePedido;
        } catch (Exception e) {
            System.err.println("Error al buscar el detalle de pedido: "+e.getMessage());
            return null;
        }
    }

    public void actualizarDetallePedido(DetallePedido detallePedido){
        try {
            if(detallePedido == null){
                throw new IllegalArgumentException("El detalle de pedido no puede ser nulo.");
            }
            if(detallePedido.getIdDetallePedido() == 0 ){
                throw new IllegalArgumentException("El ID es necesario para actualizar.");
            }

            daoDetallePedido.actualizarDetallePedido(detallePedido);
            System.out.println("Detalle de pedido actualizado correctamente.");

        } catch (IllegalArgumentException e) {
         System.err.println("Error de validación: "+e.getMessage());
        } catch (Exception e) {
         System.err.println("Error al actualizar el detalle de pedido: "+e.getMessage());
        }
    }

    public void eliminarDetallePedido(int idDetallePedido){
        try {
            daoDetallePedido.eliminarDetallePedido(idDetallePedido);
            System.out.println("Detalle de pedido eliminado correctamente.");
        } catch (Exception e) {
           System.err.println("Error al eliminar el detalle de pedido: "+ e.getMessage());
        }
    }
}
