package AlCabohne;

import BohnGame.*;


import java.lang.reflect.InvocationTargetException;
import java.security.InvalidParameterException;
import java.util.ArrayList;

public class GameContextAlCab extends GameContext {

    public Mafia mafia;
    private final AlCabAblauf phasenAblauf;
    public ArrayList<ArrayList<Karte>> aufgedeckteKarten;
    public DeckAlCabohne deckAlCabohne;


    public GameContextAlCab(int nrPlayers) {
        super(nrPlayers);
        this.decktype = new DeckAlCabohne();
        this.deckAlCabohne = new DeckAlCabohne();
        this.phasenAblauf = new AlCabAblauf();
        this.mafia = new Mafia(this);
        this.aufgedeckteKarten = new ArrayList<>();
        setupPlayers();

    }

    @Override
    public void nextPlayer() {
        aktPlayer = playerIterator.next();
        aktPlayer.phase = new PhaseBohnenVerwerten(this);
    }

    public Bohnenfeld makeMafiaBohnenfeld(){
        if(anzahlSpieler==2) {
            return new Bohnenfeld(2, false);
        } else if (anzahlSpieler==1){
            return new Bohnenfeld(3, false);
        }
        throw new IllegalArgumentException("1 or 2 players only");
    }


    public void buyField(Player player){
        player.bohnenfeld.buyField();
    }

    @Override
    public Phase getInitialPhase(){
        return new PhaseSetup(this);
    }

    @Override
    public Bohnenfeld makePlayerBohnenfeld() {
        if(anzahlSpieler==2) {
            return new Bohnenfeld(2, true);
        } else if (anzahlSpieler==1){
            return new Bohnenfeld(3, false);
        }
        throw new IllegalArgumentException("1 or 2 players only");
    }

    @Override
    public int getInitialeKartenAnzahl() {
        if (anzahlSpieler == 2) {
            return 5;
        } else if (anzahlSpieler == 1) {
            return 6;
        } else {
            throw new InvalidParameterException("unsupported number of players");
        }
    }

    @Override
    public Phase getNextPhase(Phase aktPhase, GameContext game) {
        try {
            return phasenAblauf.nextPhase(aktPhase, game);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
