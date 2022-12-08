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

public class Select {
    public String reSelectionPart1(String req){
        String rep = "";
        String[] nom_table = req.split(getNomtable(req),2);
        rep = nom_table[0];
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
    public String getDaholo(String req){
        String daholo = this.getResteAlaivo(req);
        String val = "";
        String[] reste = daholo.split(" ",this.countSplit(req));
        val = reste[0];
        return val;
    }
    public String getNomtable(String req){
        String resteAlaivo = this.getResteAlaivo(req);
        String rep = "";
        String[] nom_table = resteAlaivo.split(" ",this.countSplit(resteAlaivo));
        rep = nom_table[2];
        return rep;
    }
    public Vector<String> select(String filename){
        Vector<String> val = new Vector<>();
        File file = new File("data\\"+filename);
        FileReader read;
        try {
            read =  new FileReader(file);
            BufferedReader bfr = new BufferedReader(read); 
            Object[] tab = bfr.lines().toArray();
            for (int i = 0; i < tab.length; i++) {
                val.add(tab[i].toString());
            }
           
        } catch (Exception e) {
            //TODO: handle exception
        }
        return val;
    }
}
