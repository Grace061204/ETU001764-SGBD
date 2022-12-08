package vocabulaire;

import operation.*;

public class Vocabulaire {
    Select select = new Select();
    Selectspecificcolumn selectspecificcolumn = new Selectspecificcolumn();
    Sansdoublon sansdoublon = new Sansdoublon();
    Selectwhere selectwhere = new Selectwhere();
    Specificlinecolumn specificlinecolumn = new Specificlinecolumn();
    public String[] vocabs(String req){
        String alaivo = "alaivo";
        String daholo = "daholo";
        String ny = "ny";
        String rehefa = "rehefa";
        String ireo = "ireo";
        String anaty = "anaty";
        String[] allvocabs = new String[2];
        allvocabs[0] = alaivo+" "+daholo+" "+ny;
        return allvocabs;
    }
}
