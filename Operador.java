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
public class Operador {
    private char operador;
    private Operador sig;
    private int precedencia;
    //precedencia: 0 suma/resta, 1 multiplicación/division, 2 exponenciación, 3 función, 4 paréntesis, -1 indefinido
    
    public Operador(){
        operador='+';
        sig=null;
        precedencia=-1;
    }
    
    public Operador(char operador){
        this.operador=operador;
        sig=null;
        this.asignaPrecedencia();
    }
    
    public Operador(Operador op){
        
        operador=op.operador;
        sig=op.sig;
        precedencia=op.precedencia;
    }
    
    public char obtenOp(){
        return operador;
    }
    
    public Operador obtenSig(){
        return sig;
    }
    
    public void asignaSig(Operador sig){
        this.sig=sig;
    }
    
    public void asignaPrecedencia(){
        if(operador=='+' || operador=='-') precedencia=0;
        else if(operador=='*' || operador=='/') precedencia=1;
        else if(operador=='^') precedencia=2;
        else if(operador>='A' && operador <='Z' && operador!='E' && operador!='P') precedencia=3;
       // else if(operador=='(' || operador==')') precedencia=4;
        else precedencia=-1;
    }
    
    public int obtenPrecedencia(){
        return precedencia;
    }
    
    
    
    
}
