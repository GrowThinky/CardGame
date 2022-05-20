package BohnGame;
import io.bitbucket.plt.sdp.bohnanza.gui.CardType;

public class PhaseAnbauen implements Phase{

    final private GameContext game;
    final private int maxAnbauen = 2; // maximal anbaubar in Phase1
    private int angebaut =  0;        //bisher vom Spieler angebaute Karten

    public PhaseAnbauen(GameContext game){
        this.game = game;
    }

    /**
     *
     * @param karte Anzubauende Karte.
     * @param feldNr Nummer (1-3) des Feldes auf dem angebaut werden soll.
     * @param player spieler
     * @return
     */
    @Override
    public boolean anbauen(Karte karte, int feldNr, Player player) {
        CardType typ = player.bohnenfeld.getFeld(feldNr).typ;
        if(angebaut < maxAnbauen && ( karte.typ.equals(typ) || typ == null)) {
            if(!karte.equals(player.getTopCard())){return false;}
            if(player.handZiehen(karte)){
                player.bohnenfeld.anbauen(karte, feldNr);
                player.updateTopCard();
                angebaut++;
                return true;
                }
            }
        return false;
    }

    /**
     *
     * @param player Player der ziehen will.
     * @param decktype Instanz des Decks aus dem gezogen werden soll.
     * @param anzahl Wie viele Karten sollen gezogen werden?
     * @return
     */
    @Override
    public boolean ziehen(Player player, Decktype decktype, int anzahl) {
        return false;
    }

    /**
     *
     * @param player
     * @return gibt eine Instanz der nächsten Phase zurück.
     */
    @Override
    public Phase nextPhase(Player player) {
        if(angebaut>= 1) {
            return game.getNextPhase(this,game);
        } else {
            return null;
        }
    }

    /**
     *
     * @param player Spieler, der ernten will.
     * @param feldNr Feldnummer die geerntet werden soll.
     * @return
     */
    @Override
    public boolean ernten(Player player, int feldNr) {
        Feld feld = player.bohnenfeld.getFeld(feldNr);
        player.score += feld.ertrag();
        game.ablageStapel.addAll(feld.feldLeeren());
        return true;
    }




    @Override
    public Handel handeln(Player aktiverSpieler, Player handelsPartner) {
        return null;

    }







}