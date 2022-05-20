package BohnGame;

import java.util.*;
import io.bitbucket.plt.sdp.bohnanza.gui.CardType;

public class DeckBaseGame extends Decktype {


    public static Map<CardType, Integer> deckBase = Map.of(
            CardType.FEUER_BOHNE, 18,
            CardType.BLAUE_BOHNE, 20,
            CardType.SAU_BOHNE, 16,
            CardType.BRECH_BOHNE, 14,
            CardType.SOJA_BOHNE, 12,
            CardType.AUGEN_BOHNE, 10,
            CardType.ROTE_BOHNE, 8,
            CardType.GARTEN_BOHNE, 6
    );


    public DeckBaseGame() {
       this.deckFüllen();
    }

    @Override
    public void deckFüllen() {
        for (CardType bohnenTyp : deckBase.keySet()) {
            anzahlProBohne = deckBase.get(bohnenTyp);
            for (int i = 0; i < anzahlProBohne; i++) {
                karten.add(new Karte(bohnenTyp, dummy));
                kartenImDeck++;
            }
        }
        Collections.shuffle(karten);

    }

}


