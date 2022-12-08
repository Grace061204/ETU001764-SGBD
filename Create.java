package operation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Create {
    public String[] getCreateReq(String req){
        String[] nom_table = req.split(" ");
        return nom_table;
    }
    public String[] getColonne(String req){
        String[] getReq = this.getCreateReq(req);
        String[] colonne = getReq[3].split(",");
        return colonne;
    }
    public void creation(String req){
        // String nomFichier = "data\\"+reqSplit[2]+".txt";
        // File fichier;
 
        // fichier = new File(nomFichier);
 
        String[] colonne = this.getColonne(req);
        String[] reqSplit = this.getCreateReq(req);
        File file = new File("data\\"+reqSplit[2]+".txt");
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file,true))){
            for (int i = 0; i < colonne.length; i++) {
                bw.write(colonne[i]+" ");
            }
            bw.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
