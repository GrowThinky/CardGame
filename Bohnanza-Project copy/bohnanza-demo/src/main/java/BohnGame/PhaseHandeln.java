package BohnGame;

public class PhaseHandeln implements Phase{

    private GameContext game;
    private boolean zweiGezogen = false;

    public PhaseHandeln(GameContext game){
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
        if(!zweiGezogen) {
            zweiGezogen = true;
            player.offeneKarten.add(decktype.ziehen());
            player.offeneKarten.add(decktype.ziehen());
            return true;
        }
        return false;
    }

    @Override
    public Phase nextPhase(Player player) {
        if (zweiGezogen) {
            return game.getNextPhase(this,game);
        }
        return null;
    }

    @Override
    public boolean ernten(Player player, int feldNr) {
        Feld feld = player.bohnenfeld.getFeld(feldNr);
        player.score += feld.ertrag();
        game.ablageStapel.addAll(feld.feldLeeren());
        return true;
    }

    @Override
    public Handel handeln(Player aktiverSpieler, Player handelsPartner) {
        if(zweiGezogen) {
            return new Handel(aktiverSpieler, handelsPartner);
        }
        return null;
    }

}
