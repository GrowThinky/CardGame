public interface Phase {

    public boolean anbauen(Karte karte, int feldNr, Player player, int handPos);
    public Handel handeln(Player aktiverSpieler, Player handelsPartner);
    public boolean ziehen(Player player, Deck deck, int anzahl);
    public Phase nextPhase(Player player);
    public boolean ernten(Player player, int feldNr);
}
