/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Excepción personalizada que se lanza cuando algún dato del Alumno
 * (tipo de documento, número de documento, nivel socioeconómico o
 * tipo de beca) no cumple con las reglas de negocio del Instituto Innova.
 *
 * Al ser una excepción "checked" (extiende Exception), obliga a quien
 * use la clase Alumno a manejarla con try/catch, evitando que el
 * programa termine de forma inesperada por datos mal ingresados.
 *
 * @author YOSELY
 */
public class DocumentoInvalidoException extends Exception {

    public DocumentoInvalidoException(String mensaje) {
        super(mensaje);
    }
}