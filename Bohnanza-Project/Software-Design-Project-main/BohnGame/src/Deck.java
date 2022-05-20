import java.util.*;

public class Deck implements Stapel{

    ArrayList<Karte> karten = new ArrayList<>();
    public int anzahlProBohne;
    public int kartenImDeck;
    public ArrayList<Integer> dummy = new ArrayList<>(Arrays.asList(0,1,1,1,2,3));

    Map<Bohne,Integer> deck = Map.of(
            Bohne.FEUERBOHNE,18,
            Bohne.BLAUE_BOHNE, 20,
            Bohne.SAUBOHNE,16,
            Bohne.BRECHBOHNE,14,
            Bohne.SOJABOHNE,12,
            Bohne.AUGENBOHNE,10,
            Bohne.ROTE_BOHNE,8,
            Bohne.GARTENBOHNE,6
    );

    public Deck(){
        for(Bohne bohnenTyp : deck.keySet()){
            anzahlProBohne = deck.get(bohnenTyp);
            for(int i = 0; i < anzahlProBohne;i++) {
                karten.add(new Karte(bohnenTyp,dummy));
                kartenImDeck++;
            }
        }
        Collections.shuffle(karten);
    }

    public Karte ziehen(){
        if(!karten.isEmpty()) {
            Karte k = karten.get(0);
            karten.remove(0);
            kartenImDeck--;
            return k;
        }
        return null;
    }

}


