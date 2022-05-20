public class Phase0 implements Phase {

    int initialeKartenAnzahl = 5;

    public GameContext game;
    public Phase0(GameContext game){
        this.game = game;
    }


    @Override
    public boolean anbauen(Karte karte, int feldNr, Player player, int handPos) {
        return false;
    }

    @Override
    public Handel handeln(Player aktiverSpieler, Player handelsPartner) {
        return null;
    }

    @Override
    public boolean ziehen(Player player, Deck deck, int anzahl) {
        Karte gezogen;
        if(anzahl!= initialeKartenAnzahl){
            throw new IllegalStateException("Initianle Karten:" +initialeKartenAnzahl);
        }
        for (int i = 0; i < anzahl; i++) {
            gezogen = deck.ziehen();
            if (gezogen != null) {
                player.hand.add(gezogen);
            } else {
                break;
            }
        }
        return false;
    }


    @Override
    public Phase nextPhase(Player player) {
        if(player.equals(game.aktPlayer)){
            return new Phase1(game);
        }
        return null;
    }

    @Override
    public boolean ernten(Player player, int feldNr) {
        return false;
    }
}
