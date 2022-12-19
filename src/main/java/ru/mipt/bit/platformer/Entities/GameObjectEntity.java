package ru.mipt.bit.platformer.Entities;

import ru.mipt.bit.platformer.Render.Visualisation;
import ru.mipt.bit.platformer.util.Conversion;

import static com.badlogic.gdx.math.MathUtils.isEqual;

public abstract class GameObjectEntity {
    public final Conversion conversion;
    public final Visualisation texture;
    protected GameObjectEntity(Conversion conversion, Visualisation texture) {
        this.conversion = conversion;
        this.texture = texture;
    }
}
