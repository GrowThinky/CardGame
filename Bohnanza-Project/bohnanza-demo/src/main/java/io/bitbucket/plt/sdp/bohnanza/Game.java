package io.bitbucket.plt.sdp.bohnanza;

import AlCabohne.DeckAlCabohne;
import AlCabohne.GameContextAlCab;
import BohnGame.*;
import io.bitbucket.plt.sdp.bohnanza.gui.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Provides the game-mode selection screen and dedicated game set-up Methods for each game-mode.
 */
public class Game implements Runnable {

    private final ArrayList<Compartment> handCompartments = new ArrayList<>();
    private final ArrayList<Compartment> felderCompartments = new ArrayList<>();
    private final ArrayList<Compartment> tableCompartments = new ArrayList<>();
    private final ArrayList<Label> scores = new ArrayList<>();
    private final HashMap<CardObject,Karte> objectKarteHashMap = new HashMap<>();

    final int player_space_offset = 220;
    int left_margin = 20;
    int top_margin = 1;
    int card_X_offset = 30;
    int hand_left_margin = 15;

    int x = left_margin;
    int y = top_margin;

	private final GUI gui;
    @SuppressWarnings("unused")
    private final String[] args;
    private GameContext gameContext;
    private Button selectBaseGame;
    private Button selectExtensionSinglePlayer;
    private Button selectExtensionDuo;
    Compartment welcomeImage;
    private Label welcome;
    private String welcomeMessage = "Please select the Base Game to see a basic demonstration.";

    public Game(GUI gui, String[] args) {
        super();
        this.gui = gui;
        this.args = args;
    }


    /**
     * Initializes the game-mode selection screen
     */
    @Override
    public void run() {
         welcome = gui.addLabel(new Coordinate(400, 560), welcomeMessage );
         welcomeImage = gui.addCompartment(new Coordinate(380,100),new Size(500,400),"","bohnanza_lessdorky");
         selectBaseGame = gui.addButton("Base Game", new Coordinate(8, 530), new Size(150, 25), button -> {

             this.gameContext = new GameContextBase(3);

             for (Player player : gameContext.playerList) {
                 player.kartenZiehen(5);
             }
             runBaseGame();

             removeMenuItems();
        });

        gui.addButton("exit", new Coordinate(950, 560), new Size(80, 25), button -> {
            gui.stop();
        });

         selectExtensionSinglePlayer = gui.addButton("Al Cabohne - Single", new Coordinate(10, 560), new Size(150, 25), button -> {
            //create specific gameContext
             this.gameContext = new GameContextAlCab(1);

             for (Player player : gameContext.playerList){
                 player.kartenZiehen(6);
             }
            runExtensionSinglePlayer();

             removeMenuItems();
        });

        selectExtensionDuo = gui.addButton("Al Cabohne - Duo", new Coordinate(165, 560), new Size(150, 25), button -> {
            //create specific gameContext
            this.gameContext = new GameContextAlCab(2);

            for (Player player : gameContext.playerList){
                player.kartenZiehen(5);
                gameContext.makePlayerBohnenfeld();
            }
            runExtensionDuo();

            removeMenuItems();
        });

    }

    /**
     * Set-up method for vanilla Bohnanza without any extensions.
     */
    private void runBaseGame(){

        for (Player player : gameContext.playerList) {
            // add gui elements
            setupPlayerGuiElements(player, x ,y);
            // add player's initial set of cards to GUI
            for (Karte karte : player.hand) {
                CardObject newCard = gui.addCard(karte.typ, new Coordinate(x + hand_left_margin , y + card_X_offset)); //add cards to Compartments directly?
                objectKarteHashMap.put(newCard,karte);
                newCard.flip();
                x += card_X_offset;
            }
            x += player_space_offset;
        }


        // a label that will be used to show information on a dragged'n'dropped card
        final Label label = gui.addLabel(new Coordinate(10, 580), "<none>");


        // set the handler for drag'n'drop events. With this handler:
        // - whenever a d'n'd action finishes, the dropped card is flipped (toggle whether the front or back is shown)
        // - information on the dropped card is shown in the dedicated label
        // - the card is moved to the front, i.e., displayed top-most
        gui.setCardDnDHandler((CardObject card, Coordinate mouseCoordinate, Coordinate newCoordinate) -> {
            card.flip();
            label.updateLabel(card.toString());
            gui.moveToTop(card);
            return newCoordinate;
        });
    }


    /**
     * Set-up method for two player game for AlCabohne extension.
     */
    private void runExtensionDuo() {


        for (Player player : gameContext.playerList) {

            setupPlayerGuiElementsDuo(player, x ,y);

            for (Karte karte : player.hand) {
                CardObject newCard = gui.addCard(karte.typ, new Coordinate(x + hand_left_margin , y + card_X_offset));
                objectKarteHashMap.put(newCard,karte);
                newCard.flip();
                x += card_X_offset;
            }
            x += player_space_offset;
        }

        setupMafiaGuiDuo(800, 50);




        final Label label = gui.addLabel(new Coordinate(10, 880), "<none>");

        gui.setCardDnDHandler((CardObject card, Coordinate mouseCoordinate, Coordinate newCoordinate) -> {
            card.flip();
            label.updateLabel(card.toString());
            gui.moveToTop(card);
            return newCoordinate;
        });

    }

    /**
     * Set-up method for single player AlCabohne extension.
     */
    private void runExtensionSinglePlayer(){
        for (Player player : gameContext.playerList) {

            // add gui elements
            setupPlayerGuiElements(player, x ,y);
            setupMafiaGuiSingle(400, 200);

            // add player's initial set of cards to GUI
            for (Karte karte : player.hand) {
                CardObject newCard = gui.addCard(karte.typ, new Coordinate(x + hand_left_margin , y + card_X_offset));
                objectKarteHashMap.put(newCard,karte);
                newCard.flip();
                x += card_X_offset;
            }
            x += player_space_offset;
        }

        // a button to explicitly terminate the demo. This closes the window.
        gui.addButton("exit", new Coordinate(950, 10), new Size(80, 25), button -> {
            gui.stop();
        });

        final Label label = gui.addLabel(new Coordinate(10, 880), "<none>");

        gui.setCardDnDHandler((CardObject card, Coordinate mouseCoordinate, Coordinate newCoordinate) -> {
            card.flip();
            label.updateLabel(card.toString());
            gui.moveToTop(card);
            return newCoordinate;
        });

    }


    final int numPlayers = 3;
	final int Y = 800;
    int felderCompartment_Y = 200;
	final int feld_width = 100;
	final int anzahl_felder = 3;
	final int pf_button_y_offset = 200;
	final int ernten_button_y_offset = 205;
	final int table_compartment_X_offset = 210;
	final int button_y_spacing = 25;
	final int WIDTH = 250;
	final int HEIGHT = 300;
	final int V_SPACING = 5;
    final Size BUTTON_SIZE = new Size(100, 25);
    final Size FELDER_SIZE = new Size(100, 200);




    private void setupPflanzenButtons(int x, int y, int feldIndex, Compartment finalFeldCompartment, Player player) {
        gui.addButton("pflanzen", new Coordinate(x, y), BUTTON_SIZE, button -> {
            CardObject card= gui.getCardObjectsInCompartment(tableCompartments.get(player.id))[0];
            if(player.anbauen(objectKarteHashMap.get(card),feldIndex+1)){
                moveToField(finalFeldCompartment, card);
            }
        });
    }
    private void setupErntenButtons(int x, int y, int feldIndex, Compartment finalFeldCompartment,  Player player) {
        gui.addButton("ernten", new Coordinate(x, y  + button_y_spacing), BUTTON_SIZE, button -> {
            finalFeldCompartment.distributeHorizontal(gui.getCardObjectsInCompartment(finalFeldCompartment));
            gameContext.aktPlayer.phase.ernten(gameContext.aktPlayer, feldIndex + 1);
            scores.get(player.id).updateLabel("Score:" + gameContext.aktPlayer.score);
            for(CardObject cardObject : gui.getCardObjectsInCompartment(finalFeldCompartment)){
                gui.removeCard(cardObject);
            }
        });
    }




    private int YOffset(Player p, int feldnr){
        return 5 * p.bohnenfeld.getFeld(feldnr).getSize();
    }

    private void setupPlayerGuiElementsDuo(Player player, int x, int y){

        String name;
        Compartment feldCompartment;
        handCompartments.add(gui.addCompartment(new Coordinate(x, y), new Size(210, 140), "Handkarten_" + player.name));
        tableCompartments.add(gui.addCompartment(new Coordinate(x + table_compartment_X_offset, y), new Size (90,140),""));

        for(int i=0; i< 2;i++) {
            if(i==0){ name = "Feld_" + player.name; } else { name = ""; }
            felderCompartments.add(gui.addCompartment(new Coordinate(x + i * feld_width, felderCompartment_Y),FELDER_SIZE , name , "BOHNENFELD_CUT_"+i));
            feldCompartment = felderCompartments.get(felderCompartments.size() - 1);
            final int feldIndex = i;
            Compartment finalFeldCompartment = feldCompartment;
            setupPflanzenButtons(x + (i) * feld_width, felderCompartment_Y + pf_button_y_offset,feldIndex, finalFeldCompartment, player);
            setupErntenButtons(x + i * feld_width, felderCompartment_Y + ernten_button_y_offset,feldIndex, finalFeldCompartment, player);
        }
        gui.addButton("next phase",new Coordinate(10, 500), BUTTON_SIZE, button -> {
            gameContext.aktPlayer.nextPhase();
        });

    }

    private void setupPlayerGuiElements(Player player, int x, int y){

        String name;
        Compartment feldCompartment;
        handCompartments.add(gui.addCompartment(new Coordinate(x, y), new Size(210, 140), "Handkarten_" + player.name));
        tableCompartments.add(gui.addCompartment(new Coordinate(x + table_compartment_X_offset, y), new Size (90,140),""));

        scores.add(gui.addLabel((new Coordinate(x + 40, y + 120)), "Score: " + player.score));
        for(int i=0; i<anzahl_felder;i++) {
            if(i==0){ name = "Feld_" + player.name; } else { name = ""; }
            felderCompartments.add(gui.addCompartment(new Coordinate(x + i * feld_width, felderCompartment_Y),FELDER_SIZE , name , "BOHNENFELD_CUT_"+i));
            feldCompartment = felderCompartments.get(felderCompartments.size() - 1);
            final int feldIndex = i;
            Compartment finalFeldCompartment = feldCompartment;
            setupPflanzenButtons(x + (i) * feld_width, felderCompartment_Y + pf_button_y_offset,feldIndex, finalFeldCompartment, player);
            setupErntenButtons(x + i * feld_width, felderCompartment_Y + ernten_button_y_offset, feldIndex,finalFeldCompartment, player);
        }
        gui.addButton("next phase",new Coordinate(10, 500), BUTTON_SIZE, button -> {
            if(gameContext != null){
                gameContext.aktPlayer.nextPhase();
            } else {
                gameContext.aktPlayer.nextPhase();
            }
        });

        //Ruft ziehen Methode der entsprechenden Phase auf. Offene Karten werden gerendert, Karten die der Hand zugefügt werden, werden momentan noch nicht gerendert.
        gui.addButton("ziehen",new Coordinate(100, 500), BUTTON_SIZE, button -> {
            gameContext.aktPlayer.phase.ziehen(gameContext.aktPlayer, gameContext.decktype, 3);

            for (Karte karte : gameContext.aktPlayer.offeneKarten) {
                CardObject newCard = gui.addCard(karte.typ, new Coordinate(x + 200 , y + card_X_offset)); //add cards to Compartments directly?
                objectKarteHashMap.put(newCard,karte);
                newCard.flip();
            }

        });

    }
    private void setupMafiaGuiSingle(int x, int y){

        felderCompartments.add(gui.addCompartment(new Coordinate(x,y), new Size(150, 225), "Al Cabohne", "AL_CABOHNE"));
        felderCompartments.add(gui.addCompartment(new Coordinate(x + 180,y), new Size(150, 225), "Joe Bohnano", "JOE_BOHNANO"));
        felderCompartments.add(gui.addCompartment(new Coordinate(x + 360,y), new Size(150, 225), "Don Corlebohne", "DON_CORLEBOHNE"));
        tableCompartments.add(gui.addCompartment(new Coordinate(x + 180 , 450), new Size(200, 100), "Mafia Coins"));

    }

    private void setupMafiaGuiDuo(int x, int y){
        felderCompartments.add(gui.addCompartment(new Coordinate(x,y), new Size(150, 225), "Al Cabohne", "AL_CABOHNE"));
        felderCompartments.add(gui.addCompartment(new Coordinate(x,y + 250), new Size(150, 225), "Don Corlebohne", "DON_CORLEBOHNE"));
        tableCompartments.add(gui.addCompartment(new Coordinate(x, 550), new Size(200, 100), "Mafia Coins"));


    }

    private void moveToField(Compartment finalFeldCompartment, CardObject card) {
        gui.removeCard(card);
        card = gui.addCard(card.getCardType(),(new Coordinate(finalFeldCompartment.upperLeft.x+ card_X_offset, finalFeldCompartment.upperLeft.y )));
        finalFeldCompartment.distributeVertical(gui.getCardObjectsInCompartment(finalFeldCompartment));
        if(!card.isFrontShown()){
            card.flip();}
    }

    private void removeMenuItems(){
        gui.removeButton(selectBaseGame);
        gui.removeButton(selectExtensionSinglePlayer);
        gui.removeButton(selectExtensionDuo);
        gui.removeLabel(welcome);
        gui.removeCompartment(welcomeImage);
    }

}
