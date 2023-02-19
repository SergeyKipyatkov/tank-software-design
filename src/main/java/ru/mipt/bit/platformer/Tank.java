package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

import static com.badlogic.gdx.math.MathUtils.random;

public class Tank extends GameObject {
    private static final String texturePath = "images/tank_blue.png";
    public GridPoint2 destinationPosition;

    public Tank(){
        this.location = new Location();
        this.destinationPosition = new GridPoint2(this.location.getPosition());
        obstaclePosition.add(this.location.getPosition());
        this.graphic = new ObjectGraphic(texturePath);
    }

    public Tank(int x, int y){
        obstaclePosition.add(new GridPoint2(x, y));
        this.destinationPosition = new GridPoint2(x,y);
        this.location = new Location(x,y, 0f);
        this.graphic = new ObjectGraphic(texturePath);
    }

    public void Move(Direction direction){
        if (IsPositionFree(this.location.calculatePosition(direction))){
            this.destinationPosition.add(direction.getPosition());
            this.location.setRotation(direction.getLocation().getRotation());
        }
    }
}

