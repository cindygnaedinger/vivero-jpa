package com.vivero.persistencia;

import java.util.List;

import com.vivero.entidades.Producto;

import jakarta.persistence.EntityManager;

public class ProductoDAO {
    
    public void guardarProducto(Producto producto) throws Exception{
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(producto);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al guardar el producto: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void actualizarProducto(Producto producto) throws Exception {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(producto);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al actualizar el producto: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void eliminarProducto(int idProducto) throws Exception {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Producto producto = em.find(Producto.class, idProducto);
            if(producto != null) {
                em.remove(producto);
            } else {
                throw new Exception("Error al eliminar el producto con ID " + idProducto);
            }
        } catch (Exception e) {
            throw new Exception("Error al eliminar el producto " + idProducto);
        } finally {
            em.close();
        }
    }

    public Producto buscarProductoPorId(int idProducto) throws Exception {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Producto producto = em.find(Producto.class, idProducto);
            if(producto != null){
                throw new Exception("No se encontró el producto con ID " + idProducto);
            } 
            return producto;
        } catch (Exception e) {
            throw new Exception("Error al buscar el producto: "+e.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Producto> buscarTodosLosProductos(){
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT pr FROM Producto pr", Producto.class).getResultList();
        } catch (Exception e) {
           System.err.println("Error al listar los productos" + e.getMessage());
           return null;
        } finally {
            em.close();
        }
    }
}
