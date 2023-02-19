package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public abstract class GameObject {
    protected static final ArrayList<GridPoint2> obstaclePosition = new ArrayList<>();
    protected Location location;
    protected ObjectGraphic graphic;

    public Location getLocation() {
        return location;
    }

    public ObjectGraphic getGraphic() {
        return graphic;
    }

    protected static boolean IsPositionFree(GridPoint2 position){
        return !obstaclePosition.contains(position);
    }

}
