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

public class Op {
    
    public int nbreLigne(String filename){
        int nbreLigne = 0;
        File file = new File(filename);
        FileReader read;
        try {
            read =  new FileReader(file);
            BufferedReader bfr = new BufferedReader(read); 
            String ligne = bfr.readLine();
            //System.out.println("makato ve o");
            while(ligne!=null){
                //val.add(ligne);
                //words = ligne.split(" "); 
                ligne = bfr.readLine();
                nbreLigne++;
                //nbreMot = nbreMot + words.length; 
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        return nbreLigne;
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
            //System.out.println("makato ve o");
            while(ligne!=null){
                //val.add(ligne);
                words = ligne.split(" "); 
                ligne = bfr.readLine();
                //nbreMot++;
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
    public String specificColumn(String req){
        String apresNomTable = this.getResteAlaivo(req);
        String column = "";
        String[] splitter = apresNomTable.split(" ",this.countSplit(apresNomTable));
        column = splitter[0];
        return column;
    }
    
    public String getNomtable(String req){
        String resteAlaivo = this.getResteAlaivo(req);
        String rep = "";
        String[] nom_table = resteAlaivo.split(" ",this.countSplit(resteAlaivo));
        rep = nom_table[2];
        return rep;
    }

    public int countSousColonne(String req){
        String colonne = this.specificColumn(req);
        String[] nom_table = colonne.split(",");
        return nom_table.length;
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

    public Vector<String> informationParColonne(String filename, String req){
        Vector<String> vector = new Vector<String>();
        Vector<Integer> indice = this.getIndiceSousColonne(filename, req);
        String[][] select = this.selection(filename);
        for (int i = 0; i < select.length; i++) {
            for (int j = 0; j < select[i].length; j++) {
                for (int j2 = 0; j2 < indice.size(); j2++) {
                    if(j==indice.get(j2)){
                        vector.add(select[i][j]);
                    }
                }
            }
        }
        return vector;
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
    public int getIndiceColonne(String filename,String nom_colonne){
        int indice = 0;
        for (int i = 0; i < this.readFirstLine(filename).length; i++) {
            if(this.readFirstLine(filename)[i].compareToIgnoreCase(nom_colonne)==0){
                indice = i;
            }
        }
        return indice;
    }

    public Vector<Boolean>  getIndicedeSuppression(String filename, String colonne,int dep){
        Vector<Boolean> bool = new Vector<Boolean>();
        int indiceColonne = this.getIndiceColonne(filename, colonne);
        String[][] tableau = this.selection(filename);
        for (int i = dep+1; i < tableau.length; i++) {
            if(!tableau[i][indiceColonne].equals(tableau[dep][indiceColonne])){
                bool.add(true);
            }
            else{
                System.out.println("rehefa inona zany no makato");
                bool.add(false);
            }
        }
        return bool;
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

    public Vector<Integer> ligneSpeciale(String filename,String colonne, String valeur){
        Vector<Integer> ligne = new Vector<Integer>();
        int indice = getIndiceColonne(filename, colonne);
        String[][] donnee = this.selection(filename);
        for (int i = 0; i < donnee.length; i++) {
            //System.out.println("donnee = "+donnee[i][indice]);
            if(donnee[i][indice].compareToIgnoreCase(valeur)==0){
                //System.out.println("okay be");
                ligne.add(i);
            }
        }
        return ligne;
    }

    public Vector<String> lineAndColumnSpecial(String filename, String req){
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
    
    public void insert(String inserer,String filename){
        FileWriter write;
        FileReader read; 
        try {
            write = new FileWriter(filename,true);
            read = new FileReader(filename);
            BufferedWriter bfr = new BufferedWriter(write);
            BufferedReader rd = new BufferedReader(read);
            String val = rd.readLine();
            if(val!=null){
                bfr.write("\n");
            }
            
            bfr.write(inserer);
            bfr.close();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
    
    public String getValeurInserer(String req){
        String rep = "";
        String[] nom_table = req.split("valeur",2);
        rep = nom_table[1];
        return rep;
    }
    
    public String reSelectionPart1(String req){
        String rep = "";
        String[] nom_table = req.split(getNomtable(req),2);
        rep = nom_table[0];
        return rep;
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
    public String[] getCondition(String req){
        String[] cond = new String[2];
        String splitter = getWhere(req);
        String[] val = splitter.split("=", cond.length);
        cond[0] = val[0];
        cond[1] = val[1];
        return cond;

    }
    
    public String allVocabs(String nomTable,String where,String condition){
        String select = "alaivo daholo ny ";
        String selectWhere = " rehefa ";
        String eg = "=";
        String val = select+nomTable+selectWhere+where+eg+condition;
        return val;
    }
    public void selectWhere(){
        Scanner spo = new Scanner(System.in);
        String nbre = spo.nextLine();
        String part1 = this.reSelectionPart1(nbre);
        String nomTable = getNomtable(nbre);
        String nomColonne = this.getCondition(nbre)[0];
        String condition = this.getCondition(nbre)[1];
        String vocabulaire = this.allVocabs(nomTable, nomColonne, condition);
        if(nbre.compareToIgnoreCase(vocabulaire)==0){
            System.out.println(" tafiditra"+part1);
        }
    }

    public String getNomtableInsert(String req){
        String rep = "";
        String[] nom_table = req.split(" ",4);
        rep = nom_table[2];
        return rep;
    }

    public void vocabulaireInsert(String nom){
        String insert1 = "atao anaty ";
        String insert2 = "ny valeur(";
        String insert3 = ")";
        Scanner spo = new Scanner(System.in);
        String nbre = spo.nextLine();
        String nom_table = getNomtableInsert(nbre);
    }

    /*public void vocabulaireSelect(){
        String arret = "";
        String selection = "alaivo daholo ny ";
        System.out.println(selection);
        System.out.println("Saisir une requete");
        while(arret!="stop"){
            Scanner spo = new Scanner(System.in);
            String nbre = spo.nextLine();
            if(nbre.compareToIgnoreCase("stop")==0){
                arret = nbre;
            }
            System.out.println("requete = "+nbre);
            String req1 = reSelectionPart1(nbre);
            System.out.println("req1 = "+req1);
            String nom_table = getNomtable(nbre);
            if(req1.compareToIgnoreCase(selection)==0){
                Vector<String> vect = this.select(nom_table+".txt");
                try {
                    for (int i = 0; i < vect.size(); i++) {
                        System.out.println("val = "+this.select(nom_table+".txt").get(i));
                    }
                } catch (Exception e) {
                //TODO: handle exception
                    //System.out.println(e);
                }
            
            }
        }
        
        
    }*/

    public String[] getSelection(Vector<String> objSelect)throws Exception{
        System.out.println("taille = "+objSelect.size());
        String[] mot = new String[objSelect.size()];
        for (int p = 0; p < objSelect.size(); p++) {
            mot[p] = objSelect.get(p).split(" ").toString();
        }
        return mot;
    }

    public Object[] insertion(Object[] table,Object inserer){
        Object[] val = new Object[table.length+1];
        for (int i = 0; i < table.length; i++) {
            val[i] = table[i];
        }
        val[table.length] = inserer;
        //System.out.println(val);
        return val;
    }

    public Object[] union(Object[] tab1,Object[] tab2){
        Object[] finalO = new Object[tab1.length+tab2.length];
        //System.out.println("taille = "+finalO.length);
        for (int i = 0; i < tab1.length; i++) {
            finalO[i] = tab1[i];
        }
        for (int j = tab1.length; j < tab1.length+tab2.length; j++) {
            int x = j-tab1.length;
            finalO[j] = tab2[x];
        }
        return finalO;
    }
    public Object[] intersection(Object[] tab1,Object[] tab2){
        Object[] finalO = new Object[tab1.length+tab2.length];
        for (int i = 0; i < tab1.length; i++) {
            for (int j = 0; j < tab2.length; j++) {
                if(tab1[i].getClass().getDeclaredFields()[i].getName()==tab1[j].getClass().getDeclaredFields()[j].getName()){
                    finalO = tab1;
                }
            }
        }
        return finalO;
    }
    public Object selection(Object[] tab,String select){
        Object val = new Object();
        for (int i = 0; i < tab.length; i++) {
            if(tab[i].getClass().getDeclaredFields()[i].getName()==select){
                val = tab[i];
            }
        }
        return val;
    }
}
