package com.vivero.persistencia;

import com.vivero.entidades.Oficina;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class OficinaDAO {
    // primero: creo el entitymanagerfactory y el entitymanager
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ViveroPU");
    private final EntityManager em = emf.createEntityManager();

    // segundo: creo el metodo persistir para guardar oficina en la base de datos
    public void guardarOficina(Oficina oficina) throws Exception{
        try {
            // iniciar, persistir y confirmar transaccion
            em.getTransaction().begin();
            em.persist(oficina);
            em.getTransaction().commit();
        } catch (Exception e) {
            // si hay error, hago un rollback para evitar problemas en la base de datos
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw new Exception("Error al guardar la oficina: "+e.getMessage(), e);
        }
    }

    // tercero: metodo para actualizar oficina
    public void actualizarOficina(Oficina oficina) throws Exception{
        try {
            // iniciar, actualizar, confirmar
            em.getTransaction().begin();
            em.merge(oficina);
            em.getTransaction().commit();
        } catch (Exception e) {
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw new Exception("Error al actualizar la oficina: "+e.getMessage(), e);
        }
    }

    // cuarto: metodo para eliminar oficina
    public void eliminarOficina(int idOficina) throws Exception {
        try {
            // busco la oficina por id
            Oficina oficina = em.find(Oficina.class, idOficina);
            if(oficina != null){
                em.getTransaction().begin();
                em.remove(oficina);
                em.getTransaction().commit();
            } else {
                throw new Exception("Oficina no encontrada");
            }
        } catch (Exception e) {
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw new Exception("Error al eliminar la oficina: "+e.getMessage(), e);
        }
    }

    // quinto: metodo para obtener una oficina por id
    public Oficina obtieneOficina(int idOficina) throws Exception{
        try {
            return em.find(Oficina.class, idOficina);
        } catch (Exception e) {
            throw new Exception("Error al obtener la oficina: "+e.getMessage(), e);
        }
    }

    // sexto: cierro la conexion del entitymanager
    public void cerrar(){
        if(em.isOpen()){
            em.close();
        }
        if(emf.isOpen()){
            emf.close();
        }
    }
}
