package com.anish.calabashbros;

import java.awt.Color;

import asciiPanel.AsciiPanel;

public class Monster extends Creature {

    public Monster(Color color, int glyph, World world) {
        super(AsciiPanel.blue, (char) glyph, world);
    }

}
