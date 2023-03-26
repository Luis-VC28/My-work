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
public class Hombre {
    private Mujer pareja;
    private int intento,nombre,preferencia[],indeseables[];
    
    public Hombre(){
        pareja=null;
        indeseables=null;
        preferencia=null;
        intento=-1;
        nombre=-1;
    }
    
    public Hombre(boolean aleatorio,int cantidad,int nombre,Mujer mujeres[]){
        int poi,ind=0; //variable que selecciona si la mujer es aceptable o inaceptable
        preferencia=new int[cantidad];
        indeseables=new int[cantidad];
        Scanner xleer=new Scanner(System.in);
        for(int i=0; i<preferencia.length;i++){
            preferencia[i]=-1;
            indeseables[i]=-1;
        }
        pareja=null;
        intento=0;
        this.nombre=nombre;
        
        if(aleatorio)
        for(int i=0; i<preferencia.length; i++){
            poi=(int)(Math.floor(Math.random()*4));
            if(poi<=3) {
                int ubi=(int)(Math.floor(Math.random()*preferencia.length));
                if(preferencia[ubi]==-1)  preferencia[ubi]=i;
                else{
                    ubi=(ubi+1)%preferencia.length;
                    while(preferencia[ubi]!=-1) ubi=(ubi+1)%preferencia.length;
                    preferencia[ubi]=i;
                }
            } // 2/3 de probabilidad de que la mujer sea aceptable
            else indeseables[ind++]=i;
            
        }
        else{
            int ndes,i;
            
            System.out.print("Introduzca numero de mujeres deseables para el hombre "+this.nombre+" ");
            ndes=xleer.nextInt();
            while(ndes>cantidad || ndes<0){
                System.out.print("Numero no válido, intente de nuevo: ");
                ndes=xleer.nextInt();
            }
            System.out.print("Introduzca a las mujeres deseables en el orden de preferencia de este hombre:\n");
            for(i=0 ;i<ndes;i++){
                int aux=xleer.nextInt();
                int j=0;
               
               
                while(aux<0){
                    System.out.print("No valido, intente de nuevo: ");
                    aux=xleer.nextInt();
                }
                
                
                    for(;j<preferencia.length;j++){
                        if(preferencia[j]==aux){
                            System.out.print("Ese numero ya lo habia introducido, intente de nuevo: ");
                            aux=xleer.nextInt();
                            j=0;
                        }
                    }
                
                preferencia[i]=aux;
            }
            int k=0;
            if(ndes!=0)
            for(int j=0; j<preferencia.length;j++)
            
                for(i=0; i<ndes;i++){
                
                if(preferencia[i]==j) break;
                if(i==ndes-1)
                    indeseables[k++]=j;
        }else{
                for(i=0; i<indeseables.length;i++) indeseables[i]=i;
            }
            }
        
    }
    
    
    public void Propone(int mujer,Mujer mujeres[]){
        System.out.print("Hombre "+this.nombre+" propone a mujer "+mujeres[mujer].obtenNombre());
        mujeres[mujer].Acepta(this);
    }
    
    public void FueRechazado(){
        System.out.print("\nHombre "+this.nombre+" fue rechazado\n");
        intento++;
        pareja=null;
    }
    
    public boolean esSoltero(){
        return pareja==null;
    }
    
    public int[] obtenPreferencia(){
        return preferencia;
    }
    
    public int[] obtenIndeseables(){
        return indeseables;
    }
    
    public Mujer obtenPareja(){
        return pareja;
    }
    
    public int obtenNombre(){
        return nombre;
    }
    
    public int obtenIntento(){
        return intento;
    }
    
    public void aumentaIntento(){
        intento++;
    }
    
    public void asignaPareja(Mujer mujer){
        pareja=mujer;
    }
}
