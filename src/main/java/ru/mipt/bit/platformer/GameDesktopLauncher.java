package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

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
    private int randomTreesCount;
    private String mapPathForParser;

    public void setMapPathForParser(String mapPathForParser) {
        this.mapPathForParser = mapPathForParser;
    }

    public void setRandomTreesCount(int randomTreesCount) {
        this.randomTreesCount = randomTreesCount;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        field = new Field(batch, "level.tmx");
        chooseCreationMode();
    }

    private void chooseCreationMode() {
        // setMapPathForParser("src/main/resources/level_structure.txt");
        if (mapPathForParser == null) {
            CreateRandomObjects();
        } else {
            try {
                parser(mapPathForParser);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void CreateRandomObjects() {
        player = new Tank();
        playerControl = new Controlling(player);
        GenerateTrees();
    }

    public void GenerateTrees() {
        setRandomTreesCount(25);
        int xRandom, yRandom;
        for (int i = 0; i < randomTreesCount; i++) {
            do {
                xRandom = random(9);
                yRandom = random(7);
            } while (GameObject.obstaclePosition.contains(new GridPoint2(xRandom, yRandom)));
            trees.add(new Tree(xRandom, yRandom));
            Rectangle treeRectangle = trees.get(i).getGraphic().getRectangle();
            moveRectangleAtTileCenter(field.getGroundLayer(), treeRectangle, trees.get(i).location.getPosition());
        }
    }

    @Override
    public void render() {
        cleanScreen();

        field.getLevelRenderer().render();

        playerControl.Control(Gdx.input, field.getTileMovement());

        batch.begin();

        drawTextureRegionUnscaled(batch, player);

        for (int i = 0; i < randomTreesCount; i++) {
            drawTextureRegionUnscaled(batch, trees.get(i));
        }

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
        for (int i = 0; i < randomTreesCount; i++) {
            trees.get(i).getGraphic().getTexture().dispose();
        }
        player.getGraphic().getTexture().dispose();
        field.getLevel().dispose();
        batch.dispose();
    }

    private void cleanScreen() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    public void parser(String filePath) throws IOException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        ArrayList<String> chars = new ArrayList<>();
        int treeCounter = 0;
        while (scanner.hasNext()) {
            chars.add(scanner.nextLine());
        }
        for (int i = chars.size() - 1; i >= 0; i--) {
            for (int j = 0; j < chars.get(i).length(); j++) {
                if (chars.get(i).charAt(j) == 'X') {
                    player = new Tank(j, chars.size() - i - 1);
                    playerControl = new Controlling(player);
                }
                if (chars.get(i).charAt(j) == 'T') {
                    trees.add(new Tree(j, chars.size() - i - 1));
                    Rectangle treeRectangle = trees.get(treeCounter).getGraphic().getRectangle();
                    GridPoint2 treePosition = trees.get(treeCounter).location.getPosition();
                    moveRectangleAtTileCenter(field.getGroundLayer(), treeRectangle, treePosition);
                    treeCounter++;
                }
            }
        }
        this.randomTreesCount = treeCounter;
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}
