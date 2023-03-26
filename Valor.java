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
public class Valor {
    private char literal;
    private double valor;
    private Valor sig;
    
    public Valor(){
        valor=0.0;
    }
    
    public Valor(char literal, double valor){
        this.literal=literal;
        this.valor=valor;
        sig=null;
    }
    
    public Valor(Valor x){
        literal=x.literal;
        valor=x.valor;
        sig=x.sig;
    }
    
    public char obtenLiteral(){
        return literal;
    }
    
    public double obtenValor(){
        return valor;
    }
    
    public Valor obtenSig(){
        return sig;
    }
    
    public void asignaSig(Valor sig){
        this.sig=sig;
    }
    
    
    
}
