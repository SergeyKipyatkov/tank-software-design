package ru.mipt.bit.platformer.Render;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.util.GdxGameUtils;

public class Visualisation {
    public Texture texture;
    public TextureRegion textureRegion;
    public Rectangle rectangle;
    public Visualisation(String texturePath){
        this.texture = new Texture(texturePath);
        this.textureRegion = new TextureRegion(this.texture);
        this.rectangle = GdxGameUtils.createBoundingRectangle(this.textureRegion);
    }
    public Texture getTexture(){
        return this.texture;
    }

    public TextureRegion getTextureRegion(){
        return this.textureRegion;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
