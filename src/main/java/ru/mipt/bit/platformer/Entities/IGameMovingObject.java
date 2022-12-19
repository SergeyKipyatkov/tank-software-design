package ru.mipt.bit.platformer.Entities;

import ru.mipt.bit.platformer.util.Conversion;

public interface IGameMovingObject {
    Conversion getConversion();
    Conversion getDestinationConversion();
    void setDestinationConversion(Conversion destination);
    float getMovementProgress();
    void setMovementProgress(float movementProgress);
}