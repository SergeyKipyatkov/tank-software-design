package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

import static com.badlogic.gdx.math.MathUtils.random;

public class Tank extends GameObject {
    public GridPoint2 destinationPosition;

    public Tank(){
        this.location = new Location();
        this.destinationPosition = new GridPoint2(this.location.getPosition());
        obstaclePosition.add(this.location.getPosition());
        this.graphic = new ObjectGraphic("images/tank_blue.png");
    }

    public Tank(GridPoint2 position){
        obstaclePosition.add(position);
        this.destinationPosition = new GridPoint2(position);
        this.location = new Location(position, 0f);
        this.graphic = new ObjectGraphic("images/tank_blue.png");
    }

    public void Move(Direction direction){
        if (IsPositionFree(this.location.calculatePosition(direction))){
            this.destinationPosition.add(direction.getPosition());
            this.location.setRotation(direction.getLocation().getRotation());
        }
    }
}

