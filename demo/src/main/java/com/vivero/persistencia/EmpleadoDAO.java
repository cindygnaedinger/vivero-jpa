package com.vivero.persistencia;

import java.util.List;

import com.vivero.entidades.DetallePedido;
import com.vivero.entidades.Empleado;

import jakarta.persistence.EntityManager;

public class EmpleadoDAO {
    public void guardarEmpleado(Empleado empleado) throws Exception{
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(empleado);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error al guardar el empleado: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public Empleado buscarEmpleadoPorId(int idEmpleado){
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Empleado.class, idEmpleado);
        } catch (Exception e) {
            System.err.println("Error al buscar el empleado con ID "+idEmpleado+": "+e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    public void actualizarEmpleado(Empleado empleado) throws Exception{
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(empleado);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error al actualizar el empleado: " + e.getMessage());
        } finally {
            em.close();
        }
        
    }

    public void eliminarEmpleado(int idEmpleado){
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Empleado empleado = em.find(Empleado.class, idEmpleado);
            if(empleado != null){
                em.remove(empleado);
                em.getTransaction().commit();
            } else {
                em.getTransaction().rollback();
                System.err.println("No se encontró el empleado con ID "+idEmpleado);
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error al eliminar el empleado con ID "+idEmpleado+": "+e.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Empleado> buscarTodosLosEmpleados(){
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT e FROM Empleados e", Empleado.class).getResultList();
        } catch (Exception e) {
            System.err.println("Error al obtener la lista de empleados: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }
}