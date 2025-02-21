package com.vivero.persistencia;

import java.util.List;

import com.vivero.entidades.Pago;

import jakarta.persistence.EntityManager;

public class PagoDAO {

    public void guardarPago(Pago pago) throws Exception{
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(pago);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al guardar el pago: "+e.getMessage());
        } finally {
            em.close();
        }
    }

    public void actualizarPago(Pago pago) throws Exception {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(pago);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al actualizar el pago: "+e.getMessage());
        } finally {
            em.close();
        }
    }

    public void eliminarPago(int idPago) throws Exception{
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Pago pago = em.find(Pago.class, idPago);
            if(pago != null){
                em.remove(pago);
            } else {
                throw new Exception("No se encontró el pago con ID "+idPago);
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al eliminar el pago: "+e.getMessage());
        } finally {
            em.close();
        }
    }

    public Pago buscarPagoPorId(int idPago) throws Exception {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Pago pago = em.find(Pago.class, idPago);
            if (pago == null) {
                throw new Exception("No se encontró el pago con ID " + idPago);
            }
            return pago;
        } finally {
            em.close();
        }
    }
    
    public List<Pago> buscarTodosLosPagos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Pago p", Pago.class).getResultList();
        } finally {
            em.close();
        }
    }

}
