package ru.mipt.bit.platformer;

public class Tree extends GameObject {
    private static final String texturePath = "images/greenTree.png";

    public Tree(int x, int y) {
        location = new Location(x, y);
        obstaclePosition.add(location.getPosition());
        graphic = new ObjectGraphic(texturePath);
    }
}