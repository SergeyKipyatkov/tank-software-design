package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayList;

public class Location {
    public static final ArrayList<GridPoint2> entitiesPosition = new ArrayList<>();

    public GridPoint2 position;
    private float rotation;


    public Location(GridPoint2 position, float rotation){
        this.rotation = rotation;
        this.position = position;
    }
    public Location(GridPoint2 position){
        this.rotation = 0f;
        this.position = position;
        entitiesPosition.add(this.position);
    }

    public static boolean IsPositionFree(GridPoint2 position){
        return !entitiesPosition.contains(position);
    }

    public GridPoint2 getPosition() {
        return position;
    }

    public float getRotation() {
        return rotation;
    }

    public GridPoint2 calculatePosition(Direction direction){
        return new GridPoint2(this.position.x + direction.getLocation().position.x,
                this.position.y + direction.getLocation().position.y);
    }

    public void updateLocation(Direction direction){
        this.position = calculatePosition(direction);
        this.rotation = direction.getLocation().getRotation();
    }
}