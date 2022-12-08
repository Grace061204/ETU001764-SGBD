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

public class Specificlinecolumn {
    public int countSplit(String req){
        String[] nom_table = req.split(" ");
        return nom_table.length;
    }

    public String getNomtable(String req){
        String resteAlaivo = this.getResteAlaivo(req);
        String rep = "";
        String[] nom_table = resteAlaivo.split(" ",this.countSplit(resteAlaivo));
        rep = nom_table[2];
        return rep;
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
    public int countSousColonne(String req){
        String colonne = this.specificColumn(req);
        String[] nom_table = colonne.split(",");
        return nom_table.length;
    }

    public String specificColumn(String req){
        String apresNomTable = this.getResteAlaivo(req);
        String column = "";
        String[] splitter = apresNomTable.split(" ",this.countSplit(apresNomTable));
        column = splitter[0];
        return column;
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
    public int motParLigne(String filename){
        int val = this.countAllNbreMot(filename)/this.nbreLigne(filename);
        return val;
    }
    
    public Vector<String> chaqueSousColonne(String req){
        String specific = this.specificColumn(req);
        Vector<String> eVector = new Vector<String>();
        String[] nom_colonne = specific.split(",",this.countSousColonne(req));
        for (int i = 0; i < nom_colonne.length; i++) {
            eVector.add(nom_colonne[i]);
        }
        return eVector;
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

    public Vector<Integer> getIndiceSousColonne(String filename, String req){
        Vector<Integer> indice = new Vector<Integer>();
        String[] entete = this.readFirstLine(filename);
        String[] sousColonne = new String[this.chaqueSousColonne(req).size()];
        this.chaqueSousColonne(req).copyInto(sousColonne);
        for (int i = 0; i < entete.length; i++) {
            for (int j = 0; j < sousColonne.length; j++) {
                if(entete[i].compareToIgnoreCase(sousColonne[j])==0){
                    indice.add(i);
                }
            }   
        }
        return indice;
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
    public Vector<String> lineAndColumnSpecial(String filename, String req){
        filename = "data\\"+filename;
        String[] condition = this.getCondition(req);
        String colonne = condition[0];
        String valeur = condition[1];
        Vector<String> val = new Vector<String>();
        Vector<Integer> ligneSpeciale = this.ligneSpeciale(filename, colonne, valeur);
        Vector<Integer> colonneSpeciale = this.getIndiceSousColonne(filename, req);
        String[][] allInfo = this.selection(filename);
        for (int i = 0; i < allInfo.length; i++) {
            for (int j = 0; j < allInfo[i].length; j++) {
                for (int j2 = 0; j2 < ligneSpeciale.size(); j2++) {
                    for (int k = 0; k < colonneSpeciale.size(); k++) {
                        if(i==ligneSpeciale.get(j2) && j==colonneSpeciale.get(k)){
                            val.add(allInfo[i][j]);
                        }
                    }
                }
            }
        }
        return val;
    }
}
