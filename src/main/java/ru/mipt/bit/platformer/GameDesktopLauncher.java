package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.util.GdxGameUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static com.badlogic.gdx.math.MathUtils.random;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class GameDesktopLauncher implements ApplicationListener {
    private Field field;
    private Batch batch;

    private Tank player;
    private final ArrayList<Tree> trees = new ArrayList<>();
    private Controlling playerControl;
    private int treesCount;
    private String mapPathForParser;

    @Override
    public void create() {
        batch = new SpriteBatch();
        field = new Field(batch,"level.tmx");

        if (mapPathForParser == null) {
            player = new Tank();
            playerControl = new Controlling(player);
            GenerateTrees(5);
        } else {
            try {
                parser(mapPathForParser);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void GenerateTrees(int treesCount){
        this.treesCount = treesCount;
        int xRandom, yRandom;
        for(int i = 0; i < treesCount; i++){
            do {
                xRandom = random(10);
                yRandom = random(8);
            }while(GameObject.obstaclePosition.contains(new GridPoint2(xRandom,yRandom)));
            trees.add(new Tree(new GridPoint2(xRandom, yRandom)));
            moveRectangleAtTileCenter(field.getGroundLayer(), trees.get(i).getGraphic().getRectangle(), trees.get(i).location.getPosition());
        }
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
        for(int i = 0; i < treesCount; i++) {
            drawTextureRegionUnscaled(batch, trees.get(i));
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
        for(int i = 0; i < treesCount; i++){
            trees.get(i).getGraphic().getTexture().dispose();
        }
        player.getGraphic().getTexture().dispose();
        field.getLevel().dispose();
        batch.dispose();
    }

    private void cleanScreen(){
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    public void parser(String filePath) throws IOException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        ArrayList<String> chars= new ArrayList<>();
        int treeCounter = 0;
        while (scanner.hasNext()) {
            chars.add(scanner.nextLine());
        }
        for(int i = chars.size() - 1; i >= 0; i--) {
            for (int j = 0; j < chars.get(i).length(); j++) {
                if (chars.get(i).charAt(j) == 'X') {
                    player = new Tank(new GridPoint2(j, chars.size() - i - 1));
                    playerControl = new Controlling(player);
                }
                if (chars.get(i).charAt(j) == 'T') {
                    trees.add(new Tree(new GridPoint2(j, chars.size() - i - 1)));
                    moveRectangleAtTileCenter(field.getGroundLayer(), trees.get(treeCounter).getGraphic().getRectangle(), trees.get(treeCounter).location.getPosition());
                    treeCounter++;
                }
            }
        }
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}
