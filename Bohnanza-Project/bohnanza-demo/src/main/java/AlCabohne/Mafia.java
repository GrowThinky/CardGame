package AlCabohne;

import BohnGame.Bohnenfeld;
import BohnGame.Feld;
import BohnGame.Karte;

public class Mafia {

    private int score;
    private GameContextAlCab game;
    public Bohnenfeld mafiosi;

    public Mafia(GameContextAlCab game){
        this.game = game;
        this.mafiosi = game.makeMafiaBohnenfeld();
        setUpMafiaCards();
    }

    public boolean anbauen(Karte karte, int feldNr){
        return mafiosi.getFeld(feldNr).anbauen(karte);
    }

    public void ernten(int feldNr){
        Feld feld = mafiosi.getFeld(feldNr);
        score += feld.ertrag();
        game.ablageStapel.addAll(feld.feldLeeren());
    }

    public void setUpMafiaCards(){
        Karte karteNichtGleich;
        Karte karte1 = game.decktype.ziehen();
        mafiosi.anbauen(karte1,1);
        karteNichtGleich = plantTilDiffCard(1, karte1);
        if (mafiosi.felder.size() ==2) {
            mafiosi.anbauen(karteNichtGleich, 2);
            return;
        } else {
            mafiosi.anbauen(karteNichtGleich,2);
            karteNichtGleich =  plantTilDiffCard(2, karteNichtGleich);
            mafiosi.anbauen(karteNichtGleich, 3);
        }

    }

    public Karte plantTilDiffCard(int feldNr, Karte karte1){
        Karte karte2 = game.decktype.ziehen();
        while(karte2.typ.equals(karte1.typ)){
            mafiosi.anbauen(karte2,feldNr);
            karte2 = game.decktype.ziehen();
        }
        return karte2;
    }

    /**
     * checkt ob die Karte auf eines der Mafia Bohnenfeldder passt und baut sie in dem Fall auf
     * das entsprechende Feld an.
     * Ansonten return false.
     * @param karte die in der "Karten aufdecken" -Phase aufgedeckt wurde
     */
    public boolean takesCard(Karte karte){
        //TODO
        return false;
    }
}
