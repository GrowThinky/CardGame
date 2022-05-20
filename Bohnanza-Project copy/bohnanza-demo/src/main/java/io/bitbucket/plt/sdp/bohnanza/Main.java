package io.bitbucket.plt.sdp.bohnanza;

import io.bitbucket.plt.sdp.bohnanza.gui.Color;
import io.bitbucket.plt.sdp.bohnanza.gui.GUI;
import io.bitbucket.plt.sdp.bohnanza.gui.Size;

public class Main {

    public static void main(String[] args) {
        GUI gui = new GUI(new Size(1080, 640), new Size(60, 90), new Size(240, 360), new Color(128,151,199), new Color(0,0,0));




        new Thread(new Game(gui, args)).start();
        
        gui.start();
    }
}
