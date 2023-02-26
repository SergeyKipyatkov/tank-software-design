package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

import static com.badlogic.gdx.math.MathUtils.random;

public class Location {

    public GridPoint2 position;
    private float rotation;

    public Location(int x, int y, float rotation) {
        this.rotation = rotation;
        position = new GridPoint2(x, y);
    }

    public Location(int x, int y) {
        rotation = 0f;
        position = new GridPoint2(x, y);
    }

    public Location() {
        rotation = 0f;
        position = new GridPoint2(random(9), random(7));
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

    public GridPoint2 calculatePosition(Direction direction) {
        return new GridPoint2(position.x + direction.getLocation().position.x,
                position.y + direction.getLocation().position.y);
    }
}
