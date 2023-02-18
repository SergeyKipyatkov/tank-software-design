package ru.mipt.bit.platformer.util;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.Controlling;

import static ru.mipt.bit.platformer.util.GdxGameUtils.moveRectangleAtTileCenter;

public class TileMovement {

    private final TiledMapTileLayer tileLayer;
    private final Interpolation interpolation;

    public TileMovement(TiledMapTileLayer tileLayer, Interpolation interpolation) {
        this.tileLayer = tileLayer;
        this.interpolation = interpolation;
    }

    public Rectangle moveRectangleBetweenTileCenters(Controlling control) {
        moveRectangleAtTileCenter(tileLayer, control.tank.getGraphic().getRectangle(), control.tank.getLocation().getPosition());
        float fromTileBottomLeftX = control.tank.getGraphic().getRectangle().x;
        float fromTileBottomLeftY = control.tank.getGraphic().getRectangle().y;

        moveRectangleAtTileCenter(tileLayer, control.tank.getGraphic().getRectangle(), control.tank.destinationPosition);
        float toTileBottomLeftX = control.tank.getGraphic().getRectangle().x;
        float toTileBottomLeftY = control.tank.getGraphic().getRectangle().y;

        float intermediateBottomLeftX = interpolation.apply(fromTileBottomLeftX, toTileBottomLeftX, control.movementProgress);
        float intermediateBottomLeftY = interpolation.apply(fromTileBottomLeftY, toTileBottomLeftY, control.movementProgress);

        return control.tank.getGraphic().getRectangle()
                .setX(intermediateBottomLeftX)
                .setY(intermediateBottomLeftY);
    }
}
