import java.util.ArrayList;

public class Bohnenfeld {
    ArrayList<Feld> felder;
    Feld feld1;
    Feld feld2;
    Feld feld3;

    public Bohnenfeld(){
        feld1 = new Feld();
        feld2 = new Feld();
        feld3 = new Feld();
        felder = new ArrayList<>();
        felder.add(0,null);
        felder.add(feld1);
        felder.add(feld2);
    }

    public Feld getFeld(int index){
        return felder.get(index);
    }

    public boolean anbauen(Karte karte, int nr) {
        if (nr == 1){
            return feld1.anbauen(karte);
            }
        else if (nr == 2){
            return feld2.anbauen(karte);
            }
        else if (nr == 3){
            return feld3.anbauen(karte);
            }
        return false;
    }



}

