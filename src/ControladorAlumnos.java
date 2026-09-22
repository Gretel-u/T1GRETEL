/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador que administra la colección de alumnos.
 * Requisito del caso: debe tener un método para agregar objetos a su
 * lista y otro para listar todos los objetos agregados.
 *
 * @author YOSELY
 */
public class ControladorAlumnos {

    private final List<Alumno> listaAlumnos;

    public ControladorAlumnos() {
        this.listaAlumnos = new ArrayList<>();
    }

    /**
     * Agrega un alumno a la lista, evitando registrar documentos duplicados
     * (esto ataca directamente la causa "homonimia / registros duplicados"
     * detectada en el diagrama de Ishikawa).
     */
    public void agregarAlumno(Alumno alumno) throws DocumentoInvalidoException {
        if (alumno == null) {
            throw new DocumentoInvalidoException("No se puede agregar un alumno nulo.");
        }
        for (Alumno a : listaAlumnos) {
            if (a.getTipoDocumento().equals(alumno.getTipoDocumento())
                    && a.getNumeroDocumento().equals(alumno.getNumeroDocumento())) {
                throw new DocumentoInvalidoException(
                        "Ya existe un alumno registrado con ese mismo documento.");
            }
        }
        listaAlumnos.add(alumno);
    }

    /**
     * Lista todos los alumnos agregados hasta el momento.
     */
    public void listarAlumnos() {
        if (listaAlumnos.isEmpty()) {
            System.out.println("No hay alumnos registrados todavía.");
            return;
        }
        System.out.println("\n--- Lista de alumnos registrados (" + listaAlumnos.size() + ") ---");
        int i = 1;
        for (Alumno a : listaAlumnos) {
            System.out.println(i + ". " + a);
            i++;
        }
    }

    public int totalAlumnos() {
        return listaAlumnos.size();
    }
}