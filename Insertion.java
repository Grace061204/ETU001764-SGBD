package operation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;

public class Insertion {
    public String[] splitSpace(String req){
        String[] vocabs = req.split(" ");
        return vocabs;
    }
    public String[] getValues(String req){
        String[] vocabulaire = this.splitSpace(req);
        String[] value = vocabulaire[4].split(",");
        return value;
    }
    public void inserer(String req){
        String[] insert = this.splitSpace(req);
        File file = new File(insert[2]+".txt");
        BufferedWriter bufWriter = null;
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("data\\"+insert[2]+".txt", true);
            bufWriter = new BufferedWriter(fileWriter);
            //Insérer un saut de ligne
            bufWriter.newLine();
            for (int i = 0; i < getValues(req).length; i++) {
                bufWriter.write(" "+getValues(req)[i]+" ");
            }
            bufWriter.close();
        }catch (Exception e) {
            // TODO: handle exception
        }
        
    }
}
