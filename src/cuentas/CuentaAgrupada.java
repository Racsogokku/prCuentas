
package cuentas;

import java.util.ArrayList;
import java.util.List;

public class CuentaAgrupada extends Cuenta{
  public CuentaAgrupada(String grupo){
    super(grupo);
  }

  @Override
  public void agregarPago(String persona, String concepto, double importe) {
    List<Pago> lista=pagos.getOrDefault(persona,new ArrayList<>());
    int posicion=hayConMismoConcepto(lista, concepto);
    if(posicion==-1){
      super.agregarPago(persona,concepto,importe);
    }else{
      Pago pagoAcumulado= new Pago(concepto, importe+lista.get(posicion).getImporte());
      lista.set(posicion, pagoAcumulado);
      pagos.put(persona, lista);
    }
  }
  private int hayConMismoConcepto(List<Pago> lista, String concepto){
    int posMismoConcepto=-1;
    int i=0;
    while(i<lista.size()&&posMismoConcepto==-1){
      if(lista.get(i).getConcepto().equals(concepto)){
        posMismoConcepto=i;
      }else i++;
    }
    return posMismoConcepto;
  }
}
