package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayList;

import static com.badlogic.gdx.math.MathUtils.random;

public class Location {

    public GridPoint2 position;
    private float rotation;


    public Location(GridPoint2 position, float rotation){
        this.rotation = rotation;
        this.position = position;
    }
    public Location(GridPoint2 position){
        this.rotation = 0f;
        this.position = position;
    }
    public Location(){
        this.rotation = 0f;
        this.position = new GridPoint2(random(10), random(8));
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

    public GridPoint2 calculatePosition(Direction direction){
        return new GridPoint2(this.position.x + direction.getLocation().position.x,
                this.position.y + direction.getLocation().position.y);
    }

    public void updateLocation(Direction direction){
        this.position = calculatePosition(direction);
        this.rotation = direction.getLocation().getRotation();
    }
}
