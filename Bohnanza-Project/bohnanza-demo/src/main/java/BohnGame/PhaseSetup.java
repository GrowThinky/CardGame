package BohnGame;

public class PhaseSetup implements Phase {

    int initialeKartenAnzahl;

    public GameContext game;
    public PhaseSetup(GameContext game){
        this.game = game;
        this.initialeKartenAnzahl = game.getInitialeKartenAnzahl();
    }


    @Override
    public boolean anbauen(Karte karte, int feldNr, Player player) {
        return false;
    }

    @Override
    public Handel handeln(Player aktiverSpieler, Player handelsPartner) {
        return null;
    }

    @Override
    public boolean ziehen(Player player, Decktype decktype, int anzahl) {
        Karte karte;
        if(anzahl!= initialeKartenAnzahl){
            throw new IllegalStateException("Initianle Karten:" +initialeKartenAnzahl);
        }
        for (int i = 0; i < anzahl; i++) {
            karte = decktype.ziehen();
            if (karte != null) {
                player.hand.add(karte);
                player.updateTopCard();
            } else {
                break;
            }
        }
        return false;
    }


    @Override
    public Phase nextPhase(Player player) {
        if(player.equals(game.aktPlayer) && initialeKartenAnzahl == player.hand.size()){
            return game.getNextPhase(this,game);
        }
        return null;
    }

    @Override
    public boolean ernten(Player player, int feldNr) {
        return false;
    }
}
