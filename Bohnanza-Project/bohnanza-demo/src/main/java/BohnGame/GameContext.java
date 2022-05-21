package BohnGame;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class GameContext {
    public ArrayList<Player> playerList = new ArrayList<>();
    public Iterator<Player> playerIterator;
    public Decktype decktype;
    public Phase phase;
    public ArrayList<Karte> ablageStapel;
    public int anzahlSpieler;
    public int deckEmptyCounter = 0;
    public Player aktPlayer;


    public GameContext(int anzahlSpieler) {
        ablageStapel = new ArrayList<>();
        this.anzahlSpieler = anzahlSpieler;
    }

    public void addPlayer(Player player){
        if(playerList.isEmpty()){
            aktPlayer = player;
        }
        this.playerList.add(player);
        this.playerIterator = playerList.iterator();
    }

    public void ziehen(Player player){
        if(player.id == aktPlayer.id){
            phase.ziehen(player, decktype, 0);
            }
        }

    public void restartPlayerCycle(){
        this.playerIterator = playerList.iterator();
    }

    protected void setupPlayers(){
        for(int i=0;i<anzahlSpieler;i++){
            addPlayer(new Player("p"+i,i,this));       //Abhängigkeit Player_id -> GUI CompartmentLists
        }
    }

    public abstract void nextPlayer();
    public abstract Phase getInitialPhase();
    public abstract Bohnenfeld makePlayerBohnenfeld();
    public abstract int getInitialeKartenAnzahl();
    public abstract Phase getNextPhase(Phase aktPhase, GameContext game);



}
///