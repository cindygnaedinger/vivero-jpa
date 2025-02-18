package com.vivero.persistencia;

import java.util.List;

import com.vivero.entidades.GamaProducto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class GamaProductoDAO {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ViveroPU");
    private final EntityManager em = emf.createEntityManager();

    public void guardarGamaProducto(GamaProducto gamaProducto){
        try {
            em.getTransaction().begin();
            em.persist(gamaProducto);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error al guardar la gama de producto: " + e.getMessage());
        }
    }

    public GamaProducto buscarGamaProductoPorId(int idGama){
        try {
            return em.find(GamaProducto.class, idGama);
        } catch (Exception e) {
            System.err.println("Error al buscar la gama de producto con ID "+idGama+": "+e.getMessage());
            return null;
        }
    }

    public List<GamaProducto> obtenerTodasLasGamasProductos(){
        try {
            return em.createQuery("SELECT g FROM GamaProducto g", GamaProducto.class).getResultList();
        } catch (Exception e) {
            System.err.println("Error al obtener la lista de gamas de productos: "+e.getMessage());
            return null;
        }
    }

    public void actualizarGamaProducto(GamaProducto gamaProducto){
        try {
            em.getTransaction().begin();
            em.merge(gamaProducto);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error al actualizar la gama de producto: " + e.getMessage());
        }
    }

    public void eliminarGamaProducto(int idGama){
        try {
            em.getTransaction().begin();
            GamaProducto gamaProducto = em.find(GamaProducto.class, idGama);
            if(gamaProducto != null){
                em.remove(gamaProducto);
                em.getTransaction().commit();
            } else {
                em.getTransaction().rollback();
                System.err.println("No se encontró la gama de producto con ID "+idGama);
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error al eliminar la gama de producto con ID "+idGama+": "+e.getMessage());
        }
    }

    public void cerrar(){
        try {
            if(em.isOpen()){
                em.close();
            }
            if(emf.isOpen()){
                emf.close();
            }
        } catch (Exception e) {
            System.err.println("Error al cerrar la conexión con la base de datos: "+e.getMessage());
        }
    }
}
