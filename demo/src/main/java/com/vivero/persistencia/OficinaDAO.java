package com.vivero.persistencia;

import java.util.List;

import com.vivero.entidades.Oficina;

import jakarta.persistence.EntityManager;

public class OficinaDAO {
    // primero: creo el entitymanager y lo cierro al final de los metodos
    // segundo: creo el metodo persistir para guardar oficina en la base de datos
    public void guardarOficina(Oficina oficina) throws Exception{
        EntityManager em = JPAUtil.getEntityManager();
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
        } finally {
            em.close();
        }
    }

    // tercero: metodo para actualizar oficina
    public void actualizarOficina(Oficina oficina) throws Exception{
        EntityManager em = JPAUtil.getEntityManager();
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
        } finally {
            em.close();
        }
    }

    // cuarto: metodo para eliminar oficina
    public void eliminarOficina(int idOficina) throws Exception {
        EntityManager em = JPAUtil.getEntityManager();
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
        } finally {
            em.close();
        }
    }

    // quinto: metodo para obtener una oficina por id
    public Oficina buscarOficinaPorId(int idOficina) throws Exception{
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Oficina.class, idOficina);
        } catch (Exception e) {
            throw new Exception("Error al obtener la oficina: "+e.getMessage(), e);
        } finally {
            em.close();
        }
    }
    // quinto: metodo para buscar todas las oficinas
    public List<Oficina> buscarTodasLasOficinas(){
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT o FROM Oficina o", Oficina.class).getResultList();
        } catch (Exception e) {
            System.err.println("Error al obtener la lista de empleados: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }
}

