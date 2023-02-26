package ru.mipt.bit.platformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.util.TileMovement;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.Input.Keys.D;
import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class Controlling {

    public float movementProgress;
    public Tank tank;

    private static final float MOVEMENT_SPEED = 0.4f;

    public Controlling(Tank tank) {
        this.tank = tank;
        this.movementProgress = 1f;
    }

    public void Control(Input input, TileMovement tileMovement) {
        float deltaTime = Gdx.graphics.getDeltaTime();

        movementProgress = continueProgress(movementProgress, deltaTime, MOVEMENT_SPEED);
        tileMovement.moveRectangleBetweenTileCenters(tank, movementProgress);

        if (isEqual(movementProgress, 1f)) {
            tank.location.position.set(tank.destinationPosition);
        }

        if (!isEqual(movementProgress, 1f))
            return;

        isKeyPressed(input);
    }

    private void isKeyPressed(Input input) {
        if (input.isKeyPressed(UP) || input.isKeyPressed(W)) {
            tank.Move(Direction.UP);
            movementProgress = 0f;
        } else if (input.isKeyPressed(LEFT) || input.isKeyPressed(A)) {
            tank.Move(Direction.LEFT);
            movementProgress = 0f;
        } else if (input.isKeyPressed(DOWN) || input.isKeyPressed(S)) {
            tank.Move(Direction.DOWN);
            movementProgress = 0f;
        } else if (input.isKeyPressed(RIGHT) || input.isKeyPressed(D)) {
            tank.Move(Direction.RIGHT);
            movementProgress = 0f;
        }
    }
}