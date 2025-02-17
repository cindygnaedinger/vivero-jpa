package com.vivero.persistencia;

import java.util.List;

import com.vivero.entidades.DetallePedido;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DetallePedidoDAO {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ViveroPU");
    private final EntityManager em = emf.createEntityManager();

    public void guardarDetallePedido(DetallePedido detallePedido){
        try {
            em.getTransaction().begin();
            em.persist(detallePedido);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error al guardar el detalle del pedido: " + e.getMessage());
        }
    }

    public DetallePedido buscarDetallePedidoPorId(int idDetallePedido) {
        try {
            return em.find(DetallePedido.class, idDetallePedido);
        } catch (Exception e) {
            System.err.println("Error al buscar el detalle del pedido con ID "+idDetallePedido+": "+e.getMessage());
            return null;
        }
    }

    public List<DetallePedido> buscarTodosLosDetallesPedidos(){
        try {
            return em.createQuery("SELECT d FROM detalle_pedido d", DetallePedido.class).getResultList();
        } catch (Exception e) {
            System.err.println("Error al obtener la lista de detalles de pedidos: " + e.getMessage());
            return null;
        }
    }

    public void actualizarDetallePedido(DetallePedido detallePedido){
        try {
            em.getTransaction().begin();
            em.merge(detallePedido);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error al actualizar el detalle del pedido: "+ e.getMessage());
        }
    }

    public void eliminarDetallePedido(int idDetallePedido){
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
        }
    }

    public void cerrar(){
        try {
            if(em.isOpen()){
                em.close();
            }
            if(em.isOpen()){
                em.close();
            }
        } catch (Exception e) {
            System.err.println("Error al cerrar la conexión con la base de datos: "+e.getMessage());
        }
    }
}
