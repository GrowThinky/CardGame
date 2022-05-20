package BohnGame;

import io.bitbucket.plt.sdp.bohnanza.Game;
import io.bitbucket.plt.sdp.bohnanza.gui.CardType;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class GameContextBase extends GameContext {

    public Handel handel;
    private BaseGameAblauf phasenAblauf;
    DeckBaseGame deckBaseGame;

    public GameContextBase(int anzahlSpieler) {
        super(anzahlSpieler);
        setupPlayers();
        this.decktype = new DeckBaseGame();
        this.deckBaseGame = new DeckBaseGame();
        phasenAblauf = new BaseGameAblauf();
    }


    public void handeln(Player handelPartner) {
        Handel neuerHandel = aktPlayer.phase.handeln(aktPlayer, handelPartner);
        if (neuerHandel != null) {
            handel = neuerHandel;
        }
    }


    @Override
    public Phase getInitialPhase() {
        return new PhaseSetup(this);
    }

    @Override
    public Bohnenfeld makePlayerBohnenfeld() {
        return new Bohnenfeld(3, false);
    }


    @Override
    public void nextPlayer() {
        aktPlayer = playerIterator.next();
        aktPlayer.phase = new PhaseAnbauen(this);
    }

    @Override
    public int getInitialeKartenAnzahl() {
        return 5;
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




