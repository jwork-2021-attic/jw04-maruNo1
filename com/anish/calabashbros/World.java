package com.anish.calabashbros;

import asciiPanel.AsciiPanel;

public class World {

    public static final int WIDTH = 40;
    public static final int HEIGHT = 20;

    private Tile<Thing>[][] tiles;
    public int[][] maze;

    public int victory = 0;

    public World() {

        if (tiles == null) {
            tiles = new Tile[HEIGHT][HEIGHT];
        }

        MazeGenerator mazeGenerator = new MazeGenerator(HEIGHT);
        mazeGenerator.generateMaze();

        maze = mazeGenerator.maze;

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                tiles[i][j] = new Tile<>(i, j);
                if(i<maze.length && j<maze.length && maze[i][j] == 0)
                {
                    tiles[i][j].setThing(new Wall(this));
                }
                else
                {
                    tiles[i][j].setThing(new Floor(this));
                }
            }
        }
    }

    public Thing get(int x, int y) {
        return this.tiles[x][y].getThing();
    }

    public void put(Thing t, int x, int y) {
        this.tiles[x][y].setThing(t);
    }

    public boolean isFloor(int x,int y) {
        return (tiles[x][y].getThing().getColor() !=  AsciiPanel.cyan && tiles[x][y].getThing().getColor() !=  AsciiPanel.blue);
    }
    

    public Tile<Thing> tile(int x, int y) {
        return tiles[x][y];
    }


    public boolean isWin(int x,int y) {
        return (tiles[x][y].getThing().getColor() ==  AsciiPanel.blue);
    }

    public void win() {

            this.victory = 1;
        
    }

}
