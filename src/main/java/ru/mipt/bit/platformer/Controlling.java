package ru.mipt.bit.platformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.Input.Keys.D;
import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class Controlling {

    private static final float MOVEMENT_SPEED = 0.4f;
    private float movementProgress;
    private final Tank tank;

    public Controlling(Tank tank) {
        this.tank = tank;
        this.movementProgress = 1f;
    }

    public void Control(Input input) {
        float deltaTime = Gdx.graphics.getDeltaTime();
        if (isEqual(movementProgress, 1f)) {
            if (input.isKeyPressed(UP) || input.isKeyPressed(W)) {
                movementProgress = this.tank.Move(Direction.UP);
            }
            if (input.isKeyPressed(LEFT) || input.isKeyPressed(A)) {
                movementProgress = this.tank.Move(Direction.LEFT);
            }
            if (input.isKeyPressed(DOWN) || input.isKeyPressed(S)) {
                movementProgress = this.tank.Move(Direction.DOWN);
            }
            if (input.isKeyPressed(RIGHT) || input.isKeyPressed(D)) {
                movementProgress = this.tank.Move(Direction.RIGHT);
            }
        }
        movementProgress = continueProgress(movementProgress, deltaTime, MOVEMENT_SPEED);
        if (isEqual(this.movementProgress, 1f)){
            tank.location.position.set(tank.destinationPosition);
        }
    }

}
