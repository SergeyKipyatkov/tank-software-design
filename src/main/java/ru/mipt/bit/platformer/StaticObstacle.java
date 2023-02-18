package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

public class StaticObstacle extends GameObject{
    public Location location;
    public ObjectGraphic graphic;

    public StaticObstacle(GridPoint2 position, String texturePath){
        this.location = new Location(position);
        this.graphic = new ObjectGraphic(texturePath);
    }
}
