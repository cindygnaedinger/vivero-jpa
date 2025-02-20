package com.vivero.servicios;

import java.util.List;

import com.vivero.entidades.Cliente;
import com.vivero.persistencia.ClienteDAO;

public class ClienteServicio {
    private final ClienteDAO daoCliente;

    public ClienteServicio() {
        this.daoCliente = new ClienteDAO();
    }

    public void crearCliente(int idCliente, String apellido, String ciudad, String codigoPostal, String fax, double limiteCredito, String nombreCliente, String nombreContacto, String pais, String region, String telefono) {
        try {
            if(apellido == null || apellido.trim().isEmpty()) {
                throw new IllegalArgumentException("El campo apellido no puede estar vacío.");
            }
            if(ciudad == null || ciudad.trim().isEmpty()) {
                throw new IllegalArgumentException("El campo ciudad no puede estar vacío.");
            }
            if(codigoPostal == null || codigoPostal.trim().isEmpty()) {
                throw new IllegalArgumentException("El campo código postal no puede estar vacío.");
            }
            if(fax == null || fax.trim().isEmpty()) {
                throw new IllegalArgumentException("El campo fax no puede estar vacío.");
            }
            if(limiteCredito < 0) {
                throw new IllegalArgumentException("El límite de crédito no puede ser negativo.");
            }
            if(nombreCliente == null || nombreCliente.trim().isEmpty()) {
                throw new IllegalArgumentException("El campo nombre del cliente no puede estar vacío.");
            }
            if(nombreContacto == null || nombreContacto.trim().isEmpty()) {
                throw new IllegalArgumentException("El campo nombre de contacto no puede estar vacío.");
            }
            if(pais == null || pais.trim().isEmpty()) {
                throw new IllegalArgumentException("El campo país no puede estar vacío.");
            }
            if(region == null || region.trim().isEmpty()) {
                throw new IllegalArgumentException("El campo región no puede estar vacío.");
            }
            if(telefono == null || telefono.trim().isEmpty()) {
                throw new IllegalArgumentException("El campo teléfono no puede estar vacío.");
            }

            Cliente nuevoCliente = new Cliente();
            nuevoCliente.setIdCliente(idCliente);
            nuevoCliente.setApellido(apellido);
            nuevoCliente.setCiudad(ciudad);
            nuevoCliente.setCodigoPostal(codigoPostal);
            nuevoCliente.setNombreCliente(nombreCliente);
            nuevoCliente.setNombreContacto(nombreContacto);
            nuevoCliente.setPais(pais);
            nuevoCliente.setRegion(region);
            nuevoCliente.setTelefono(telefono);

            daoCliente.guardarCliente(nuevoCliente);
            System.out.println("Cliente creado éxitosamente.");

        } catch (IllegalArgumentException e) {
            System.err.println("Error de validación: " + e.getMessage());
        }  
        catch (Exception e) {
            System.err.println("No se guardó el cliente de forma correcta: " + e.getMessage());
        }
    }

    public List<Cliente> buscarTodosLoClientes() {
        try {
            return daoCliente.buscarTodosLosClientes();
        } catch (Exception e) {
            System.err.println("Error al listar los clientes: "+e.getMessage());
            return null;
        }
    }

    public Cliente buscarClientePorId(int idCliente){
        try {
            Cliente cliente = daoCliente.buscarClientePorId(idCliente);
            if(cliente == null){
                throw new Exception("No se encontró el cliente con ID: "+ idCliente);
            }
            return cliente;
        } catch (Exception e) {
            System.err.println("Error al buscar el cliente: "+e.getMessage());
            return null;
        }
    }

    public void actualizarCliente(Cliente cliente){
        try {
            if(cliente == null) {
                throw new IllegalArgumentException("El cliente no puede ser nulo.");
            }
            if (cliente.getIdCliente() == 0) {
                throw new IllegalArgumentException("El ID es necesario para actualizar.");
            }

            daoCliente.actualizarCliente(cliente);
            System.out.println("Cliente actualizado correctamente.");
        } catch (IllegalArgumentException e) {
            System.err.println("Error de validación: "+e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al actualizar el cliente: " + e.getMessage());
        }
    }

    public void eliminarCliente(int idCliente){
        try {
            daoCliente.eliminarCliente(idCliente);
            System.out.println("Cliente eliminado correctamente.");
        } catch (Exception e) {
            System.err.println("Error al eliminar el cliente: "+e.getMessage());
        }
    }
}
