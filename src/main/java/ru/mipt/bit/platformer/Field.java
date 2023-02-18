package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.util.TileMovement;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createSingleLayerMapRenderer;
import static ru.mipt.bit.platformer.util.GdxGameUtils.getSingleLayer;

public class Field {
    private final TiledMap level;
    private final MapRenderer levelRenderer;
    private final TileMovement tileMovement;
    private final TiledMapTileLayer groundLayer;

    public Field (Batch batch, String fieldPath) {
        level = new TmxMapLoader().load(fieldPath);
        levelRenderer = createSingleLayerMapRenderer(level, batch);
        groundLayer = getSingleLayer(level);
        tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
    }

    public TiledMap getLevel() {
        return level;
    }

    public MapRenderer getLevelRenderer() {
        return levelRenderer;
    }

    public TiledMapTileLayer getGroundLayer() {
        return groundLayer;
    }

}
