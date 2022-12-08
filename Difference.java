package operation;

import operation.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.server.Operation;
import java.util.Vector;
import java.util.Scanner;
import java.beans.Expression;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.*;

public class Difference {
    Op op = new Op();
    
    public Vector<String> getDifference(String nomTable1, String nomTable2){
        int nbre_ligne1 = op.nbreLigne(nomTable1);
        int nbre_ligne2 = op.nbreLigne(nomTable2);
        Vector<String> diff = new Vector<String>();
        String[][] select1 = op.selection(nomTable1);
        String[][] select2 = op.selection(nomTable2);
        if(nbre_ligne1<nbre_ligne2){
            System.out.println("nbre_ligne1<nbre_ligne2");
            for (int i = 0; i < select1.length; i++) {
                for (int j = 0; j < select1[i].length; j++) {
                    for (int j2 = 0; j2 < select2.length; j2++) {
                        for (int k = 0; k < select2[j2].length; k++) {
                            if(!select1[j2][k].equals(select2[i][j])){
                                diff.add(select2[j2][k]);
                            }
                            break;
                        }
                        break;
                        
                    }
                    
                }
            }
        }
        else if(nbre_ligne1>nbre_ligne2){
            //System.out.println("nbre_ligne1>nbre_ligne2");
            for (int i = 0; i < select2.length; i++) {
                for (int j = 0; j < select2[i].length; j++) {
                    for (int j2 = 0; j2 < select1.length; j2++) {
                        for (int k = 0; k < select1[j2].length; k++) {
                            if(!select2[i][j].equals(select1[j2][k])){
                                diff.add(select1[j2][k]);
                            }
                        }
                        break;
                        
                    }
                    break;
                }
                break;
            }

        }
        return diff;
    }
}
