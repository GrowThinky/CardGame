package BohnGame;

import java.util.ArrayList;

public class Bohnenfeld {
    public ArrayList<Feld> felder;
    private final boolean extendable;
    private boolean boughtField = false;


    public Bohnenfeld(int nrOfFields, boolean extendable) {
        this.extendable = extendable;
        felder = new ArrayList<>();
        for (int i = 0; i < nrOfFields;i++) {
            felder.add(new Feld());
        }
    }


    /**
     * @param feldNr: Nummer des Feldes das zurückgegeben werden soll (1,2 ... )
     * @return Feld
     */
    public Feld getFeld(int feldNr){
        try{
            return felder.get(feldNr-1);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("Feld doesn't exist");
        }
        return null;

    }

    public boolean anbauen(Karte karte, int nr) {
        return getFeld(nr).anbauen(karte);
    }

    public void buyField(){
        if(extendable) {
            if (!boughtField) {
                boughtField = true;
                felder.add(new Feld());
            }
        }
    }




}

