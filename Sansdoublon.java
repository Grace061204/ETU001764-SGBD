package operation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;
import java.util.Scanner;
import java.beans.Expression;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.*;


public class Sansdoublon {      //alaivo ireo job anaty emp
    public int countSplit(String req){
        String[] nom_table = req.split(" ");
        return nom_table.length;
    }
    public String getAlaivo(String req){
        String rep = "";
        String[] nom_table = req.split(" ",this.countSplit(req));
        rep = nom_table[0];
        return rep;
    }
    
    public String getResteAlaivo(String req){
        String alaivo = this.getAlaivo(req);
        String val = "";
        String[] reste = req.split(alaivo+" ",2);
        val = reste[1];
        return val;
    }
    public String[] getColonne(String req){
        String[] nom = new String[2];
        String reste = this.getResteAlaivo(req);
        String[] div = reste.split(" ");
        nom[0] = div[1];
        nom[1] = div[3];
        return nom;
    }
    public int countAllNbreMot(String filename){
        int nbreMot = 0;
        String[] words = null;
        File file = new File(filename);
        FileReader read;
        try {
            read =  new FileReader(file);
            BufferedReader bfr = new BufferedReader(read); 
            String ligne = bfr.readLine();
            while(ligne!=null){
                words = ligne.split(" "); 
                ligne = bfr.readLine();
                nbreMot = nbreMot + words.length; 
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        return nbreMot;
    }
    public int motParLigne(String filename){
        int val = this.countAllNbreMot(filename)/this.nbreLigne(filename);
        return val;
    }
    public int nbreLigne(String filename){
        int nbreLigne = 0;
        File file = new File(filename);
        FileReader read;
        try {
            read =  new FileReader(file);
            BufferedReader bfr = new BufferedReader(read); 
            String ligne = bfr.readLine();
            while(ligne!=null){
                ligne = bfr.readLine();
                nbreLigne++;
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        return nbreLigne;
    }
    public String[] readFirstLine(String filename){
        String[] words = null;
        try {
            
            FileReader file = new FileReader(filename);
            BufferedReader buffer = new BufferedReader(file);
            String line = buffer.readLine();
            words = line.split(" "); 
        } catch (Exception e) {
            //TODO: handle exception
        }
        return words;
    }
    public String[][] selection(String filename){
        String[] words = null;
        String[][] tab = new String[this.nbreLigne(filename)-1][this.motParLigne(filename)];
        int countLigne = 0;
        File file = new File(filename);
        FileReader read;
        try {
            read =  new FileReader(file);
            BufferedReader bfr = new BufferedReader(read); 
            String ligne = bfr.readLine();
            while(ligne!=null){
                ligne = bfr.readLine();
                words = ligne.split(" "); 
                for (int j = 0; j < words.length; j++) {
                    tab[countLigne][j] = words[j];
                }
                countLigne++;
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        return tab;
    }
    public int getIndiceColonne(String filename,String nom_colonne){
        int indice = 0;
        for (int i = 0; i < this.readFirstLine(filename).length; i++) {
            if(this.readFirstLine(filename)[i].compareToIgnoreCase(nom_colonne)==0){
                indice = i;
            }
        }
        return indice;
    }
    public Vector<String> supprDoublon(String filename, String colonne){
        int count = 0;
        Vector<String> vector = new Vector<String>();
        int indiceColonne = this.getIndiceColonne(filename, colonne);
        String[][] tableau = this.selection(filename);
        vector.add(tableau[count][indiceColonne]);
        while(count<tableau.length){
            for (int i = count+1; i < tableau.length; i++) {
                if(!tableau[count][indiceColonne].equals(tableau[i][indiceColonne]) && i!=tableau.length-1){
                    vector.add(tableau[i][indiceColonne]);
                }
                break;
            }
            count++;
        }
        for (int i = 0; i < tableau.length; i++) {
            if(!tableau[i][indiceColonne].equals(tableau[tableau.length-1][indiceColonne])){
                vector.add(tableau[tableau.length-1][indiceColonne]);
            }
            break;
        }
        return vector;
    }
    public String reSelectionPart1(String req){
        String[] val = this.getColonne(req);
        String rep = "";
        String[] nom_table = req.split(val[0],2);
        rep = nom_table[0];
        return rep;
    }
}
