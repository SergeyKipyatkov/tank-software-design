package ru.mipt.bit.platformer.Controllers;

import com.badlogic.gdx.Input;
import ru.mipt.bit.platformer.util.Direction;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.Input.Keys.D;
import static com.badlogic.gdx.math.MathUtils.isEqual;

/*  */
public class TankControl implements IControl {
    @Override
    public Direction getDirection(Input input) {
        Direction finalDirection = null;

        if (input.isKeyPressed(UP) || input.isKeyPressed(W)) {
            finalDirection = Direction.Up;
        }
        if (input.isKeyPressed(LEFT) || input.isKeyPressed(A)) {
            finalDirection = Direction.Left;
        }
        if (input.isKeyPressed(DOWN) || input.isKeyPressed(S)) {
            finalDirection = Direction.Down;
        }
        if (input.isKeyPressed(RIGHT) || input.isKeyPressed(D)) {
            finalDirection = Direction.Right;
        }

        return finalDirection;
    }
}
