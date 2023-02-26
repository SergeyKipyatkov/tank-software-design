package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

public class Tank extends GameObject {
    private static final String texturePath = "images/tank_blue.png";
    public GridPoint2 destinationPosition;

    public Tank() {
        location = new Location();
        destinationPosition = new GridPoint2(location.getPosition());
        obstaclePosition.add(location.getPosition());
        graphic = new ObjectGraphic(texturePath);
    }

    public Tank(int x, int y) {
        obstaclePosition.add(new GridPoint2(x, y));
        destinationPosition = new GridPoint2(x, y);
        location = new Location(x, y, 0f);
        graphic = new ObjectGraphic(texturePath);
    }

    public void Move(Direction direction) {
        if (IsPositionFree(location.calculatePosition(direction))) {
            destinationPosition.add(direction.getPosition());
            location.setRotation(direction.getLocation().getRotation());
        }
    }
}

