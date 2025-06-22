

package cuentas;

import java.util.Comparator;

public class ComparadorPagosPorConcepto implements Comparator<Pago> {
  public int compare(Pago p1, Pago p2){
    int resultado=p1.getConcepto().compareToIgnoreCase(p2.getConcepto());
    if(resultado==0){
      resultado=Double.compare(p1.getImporte(), p2.getImporte());
    }
    return resultado;
  }
}
