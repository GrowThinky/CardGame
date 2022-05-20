package BohnGame;

import java.util.ArrayList;
import io.bitbucket.plt.sdp.bohnanza.gui.CardType;

public class Karte {
    public io.bitbucket.plt.sdp.bohnanza.gui.CardType typ;
    public int position; //position in PlayerHand; -1 if not in Hand.
    public ArrayList<Integer> bohnOMeter;

    public Karte(CardType typ, ArrayList<Integer> bohnOMeter){
        this.bohnOMeter = bohnOMeter;
        this.typ = typ;
    }


}
