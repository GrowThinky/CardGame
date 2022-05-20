import java.util.ArrayList;


public class Player {

    public int id;
    public int score = 0;
    public String name;
    public Bohnenfeld bohnenfeld;
    public Phase phase;
    public GameContext game;
    public ArrayList<Karte> hand;
    public ArrayList<Karte> phase2Karten;


    public Player(String name, int id, GameContext game){
        this.id = id;
        this.name = name;
        this.bohnenfeld = new Bohnenfeld();
        this.phase = new Phase0(game);
        this.hand = new ArrayList<>();
        this.phase2Karten = new ArrayList<>();
        this.game = game;
    }

    public void kartenZiehen(int anzahl){
        phase.ziehen(this,game.deck,anzahl);
    }

    public boolean ernten(int feldNr){
        return phase.ernten(this,feldNr);
    }

    public boolean anbauen(int handPos, int feldNr){
        return phase.anbauen(hand.get(handPos),feldNr,this, handPos);
    }

    public void nextPhase(){
        Phase next = phase.nextPhase(this);
        if (next != null) {
            phase = next;
        }
    }


    public boolean phase2Anbauen(int pos, int feldnr){
        return phase.anbauen(phase2Karten.get(pos),feldnr,this,pos);
    }


    public boolean handZiehen(Karte k){
        //Karte karte = hand.get(index);  .
        return hand.remove(k);

    }




    public void handeln(Karte erstesAngebot){
        //phase2Karten traden (+alle Karten)
    }




}



