package BohnGame;

import io.bitbucket.plt.sdp.bohnanza.gui.CardType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public abstract class Decktype {

    public ArrayList<Karte> karten = new ArrayList<>();
    public int anzahlProBohne;
    public int kartenImDeck;
    public ArrayList<Integer> dummy = new ArrayList<>(Arrays.asList(0,1,1,1,2,3));


    public Decktype(){
        deckFüllen();
    }



    public Karte ziehen(){
        if(!karten.isEmpty()) {
            Karte k = karten.get(0);
            karten.remove(0);
            kartenImDeck--;
            return k;
        }
        return null;
    }

    public abstract void deckFüllen();
}

