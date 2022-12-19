package ru.mipt.bit.platformer.util;

import com.badlogic.gdx.math.GridPoint2;

public class Conversion {
    private GridPoint2 position;
    private float rotation;

    public Conversion(GridPoint2 position, float rotation){
        this.position = position;
        this.rotation = rotation;
    }

    public void copyFromConversion(Conversion conversion){
        this.position = new GridPoint2(conversion.getPosition());
        this.rotation = conversion.getRotation();
    }

    public GridPoint2 getPosition() {
        return position;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
}
