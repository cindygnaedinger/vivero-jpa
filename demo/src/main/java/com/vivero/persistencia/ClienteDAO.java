package com.vivero.persistencia;

import java.util.List;

import com.vivero.entidades.Cliente;

import jakarta.persistence.EntityManager;

public class ClienteDAO {
    public void guardarCliente(Cliente cliente){
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error al guardar el cliente: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public Cliente buscarClientePorId(int idCliente){
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Cliente.class, idCliente);
        } catch (Exception e) {
            System.err.println("Error al buscar el cliente con ID " + idCliente + ": " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    public List<Cliente> buscarTodosLosClientes(){
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM cliente c", Cliente.class).getResultList();
        } catch (Exception e) {
            System.err.println("Error al buscar la lista de clientes: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    public void actualizarCliente(Cliente cliente) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error al actualizar el cliente: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void eliminarCliente(int idCliente){
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Cliente cliente = em.find(Cliente.class, idCliente);
            if(cliente != null){
                em.remove(cliente);
                em.getTransaction().commit();
            } else {
                em.getTransaction().rollback();
                System.err.println("No se encontró el cliente con ID "+idCliente);
            }
        } catch (Exception e) {
           em.getTransaction().rollback();
           System.err.println("Error al eliminar el cliente con ID "+idCliente+": "+e.getMessage());
        } finally {
            em.close();
        }
    }
}
