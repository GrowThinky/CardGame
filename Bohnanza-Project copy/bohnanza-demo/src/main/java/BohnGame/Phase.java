package BohnGame;

public interface Phase {

    public boolean anbauen(Karte karte, int feldNr, Player player);
    public Handel handeln(Player aktiverSpieler, Player handelsPartner);
    public boolean ziehen(Player player, Decktype decktype, int anzahl);
    public Phase nextPhase(Player player);
    public boolean ernten(Player player, int feldNr);
}
