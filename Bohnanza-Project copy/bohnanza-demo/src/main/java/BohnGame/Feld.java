package BohnGame;

import java.util.ArrayList;

public class Feld {

    ArrayList<Karte> feld;
    io.bitbucket.plt.sdp.bohnanza.gui.CardType typ = null;
    ArrayList<Integer> bohnOMeter;

    public Feld(){
        this.feld = new ArrayList<>();
    }

    public boolean anbauen(Karte karte) {
        if (feld.size() == 0) {
            this.typ = karte.typ;
            this.bohnOMeter = karte.bohnOMeter;
            feld.add(karte);
            return true;
        } else if ( karte.typ == this.typ) {
            feld.add(karte);
        }
        return false;

    }

    public int ertrag(){
        return bohnOMeter.get(feld.size());
    }

    public ArrayList<Karte> feldLeeren(){
        ArrayList<Karte> tmp = new ArrayList<>(feld);
        feld = new ArrayList<>();
        typ = null;
        return tmp;
    }

    public int getSize(){
        return feld.size();
    }

    public ArrayList<Karte> getFeld(){
        return this.feld;
    }

}
