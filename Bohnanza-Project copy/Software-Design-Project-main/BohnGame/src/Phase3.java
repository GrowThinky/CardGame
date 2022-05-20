public class Phase3 implements Phase{

    public GameContext game;
    public Phase3(GameContext game){
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
    @Override //boolean von Feld.anbauen nehmen = ez.
    public boolean anbauen(Karte karte, int feldNr, Player player, int handPos) {
        Bohne typ = player.bohnenfeld.getFeld(feldNr).typ;
        if(!player.phase2Karten.isEmpty()) {
                if (player.phase2Karten.contains(karte) && ( karte.typ.equals(typ) || typ == null)) {
                    player.bohnenfeld.anbauen(karte, feldNr);
                    player.phase2Karten.remove(karte);
                    return true;

            }
        }
        return false;
    }

    @Override
    public Phase nextPhase(Player player) {
        if(player.phase2Karten.isEmpty()) {
            return new Phase4(game);
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
     * @param deck Instanz des Decks aus dem gezogen werden soll.
     * @param anzahl Wie viele Karten sollen gezogen werden?
     * @return
     */
    @Override
    public boolean ziehen(Player player, Deck deck, int anzahl) {
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
