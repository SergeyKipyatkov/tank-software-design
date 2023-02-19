package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

import static com.badlogic.gdx.math.MathUtils.random;

public class Tree extends GameObject{
    private static final String texturePath = "images/greenTree.png";

    public Tree(int x, int y){
        this.location = new Location(x, y);
        obstaclePosition.add(location.getPosition());
        this.graphic = new ObjectGraphic(texturePath);
    }
}
