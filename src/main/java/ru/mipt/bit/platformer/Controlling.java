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

    private static final float MOVEMENT_SPEED = 0.4f;
    public float movementProgress;
    public Tank tank;

    public Controlling(Tank tank) {
        this.tank = tank;
        this.movementProgress = 1f;
    }

    public void Control(Input input, TileMovement tileMovement) {
        float deltaTime = Gdx.graphics.getDeltaTime();

        this.movementProgress = continueProgress(this.movementProgress, deltaTime, MOVEMENT_SPEED);
        Rectangle rectangle = tank.graphic.getRectangle();
        tileMovement.moveRectangleBetweenTileCenters(rectangle, tank.getLocation().getPosition(), tank.destinationPosition, this.movementProgress);
        
        if (isEqual(this.movementProgress, 1f)){
            tank.location.position.set(tank.destinationPosition);
        }

        if(!isEqual(movementProgress, 1f))
            return;

        if (input.isKeyPressed(UP) || input.isKeyPressed(W)) {
            this.tank.Move(Direction.UP);
            this.movementProgress = 0f;
        }
        if (input.isKeyPressed(LEFT) || input.isKeyPressed(A)) {
            this.tank.Move(Direction.LEFT);
            this.movementProgress = 0f;
        }
        if (input.isKeyPressed(DOWN) || input.isKeyPressed(S)) {
            this.tank.Move(Direction.DOWN);
            this.movementProgress = 0f;
        }
        if (input.isKeyPressed(RIGHT) || input.isKeyPressed(D)) {
            this.tank.Move(Direction.RIGHT);
            this.movementProgress = 0f;
        }
    }
}