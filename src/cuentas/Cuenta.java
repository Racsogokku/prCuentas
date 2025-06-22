

package cuentas;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.*;

public class Cuenta {
    private String evento;
    protected Map<String, List<Pago>> pagos;

    //Constructor
    public Cuenta (String evento) {
        this.evento = evento;
        pagos = new HashMap<>();
    }

    public String getEvento () {
        return evento;
    }

    public void agregarPago (String persona, String concepto, double importe) {
        Pago pago = new Pago(concepto, importe);
        pagos.computeIfAbsent(persona, k -> new ArrayList<>()).add(pago);
    }

    public void agregarPagos (String nombreFichero) {
        Path caminoFichero = Path.of(nombreFichero);
        String linea;
        String[] auxMapa;
        String[] auxPago;
        try (Scanner scanner = new Scanner(caminoFichero)) {
            while (scanner.hasNextLine()) {
                linea = scanner.nextLine();
                auxMapa = linea.split(":");
                if (auxMapa.length != 2) {
                    throw new CuentasException("Error en el formato de \"persona:pago\":" + linea);
                }
                auxPago = auxMapa[1].split(",");
                if (auxPago.length % 2 != 0) {
                    throw new CuentasException("Error en el formato de \"concepto,importe\":" + auxMapa);
                }
                try {
                    for (int i = 0; i < auxPago.length; i += 2) {
                        agregarPago(auxMapa[0], auxPago[i], Double.parseDouble(auxPago[i + 1]));
                    }
                } catch (Exception e) {
                    throw new CuentasException("Error en el formato del importe: " + Arrays.toString(auxPago));
                }
            }
        } catch (IOException exception) {
            throw new CuentasException("No se ha encontrado un archivo con ese nombre");
        }
    }

    public List<Pago> todosPagos () {
        List<Pago> lista = new ArrayList<>();
        for (Map.Entry<String, List<Pago>> entry : pagos.entrySet()) {
            lista.addAll(entry.getValue());
        }
        lista.sort(new ComparadorPagosPorImporte());
        return lista;
    }

    public void guardarPagos (String nombreFichero) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(nombreFichero);
        enviarPagos(printWriter);
    }

    public void enviarPagos (PrintWriter pw) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("*** ");
        stringBuilder.append(evento);
        stringBuilder.append(" ***");
        pw.println(stringBuilder);
        for (Map.Entry<String, List<Pago>> entry : pagos.entrySet()) {
            pw.println(entry.getKey() + ":");
            for (Pago pago : entry.getValue()) {
                pw.println(" ".repeat(5) + pago.getConcepto() + " -> " + pago.getImporte());
            }
        }
    }
}
