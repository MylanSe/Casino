package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;

public class Program {
    public static void main(String[] args) throws FileNotFoundException, IOException{
        ColumnsHandler c = new ColumnsHandler();
        Machine m = new Machine(null);
        Scanner scanner = new Scanner(System.in);
        Gson gson = new Gson();

        List<List<String>> listeSymbole = c.deserialisation();
        String[][] matriceSymbole = new String[3][3];

        int jetonsTotal = 10;
        int partieJoue = 0;
        int partieGagne = 0;
        int miseTotal = 0;

        Boolean test = false;
        Boolean rejouer = true;
        int mise = 0;
        System.out.println("Bienvenue au Casino céldadophole");
        while(rejouer == true){
            while (test == false){
                System.out.print("Combien de jetons voulez vous utiliser, 1 2 3 : ");
                String saisieUtilisateur = scanner.nextLine();
                if (StringUtils.isNumeric(saisieUtilisateur)) {
                    int nombre = Integer.parseInt(saisieUtilisateur);

                    if (nombre == 1 || nombre == 2 || nombre == 3) {
                        if(nombre>jetonsTotal){
                            System.out.println("Pas assez de jetons. Voici votre nombre de jetons" + jetonsTotal);
                        }
                        else{  
                            mise = nombre;
                            miseTotal += nombre;
                            test = true;
                        }
                    } 
                    else {
                        System.out.println("Valeur incorrect. Recommencez");
                    }
                }
                else {
                    System.out.println("Valeur incompatible. Recommencez");
                }

            }

            jetonsTotal = jetonsTotal - mise;



            for(int i=0; i<3; i++){
                int random = m.randomNombre(14);

                matriceSymbole[0][i] = m.getString(i, random%15);
                matriceSymbole[1][i] = m.getString(i, (random+1)%15);
                matriceSymbole[2][i] = m.getString(i, (random+2)%15);
            }
            for (int i = 0; i < matriceSymbole.length; i++) {
                for (int j = 0; j < matriceSymbole[i].length; j++) {
                    System.out.print(matriceSymbole[i][j]);
    
                    if (j < matriceSymbole[i].length - 1) {
                        System.out.print(" | ");
                    }
                }
    
                System.out.println();
            }

            int gain = m.ligneValide(matriceSymbole, mise);
            jetonsTotal += gain;
            if(gain>0){
                partieGagne +=1;
            }
            System.out.println("Voila vos gains :" + gain);
            System.out.println("Voulez vous arrêter? (o/n)");
            String saisieUtilisateur = scanner.nextLine();
            partieJoue +=1;
            if ("o".equals(saisieUtilisateur) || "O".equals(saisieUtilisateur) || "oui".equals(saisieUtilisateur)) {
                rejouer = false;
            }
            else if(jetonsTotal <= 0){
                System.out.println("Vous n'avez plus de jetons.");
                rejouer = false;
            }
            else{
                test = false;
            }

            System.out.println("--------------------------------");




        }
        System.out.println("Merci d'être passé au casino, vous repartez avec " + jetonsTotal + "jetons.");
        Map<String, Object> donnees = new HashMap<>();
        donnees.put("jetons", jetonsTotal);
        donnees.put("parties", partieJoue);
        donnees.put("victoires", partieGagne);
        donnees.put("depense", miseTotal);
        String json = gson.toJson(donnees);
        FileWriter fw = new FileWriter("C:\\Users\\Utilisateur\\Documents\\Cours\\Licence\\java\\Chevalier\\casino\\src\\main\\resources\\donnees.json");
        fw.write(json);
        fw.close();
        
        

    }
}