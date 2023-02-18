package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;

public class ObjectGraphic {
    private final Texture texture;
    private final TextureRegion textureRegion;
    private final Rectangle rectangle;

    public ObjectGraphic(String texturePath){
        this.texture = new Texture(texturePath);
        this.textureRegion = new TextureRegion(texture);
        this.rectangle = createBoundingRectangle(textureRegion);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Texture getTexture() {
        return texture;
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }
}
