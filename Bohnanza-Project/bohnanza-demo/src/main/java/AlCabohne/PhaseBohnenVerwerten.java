package AlCabohne;

import BohnGame.*;

public class PhaseBohnenVerwerten implements Phase {

    GameContextAlCab game;

    public PhaseBohnenVerwerten(GameContextAlCab game) {
        this.game = game;
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
        return false;
    }

    @Override
    public Phase nextPhase(Player player) {
        return null;
    }

    @Override
    public boolean ernten(Player player, int feldNr) {
        return false;
    }
}
