package com.vivero.persistencia;

import java.util.ArrayList;
import java.util.List;

import com.vivero.entidades.Pedido;

import jakarta.persistence.EntityManager;

public class PedidoDAO {
    
    public void guardarPedido(Pedido pedido) throws Exception {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(pedido);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al guardar el pedido: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void actualizarPedido(Pedido pedido) throws Exception {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(pedido);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al actualizar el pedido: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void eliminarPedido(int idPedido) throws Exception {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Pedido pedido = em.find(Pedido.class, idPedido);
            if(pedido != null){
                em.remove(pedido);
            } else {
                throw new Exception("Error al eliminar el pedido con ID "+idPedido);
            }
        } catch (Exception e) {
            throw new Exception("Error al eliminar el pedido: "+e.getMessage());
        } finally {
            em.close();
        }
    }

    public Pedido buscarPedidoPorId(int idPedido) throws Exception {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Pedido pedido = em.find(Pedido.class, idPedido);
            if(pedido != null){
                throw new Exception("No se encontró el pedido con ID " + idPedido);
            } 
            return pedido;
        } catch (Exception e) {
            throw new Exception("Error al buscar el pedido: "+e.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Pedido> listarPedidos(){
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT pe FROM Pedido pe", Pedido.class).getResultList();
        } catch (Exception e) {
           System.err.println("Error al listar los pedidos" + e.getMessage());
           return new ArrayList<>();
        } finally {
            em.close();
        }
    }

}
