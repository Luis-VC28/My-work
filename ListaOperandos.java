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
public class ListaOperandos {
    private Operando elemento;
    
    public ListaOperandos(){
        elemento=null;
    }
    
    public ListaOperandos(Operando elemento){
        this.elemento=elemento;
    }
    
//    public ListaOperandos(char operando){
        
//    }
    public void inserta(Operando op){
        Operando aux=new Operando(op);
        if(elemento==null){
            elemento=op;
            return;
        }
        aux.asignaSig(elemento);
        elemento=aux;
        
    }
    
    public void inserta(double op){
        Operando aux=new Operando(op);
        if(elemento==null){
            elemento=aux;
            return;
        }
        aux.asignaSig(elemento);
        elemento=aux;
    }
    
    public Operando saca(){
        if(elemento==null) return null;
        Operando aux=new Operando(elemento);
        elemento=elemento.obtenSig();
        return aux;
    }
    
    
}
