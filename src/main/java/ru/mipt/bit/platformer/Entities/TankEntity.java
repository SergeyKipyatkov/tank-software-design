package ru.mipt.bit.platformer.Entities;

import ru.mipt.bit.platformer.Render.Visualisation;
import ru.mipt.bit.platformer.util.Conversion;


public class TankEntity extends GameObjectEntity implements IGameMovingObject {
    private float movementProgress;
    private Conversion destinationConversion;

    public TankEntity(Conversion conversion, Visualisation texture) {
        super(conversion, texture);
        this.movementProgress = 0f;
        this.destinationConversion = conversion;
    }

    public void setDestinationConversion(Conversion conversion) {
        this.destinationConversion = conversion;
        this.conversion.setRotation(conversion.getRotation());
        this.movementProgress = 0f;
    }

    public void setDestinationPositionAsPosition(){
        this.conversion.copyFromConversion(this.destinationConversion);
    }

    @Override
    public Conversion getConversion() {
        return this.conversion;
    }

    @Override
    public Conversion getDestinationConversion() {
        return destinationConversion;
    }

    @Override
    public float getMovementProgress() {
        return movementProgress;
    }

    public void setMovementProgress(float movementProgress) {
        if(movementProgress > 1f || movementProgress < 0f)
            throw new IllegalArgumentException("You need to enter a value from 0 to 1: " + movementProgress);
        this.movementProgress = movementProgress;
    }
}
