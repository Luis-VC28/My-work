/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progII.esfm;

/**
 *
 * @author luisv
 */
public class ListaValores {
    private Valor elemento;
    
    public ListaValores(){
        elemento=null;
    }
    
    public ListaValores(ListaValores a){
        elemento=a.elemento;
    }
    
    public boolean hayLetra(char letra){
        ListaValores aux=new ListaValores(this);
        while(aux.elemento!=null){
            if(aux.elemento.obtenLiteral()==letra) return true;
            aux.elemento=aux.elemento.obtenSig();
        }
        return false;
    }
    
    public double buscaValor(char letra){
       ListaValores aux=new ListaValores(this);
        while(aux.elemento!=null){
            if(aux.elemento.obtenLiteral()==letra) return aux.elemento.obtenValor();
            aux.elemento=aux.elemento.obtenSig();
        }
        return 0.0; //EN TEORIA NO DEBE LLEGAR AQUÍ, EN EL MAIN SE PROHIBE ESA POSIBILIDAD
    }
    
    public void inserta(Valor x){
        Valor aux=new Valor(x);
        if(elemento==null){
            elemento=aux;
            return;
        }
        aux.asignaSig(elemento);
        elemento=aux;
    }
    
}
