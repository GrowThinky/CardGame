public class Phase2 implements Phase{

    private GameContext game;
    private boolean zweiGezogen = false;

    public Phase2(GameContext game){
        this.game = game;
    }

    /**
     *
     * @param karte Anzubauende Karte.
     * @param feldNr Nummer (1-3) des Feldes auf dem angebaut werden soll.
     * @param player spieler
     * @param handPos position der anzubauenden Karte in der Hand des spielers
     * @return
     */
    @Override
    public boolean anbauen(Karte karte, int feldNr, Player player, int handPos) {
        return false;
    }

    /**
     *
     * @param player Player der ziehen will.
     * @param deck Instanz des Decks aus dem gezogen werden soll.
     * @param anzahl Wie viele Karten sollen gezogen werden?
     * @return
     */
    @Override
    public boolean ziehen(Player player, Deck deck, int anzahl) {
        if(!zweiGezogen) {
            zweiGezogen = true;
            player.phase2Karten.add(deck.ziehen());
            player.phase2Karten.add(deck.ziehen());
        }
        return false;
    }

    @Override
    public Phase nextPhase(Player player) {
        if (zweiGezogen) {
            return new Phase3(game);
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
