package com.vivero.persistencia;

import java.util.List;

import com.vivero.entidades.GamaProducto;

import jakarta.persistence.EntityManager;

public class GamaProductoDAO {

    public void guardarGamaProducto(GamaProducto gamaProducto){
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(gamaProducto);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error al guardar la gama de producto: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public GamaProducto buscarGamaProductoPorId(int idGama){
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(GamaProducto.class, idGama);
        } catch (Exception e) {
            System.err.println("Error al buscar la gama de producto con ID "+idGama+": "+e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    public List<GamaProducto> buscarTodasLasGamasProductos(){
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT g FROM GamaProducto g", GamaProducto.class).getResultList();
        } catch (Exception e) {
            System.err.println("Error al obtener la lista de gamas de productos: "+e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    public void actualizarGamaProducto(GamaProducto gamaProducto){
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(gamaProducto);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error al actualizar la gama de producto: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void eliminarGamaProducto(int idGama){
        EntityManager em = JPAUtil.getEntityManager();
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
        } finally {
            em.close();
        }
    }
}
