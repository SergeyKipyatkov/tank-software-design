package ru.mipt.bit.platformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import ru.mipt.bit.platformer.util.TileMovement;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.Input.Keys.D;
import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class Controlling {

    private static final float MOVEMENT_SPEED = 0.4f;
    public float movementProgress;
    public Tank tank;

    public Controlling(Tank tank) {
        this.tank = tank;
        this.movementProgress = 1f;
    }

    public void Control(Input input, TileMovement tileMovement) {
        float deltaTime = Gdx.graphics.getDeltaTime();
        if (isEqual(movementProgress, 1f)) {
            if (input.isKeyPressed(UP) || input.isKeyPressed(W)) {
                this.tank.Move(Direction.UP, this.movementProgress);
            }
            if (input.isKeyPressed(LEFT) || input.isKeyPressed(A)) {
                this.tank.Move(Direction.LEFT, this.movementProgress);
            }
            if (input.isKeyPressed(DOWN) || input.isKeyPressed(S)) {
                this.tank.Move(Direction.DOWN, this.movementProgress);
            }
            if (input.isKeyPressed(RIGHT) || input.isKeyPressed(D)) {
                this.tank.Move(Direction.RIGHT, this.movementProgress);
            }
        }
        tileMovement.moveRectangleBetweenTileCenters(tank.graphic.getRectangle(), tank.getLocation().getPosition(), tank.destinationPosition, movementProgress);
        movementProgress = continueProgress(movementProgress, deltaTime, MOVEMENT_SPEED);
        if (isEqual(this.movementProgress, 1f)){
            tank.location.position.set(tank.destinationPosition);
        }
    }

}
