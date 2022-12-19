package ru.mipt.bit.platformer.Obstacles;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Entities.GameObjectEntity;

import java.util.HashSet;

public class ObstacleInLevel implements IObstacleInLevel {
    private final HashSet<GameObjectEntity> obstacleEntities;
    private final HashSet<GridPoint2> obstaclePositions;

    public ObstacleInLevel(){
        this.obstacleEntities = new HashSet<>();
        this.obstaclePositions = new HashSet<>();
    }

    public void addObstacle(GameObjectEntity entity) {
        obstaclePositions.add(entity.conversion.getPosition());
        obstacleEntities.add(entity);
    }

    public void removeObstacle(GameObjectEntity entity) {
        obstaclePositions.remove(entity.conversion.getPosition());
        obstacleEntities.remove(entity);
    }

    public HashSet<GridPoint2> getObstaclePositions(){
        return this.obstaclePositions;
    }

    public HashSet<GameObjectEntity> getObstacleEntities(){
        return this.obstacleEntities;
    }
}
