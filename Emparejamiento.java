/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esfm.ipn;

import java.util.Scanner;

/**
 *
 * @author luisv
 */
public class Emparejamiento {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Hombre[] hombres;
        Mujer[] mujeres;
        Scanner xleer=new Scanner(System.in);
        int i,cantidad,introducir;
        boolean huborechazo=true,aleatorio=true;
        System.out.print("Este programa implementa el algoritmo de Cale-Shapley para los matrimonios "
                + "estables en el caso de mujeres monógamas.\nLos datos se generan aleatoriamente, con "
                + "una probabilidad de 1/3 por hombre o mujer de que se consideren indeseables.\n"
                + "Introduzca el numero de hombres y mujeres que desea casar: ");
        cantidad=xleer.nextInt();
        hombres=new Hombre[cantidad];
        mujeres=new Mujer[cantidad];
        System.out.print("Introduzca 1 si desea introducir los datos usted mismo, introduzca 2 si "
                + "prefiere que el programa los genere aleatoriamente: ");
        introducir=xleer.nextInt();
        if(introducir!=2) aleatorio=false;
        for(i=0; i<hombres.length;i++){
            hombres[i]=new Hombre(aleatorio,cantidad,i,mujeres);
            mujeres[i]=new Mujer(aleatorio,cantidad,i,hombres);
        }
        
        while(huborechazo){
            huborechazo=false;
            for(i=0; i<hombres.length;i++){
                if(hombres[i].esSoltero() && hombres[i].obtenIntento()<hombres[i].obtenPreferencia().length){   //Si el hombre es soltero y no ha agotado sus opciones
                    int aux=hombres[i].obtenIntento();
                    if(hombres[i].obtenPreferencia()[hombres[i].obtenIntento()]!=-1){
                hombres[i].Propone(hombres[i].obtenPreferencia()[hombres[i].obtenIntento()],mujeres);
                if(!huborechazo)  huborechazo=aux<hombres[i].obtenIntento(); //si el intento aumentó, hubo rechazo
                    }else{
                        while(hombres[i].obtenPreferencia()[hombres[i].obtenIntento()]==-1){
                            hombres[i].aumentaIntento();
                            if(hombres[i].obtenIntento()==cantidad){
                                hombres[i].FueRechazado();
                                break;
                            }
                        }
                        if(hombres[i].obtenIntento()<cantidad) hombres[i].Propone(hombres[i].obtenPreferencia()[hombres[i].obtenIntento()],mujeres);
                if(!huborechazo)  huborechazo=aux<hombres[i].obtenIntento();
                    }
                }
            }
        }
        for(i=0; i<hombres.length; i++){
            System.out.format("Orden de preferencia hombre %d: ", i);
            for(int j=0; j<hombres[i].obtenPreferencia().length; j++)
                if(hombres[i].obtenPreferencia()[j]!=-1) System.out.format("%d\n", hombres[i].obtenPreferencia()[j]);
            for(int j=0;j<hombres[i].obtenIndeseables().length;j++)
                if(hombres[i].obtenIndeseables()[j]!=-1) System.out.format("ind: %d\n", hombres[i].obtenIndeseables()[j]);
            
        }
        
        for(i=0; i<mujeres.length; i++){
            System.out.format("Orden de preferencia mujer %d: ", i);
            for(int j=0; j<mujeres[i].obtenPreferencia().length; j++)
                if(mujeres[i].obtenPreferencia()[j]!=-1) System.out.format("%d\n", mujeres[i].obtenPreferencia()[j]);
            for(int j=0;j<mujeres[i].obtenIndeseables().length;j++)
                if(mujeres[i].obtenIndeseables()[j]!=-1) System.out.format("ind: %d\n", mujeres[i].obtenIndeseables()[j]);
            
            
        }
        
        for(i=0; i<hombres.length; i++){
            if(!hombres[i].esSoltero())
            System.out.format("Hombre %d emparejado con mujer %d\n", hombres[i].obtenNombre(),hombres[i].obtenPareja().obtenNombre());
            else
            System.out.format("Hombre %d es soltero\n", hombres[i].obtenNombre());
        }
        
        for(i=0; i<mujeres.length; i++){
            if(!mujeres[i].esSoltera())
            System.out.format("Mujer %d emparejada con hombre %d\n", mujeres[i].obtenNombre(),mujeres[i].obtenPareja().obtenNombre());
            else
            System.out.format("Mujer %d es soltera\n", mujeres[i].obtenNombre());
        }
    }
    
}
