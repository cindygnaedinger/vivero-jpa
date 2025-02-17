package com.vivero.persistencia;

import com.vivero.entidades.Empleado;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EmpleadoDAO {
    
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ViveroPU");

    private final EntityManager em = emf.createEntityManager();

    public void guardarEmpleado(Empleado empleado) throws Exception{
        em.getTransaction().begin();
        em.persist(empleado);
        em.getTransaction().commit();
    }

    public Empleado buscarEmpleadoPorId(int idEmpleado){
        return em.find(Empleado.class, idEmpleado);
    }

    public void actualizarEmpleado(Empleado empleado) throws Exception{
        em.getTransaction().begin();
        em.merge(empleado);
        em.getTransaction().commit();
    }

    public void eliminarEmpleado(int idEmpleado) throws Exception
    {
        em.getTransaction().begin();
        Empleado empleado = em.find(Empleado.class, idEmpleado);
        if(empleado != null){
            em.remove(empleado);
        }
        em.getTransaction().commit();
    }

    public void cerrar(){
        em.close();
        emf.close();
    }
}
