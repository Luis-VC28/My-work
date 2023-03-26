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
public class ListaOperadores {
    private Operador elemento;
    
    public ListaOperadores(){
        elemento=null;
    }
    
    public ListaOperadores(ListaOperadores a){
        elemento=a.elemento;
    }
    
    public void inserta(Operador op){
        Operador aux=new Operador(op);
        if(elemento==null){
            elemento=op;
            return;
        }
        aux.asignaSig(elemento);
        elemento=aux;
    }
    
    public Operador saca(){
        if(elemento==null) return null;
        Operador ret=new Operador(elemento);
        elemento=elemento.obtenSig();
        return ret;
        
    }
    
    public Operador obten(){
        if(elemento==null) return null;
        Operador ret=new Operador(elemento);
        return ret;
    }
    
    public boolean esVacio(){
        return elemento==null;
    }
    
    public boolean esParentesis(){
        if(elemento==null) return false;
        return elemento.obtenOp()=='(';
    }
    
    public void inserta(char op){
        Operador aux=new Operador(op);
        
        aux.asignaPrecedencia();
        aux.asignaSig(elemento);
        elemento=aux;
    }
    
    
}
