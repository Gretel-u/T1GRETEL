/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Clase Alumno del Instituto Innova.
 *
 * Aplica ABSTRACCIÓN: representa únicamente los datos y comportamientos
 * relevantes de un alumno para el problema (identidad, categoría y beca),
 * dejando fuera todo lo que no importa para el caso.
 *
 * Aplica ENCAPSULAMIENTO: todos los atributos son privados y solo pueden
 * modificarse a través de métodos (set) que validan la información antes
 * de guardarla, evitando que queden datos inconsistentes como en los
 * antiguos archivos de Excel.
 *
 * @author YOSELY
 */
public class Alumno {

    // ---------- Atributos (privados = encapsulados) ----------
    private String nombre;
    private String tipoDocumento;      // "DNI" o "CARNET"
    private String numeroDocumento;    // 8 dígitos si es DNI, 11 si es CARNET
    private String nivelSocioeconomico; // "A", "B" o "C"
    private String tipoBeca;           // "NINGUNA", "PARCIAL" o "TOTAL"

    // Tarifas base de pensión según nivel socioeconómico (valores de ejemplo,
    // se pueden ajustar según la política real del instituto).
    private static final double TARIFA_NIVEL_A = 300.0;
    private static final double TARIFA_NIVEL_B = 500.0;
    private static final double TARIFA_NIVEL_C = 800.0;

    // ---------- Constructor ----------
    public Alumno(String nombre, String tipoDocumento, String numeroDocumento,
                  String nivelSocioeconomico, String tipoBeca) throws DocumentoInvalidoException {
        setNombre(nombre);
        setTipoDocumento(tipoDocumento);
        setNumeroDocumento(numeroDocumento); // depende de tipoDocumento, por eso va después
        setNivelSocioeconomico(nivelSocioeconomico);
        setTipoBeca(tipoBeca);
    }

    // ---------- Getters y Setters con validación ----------

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws DocumentoInvalidoException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new DocumentoInvalidoException("El nombre del alumno no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) throws DocumentoInvalidoException {
        if (tipoDocumento == null ||
                !(tipoDocumento.equalsIgnoreCase("DNI") || tipoDocumento.equalsIgnoreCase("CARNET"))) {
            throw new DocumentoInvalidoException(
                    "Tipo de documento inválido. Debe ser 'DNI' o 'CARNET'.");
        }
        this.tipoDocumento = tipoDocumento.toUpperCase();
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) throws DocumentoInvalidoException {
        if (numeroDocumento == null || !numeroDocumento.matches("\\d+")) {
            throw new DocumentoInvalidoException(
                    "El número de documento solo debe contener dígitos.");
        }
        int longitudEsperada = tipoDocumento.equals("DNI") ? 8 : 11;
        if (numeroDocumento.length() != longitudEsperada) {
            throw new DocumentoInvalidoException(
                    "El " + tipoDocumento + " debe tener exactamente " + longitudEsperada + " dígitos.");
        }
        this.numeroDocumento = numeroDocumento;
    }

    public String getNivelSocioeconomico() {
        return nivelSocioeconomico;
    }

    public void setNivelSocioeconomico(String nivelSocioeconomico) throws DocumentoInvalidoException {
        if (nivelSocioeconomico == null ||
                !nivelSocioeconomico.matches("(?i)[ABC]")) {
            throw new DocumentoInvalidoException(
                    "El nivel socioeconómico debe ser 'A', 'B' o 'C'.");
        }
        this.nivelSocioeconomico = nivelSocioeconomico.toUpperCase();
    }

    public String getTipoBeca() {
        return tipoBeca;
    }

    public void setTipoBeca(String tipoBeca) throws DocumentoInvalidoException {
        if (tipoBeca == null ||
                !(tipoBeca.equalsIgnoreCase("NINGUNA") ||
                  tipoBeca.equalsIgnoreCase("PARCIAL") ||
                  tipoBeca.equalsIgnoreCase("TOTAL"))) {
            throw new DocumentoInvalidoException(
                    "El tipo de beca debe ser 'NINGUNA', 'PARCIAL' o 'TOTAL'.");
        }
        this.tipoBeca = tipoBeca.toUpperCase();
    }

    // ---------- Lógica de negocio ----------

    /**
     * Obtiene la tarifa base de pensión según el nivel socioeconómico.
     */
    private double getTarifaBase() {
        switch (nivelSocioeconomico) {
            case "A": return TARIFA_NIVEL_A;
            case "B": return TARIFA_NIVEL_B;
            case "C": return TARIFA_NIVEL_C;
            default: return 0.0; // nunca debería llegar aquí gracias a la validación
        }
    }

    /**
     * Calcula el monto final de la pensión cruzando el nivel socioeconómico
     * (tarifa base) con el porcentaje de descuento según el tipo de beca.
     * - NINGUNA: paga 100% de la tarifa base.
     * - PARCIAL: paga 50% de la tarifa base.
     * - TOTAL:   paga 0% (exonerado).
     */
    public double calcularPensionFinal() {
        double tarifaBase = getTarifaBase();
        double porcentajeDescuento;

        switch (tipoBeca) {
            case "PARCIAL":
                porcentajeDescuento = 0.50;
                break;
            case "TOTAL":
                porcentajeDescuento = 1.00;
                break;
            default: // NINGUNA
                porcentajeDescuento = 0.0;
        }

        return tarifaBase * (1 - porcentajeDescuento);
    }

    @Override
    public String toString() {
        return String.format(
                "Alumno{nombre='%s', %s=%s, nivel=%s, beca=%s, pensionFinal=S/ %.2f}",
                nombre, tipoDocumento, numeroDocumento, nivelSocioeconomico, tipoBeca,
                calcularPensionFinal());
    }
}