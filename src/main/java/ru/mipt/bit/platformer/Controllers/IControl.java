package ru.mipt.bit.platformer.Controllers;

import com.badlogic.gdx.Input;
import ru.mipt.bit.platformer.util.Direction;

public interface IControl {
    Direction getDirection(Input input);
}