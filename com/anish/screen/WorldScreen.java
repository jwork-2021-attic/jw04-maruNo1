package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.Monster;
import com.anish.calabashbros.World;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;
    private Calabash bros;
    private Monster mons;
    private String messages="";

    public WorldScreen(){


        world = new World();

        bros = new Calabash(new Color(200, 0, 0), 2, world);  
        
        mons = new Monster(AsciiPanel.blue, 3, world); 

        world.put(bros, 0, 0);

        world.put(mons, World.HEIGHT - 1, World.HEIGHT - 1 );
        
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {
        // displayTiles
        for (int x = 0; x < World.HEIGHT; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        } 
        displayMessages(terminal, this.messages);              
    }

    private void displayMessages(AsciiPanel terminal, String messages) {
       
        int i = messages.length()/18;

        for(int j=0;j<i;j++)
        {
            terminal.write(messages.substring(0+18*j, 18*(j+1)), 21, j);
        }
        terminal.write(messages.substring(0+18*i, messages.length()), 21, i);
        
    }

    private void addMessage(String s) {
        this.messages += s;
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {

        if(this.world.victory == 0){
            switch (key.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    bros.moveTo(-1, 0);
                    this.addMessage("L");
                    break;
                case KeyEvent.VK_RIGHT:
                    bros.moveTo(1, 0);
                    this.addMessage("R");
                    break;
                case KeyEvent.VK_UP:
                    bros.moveTo(0, -1);
                    this.addMessage("U");
                    break;
                case KeyEvent.VK_DOWN:
                    bros.moveTo(0, 1);
                    this.addMessage("D");
                    break;
                case KeyEvent.VK_G:
                    return new WinScreen();
            }
        }
        else if(this.world.victory == 1){
                    return new WinScreen();
            }
        
        return this;
    }

}
