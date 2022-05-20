public class Phase1 implements Phase{

    final private GameContext game;
    final private int maxAnbauen = 2; // maximal anbaubar in Phase1
    private int angebaut =  0;        //bisher vom Spieler angebaute Karten

    public Phase1(GameContext game){
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
        Bohne typ = player.bohnenfeld.getFeld(feldNr).typ;
        if(angebaut < maxAnbauen && ( karte.typ.equals(typ) || typ == null)) {
            if(handPos!=player.hand.size()-1){return false;}

            if(player.handZiehen(karte)){
                player.bohnenfeld.anbauen(karte, feldNr);
                angebaut++;
                return true;
                }
            }
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
            return new Phase2(game);
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