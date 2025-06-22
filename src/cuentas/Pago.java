

package cuentas;

import java.util.Objects;
import java.util.StringJoiner;

public class Pago implements Comparable<Pago> {

    private String concepto;
    private double importe;

    //Constructor
    public Pago (String concepto, double importe) {

        if (importe < 0) {
            throw new CuentasException("El importe no puede ser negativo");
        }

        this.concepto = concepto;
        this.importe = importe;
    }

    public String getConcepto () { return concepto; }


    public double getImporte () { return importe; }

    @Override
    public boolean equals (Object obj) {
        boolean igual = false;
        if (obj instanceof Pago otro) {
            igual = (otro.concepto.equalsIgnoreCase(concepto)) && (otro.importe == importe);
        }
        return igual;
    }

    @Override
    public int hashCode () { return Objects.hash(concepto.toUpperCase(), importe); }

    @Override
    public String toString () {
        StringJoiner stringJoiner = new StringJoiner(", ", "Pago(", ")");
        stringJoiner.add(concepto);
        stringJoiner.add(Double.toString(importe));
        return stringJoiner.toString();
    }

    public int compareTo (Pago otro) {
        int resultado = concepto.compareToIgnoreCase(otro.concepto);
        if (resultado == 0) {
            resultado = Double.compare(importe, otro.importe);
        }
        return resultado;
    }
}
