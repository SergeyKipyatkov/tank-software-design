package ru.mipt.bit.platformer.util;

import com.badlogic.gdx.math.GridPoint2;

public class Direction {
    private final Conversion direction;

    public static Direction Up = new Direction(new GridPoint2(0, 1), 90f);
    public static Direction Down = new Direction(new GridPoint2(0, -1), -90f);
    public static Direction Left = new Direction(new GridPoint2(-1, 0), 180f);
    public static Direction Right = new Direction(new GridPoint2(1, 0), 0f);

    public Direction(GridPoint2 direction, float rotation) {
        this.direction = new Conversion(direction, rotation);
    }

    public Conversion moveInCurrentDirection(Conversion startTransform){
        GridPoint2 newPosition = new GridPoint2(
                this.direction.getPosition().x + startTransform.getPosition().x,
                this.direction.getPosition().y + startTransform.getPosition().y
        );

        return new Conversion(newPosition, this.direction.getRotation());
    }
}
