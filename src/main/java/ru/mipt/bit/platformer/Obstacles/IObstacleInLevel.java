package ru.mipt.bit.platformer.Obstacles;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Entities.GameObjectEntity;
import ru.mipt.bit.platformer.Entities.ObstacleEntity;

import java.util.HashSet;

public interface IObstacleInLevel {
    void addObstacle(GameObjectEntity obstacle);
    void removeObstacle(GameObjectEntity obstacle);

    HashSet<GridPoint2> getObstaclePositions();
    HashSet<GameObjectEntity> getObstacleEntities();
}
