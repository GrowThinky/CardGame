package AlCabohne;

import BohnGame.Decktype;
import BohnGame.Karte;
import io.bitbucket.plt.sdp.bohnanza.gui.CardType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class DeckAlCabohne extends Decktype {


    public static Map<CardType,Integer> deckAlCabohne = Map.of(
            CardType.BLAUE_BOHNE,20,
            CardType.KIDNEY_BOHNE, 19,
            CardType.FEUER_BOHNE,18,
            CardType.PUFF_BOHNE,16,
            CardType.SAU_BOHNE,16,
            CardType.BRECH_BOHNE,14,
            CardType.STANGEN_BOHNE,13
    );

    public DeckAlCabohne(){
        this.deckFüllen();
    }

    @Override
    public void deckFüllen() {
        for(CardType bohnenTyp : deckAlCabohne.keySet()){
            anzahlProBohne = deckAlCabohne.get(bohnenTyp);
            for(int i = 0; i < anzahlProBohne;i++) {
                karten.add(new Karte(bohnenTyp,dummy));
                kartenImDeck++;
            }
        }
        Collections.shuffle(karten);

    }
}
