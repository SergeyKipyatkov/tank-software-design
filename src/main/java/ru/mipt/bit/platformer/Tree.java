package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

import static com.badlogic.gdx.math.MathUtils.random;

public class Tree extends GameObject{
    private final String texturePath = "images/greenTree.png";

    public Tree(GridPoint2 position){
        this.location = new Location(position);
        obstaclePosition.add(position);
        this.graphic = new ObjectGraphic(texturePath);
    }
}
