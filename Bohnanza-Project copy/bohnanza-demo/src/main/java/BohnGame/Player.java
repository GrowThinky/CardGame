package BohnGame;

import java.util.ArrayList;


public class Player {

    public int id;
    public int score = 0;
    public String name;
    public Bohnenfeld bohnenfeld;
    public Phase phase;
    public GameContext game;
    public ArrayList<Karte> hand;
    public ArrayList<Karte> offeneKarten;
    private Karte topCard;



    public Player(String name, int id, GameContext game){
        this.id = id;
        this.name = name;
        this.bohnenfeld = game.makePlayerBohnenfeld();
        this.phase = game.getInitialPhase();
        this.hand = new ArrayList<>();
        this.offeneKarten = new ArrayList<>();
        this.game = game;
    }

    public void kartenZiehen(int anzahl){
        phase.ziehen(this, game.decktype, anzahl);
    }

    public boolean ernten(int feldNr){
        return phase.ernten(this,feldNr);
    }

    public boolean anbauen(Karte karte, int feldNr){
        return phase.anbauen(karte,feldNr,this);
    }

    public void nextPhase(){
        Phase next = phase.nextPhase(this);
        if (next != null) {
            this.phase = next;
        }
    }


    public boolean handZiehen(Karte k){
        //Karte karte = hand.get(index);  .
        return hand.remove(k);

    }

    public Karte getTopCard(){
        return this.topCard;
    }

    public void updateTopCard(){
        this.topCard = hand.get(hand.size()-1);
    }






    public void handeln(Karte erstesAngebot){
        //phase2Karten traden (+alle Karten)
    }




}



