package BohnGame;

import java.util.ArrayList;

public class Handel {

    private boolean aktPlOK=false;
    private boolean partnerOK=false;

    private Player handelPlayer1;
    private Player handelPlayer2;

    public ArrayList<Karte> aktPlAngebot;
    public ArrayList<Karte> partnerAngebot;

    public Handel(Player handelPlayer1, Player handelPlayer2){
        this.aktPlAngebot = new ArrayList<>();
        this.partnerAngebot = new ArrayList<>();
        this.handelPlayer1 = handelPlayer1;
        this.handelPlayer1 = handelPlayer2;
    }

    public boolean checkOut(){
         if(aktPlOK && partnerOK){
             return true;
         }
         return false;
    }





}
