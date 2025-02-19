package com.vivero.servicios;

import com.vivero.entidades.Oficina;
import com.vivero.persistencia.OficinaDAO;
import java.util.List;

public class OficinaServicio {

    private final OficinaDAO daoOficina;

    public OficinaServicio() {
        this.daoOficina = new OficinaDAO();
    }

    // Crear una nueva oficina
    public void crearOficina(int idOficina, String ciudad, String pais,
                              String region, String telefono, String codigoPostal) {
        try {
            // Validaciones básicas
            if (ciudad == null || ciudad.trim().isEmpty()) {
                throw new IllegalArgumentException("La ciudad no puede estar vacía.");
            }
            if (pais == null || pais.trim().isEmpty()) {
                throw new IllegalArgumentException("El país no puede estar vacío.");
            }
            if (telefono == null || telefono.trim().isEmpty()) {
                throw new IllegalArgumentException("El teléfono no puede estar vacío.");
            }
            if (codigoPostal == null || codigoPostal.trim().isEmpty()) {
                throw new IllegalArgumentException("El código postal no puede estar vacío.");
            }

            Oficina oficinaNueva = new Oficina();
            oficinaNueva.setIdOficina(idOficina);
            oficinaNueva.setCiudad(ciudad);
            oficinaNueva.setPais(pais);
            oficinaNueva.setRegion(region);
            oficinaNueva.setTelefono(telefono);
            oficinaNueva.setCodigoPostal(codigoPostal);

            daoOficina.guardarOficina(oficinaNueva);
            System.out.println("Oficina creada exitosamente.");

        } catch (IllegalArgumentException e) {
            System.err.println("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("No se guardó la nueva oficina de manera correcta: " + e.getMessage());
        }
    }

    // Listar todas las oficinas
    public List<Oficina> buscarTodasLasOficinas() {
        try {
            return daoOficina.buscarTodasLasOficinas();
        } catch (Exception e) {
            System.err.println("Error al listar oficinas: " + e.getMessage());
            return null;
        }
    }

    // Buscar una oficina por ID
    public Oficina buscarOficinaPorId(int idOficina) {
        try {
            Oficina oficina = daoOficina.buscarOficinaPorId(idOficina);
            if (oficina == null) {
                throw new Exception("No se encontró la oficina con ID: " + idOficina);
            }
            return oficina;
        } catch (Exception e) {
            System.err.println("Error al buscar la oficina: " + e.getMessage());
            return null;
        }
    }

    // Actualizar una oficina existente
    public void actualizarOficina(Oficina oficina) {
        try {
            if (oficina == null) {
                throw new IllegalArgumentException("La oficina no puede ser nula.");
            }
            if (oficina.getIdOficina() == 0) {
                throw new IllegalArgumentException("El ID de oficina es obligatorio para actualizar.");
            }

            daoOficina.actualizarOficina(oficina);
            System.out.println("Oficina actualizada correctamente.");

        } catch (IllegalArgumentException e) {
            System.err.println("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al actualizar la oficina: " + e.getMessage());
        }
    }

    // Eliminar una oficina por ID
    public void eliminarOficina(int idOficina) {
        try {
            daoOficina.eliminarOficina(idOficina);
            System.out.println("Oficina eliminada correctamente.");
        } catch (Exception e) {
            System.err.println("Error al eliminar la oficina: " + e.getMessage());
        }
    }
}




