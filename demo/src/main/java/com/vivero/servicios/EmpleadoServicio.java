package com.vivero.servicios;

import java.util.List;

import com.vivero.entidades.Empleado;
import com.vivero.persistencia.EmpleadoDAO;

public class EmpleadoServicio {
    private final EmpleadoDAO daoEmpleado;

    public EmpleadoServicio(){
        this.daoEmpleado = new EmpleadoDAO();
    }

    public void crearEmpleado(int idEmpleado, String apellido, int codigoEmpleado, String email, String extension, int idJefe, String nombre, String puesto){
        try {
            if(apellido == null || apellido.trim().isEmpty()) {
                throw new IllegalArgumentException("El campo apellido no puede estar vacío.");
            }
            if(codigoEmpleado <= 0) {
                throw new IllegalArgumentException("El codigo del empleado debe ser mayor a 0.");
            }
            if(email == null || email.trim().isEmpty()) {
                throw new IllegalArgumentException("El campo email no puede estar vacío.");
            }
            if(extension == null || extension.trim().isEmpty()) {
                throw new IllegalArgumentException("El campo extension no puede estar vacío.");
            }
            if(idJefe <= 0) {
                throw new IllegalArgumentException("El ID del jefe debe ser mayor a 0.");
            }
            if(nombre == null || nombre.trim().isEmpty()) {
                throw new IllegalArgumentException("El campo nombre no puede estar vacío.");
            }
            if(puesto == null || puesto.trim().isEmpty()) {
                throw new IllegalArgumentException("El campo puesto no puede estar vacío.");
            }

            Empleado nuevoEmpleado = new Empleado();
            nuevoEmpleado.setIdEmpleado(idEmpleado);
            nuevoEmpleado.setApellido(apellido);
            nuevoEmpleado.setCodigoEmpleado(codigoEmpleado);
            nuevoEmpleado.setEmail(email);
            nuevoEmpleado.setExtension(extension);
            nuevoEmpleado.setIdJefe(idJefe);
            nuevoEmpleado.setNombre(nombre);
            nuevoEmpleado.setPuesto(puesto);

            daoEmpleado.guardarEmpleado(nuevoEmpleado);
            System.out.println("Empleado creado éxitosamente.");
        } catch (IllegalArgumentException e) {
            System.err.println("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("No se guardó el empleado de forma correcta: " + e.getMessage());
        }
    }

    public List<Empleado> buscarTodosLosEmpleados(){
        try {
            return daoEmpleado.buscarTodosLosEmpleados();
        } catch (Exception e) {
            System.err.println("Error al listar los empleados: "+e.getMessage());
            return null;
        }
    }

    public Empleado buscarEmpleadoPorId(int idEmpleado){
        try {
            Empleado empleado = daoEmpleado.buscarEmpleadoPorId(idEmpleado);
            if(empleado == null){
                throw new Exception("No se encontró el empleado con ID: "+ idEmpleado);
            }
            return empleado;
        } catch (Exception e) {
            System.err.println("Error al buscar el empleado: "+e.getMessage());
            return null;
        }
    }

    public void actualizarEmpleado(Empleado empleado){
        try {
            if(empleado == null) {
                throw new IllegalArgumentException("El empleado no puede ser nulo.");
            }
            if (empleado.getIdEmpleado() == 0) {
                throw new IllegalArgumentException("El ID es necesario para actualizar.");
            }

            daoEmpleado.actualizarEmpleado(empleado);
            System.out.println("Empleado actualizado correctamente.");
        } catch (IllegalArgumentException e) {
            System.err.println("Error de validación: "+e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al actualizar el empleado: " + e.getMessage());
        }
    }

    public void eliminarEmpleado(int idEmpleado){
        try {
            daoEmpleado.eliminarEmpleado(idEmpleado);
            System.out.println("Empleado eliminado correctamente.");
        } catch (Exception e) {
            System.err.println("Error al eliminar el empleado: "+e.getMessage());
        }
    }
}
