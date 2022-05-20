import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class _Test {
    GameContext game = new GameContext(new Deck());
    Player p1 = new Player("p1",1,game);
    Player p2 = new Player("p2",2,game);
    Player p3 = new Player("p3",3,game);

    @Before
    public void setUp() throws Exception {
        game.addPlayer(p1);
        game.addPlayer(p2);
        game.addPlayer(p3);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addPlayer() {
        assertEquals(3,game.playerList.size());
        assertEquals(p1,game.playerIterator.next());
        assertEquals(p1,game.playerList.get(0));
    }

    @Test
    public void kartenZiehen(){
        assertEquals(104,game.deck.kartenImDeck);
        for(Player p : game.playerList){
            p.kartenZiehen(5);
        }
        assertEquals(5,p1.hand.size());
        assertEquals(5,p2.hand.size());
        assertEquals(5,p3.hand.size());
        assertEquals(89,game.deck.kartenImDeck);
    }
    @Test
    public void kartenAnbauen(){
        p1.kartenZiehen(5);
        p1.nextPhase();
        assertEquals(Phase1.class, p1.phase.getClass());
        Karte k = p1.hand.get(4);
        p1.anbauen(4,1);
        assertEquals(4,p1.hand.size());
        assertEquals(1, p1.bohnenfeld.getFeld(1).feld.size());
        assertEquals(k,p1.bohnenfeld.getFeld(1).feld.get(0));
    }


}