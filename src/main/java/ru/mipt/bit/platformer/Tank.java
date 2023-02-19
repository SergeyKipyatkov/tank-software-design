package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

public class Tank extends GameObject {
    public GridPoint2 destinationPosition;

    public Tank(GridPoint2 position, float rotation, String texturePath){
        obstaclePosition.add(position);
        this.destinationPosition = new GridPoint2(position);
        this.location = new Location(position, rotation);
        this.graphic = new ObjectGraphic(texturePath);
    }

    public void Move(Direction direction){
        if (IsPositionFree(this.location.calculatePosition(direction))){
            this.destinationPosition.add(direction.getPosition());
            this.location.setRotation(direction.getLocation().getRotation());
        }
    }
}

