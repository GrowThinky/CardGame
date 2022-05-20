package AlCabohne;


import BohnGame.*;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;


import static org.junit.Assert.*;

public class _Test_elCab {

    static GameContextAlCab game;
    static Player p1;
    static Player p2;
    static Player p3;

    @Before
    public void setUp() {
        game = new GameContextAlCab(2);
        p1 = game.playerList.get(0);
        p2 = game.playerList.get(1);

    }

    @Test
    public void buyField(){
        assertEquals(2,p1.bohnenfeld.felder.size()); 
        game.buyField(p1);
        assertEquals(3,p1.bohnenfeld.felder.size());
        game.buyField(p1);
        assertEquals(3,p1.bohnenfeld.felder.size());
    }

    @Test
    public void setupMafiaCards() {
        Mafia mafia = game.mafia;
        Feld feld;
        // workaround because @RepeatedTest doesn't work.
        for(int repetition = 0; repetition < 20; repetition++) {
            game.mafia.setUpMafiaCards();
            for (int i = 1; i < mafia.mafiosi.felder.size() + 1; i++) {
                feld = mafia.mafiosi.getFeld(i);
                assertTrue(feld.getSize() > 0);
                //assertTrue(verifyAllEqualUsingStream(feld.getFeld()));
            }
        }
    }

    @Test
    public void phase1AlCab(){
        assertEquals(PhaseSetup.class, p1.phase.getClass());
        // vor Erfüllung der Bedingung:
        p1.nextPhase();
        assertEquals(PhaseSetup.class, p1.phase.getClass());
        // nach Erfüllung der Bedingung:
        p1.kartenZiehen(game.getInitialeKartenAnzahl());
        p1.nextPhase();
        assertEquals(PhaseAnbauen.class, p1.phase.getClass());

    }









        public boolean verifyAllEqualUsingStream(ArrayList<Karte> list) {
            return list.stream()
                    .distinct()
                    .count() <= 1;
        }

}