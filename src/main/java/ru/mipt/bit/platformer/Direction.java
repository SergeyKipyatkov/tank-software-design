package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;
enum Direction{
    UP(new Location(0, 1, 90f)),
    DOWN(new Location(0, -1, -90f)),
    LEFT(new Location(-1, 0, 180f)),
    RIGHT(new Location(1, 0, 0f));

    private final Location location;

    Direction(Location location){
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
    public GridPoint2 getPosition(){
        return this.location.position;
    }
}
