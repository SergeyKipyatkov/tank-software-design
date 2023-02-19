package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class GameDesktopLauncher implements ApplicationListener {
    private Field field;
    private Batch batch;

    private Tank player;
    private Tree[] trees;
    private final int treesCount = 5;
    private Controlling playerControl;


    @Override
    public void create() {
        batch = new SpriteBatch();
        field = new Field(batch,"level.tmx");

        player = new Tank(new GridPoint2(1,1), 0f, "images/tank_blue.png");
        trees = new Tree[treesCount];
        for (int i = 0; i<treesCount; i++){
            trees[i] = new Tree();
        }
        playerControl = new Controlling(player);

    }

    @Override
    public void render() {
        cleanScreen();

        field.getLevelRenderer().render();

        playerControl.Control(Gdx.input, field.getTileMovement());

        // start recording all drawing commands
        batch.begin();

        // render player
        drawTextureRegionUnscaled(batch, player);

        // render tree obstacle
        for(int i = 0; i<treesCount; i++) {
            drawTextureRegionUnscaled(batch, trees[i]);
        }
        // submit all drawing requests
        batch.end();
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
        // dispose of all the native resources (classes which implement com.badlogic.gdx.utils.Disposable)
        for(int i = 0; i<treesCount; i++) {
            trees[i].getGraphic().getTexture().dispose();
        }
        player.getGraphic().getTexture().dispose();
        field.getLevel().dispose();
        batch.dispose();
    }

    private void cleanScreen(){
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}
