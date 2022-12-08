package operation;

import java.text.MessageFormat;
import java.util.Vector;
import java.io.*;

public class Traitement {
    Select select = new Select();
    String daholo = "daholo";
    String nom_table = "";
    String req1 = "";
    String alaivo = "alaivo";
    String ny = "ny";
    String rehefa = "rehefa";
    Selectwhere selectwhere = new Selectwhere();
    String selection = alaivo+" "+daholo+" "+ny+" ";
    String createReq = "create";
    String table = "table";
    Create create = new Create();
    String creer = createReq+" "+table+" ";
    String insert = "insert";
    String into = "into";
    String values = "values";
    String reqInsertion = insert+" "+into+" ";
    Specificlinecolumn specificlinecolumn = new Specificlinecolumn();
    Insertion insertion = new Insertion();
    Selectspecificcolumn specificcolumn = new Selectspecificcolumn();

    public Vector<String> getTraitement(String message){
        String column = "";
        Vector<String> rep = new Vector<String>();
        if(!message.startsWith("/quit")){
            if(!message.contains("create") && !message.contains("alaivo")  && !message.contains("insert")){

            }
            else{

                if(!message.contains("insert") && !message.contains("create")){
        
                    column = alaivo+" "+specificcolumn.specificColumn(message)+" "+ny+" "+specificcolumn.getNomtable(message);
                }
                String nomTableInsert = insertion.splitSpace(message)[2];
                System.out.println("column = "+column);
                String[] creation = create.getCreateReq(message);
                String requeteCreation = creation[0]+" "+creation[1]+" ";
                System.out.println("req1 = "+req1);
                System.out.println(message);
                if(select.getDaholo(message).compareToIgnoreCase(daholo)==0){
                    nom_table = select.getNomtable(message);
                    req1 = select.reSelectionPart1(message);
                    if(!message.contains("REHEFA") && !message.contains("rehefa")){
                        System.out.println("requete = "+message);
                        System.out.println("req1 = "+req1);
                        System.out.println("selection = "+selection);
                        Vector<String> vect = this.select.select(nom_table+".txt");
                        try {
                                rep = vect;
                        } catch (Exception e) {
                            //TODO: handle exception
                            System.out.println(e);
                        }
                                            
                    }
                                        
                    if(message.contains(rehefa) || message.contains("REHEFA")){
                        nom_table = selectwhere.getNomtable(message);
                        req1 = select.reSelectionPart1(message);
                        System.out.println("req1 = "+req1);
                        selection = alaivo+" "+daholo+" "+ny+" ";
                        System.out.println("selection = "+selection);
                        Vector<String> val = selectwhere.getAllLigneSpeciale(nom_table+".txt", message);
                        try {
                               
                            rep = val;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            e.printStackTrace();
                                            //TODO: handle exception
                        }
                    }
                }
                else if(!message.contains("daholo") && specificlinecolumn.getAlaivo(message).compareToIgnoreCase(alaivo)==0 && message.contains("rehefa")){
                    System.out.println("nomTable"+specificlinecolumn.getNomtable(message));
                    Vector<String> whereSpecial = specificlinecolumn.lineAndColumnSpecial(specificlinecolumn.getNomtable(message)+".txt", message);
                    System.out.println("tafiditra");
                    try {
                        rep = whereSpecial;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                        //TODO: handle exception
                    }
                }
                else if(!message.contains("daholo") && message.compareToIgnoreCase(column)==0){
                    Vector<String> vector = specificcolumn.informationParColonne(specificcolumn.getNomtable(message)+".txt", message);
                        
                    rep = vector;
                }
                else if(message.contains("insert") || message.contains("INSERT")){
                    String allInsert = reqInsertion+nomTableInsert+" "+values+" "+insertion.splitSpace(message)[4];
                    if(message.compareToIgnoreCase(allInsert)==0){
                        insertion.inserer(message);
                    }
                }
                else if(message.contains("create") || message.contains("CREATE")){
                    if(requeteCreation.compareToIgnoreCase(creer)==0){
                        create.creation(message);
                    }
                }
            }

        }
        return rep;
    }

    public String verification(String message){
        String column = "";
        String rep = "";
        if(!message.startsWith("/quit")){
            if(!message.contains("create") && !message.contains("alaivo")  && !message.contains("insert")){
                rep = "syntaxe invalide";

            }
            else{

                if(!message.contains("insert") && !message.contains("create")){
        
                    column = alaivo+" "+specificcolumn.specificColumn(message)+" "+ny+" "+specificcolumn.getNomtable(message);
                }
                String nomTableInsert = insertion.splitSpace(message)[2];
                System.out.println("column = "+column);
                String[] creation = create.getCreateReq(message);
                String requeteCreation = creation[0]+" "+creation[1]+" ";
                System.out.println("req1 = "+req1);
                System.out.println(message);
                if(select.getDaholo(message).compareToIgnoreCase(daholo)==0){
                    nom_table = select.getNomtable(message);
                    req1 = select.reSelectionPart1(message);
                    if( !message.contains("REHEFA") && !message.contains("rehefa")){
                    }
                                    
                    if(message.contains(rehefa) || message.contains("REHEFA")){
                        nom_table = selectwhere.getNomtable(message);
                        req1 = select.reSelectionPart1(message);
                        System.out.println("req1 = "+req1);
                        selection = alaivo+" "+daholo+" "+ny+" ";
                        System.out.println("selection = "+selection);
                        Vector<String> val = selectwhere.getAllLigneSpeciale(nom_table+".txt", message);
                        try {
                            for (int i = 0; i < val.size(); i++) {
                                if(val.get(i).equals("")){
                                    rep = "aucune ligne selectionne";
                                }    
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            e.printStackTrace();
                            //TODO: handle exception
                        }
                    }
                }
                else if(!message.contains("daholo") && specificlinecolumn.getAlaivo(message).compareToIgnoreCase(alaivo)==0 && message.contains("rehefa")){
                    System.out.println("nomTable"+specificlinecolumn.getNomtable(message));
                    Vector<String> whereSpecial = specificlinecolumn.lineAndColumnSpecial(specificlinecolumn.getNomtable(message)+".txt", message);
                    
                    try {
                        for (int i = 0; i < whereSpecial.size(); i++) {
                            if(whereSpecial.get(i).equals("")){
                                rep = "aucune ligne selectionne";
                            }    
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                        //TODO: handle exception
                    }
                }
                else if(!message.contains("daholo") && message.compareToIgnoreCase(column)==0){
                    Vector<String> vector = specificcolumn.informationParColonne(specificcolumn.getNomtable(message)+".txt", message);
                }
                else if(message.contains("insert") || message.contains("INSERT")){
                    String allInsert = reqInsertion+nomTableInsert+" "+values+" "+insertion.splitSpace(message)[4];
                    if(message.compareToIgnoreCase(allInsert)==0){
                        rep = "insertion effectuee";
                    }
                }
                else if(message.contains("create") || message.contains("CREATE")){
                    if(requeteCreation.compareToIgnoreCase(creer)==0){
                       
                        rep = "creation de table effectuee";
                     
                    }
                }
                else{
                    rep = "syntaxe invalide";
                }
            }

        }
        return rep;
    }
}
