
package cuentas;

import java.util.Comparator;

public class ComparadorPagosPorImporte implements Comparator<Pago> {

  @Override
  public int compare(Pago p1, Pago p2) {
    int resultado=Double.compare(p2.getImporte(), p1.getImporte());
    if(resultado==0){
      resultado=p1.getConcepto().compareToIgnoreCase(p2.getConcepto());
    }
    return resultado;
  }
}
