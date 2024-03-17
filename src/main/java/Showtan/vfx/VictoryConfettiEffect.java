package Showtan.vfx;

import Showtan.util.TextureLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class VictoryConfettiEffect extends AbstractGameEffect {

    // Settings

    // Textures.
    private static Texture texture_weedle = TextureLoader.getTexture("Showtan/images/vfx/WeedleOrb.png");
    private static Texture texture_totem = TextureLoader.getTexture("Showtan/images/vfx/TotemOrb.png");
    private static Texture texture_brave = TextureLoader.getTexture("Showtan/images/vfx/BraveOrb.png");
    private static Texture texture_koffing = TextureLoader.getTexture("Showtan/images/vfx/KoffingOrb.png");
    private static Texture texture_ball = TextureLoader.getTexture("Showtan/images/vfx/Ball.png");
    private Texture TextureUsed;
    private Texture FinalTexture;

    // Duration.
    private float startingDuration;
    private float duration;

    // Position
    private float x;
    private float y;

    // Where go
    private float speed;
    private float direction = 270.0F;

    // How move
    private float rotation;
    private float rotation_speed;
    private float flip_speed;
    private float flip_counter;
    private float scale;
    private float totalscale;

    public VictoryConfettiEffect() {
        this(
                MathUtils.random(0.0F, Settings.WIDTH),
                Settings.HEIGHT + 40.0F * Settings.scale
        );
    }

    public VictoryConfettiEffect(float x, float y, float imgScale) {
        this(x, y);
        totalscale = imgScale;
    }

    public VictoryConfettiEffect(float x, float y) {
        int randomtex = MathUtils.random(0,3);

        if (randomtex == 0)
            this.TextureUsed = texture_weedle;
        else if (randomtex == 1)
            this.TextureUsed = texture_totem;
        else if (randomtex == 2)
            this.TextureUsed = texture_brave;
        else if (randomtex == 3)
            this.TextureUsed = texture_koffing;

        this.FinalTexture = this.TextureUsed;
        this.startingDuration = MathUtils.random(3.0F,7.0F);
        this.duration = this.startingDuration;
        this.startingDuration = this.duration;
        this.renderBehind = true;
        this.rotation = MathUtils.random(0.0F, 360.0F);
        this.rotation_speed = MathUtils.random(-24.0F, 24.0F) * Settings.scale;
        this.flip_speed = MathUtils.random(-2.0F, 2.0F) * Settings.scale;
        this.flip_counter = MathUtils.random(0.0F, 6.4F);
        this.speed = MathUtils.random(200.0F, 300.0F) * Settings.scale;
        this.scale = MathUtils.random(-1.0F,1.0F) * Settings.scale;
        this.totalscale = MathUtils.random(0.7F,1.0F);
        if (MathUtils.random(0,4) == 0 && this.totalscale >= 0.9F)
            this.totalscale = MathUtils.random(0.9F,2F);


        // Location
        this.y = y;
        this.x = x;

        this.color = new Color(1, 1, 1, 1F);
    }



    @Override
    public void render(SpriteBatch sb) {
        this.color.a = this.duration / this.startingDuration;
        sb.setColor(this.color);

        final int w = this.FinalTexture.getWidth();
        final int h = this.FinalTexture.getHeight();
        final int w2 = this.FinalTexture.getWidth();
        final int h2 = this.FinalTexture.getHeight();
        sb.draw(this.FinalTexture, x-w2/2f, y-h2/2f,
                w/2f, h/2f,
                w2, h2,
                this.scale *Settings.scale*this.totalscale, 1.0F*Settings.scale*this.totalscale,
                this.rotation,
                0, 0,
                w2, h2,
                false, false);
    }

    @Override
    public void dispose() {
    }

    @Override
    public void update() {
        final float dt = Gdx.graphics.getDeltaTime();

        this.y += MathUtils.sinDeg(this.direction) * this.speed * dt;
        this.x += MathUtils.cosDeg(this.direction) * this.speed * dt;

        this.rotation += this.rotation_speed * dt;
        this.flip_counter += this.flip_speed * dt;

        this.scale = MathUtils.sin(this.flip_counter);

        if (this.scale > 0.0F) {
            this.FinalTexture = this.TextureUsed;
        }
        else
            this.FinalTexture = texture_ball;

        this.duration -= dt;
        if (this.duration < 0.0F || y > Settings.HEIGHT + FinalTexture.getHeight() || y < -FinalTexture.getHeight()) {
            this.isDone = true;
        }
    }
}