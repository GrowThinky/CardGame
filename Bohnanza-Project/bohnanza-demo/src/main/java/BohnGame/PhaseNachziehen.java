package BohnGame;

public class PhaseNachziehen implements Phase{

    int zuZiehendeKarten = 3;
    int gezogeneKarten = 0;
    public GameContext game;

    public PhaseNachziehen(GameContext game){
        this.game = game;
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
        Karte karte;
        for(int i=0;i<anzahl;i++) {
            if(gezogeneKarten<zuZiehendeKarten) {
                karte = decktype.ziehen();
                if(karte != null) {
                    player.hand.add(karte);
                    player.updateTopCard();
                    gezogeneKarten++;
                }
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
            if(!game.playerIterator.hasNext()) {
                game.restartPlayerCycle();
            }
            game.nextPlayer();
            return game.getNextPhase(this,game);

        }
        return null;
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
