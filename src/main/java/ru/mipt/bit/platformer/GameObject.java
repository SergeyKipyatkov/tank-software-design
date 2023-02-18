package ru.mipt.bit.platformer;

public abstract class GameObject {
    protected Location location;
    protected ObjectGraphic graphic;

    public Location getLocation() {
        return location;
    }

    public ObjectGraphic getGraphic() {
        return graphic;
    }

}
