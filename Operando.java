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
public class Operando {
    private double operando;
    private Operando sig;
    
    public Operando(){
        operando=0.0;
        sig=null;
        
    }
    
    public Operando(double op){
        operando=op;
        sig=null;
    }
    
    public Operando(Operando op){
        operando=op.operando;
        sig=op.sig;
    }
    
    public Operando obtenSig(){
        return sig;
    }
    
    public double obtenOp(){
        return operando;
    }
    
    public void asignaSig(Operando sig){
        this.sig=sig;
    }
}
