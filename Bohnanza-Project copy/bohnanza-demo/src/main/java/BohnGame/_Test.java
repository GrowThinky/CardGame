package BohnGame;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class _Test {

    static GameContextBase game;
    static Player p1;
    static Player p2;
    static Player p3;

    @Before
    public void setUp() {
        game = new GameContextBase(3);
        p1 = game.playerList.get(0);
        p2 = game.playerList.get(1);
        p3 = game.playerList.get(2);
        for(Player p : game.playerList){
            p.kartenZiehen(5);
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    //@DisplayName()
    public void addPlayer() {
        assertEquals(3,game.playerList.size());
        assertEquals(p1,game.playerIterator.next());
        assertEquals(p1,game.playerList.get(0));
    }

    @Test
    public void kartenZiehen(){
        assertEquals(89,game.decktype.kartenImDeck);
        for(Player p : game.playerList){
            p.kartenZiehen(5);
        }
        assertEquals(10,p1.hand.size());
        assertEquals(10,p2.hand.size());
        assertEquals(10,p3.hand.size());
        assertEquals(74,game.decktype.kartenImDeck);

        for(Player p: game.playerList){
            assertEquals(p.getTopCard(),p.hand.get(p.hand.size()-1));
        }
    }


    @Test
    public void Phase1Test(){
        game.playerIterator.next();
        assertEquals(p1,game.aktPlayer);
        assertEquals(p1.hand.size(),5);
        p1.nextPhase();
        assertEquals(PhaseAnbauen.class, p1.phase.getClass());
        //test: Oberste Karte anbauen:
        Karte k = p1.hand.get(4);
        p1.anbauen(p1.getTopCard(),1);
        assertEquals(p1.bohnenfeld.getFeld(1).typ, k.typ);
        assertEquals(4,p1.hand.size());
        assertEquals(p1.getTopCard(),p1.hand.get(p1.hand.size()-1));
        assertEquals(1, p1.bohnenfeld.getFeld(1).feld.size());
        assertEquals(k,p1.bohnenfeld.getFeld(1).feld.get(0));
        //test Falsche karte anbauen:
        p1.anbauen(p1.hand.get(2),2);
        assertEquals(4,p1.hand.size());
        assertEquals(0, p1.bohnenfeld.getFeld(2).feld.size());
        p1.nextPhase();
        assertEquals(PhaseHandeln.class, p1.phase.getClass());
    }

    @Test
    public void fieldOnlyAcceptsOneBeanType() {
        p1.nextPhase();
        assertEquals(p1.phase.getClass(), PhaseAnbauen.class);
        Karte k1 = p1.hand.get(p1.hand.size()-1);
        p1.anbauen(p1.getTopCard(),1);

        //selbe karte Anbauen:
        p1.hand.add(k1);
        p1.updateTopCard();
        p1.anbauen(p1.getTopCard(),1);
        assertEquals(2, p1.bohnenfeld.getFeld(1).feld.size());

        //andere Karte anbauen
        Karte k2 = game.decktype.karten.get(0);
        int i = 1;
        while(k2.typ.equals(k1.typ)){
            k2 = game.decktype.karten.get(i);
            i++;
        }
        p1.hand.add(k2);
        p1.anbauen(p1.getTopCard(),1);
        assertEquals(p1.bohnenfeld.getFeld(1).feld.size(),2);
        p1.nextPhase();
        assertEquals(PhaseHandeln.class, p1.phase.getClass());
    }

    @Test
    public void Phase2Test() {
        p2.phase = new PhaseHandeln(game);
        p2.kartenZiehen(2);
        assertEquals(2,p2.offeneKarten.size());
    }

    @Test
    public void Phase3Test() {

    }
    @Test
    public void Phase4Test() {
        p3.phase = new PhaseNachziehen(game);
        p3.kartenZiehen(2);
        assertEquals(7,p3.hand.size());
        //try to change players BEFORE fulfilling requirements
        p3.nextPhase();
        assertEquals(PhaseNachziehen.class, p3.phase.getClass());

        //try to change players AFTER fulfilling requirements
        p3.kartenZiehen(1);
        p3.nextPhase();
        assertEquals(PhaseNichtAktiv.class, p3.phase.getClass());
        assertEquals(p1,game.aktPlayer);
    }



}