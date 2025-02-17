package com.vivero.persistencia;

import java.util.List;

import com.vivero.entidades.Cliente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ClienteDAO {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ViveroPU");
    private final EntityManager em = emf.createEntityManager();

    public void guardarCliente(Cliente cliente){
        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error al guardar el cliente: " + e.getMessage());
        }
    }

    public Cliente buscarClientePorId(int idCliente){
        try {
            return em.find(Cliente.class, idCliente);
        } catch (Exception e) {
            System.err.println("Error al buscar el cliente con ID " + idCliente + ": " + e.getMessage());
            return null;
        }
    }

    public List<Cliente> buscarTodosLosClientes(){
        try {
            return em.createQuery("SELECT c FROM cliente c", Cliente.class).getResultList();
        } catch (Exception e) {
            System.err.println("Error al buscar la lista de clientes: " + e.getMessage());
            return null;
        }
    }

    public void actualizarCliente(Cliente cliente) {
        try {
            em.getTransaction().begin();
            em.merge(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error al actualizar el cliente: " + e.getMessage());
        }
    }

    public void eliminarCliente(int idCliente){
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
