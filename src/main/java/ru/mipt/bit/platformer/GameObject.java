package ru.mipt.bit.platformer;

public abstract class GameObject {
    private Location orientation;
    private ObjectGraphic graphic;

    public Location getOrientation() {
        return orientation;
    }

    public ObjectGraphic getGraphic() {
        return graphic;
    }

}
