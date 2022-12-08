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
import java.util.concurrent.locks.ReentrantLock;
import java.util.Scanner;
import java.beans.Expression;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.*;

public class Selectwhereplus {
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
    public Vector<String> allCondition(String req){
        String getWhere = this.getWhere(req);
        Vector<String> cond = new Vector<String>();
        String[] allCondition = getWhere.split(",");
        for (int i = 0; i < allCondition.length; i++) {
            cond.add(allCondition[i]);
        }
        return cond;
    }
    public Vector<String> getNomColonne(String req){
        Vector<String> nomColonne = new Vector<String>();
        Vector<String> condition = this.allCondition(req);
        String[] getNomColonne = new String[condition.size()];
        for (int i = 0; i < condition.size(); i++) {
            getNomColonne = condition.get(i).split("=");
            nomColonne.add(getNomColonne[0]);
        }
        return nomColonne;
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
    public Vector<String> getValueColumn(String req){
        Vector<String> valueColumn = new Vector<String>();
        Vector<String> condition = this.allCondition(req);
        String[] getValueColumn = new String[condition.size()];
        for (int i = 0; i < condition.size(); i++) {
            getValueColumn = condition.get(i).split("=");
            valueColumn.add(getValueColumn[1]);
        }
        return valueColumn;
    }
    public Vector<Integer> getIndiceColonne(String filename,String req){
        Vector<String> nomColonne = this.getNomColonne(req);
        Vector<Integer> indice =  new Vector<Integer>();
        for (int j = 0; j < nomColonne.size(); j++) {
            for (int i = 0; i < this.readFirstLine(filename).length; i++) {
                if(this.readFirstLine(filename)[i].compareToIgnoreCase(nomColonne.get(j))==0){
                    indice.add(i);
                }
            }
        }
        return indice;
    }
    
}
