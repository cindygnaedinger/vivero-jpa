package com.vivero.servicios;

import java.util.List;

import com.vivero.entidades.Producto;
import com.vivero.persistencia.ProductoDAO;

public class ProductoServicio {
    private final ProductoDAO daoProducto;

    public ProductoServicio(){
        this.daoProducto = new ProductoDAO();
    }

    public void crearProducto(int idProducto, int cantidadStock, String codigoProducto, String descripcion, String dimensiones, String nombre, int precioProveedor, int precioVenta, String proveedor){
        try {
            if(cantidadStock <= 0) {
                throw new IllegalArgumentException("La cantidad de stock debe ser mayor a 0.");
            }
            if (codigoProducto == null || codigoProducto.trim().isEmpty()) {
                throw new IllegalArgumentException("El codigo del producto no puede estar vacío.");
            }
            if (descripcion == null || descripcion.trim().isEmpty()) {
                throw new IllegalArgumentException("La descripción no puede estar vacía.");
            }
            if (dimensiones == null || dimensiones.trim().isEmpty()) {
                throw new IllegalArgumentException("La dimensión no puede estar vacía.");
            }
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new IllegalArgumentException("El nombre no puede estar vacío.");
            }
            if(precioProveedor <= 0) {
                throw new IllegalArgumentException("El precio del proveedor debe ser mayor a 0.");
            }
            if(precioVenta <= 0) {
                throw new IllegalArgumentException("El precio de venta debe ser mayor a 0.");
            }
            if (proveedor == null || proveedor.trim().isEmpty()) {
                throw new IllegalArgumentException("El proveedor no puede estar vacío.");
            }

            Producto nuevoProducto = new Producto();
            nuevoProducto.setIdProducto(idProducto);
            nuevoProducto.setCantidadStock(cantidadStock);
            nuevoProducto.setCodigoProducto(codigoProducto);
            nuevoProducto.setDescripcion(descripcion);
            nuevoProducto.setDimensiones(dimensiones);
            nuevoProducto.setNombre(nombre);
            nuevoProducto.setPrecioProveedor(precioProveedor);
            nuevoProducto.setPrecioVenta(precioVenta);
            nuevoProducto.setProveedor(proveedor);

            daoProducto.guardarProducto(nuevoProducto);
            System.out.println("Producto creado éxitosamente.");
        } catch (IllegalArgumentException e) {
            System.err.println("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("No se guardó el nuevo producto de manera correcta: " + e.getMessage());
        }
    }

    public List<Producto> buscarTodosLosProductos(){
        try {
            return daoProducto.buscarTodosLosProductos();
        } catch (Exception e) {
            System.err.println("Error al listar productos: " + e.getMessage());
            return null;
        }
    }

    public Producto buscarProductoPorId(int idProducto){
        try {
            Producto producto = daoProducto.buscarProductoPorId(idProducto);
            if(producto == null){
                throw new Exception("No se encontró el producto con ID: " + idProducto);
            }
            return producto;
        } catch (Exception e) {
            System.err.println("Error al buscar el producto: " + e.getMessage());
            return null;
        }
    }

    public void actualizarProducto(Producto producto){
        try {
            if (producto == null) {
                throw new IllegalArgumentException("El producto no puede ser nulo.");
            }
            if (producto.getIdProducto() == 0) {
                throw new IllegalArgumentException("El ID de producto es obligatorio para actualizar.");
            }
            daoProducto.actualizarProducto(producto);
            System.out.println("Producto actualizado correctamente.");
        } catch (IllegalArgumentException e) {
            System.err.println("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al actualizar el producto: " + e.getMessage());
        }
    }

    public void eliminarProducto(int idProducto) {
        try {
            daoProducto.eliminarProducto(idProducto);
            System.out.println("Producto eliminado correctamente.");
        } catch (Exception e) {
            System.err.println("Error al eliminar el producto: " + e.getMessage());
        }
    }
}
