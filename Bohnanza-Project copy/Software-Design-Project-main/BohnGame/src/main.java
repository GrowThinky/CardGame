import java.util.ArrayList;

public class main {


    public static void main(String[] args) {


        GameContext game = new GameContext(new Deck());

        Player p1 = new Player("p1",1,game);
        Player p2 = new Player("p2",2,game);
        Player p3 = new Player("p3",3,game);
        game.addPlayer(p1);
        game.addPlayer(p2);
        game.addPlayer(p3);


        p1.kartenZiehen(5);
        p2.kartenZiehen(5);
        p3.kartenZiehen(5);
        int lucky = 0;

        //System.out.println(p1.anbauen(4,1));

            game.playerIterator.next();
            p1.nextPhase();
        for(Player p : game.playerList) {
            System.out.println();
            System.out.println(p.name + " ist an der Reihe!");
            System.out.println(p.name+" Hand: ");
            for (Karte k : p.hand) {
                System.out.println(k.typ);
            }
            System.out.println();

            System.out.println("Phase 1: ");
            System.out.println(p.name+" baut " + p.hand.get(4).typ + " auf Feld 1 an.");
            p.anbauen(4, 1);

            System.out.println();
            System.out.println(p.name+ " Hand: ");
            for (Karte k : p.hand) {
                System.out.println(k.typ);
            }
            System.out.println();

            Karte tmp = p.hand.get(p.hand.size()-1);
            p.anbauen(p.hand.size()-1, 1);
            if (tmp.typ.equals(p.bohnenfeld.getFeld(1).typ)) {
                System.out.println(p.name+" baut " + p.hand.get(p.hand.size()-1).typ + " auf Feld 1 an.");
                lucky+=1;

            }
            System.out.println();
            p.nextPhase();
            System.out.println(p1.name + " is in "+p.phase.getClass().getName());
            System.out.println("Phase 2: ");
            p.kartenZiehen(2);
            System.out.println(p.name+" hat eine " + p.phase2Karten.get(0).typ + " und eine " + p.phase2Karten.get(1).typ + " gezogen.");


            System.out.println();
            p.nextPhase();
            System.out.println(p1.name + " is in "+p.phase.getClass().getName());
            System.out.println("Phase 3: ");

            System.out.println(p.name + " erntet feld1");
            p.ernten(1);
            p.phase2Anbauen(0, 1);
            p.phase2Anbauen(0, 2);

            System.out.println();
            p.nextPhase();
            System.out.println(p1.name + " is in "+p.phase.getClass().getName());
            System.out.println("Phase 4: ");

            p.kartenZiehen(3);
            System.out.println(p.name+" hat " + p.hand.get(0).typ + ", " + p.hand.get(1).typ + " und " + p.hand.get(2).typ + " gezogen.");

            System.out.println();
            p.nextPhase();
            System.out.println(p.name + " is in "+p.phase.getClass().getName());
            System.out.println();
            System.out.println(p.name+" ist fertig!");

        }
    }
}
