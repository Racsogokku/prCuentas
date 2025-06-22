
package cuentas;

import java.util.*;

public class PruebaPago {
    public static void main (String[] args) {
        Pago pago1 = new Pago("Tapas", 30);
        Pago pago2 = new Pago("Excursion", 50);
        Pago pago3 = new Pago("Cena", 45);
        Pago pago4 = new Pago("Visita a museo", 20);
        Pago pago5 = new Pago("Tapas", 25);
        Pago pago6 = new Pago("Tapas", 30);
        SortedSet<Pago> setOrdenado = new TreeSet<>();


        setOrdenado.add(pago1);
        setOrdenado.add(pago2);
        setOrdenado.add(pago3);
        setOrdenado.add(pago4);
        setOrdenado.add(pago5);
        setOrdenado.add(pago6);
        System.out.println("Pagos ordenados por orden natural: " + setOrdenado);
        List<Pago> lista = new ArrayList<>();
        lista.add(pago1);
        lista.add(pago2);
        lista.add(pago3);
        lista.add(pago4);
        lista.add(pago5);
        lista.add(pago6);
        lista.sort(new ComparadorPagosPorConcepto().reversed());
        System.out.println("Pagos ordenados por orden inverso: " + lista);


        if (args.length >= 2) {
            try {
                double importe = Double.parseDouble(args[1]);
                String concepto = args[0];
                Pago pagoMain = new Pago(concepto, importe);
                System.out.println(pagoMain);
            } catch (NumberFormatException e) {
                System.err.println("Error: El importe debe ser un número válido.");
            }
        }


    }
}
