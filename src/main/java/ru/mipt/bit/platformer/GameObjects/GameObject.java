package ru.mipt.bit.platformer.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;

public abstract class GameObject {
    private GridPoint2 coordinates;
    private Texture texture;
    private TextureRegion textureRegion;
    private Rectangle rectangle;

    public GameObject(GridPoint2 coordinates, Texture texture){
        this.coordinates = coordinates;
        this.texture = texture;
        this.textureRegion = new TextureRegion(texture);
        this.rectangle = createBoundingRectangle(textureRegion);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }
    
    public Texture getTexture() {
        return texture;
    }

    public GridPoint2 getCoordinates(){
        return this.coordinates;
    };
}
