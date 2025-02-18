package com.vivero.persistencia;

import java.util.List;

import com.vivero.entidades.DetallePedido;

import jakarta.persistence.EntityManager;

public class DetallePedidoDAO {
    public void guardarDetallePedido(DetallePedido detallePedido){
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(detallePedido);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error al guardar el detalle del pedido: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public DetallePedido buscarDetallePedidoPorId(int idDetallePedido) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(DetallePedido.class, idDetallePedido);
        } catch (Exception e) {
            System.err.println("Error al buscar el detalle del pedido con ID "+idDetallePedido+": "+e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    public List<DetallePedido> buscarTodosLosDetallesPedidos(){
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT d FROM DetallePedido d", DetallePedido.class).getResultList();
        } catch (Exception e) {
            System.err.println("Error al obtener la lista de detalles de pedidos: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    public void actualizarDetallePedido(DetallePedido detallePedido){
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(detallePedido);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error al actualizar el detalle del pedido: "+ e.getMessage());
        } finally {
            em.close();
        }
    }

    public void eliminarDetallePedido(int idDetallePedido){
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            DetallePedido detallePedido = em.find(DetallePedido.class, idDetallePedido);
            if(detallePedido != null){
                em.remove(detallePedido);
                em.getTransaction().commit();
            } else {
                em.getTransaction().rollback();
                System.err.println("No se encontró el detalle del pedido con ID "+idDetallePedido);
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error al eliminar el detalle del pedido con ID " + idDetallePedido+ ": "+ e.getMessage());
        } finally {
            em.close();
        }
    }
}
