package com.anish.calabashbros;

import java.awt.Color;

public class Creature extends Thing {

    static int H[] = { 1, 0, -1, 0 }, V[] = { 0, 1, 0, -1 };

    public int Maze[][];

    Creature(Color color, char glyph, World world) {
        super(color, glyph, world);
    }

    public void moveTo(int xPos, int yPos) {

        if( this.world.isFloor(this.getX() + xPos, this.getY() + yPos) )
        {
            int x = this.getX();
            int y = this.getY();
            this.world.put(this, x + xPos, y + yPos);
            this.world.put(new Floor(this.world), x, y);

        }
        else if(this.world.isWin(this.getX() + xPos, this.getY() + yPos))
        {
            int x = this.getX();
            int y = this.getY();
            this.world.put(this, x + xPos, y + yPos);
            this.world.put(new Floor(this.world), x, y);
            this.world.win();
        }
        
    }

}
  