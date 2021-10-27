package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.anish.calabashbros.BubbleSorter;
import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.World;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;
    private Calabash[][] bros;
    private Calabash[] Bros;
    String[] sortSteps;

    public WorldScreen() {
        world = new World();

        bros = new Calabash[4][4];

        bros[3][0] = new Calabash(new Color(250, 250, 0), 1, world);
        bros[3][1] = new Calabash(new Color(235, 235, 0), 2, world);
        bros[3][2] = new Calabash(new Color(220, 220, 0), 3, world);
        bros[3][3] = new Calabash(new Color(205, 205, 0), 4, world);
        bros[2][0] = new Calabash(new Color(190, 190, 0), 5, world);
        bros[2][1] = new Calabash(new Color(175, 175, 0), 6, world);
        bros[2][2] = new Calabash(new Color(160, 160, 0), 7, world);
        bros[2][3] = new Calabash(new Color(145, 145, 0), 8, world);
        bros[1][0] = new Calabash(new Color(130, 130, 0), 9, world);
        bros[1][1] = new Calabash(new Color(115, 115, 0), 10, world);
        bros[1][2] = new Calabash(new Color(100, 100, 0), 11, world);
        bros[1][3] = new Calabash(new Color(85, 85, 0), 12, world);
        bros[0][0] = new Calabash(new Color(70, 70, 0), 13, world);
        bros[0][1] = new Calabash(new Color(55, 55, 0), 14, world);
        bros[0][2] = new Calabash(new Color(40, 40, 0), 15, world);
        bros[0][3] = new Calabash(new Color(25, 25, 0), 16, world);

        world.put(bros[0][0], 10, 10);
        world.put(bros[0][1], 12, 10);
        world.put(bros[0][2], 14, 10);
        world.put(bros[0][3], 16, 10);
        world.put(bros[1][0], 10, 12);
        world.put(bros[1][1], 12, 12);
        world.put(bros[1][2], 14, 12);
        world.put(bros[1][3], 16, 12);
        world.put(bros[2][0], 10, 14);
        world.put(bros[2][1], 12, 14);
        world.put(bros[2][2], 14, 14);
        world.put(bros[2][3], 16, 14);
        world.put(bros[3][0], 10, 16);
        world.put(bros[3][1], 12, 16);
        world.put(bros[3][2], 14, 16);
        world.put(bros[3][3], 16, 16);

        
        int cap = bros.length * bros[0].length;
        Bros = new Calabash[cap];

        for (int i = 0; i < bros.length; i++) {
            for (int j = 0; j < bros[i].length; j++) {
                Bros[i*bros.length + j] = bros[i][j];
            }
        }

        BubbleSorter<Calabash> b = new BubbleSorter<>();
        b.load(Bros);
        b.sort();

        sortSteps = this.parsePlan(b.getPlan());
    }

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(Calabash[] bros, String step) {
        String[] couple = step.split("<->");
        getBroByRank(bros, Integer.parseInt(couple[0])).swap(getBroByRank(bros, Integer.parseInt(couple[1])));
    }

    private Calabash getBroByRank(Calabash[] bros, int rank) {
        for (Calabash bro : bros) {
            if (bro.getRank() == rank) {
                return bro;
            }
        }
        return null;
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {

        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }
    }

    int i = 0;

    @Override
    public Screen respondToUserInput(KeyEvent key) {

        if (i < this.sortSteps.length) {
            this.execute(Bros, sortSteps[i]);
            i++;
        }

        return this;
    }

}
