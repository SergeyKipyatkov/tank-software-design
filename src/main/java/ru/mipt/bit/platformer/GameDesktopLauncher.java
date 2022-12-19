package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.Builder.IObstacleLevelBuilder;
import ru.mipt.bit.platformer.Builder.ObstacleLevelBuilder;
import ru.mipt.bit.platformer.Controllers.IControl;
import ru.mipt.bit.platformer.Controllers.TankControl;
import ru.mipt.bit.platformer.Entities.*;
import ru.mipt.bit.platformer.Obstacles.IObstacleInLevel;
import ru.mipt.bit.platformer.Render.LevelRender;
import ru.mipt.bit.platformer.Render.Visualisation;
import ru.mipt.bit.platformer.util.Conversion;
import ru.mipt.bit.platformer.util.Direction;

import java.util.Random;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class GameDesktopLauncher implements ApplicationListener {

    private static final float MOVEMENT_SPEED = 0.4f;
    private Batch batch;
    private final LevelRender levelRender = LevelRender.getInstance();
    private TankEntity tank;
    private IControl controller;
    private IObstacleInLevel obstacleInLevel;
    @Override
    public void create() {
        batch = new SpriteBatch();

        levelRender.setLevelMap("level.tmx");
        levelRender.setLayerRenderer(batch);
        levelRender.setLayerMovement("Ground", Interpolation.smooth);

        IObstacleLevelBuilder obstacleBuilder = new ObstacleLevelBuilder();
        obstacleBuilder.addBush(new GridPoint2(3, 1));
        obstacleBuilder.addBush(new GridPoint2(5, 4));
        obstacleBuilder.addBush(new GridPoint2(2, 6));
        obstacleInLevel = obstacleBuilder.createBush();

        levelRender.setObstacle(obstacleInLevel);

        Visualisation tankTexture = new Visualisation("images/tank_blue.png");
        Conversion tankTransform = new Conversion(new GridPoint2(1, 1), 0f);
        tank = new TankEntity(tankTransform, tankTexture);
        controller = new TankControl();
    }

    @Override
    public void render() {
        float timePeriod = Gdx.graphics.getDeltaTime();

        clearTheScreen();

        Direction direction = controller.getDirection(Gdx.input);

        if(direction != null) {
            calculatePosition(tank, direction);
        }

        levelRender.mapMovements.get("Ground").moveRectangleBetweenTileCenters(tank.texture.getRectangle(), tank);

        interpolatedCoordinates(timePeriod);

        levelRender.render();

        batch.begin();

        draw(tank.texture, tank.conversion);

        for (GameObjectEntity entity : obstacleInLevel.getObstacleEntities()) draw(entity.texture, entity.conversion);

        batch.end();
    }

    private void calculatePosition(IGameMovingObject movingObject, Direction direction) {
        if (!isEqual(movingObject.getMovementProgress(), 1f)) return;

        Conversion newConversion = direction.moveInCurrentDirection(movingObject.getConversion());
        boolean isObstaclePosition = obstacleInLevel.getObstaclePositions().contains(newConversion.getPosition());

        if (!isObstaclePosition) {
            movingObject.setDestinationConversion(newConversion);
        }
    }

    private void draw(Visualisation graphic, Conversion transform) {
        drawTextureRegionUnscaled(batch, graphic.getTextureRegion(), graphic.getRectangle(), transform.getRotation());
    }

    private static void clearTheScreen() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    private IObstacleInLevel createNObstacles (int obstaclesAmount){
        IObstacleLevelBuilder obstacleBuilder = new ObstacleLevelBuilder();
        for (int i = 0; i < 5; i++){
            obstacleBuilder.addBush(new GridPoint2(new Random().nextInt(9) + 1, new Random().nextInt(5) + 1));
        }

        return obstacleBuilder.createBush();
    }

    private void interpolatedCoordinates(float timePeriod) {
        float newProgress = continueProgress(tank.getMovementProgress(), timePeriod, MOVEMENT_SPEED);
        tank.setMovementProgress(newProgress);
        if (isEqual(tank.getMovementProgress(), 1f)) {
            tank.setDestinationPositionAsPosition();
        }
    }

    @Override
    public void resize(int width, int height) {
        // do not react to window resizing
    }

    @Override
    public void pause() {
        // game doesn't get paused
    }

    @Override
    public void resume() {
        // game doesn't get paused
    }

    @Override
    public void dispose() {
        for (GameObjectEntity entity : obstacleInLevel.getObstacleEntities()) {
            entity.texture.getTexture().dispose();
        }
        tank.texture.getTexture().dispose();
        levelRender.tiledMap.dispose();
        batch.dispose();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}