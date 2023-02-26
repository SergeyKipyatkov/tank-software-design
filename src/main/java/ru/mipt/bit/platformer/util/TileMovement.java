package ru.mipt.bit.platformer.util;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.GameObject;
import ru.mipt.bit.platformer.Tank;

import static ru.mipt.bit.platformer.util.GdxGameUtils.moveRectangleAtTileCenter;

public class TileMovement {

    private final TiledMapTileLayer tileLayer;
    private final Interpolation interpolation;

    public TileMovement(TiledMapTileLayer tileLayer, Interpolation interpolation) {
        this.tileLayer = tileLayer;
        this.interpolation = interpolation;
    }

    public Rectangle moveRectangleBetweenTileCenters(Tank tank, float progress) {
        moveRectangleAtTileCenter(tileLayer, tank.getGraphic().getRectangle(), tank.getLocation().position);
        float fromTileBottomLeftX = tank.getGraphic().getRectangle().x;
        float fromTileBottomLeftY = tank.getGraphic().getRectangle().y;

        moveRectangleAtTileCenter(tileLayer, tank.getGraphic().getRectangle(), tank.destinationPosition);
        float toTileBottomLeftX = tank.getGraphic().getRectangle().x;
        float toTileBottomLeftY = tank.getGraphic().getRectangle().y;

        float intermediateBottomLeftX = interpolation.apply(fromTileBottomLeftX, toTileBottomLeftX, progress);
        float intermediateBottomLeftY = interpolation.apply(fromTileBottomLeftY, toTileBottomLeftY, progress);

        return tank.getGraphic().getRectangle()
                .setX(intermediateBottomLeftX)
                .setY(intermediateBottomLeftY);
    }
}
