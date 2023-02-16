package ru.mipt.bit.platformer.GameObjects.MovingObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.GameObjects.GameObject;

public class Tank extends GameObject {
    float rotation;
    public Tank(GridPoint2 coordinates, Texture texture, float rotation){
        super(coordinates, texture);
        this.rotation = rotation;
    }
}
