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
public class Mujer {
    private Hombre pareja;
    private int nombre,preferencia[],indeseables[];
    
    public Mujer(){
       // for(int i=0; i<preferencia.length;i++) preferencia[i]=-1;
       preferencia=null;
        pareja=null;
        indeseables=null;
        nombre=-1;
    }
    
    public Mujer(boolean aleatorio,int cantidad,int nombre,Hombre hombres[]){
        int poi,ind=0; //variable que selecciona si la mujer es aceptable o inaceptable
        preferencia=new int[cantidad];
        indeseables=new int[cantidad];
        Scanner xleer=new Scanner(System.in);
        for(int i=0; i<preferencia.length;i++){
            preferencia[i]=-1;
            indeseables[i]=-1;
        }
        pareja=null;
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
            
            System.out.print("Introduzca numero de hombres deseables para la mujer "+this.nombre+" ");
            ndes=xleer.nextInt();
            while(ndes>cantidad || ndes<0){
                System.out.print("Numero no válido, intente de nuevo: ");
                ndes=xleer.nextInt();
            }
            
            System.out.print("Introduzca a los hombres deseables en el orden de preferencia de esta mujer:\n");
            for(i=0 ;i<ndes;i++){
                //preferencia[i]=xleer.nextInt();
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
            for(int j=0; j<preferencia.length;j++)
            
                for(i=0; i<ndes;i++){
                
                if(preferencia[i]==j) break;
                if(i==ndes-1)
                    indeseables[k++]=j;
        }
            }
        
    }
    
    public void Acepta(Hombre hombre){
        if(esSoltera()){
            for(int i=0; i<indeseables.length;i++)          //BÚSQUEDA LINEAL DE INDESEABLES
                if( hombre.obtenNombre()==indeseables[i]){
                    hombre.FueRechazado();
                    return;
                }
            
                    pareja=hombre;
                    hombre.asignaPareja(this);
                    System.out.print("\nLo acepta\n");
                    
        }else{
            for(int i=0; i<preferencia.length;i++){
                if(preferencia[i]==hombre.obtenNombre()){
                    System.out.print("\nLo acepta y termina su anterior compromiso con "+pareja.obtenNombre()+"\n");
                    pareja.FueRechazado();
                    pareja=hombre;
                    hombre.asignaPareja(this);
                    return;
                }if(preferencia[i]==pareja.obtenNombre()){
                    hombre.FueRechazado();
                    return;
                }
            }
            
        }
    }
    
    public boolean esSoltera(){
        return pareja==null;
    }
    
     public int[] obtenPreferencia(){
        return preferencia;
    }
    
    public int[] obtenIndeseables(){
        return indeseables;
    }
    
    public Hombre obtenPareja(){
        return pareja;
    }
    
    public int obtenNombre(){
        return nombre;
    }
    
}
