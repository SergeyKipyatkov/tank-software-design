package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

public class Tree extends GameObject{

    public Tree(GridPoint2 position){
        this.location = new Location(position);
        obstaclePosition.add(position);
        String texturePath = "images/greenTree.png";
        this.graphic = new ObjectGraphic(texturePath);
    }
}
