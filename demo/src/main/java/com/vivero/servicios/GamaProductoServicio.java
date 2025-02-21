package com.vivero.servicios;

import java.util.List;
import com.vivero.entidades.GamaProducto;
import com.vivero.persistencia.GamaProductoDAO;

public class GamaProductoServicio {
    private final GamaProductoDAO daoGamaProducto;

    public GamaProductoServicio(){
        this.daoGamaProducto = new GamaProductoDAO();
    }

    public void crearGamaProducto(int idGama, String descripcionTexto, String gama, String imagen){
        try {
            if(descripcionTexto == null || descripcionTexto.trim().isEmpty()) {
                throw new IllegalArgumentException("El campo descripción no puede estar vacío.");
            }
            if(gama == null || gama.trim().isEmpty()) {
                throw new IllegalArgumentException("El campo gama no puede estar vacío.");
            }

            GamaProducto nuevoGamaProducto = new GamaProducto();
            nuevoGamaProducto.setIdGama(idGama);
            nuevoGamaProducto.setDescripcionTexto(descripcionTexto);
            nuevoGamaProducto.setGama(gama);
            nuevoGamaProducto.setImagen(imagen);

            daoGamaProducto.guardarGamaProducto(nuevoGamaProducto);
            System.out.println("Gama del producto creada éxitosamente.");
        } catch (IllegalArgumentException e) {
            System.err.println("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("No se guardó la gama del producto de forma correcta: " + e.getMessage());
        }
    }

    public List<GamaProducto> buscarTodasLasGamasProductos(){
        try {
            return daoGamaProducto.buscarTodasLasGamasProductos();
        } catch (Exception e) {
            System.err.println("Error al listar las gamas de productos: "+e.getMessage());
            return null;
        }
    }

    public GamaProducto buscarGamaProductoPorId(int idGama){
        try {
            GamaProducto gamaProducto = daoGamaProducto.buscarGamaProductoPorId(idGama);
            if(gamaProducto == null){
                throw new Exception("No se encontró la gama con ID: "+ idGama);
            }
            return gamaProducto;
        } catch (Exception e) {
            System.err.println("Error al buscar la gama: "+e.getMessage());
            return null;
        }
    }

    public void actualizarGamaProducto(GamaProducto gamaProducto){
        try {
            if(gamaProducto == null) {
                throw new IllegalArgumentException("La gama no puede ser nula.");
            }
            if (gamaProducto.getIdGama() == 0) {
                throw new IllegalArgumentException("El ID es necesario para actualizar.");
            }

            daoGamaProducto.actualizarGamaProducto(gamaProducto);
            System.out.println("Gama actualizada correctamente.");
        } catch (IllegalArgumentException e) {
            System.err.println("Error de validación: "+e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al actualizar la gama: " + e.getMessage());
        }
    }

    public void eliminarGamaProducto(int idGama){
        try {
            daoGamaProducto.eliminarGamaProducto(idGama);
            System.out.println("Gama eliminada correctamente.");
        } catch (Exception e) {
            System.err.println("Error al eliminar la gama: "+e.getMessage());
        }
    }
}
