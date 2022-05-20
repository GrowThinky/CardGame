public class Phase4 implements Phase{

    int zuZiehendeKarten = 3;
    int gezogeneKarten = 0;
    public GameContext game;

    public Phase4(GameContext game){
        this.game = game;
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

        for(int i=0; i<anzahl;i++) {
            if(gezogeneKarten<zuZiehendeKarten) {
                player.hand.add(0, deck.ziehen());
                gezogeneKarten++;
            } else {return false;}
        }
        return true;
    }

    /**
     *
     * @param player
     * @return gibt eine Instanz der nächsten Phase zurück.
     */
    @Override
    public Phase nextPhase(Player player) {
        if(gezogeneKarten==zuZiehendeKarten) {
            if(game.playerIterator.hasNext()) {
                game.aktPlayer = game.playerIterator.next();
                game.aktPlayer.phase = new Phase1(game);
                return new PhaseNichtAktiv(game);
            }
        }
        return null;
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

    @Override
    public Handel handeln(Player aktiverSpieler, Player handelsPartner) {
        return null;
    }

    @Override
    public boolean ernten(Player player, int feldNr) {
        Feld feld = player.bohnenfeld.getFeld(feldNr);
        player.score += feld.ertrag();
        game.ablageStapel.addAll(feld.feldLeeren());
        return true;
    }


}
