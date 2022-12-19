package ru.mipt.bit.platformer.Builder;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Obstacles.IObstacleInLevel;
import ru.mipt.bit.platformer.Obstacles.ObstacleInLevel;
import ru.mipt.bit.platformer.Entities.ObstacleEntity;
import ru.mipt.bit.platformer.Render.Visualisation;
import ru.mipt.bit.platformer.util.Conversion;

public class ObstacleLevelBuilder implements IObstacleLevelBuilder {
    private IObstacleInLevel obstacle;

    public ObstacleLevelBuilder(){
        this.obstacle = new ObstacleInLevel();
    }

    @Override
    public IObstacleInLevel createBush() {
        IObstacleInLevel result = this.obstacle;
        this.clear();
        return result;
    }

    @Override
    public IObstacleLevelBuilder addBush(GridPoint2 bushPosition) {
        Visualisation textureObstacle = new Visualisation("images/greenTree.png");
        Conversion treeConversion = new Conversion(bushPosition, 0f);
        ObstacleEntity obstacleEntity = new ObstacleEntity(treeConversion, textureObstacle);
        this.obstacle.addObstacle(obstacleEntity);
        return this;
    }

    @Override
    public void clear() {
        this.obstacle = new ObstacleInLevel();
    }
}
