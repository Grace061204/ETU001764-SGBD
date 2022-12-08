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

public class Selectwhere {
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

    public String splitEspace(String req){
        String splitter = getWhere(req);
        String[] val = splitter.split(" ", 2);
        return val[1];
    }
    public String[] getCondition(String req){
        String[] cond = new String[2];
        String splitter = splitEspace(req);
        String[] val = splitter.split("=", cond.length);
        cond[0] = val[0];
        cond[1] = val[1];
        return cond;

    }

    public String reSelectionPart2(String req){
        String rep = "";
        String[] nom_table = req.split(getNomtable(req)+" ",2);
        rep = nom_table[1];
        return rep;
    }
    public String getNomtable(String req){
        String resteAlaivo = this.getResteAlaivo(req);
        String rep = "";
        String[] nom_table = resteAlaivo.split(" ",this.countSplit(resteAlaivo));
        rep = nom_table[2];
        return rep;
    }
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
    public String getRehefa(String req){
        String part1 = this.reSelectionPart2(req);
        String[] spl = part1.split(" ");
        return spl[0];
    }
    public String getWhere(String req){
        String rep = "";
        String[] nom_table = req.split(this.getRehefa(req),2);
        rep = nom_table[1];
        return rep;
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

    public Vector<Integer> ligneSpeciale(String filename,String colonne, String valeur){
        Vector<Integer> ligne = new Vector<Integer>();
        int indice = getIndiceColonne(filename, colonne);
        String[][] donnee = this.selection(filename);
        for (int i = 0; i < donnee.length; i++) {
            if(donnee[i][indice].compareToIgnoreCase(valeur)==0){
                ligne.add(i);
            }
        }
        return ligne;
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
    public Vector<String> getAllLigneSpeciale(String filename, String req){
        filename = "data\\"+filename;
        String[] condition = this.getCondition(req);
        String nom_colonne = condition[0];
        String valeur = condition[1];
        int counter = 0;
        Vector<Integer> nbre_ligne = this.ligneSpeciale(filename, nom_colonne, valeur);
        Vector<String> val = new Vector<>();
        File file = new File(filename);
        FileReader read;
        try {
            read =  new FileReader(file);
            BufferedReader bfr = new BufferedReader(read); 
            String ligne = bfr.readLine();
            while(ligne!=null){
                ligne = bfr.readLine();
                for (int i = 0; i < this.ligneSpeciale(filename, nom_colonne, valeur).size(); i++) {
                    
                    if(counter==nbre_ligne.get(i)){
                        val.add(ligne);
                    }
                }
                counter++;
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        return val;
    }
    
}
