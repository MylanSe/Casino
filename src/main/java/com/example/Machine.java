package com.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class Machine {
    public String[][] matriceSymbole = new String[3][3];

    public Machine(String [][] matriceSymbole){
        this.matriceSymbole = matriceSymbole;
    }


    public boolean testCase(String[][] matrice, int nbrA, int nbrB){
        if(matrice[nbrA][nbrB]!=null){
            return true;
        }
        else{
            return false;
        }
    }

    public void ajoutCase(String[][] matrice, int nbrA, int nbrB, String c){
        matrice[nbrA][nbrB] = c;
    }

    public int randomNombre(int x){
        Random random = new Random();
        int nbr = random.nextInt(x);
        return nbr;
    }

    public String getString(int nbrA, int nbrB) throws FileNotFoundException, IOException{
        ColumnsHandler c = new ColumnsHandler();
        List<List<String>> listeSymbole = c.deserialisation();
        String symbole = listeSymbole.get(nbrA).get(nbrB);
        if( symbole.equals("7")){
            symbole = ("*7*");
        }
        else if(!symbole.equals("BAR")){
            symbole = ("(" + symbole + ")" );
        }
        return symbole;
    }
    
    public int ligneValide(String[][] matrice, int mise){
        int jetons=0;
        if(mise==1){
            if(matrice[1][0].equals(matrice[1][1]) && matrice[1][1].equals(matrice[1][2])){
                jetons+=gain(matrice[1][0]);
            }
        }

        else if(mise > 1){
            for(int i=0; i<2; i++){
                if(matrice[i][0].equals(matrice[i][1]) && matrice[i][1].equals(matrice[i][2])){
                    jetons += gain(matrice[i][0]);
                }
            }
            
        
        }
        if(mise>2){
            if(matrice[0][0].equals(matrice[1][1]) && matrice[1][1].equals(matrice[2][2])){
                jetons += gain(matrice[1][1]);
            }
            if(matrice[2][0].equals(matrice[1][1]) && matrice[1][1].equals(matrice[0][2])){
                jetons += gain(matrice[1][1]);
            }
        }
        return jetons;
    }

    public int gain(String symbole){
        int jetonsGagne = 0;
        switch (symbole) {
            case "7":
                jetonsGagne += 300;
                break;
        
            case "BAR":
                jetonsGagne += 100;
                break;
                
            case "R":
                jetonsGagne += 15;
                break;
        
            case "P":
                jetonsGagne += 15;
                break;

            case "T":
                jetonsGagne += 15;
                break;
            
            case "C":
                jetonsGagne += 8;
                break;
        }
        return jetonsGagne;
    
    }


}
