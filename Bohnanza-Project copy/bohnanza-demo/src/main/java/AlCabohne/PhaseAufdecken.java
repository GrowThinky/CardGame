package AlCabohne;

import BohnGame.*;

import java.util.ArrayList;

/**
 * Phase 4 und 5 der AlCabohne Erweiterung
 * führt die Schritte von "karten Aufdecken" aus
 * und gibt dem aktuellen Spieler die Rechte die in Phase 5
 * der Spielanleitung beschriben sind.
 */
public class PhaseAufdecken implements Phase {

    GameContextAlCab game;


    public PhaseAufdecken(GameContext game){
        this.game = (GameContextAlCab) game;
    }

    /**
     * Führt die automatischen Handlungen der "Karten aufdecken"-Phase der AlCabohne Erweiterung aus.
     */
    private void bohnenAufdecken(){
        Karte karte;
        while(!dreiKartenNebeneinander()) {
            karte = game.decktype.ziehen();
            if (!game.mafia.takesCard(karte)) {
                kartenVerteilen(game.aufgedeckteKarten, karte);
            }
        }
    }


    @Override
    public boolean anbauen(Karte karte, int feldNr, Player player) {
        for (ArrayList<Karte> teilStapel : game.aufgedeckteKarten) {
            for (Karte karteImStapel : teilStapel) {
                if (karteImStapel.equals(karte)){
                    player.bohnenfeld.getFeld(feldNr).anbauen(karte);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Handel handeln(Player aktiverSpieler, Player handelsPartner) {
        return null;
    }

    @Override
    public boolean ziehen(Player player, Decktype decktype, int anzahl) {
        return false;
    }

    @Override
    public Phase nextPhase(Player player) {
        return game.getNextPhase(this,game);
    }

    @Override
    public boolean ernten(Player player, int feldNr) {
        Feld feld = player.bohnenfeld.getFeld(feldNr);
        player.score += feld.ertrag();
        game.ablageStapel.addAll(feld.feldLeeren());
        return true;
    }

    /**
     * @param karten die aufgedeckten Karten der Phase 4
     * @param karte die aktuell aufgedeckte und zu verteilende Karte
     */
    public void kartenVerteilen(ArrayList<ArrayList<Karte>> karten, Karte karte){
        for(ArrayList<Karte> teilStapel : karten){
            if(teilStapel.isEmpty()){
                teilStapel.add(karte);
                return;
            }
            if(teilStapel.get(0).typ.equals(karte.typ)){
                teilStapel.add(karte);
                return;
            }
        }
    }

    public boolean dreiKartenNebeneinander(){
        for(ArrayList<Karte> teilStapel : game.aufgedeckteKarten){
            if(teilStapel.size()==3){
                return true;
            }
        }
        return false;
    }


}
