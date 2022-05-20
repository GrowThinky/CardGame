


import AlCabohne.GameContextAlCab;
import BohnGame.Feld;
import BohnGame.Karte;
import BohnGame.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.RepeatedTest;


import java.util.ArrayList;
import AlCabohne.*;

import static org.junit.Assert.*;

public class TestAlCab {

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

    @RepeatedTest(20)
    public void setupMafiaCards() {
        Mafia mafia = game.mafia;
        Feld feld;
        game.mafia.setUpMafiaCards();
        for (int i = 1; i < mafia.mafiosi.felder.size()+1; i++) {
            feld = mafia.mafiosi.getFeld(i);
            assertTrue(feld.getSize() > 0);
            //assertTrue(verifyAllEqualUsingStream(feld.getFeld()));
            for(Karte k: feld.getFeld()){
                System.out.println(k.typ);
            }
        }
    }

    public boolean verifyAllEqualUsingStream(ArrayList<Karte> list) {
        return list.stream()
                .distinct()
                .count() <= 1;
    }

}