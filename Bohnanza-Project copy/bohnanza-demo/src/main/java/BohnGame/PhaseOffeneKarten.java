package BohnGame;
import io.bitbucket.plt.sdp.bohnanza.gui.CardType;

public class PhaseOffeneKarten implements Phase{

    public GameContext game;
    public PhaseOffeneKarten(GameContext game){
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
        //check ob Karten auch von offeneKarten stammen
        if(!player.offeneKarten.isEmpty()) {
                if (player.offeneKarten.contains(karte) && ( karte.typ.equals(typ) || typ == null)) {
                    player.bohnenfeld.anbauen(karte, feldNr);
                    player.offeneKarten.remove(karte);
                    return true;
            }
        }
        return false;
    }

    @Override
    public Phase nextPhase(Player player) {
        if(player.offeneKarten.isEmpty()) {
            return game.getNextPhase(this,game);
        }
        return null;
    }

    @Override
    public Handel handeln(Player aktiverSpieler, Player handelsPartner) {
        return null;
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

    @Override
    public boolean ernten(Player player, int feldNr) {
        Feld feld = player.bohnenfeld.getFeld(feldNr);
        player.score += feld.ertrag();
        game.ablageStapel.addAll(feld.feldLeeren());
        return true;
    }



}
