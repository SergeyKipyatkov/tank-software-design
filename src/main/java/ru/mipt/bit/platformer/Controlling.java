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

        movementProgress = continueProgress(movementProgress, deltaTime, MOVEMENT_SPEED);
        tileMovement.moveRectangleBetweenTileCenters(this.tank.graphic.getRectangle(), this.tank.getLocation().getPosition(), this.tank.destinationPosition, this.movementProgress);
        if (isEqual(this.movementProgress, 1f)){
            tank.location.position.set(tank.destinationPosition);
        }

        if (isEqual(movementProgress, 1f)) {
            if (input.isKeyPressed(UP) || input.isKeyPressed(W)) {
                this.movementProgress = this.tank.Move(Direction.UP);
            }
            if (input.isKeyPressed(LEFT) || input.isKeyPressed(A)) {
                this.movementProgress = this.tank.Move(Direction.LEFT);
            }
            if (input.isKeyPressed(DOWN) || input.isKeyPressed(S)) {
                this.movementProgress = this.tank.Move(Direction.DOWN);
            }
            if (input.isKeyPressed(RIGHT) || input.isKeyPressed(D)) {
                this.movementProgress = this.tank.Move(Direction.RIGHT);
            }
        }


    }

}
