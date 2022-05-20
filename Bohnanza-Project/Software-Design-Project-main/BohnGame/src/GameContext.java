import java.util.ArrayList;
import java.util.Iterator;

public class GameContext  {

    public Player p1;
    public Player p2;
    public Player p3;
    public ArrayList<Player> playerList = new ArrayList<>();
    public Iterator<Player> playerIterator;
    public Deck deck;
    public Phase phase;
    public ArrayList<Karte> ablageStapel;
    public int deckEmptyCounter = 0;

    public Player aktPlayer;
    public Handel handel;


    public GameContext(Deck deck){
        ablageStapel = new ArrayList<>();
        this.deck = deck;
    }

    public void addPlayer(Player player){
        if(playerList.isEmpty()){
            aktPlayer = player;
        }
        this.playerList.add(player);
        this.playerIterator = playerList.iterator();
    }


    public void handeln(Player handelPartner){
        Handel neuerHandel = aktPlayer.phase.handeln(aktPlayer,handelPartner);
        if(neuerHandel != null){
            handel = neuerHandel;
        }
    }

    public void ziehen(Player player){
        if(player.id == aktPlayer.id){
            phase.ziehen(player, deck, 0);
            }
        }

    }






