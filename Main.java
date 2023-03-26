/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progII.esfm;

import java.util.Scanner;

/**
 *
 * @author luisv
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String cadena=new String();
        int variable=-5,i,lim;
        Scanner xleer=new Scanner(System.in);
        ListaOperadores operadores=new ListaOperadores();
        ListaOperandos operandos=new ListaOperandos();
        double valor,evaluacion=0.0,evtemp;
        char operador;
        boolean hayequis=false;
        Operador aux;
        ListaValores memoria=new ListaValores(); //Este objeto guarda las literales a las que ya se les haya dado valor para no volver a pedirlo
        Valor valorauxiliar; //Este objeto se insertará en memoria cada de que encuentre una literal de la cual no se haya especificado su valor
        System.out.println("Este programa evalua una expresion dada en [|-5,5|]\nIntroduzca la expresión a evaluar");
        System.out.println("P=pi\nE=e\nS=seno\nC=coseno\nT=tangente\nF=arcoseno\nG=arcocoseno\nH=arcotangente");
        System.out.println("L=logaritmo(precedido de la base, después del operador inserte el argumento del logaritmo)");
        cadena=xleer.nextLine();
        //Ahora se recorrerá cada caracter de la cadena y se verá si es operador u operando
        //En cada caso, se insertará en la pila que corresponda
        lim=cadena.length();
        
        for(;variable<=5; variable++){
            //PASO 1
        for(i=0; i<lim; i++){
            if(cadena.charAt(i)>='a' && cadena.charAt(i)<='z' && cadena.charAt(i)!='x'){
                if(memoria.hayLetra(cadena.charAt(i))) valor=memoria.buscaValor(cadena.charAt(i));
                else{
                System.out.format("\nIntroduzca el valor de %c: ",cadena.charAt(i));
                valor=xleer.nextDouble();
                valorauxiliar=new Valor(cadena.charAt(i),valor);
                memoria.inserta(valorauxiliar);
                
                }
                operandos.inserta(valor);
                
            }
            if(cadena.charAt(i)=='P') operandos.inserta(Math.PI);
            if(cadena.charAt(i)=='E') operandos.inserta(Math.E);
            if(cadena.charAt(i)>='A' && cadena.charAt(i)<='Z' && cadena.charAt(i)!='E' && cadena.charAt(i)!='P')operadores.inserta(cadena.charAt(i));
            if(cadena.charAt(i)=='x'){
                operandos.inserta((double)variable);
                hayequis=true;
            }
            if(cadena.charAt(i)=='(') operadores.inserta('(');
            if(cadena.charAt(i)==')'){
                while(!operadores.esParentesis()){
                    if(operadores.obten()==null) break;
                    operador=operadores.saca().obtenOp();
                    switch(operador){
                        case '+':
                            evtemp=operandos.saca().obtenOp();
                            evtemp=operandos.saca().obtenOp()+evtemp;
                            operandos.inserta(evtemp);
                            System.out.print("Entro a la suma del parentesis");
                            break;
                        case '-':
                            evtemp=operandos.saca().obtenOp();
                            evtemp=operandos.saca().obtenOp()-evtemp;
                            operandos.inserta(evtemp);
                            break;
                        case '*':
                            evtemp=operandos.saca().obtenOp();
                            evtemp=operandos.saca().obtenOp()*evtemp;
                            operandos.inserta(evtemp);
                            break;
                        case '/':
                            evtemp=operandos.saca().obtenOp();
                            evtemp=operandos.saca().obtenOp()/evtemp;
                            operandos.inserta(evtemp);
                            break;
                        case '%':
                            evtemp=operandos.saca().obtenOp();
                            evtemp=operandos.saca().obtenOp()%evtemp;
                            operandos.inserta(evtemp);
                            break;
                        case 'S':
                            evtemp=operandos.saca().obtenOp();
                            evtemp=Math.sin(evtemp);
                            operandos.inserta(evtemp);
                            break;
                        case 'C':
                            evtemp=operandos.saca().obtenOp();
                            evtemp=Math.cos(evtemp);
                            operandos.inserta(evtemp);
                            break;
                        case 'T':
                            evtemp=operandos.saca().obtenOp();
                            evtemp=Math.tan(evtemp);
                            operandos.inserta(evtemp);
                            break;
                        case 'F':
                            evtemp=operandos.saca().obtenOp();
                            evtemp=Math.asin(evtemp);
                            operandos.inserta(evtemp);
                            break;
                        case 'G':
                            evtemp=operandos.saca().obtenOp();
                            evtemp=Math.acos(evtemp);
                            operandos.inserta(evtemp);
                            break;
                        case 'H':
                            evtemp=operandos.saca().obtenOp();
                            evtemp=Math.atan(evtemp);
                            operandos.inserta(evtemp);
                            break;
                        case 'L':
                            evtemp=operandos.saca().obtenOp();
                            evtemp=Math.log(evtemp)/Math.log(operandos.saca().obtenOp());
                            operandos.inserta(evtemp);
                            break;
                        case '^':
                            evtemp=operandos.saca().obtenOp();
                            evtemp=Math.pow(evtemp, operandos.saca().obtenOp());
                            operandos.inserta(evtemp);
                            break;
                        default:
                            System.out.print("Error en la expresion\n");
                            return;
                            
                    }
                }
                operadores.saca(); //SOLO DESCARTA EL PARENTESIS IZQUIERDO, NO SE NECESITA MÁS
                
            }
            if(cadena.charAt(i)=='+' || cadena.charAt(i)=='-' || cadena.charAt(i)=='*' || cadena.charAt(i)=='/' || cadena.charAt(i)=='%' ||cadena.charAt(i)=='^'){
                aux=new Operador(cadena.charAt(i));
                while(!operadores.esVacio() && operadores.obten().obtenPrecedencia()>=aux.obtenPrecedencia()){
                   operador=operadores.saca().obtenOp();
                    switch(operador){
                        case '+':
                            evtemp=operandos.saca().obtenOp();
                            evtemp=operandos.saca().obtenOp()+evtemp;
                            operandos.inserta(evtemp);
                            
                            break;
                        case '-':
                            evtemp=operandos.saca().obtenOp();
                            evtemp=operandos.saca().obtenOp()-evtemp;
                            operandos.inserta(evtemp);
                            break;
                        case '*':
                            evtemp=operandos.saca().obtenOp();
                            evtemp=operandos.saca().obtenOp()*evtemp;
                            operandos.inserta(evtemp);
                            break;
                        case '/':
                            evtemp=operandos.saca().obtenOp();
                            evtemp=operandos.saca().obtenOp()/evtemp;
                            operandos.inserta(evtemp);
                            break;
                        case '%':
                            evtemp=operandos.saca().obtenOp();
                            evtemp=operandos.saca().obtenOp()%evtemp;
                            operandos.inserta(evtemp);
                            break;
                        case '^':
                            evtemp=operandos.saca().obtenOp();
                            evtemp=Math.pow(evtemp, operandos.saca().obtenOp());
                            operandos.inserta(evtemp);
                            break;
                        
                            
                    }
            }
                operadores.inserta(aux);
            
        }
        
    }
        //PASO 2
        while(!operadores.esVacio()){
            operador=operadores.saca().obtenOp();
            
                    switch(operador){
                        case '+':
                            evtemp=operandos.saca().obtenOp();
                            evtemp=operandos.saca().obtenOp()+evtemp;
                            operandos.inserta(evtemp);
                            break;
                        case '-':
                            evtemp=operandos.saca().obtenOp();
                            evtemp=operandos.saca().obtenOp()-evtemp;
                            operandos.inserta(evtemp);
                            break;
                        case '*':
                            evtemp=operandos.saca().obtenOp();
                            evtemp=operandos.saca().obtenOp()*evtemp;
                            operandos.inserta(evtemp);
                            break;
                        case '/':
                            evtemp=operandos.saca().obtenOp();
                            evtemp=operandos.saca().obtenOp()/evtemp;
                            operandos.inserta(evtemp);
                            break;
                        case '%':
                            evtemp=operandos.saca().obtenOp();
                            evtemp=operandos.saca().obtenOp()%evtemp;
                            operandos.inserta(evtemp);
                            break;
                        case 'S':
                            evtemp=operandos.saca().obtenOp();
                            evtemp=Math.sin(evtemp);
                            operandos.inserta(evtemp);
                            break;
                        case 'C':
                            evtemp=operandos.saca().obtenOp();
                            evtemp=Math.cos(evtemp);
                            operandos.inserta(evtemp);
                            break;
                        case 'T':
                            evtemp=operandos.saca().obtenOp();
                            evtemp=Math.tan(evtemp);
                            operandos.inserta(evtemp);
                            break;
                        case 'F':
                            evtemp=operandos.saca().obtenOp();
                            evtemp=Math.asin(evtemp);
                            operandos.inserta(evtemp);
                            break;
                        case 'G':
                            evtemp=operandos.saca().obtenOp();
                            evtemp=Math.acos(evtemp);
                            operandos.inserta(evtemp);
                            break;
                        case 'H':
                            evtemp=operandos.saca().obtenOp();
                            evtemp=Math.atan(evtemp);
                            operandos.inserta(evtemp);
                            break;
                        case 'L':
                            evtemp=operandos.saca().obtenOp();
                            evtemp=Math.log(evtemp)/Math.log(operandos.saca().obtenOp());
                            operandos.inserta(evtemp);
                            break;
                        case '^':
                            evtemp=operandos.saca().obtenOp();
                            evtemp=Math.pow(evtemp, operandos.saca().obtenOp());
                            operandos.inserta(evtemp);
                            break;
                        default:
                            System.out.print("Error en la expresion\n");
                            return;
                            
                    }
        }
        //PASO 3
        if(hayequis==false){
            System.out.format("\nEl resultado es %g\n",operandos.saca().obtenOp());
            return;
        }
        System.out.format("\nEl resultado cuando x=%d es %g\n",variable,operandos.saca().obtenOp());
        
    }
    
}}
