package com.mygdx.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static ru.samsung.game.Constants.PIXELS_IN_METRE;

public class SpikeActor extends Actor {

    private Texture texture;
    private World world;
    private Body body;
    private Fixture fixture;

    public SpikeActor(World world, Texture texture, float x, float y) {
        this.world = world;
        this.texture = texture;

        BodyDef def = new BodyDef();
        def.position.set(x, y + 0.5f);
        body = world.createBody(def);

        PolygonShape box = new PolygonShape();
        Vector2[] vectors = new Vector2[3];
        vectors[0] = new Vector2(-0.5f, -0.5f);
        vectors[1] = new Vector2(0.5f, -0.5f);
        vectors[2] = new Vector2(0, 0.5f);
        box.set(vectors);
        fixture = body.createFixture(box, 1);
        fixture.setUserData("spike");
        box.dispose();

        setSize(PIXELS_IN_METRE, PIXELS_IN_METRE);
        setPosition((x - 0.5f) * PIXELS_IN_METRE, y * PIXELS_IN_METRE);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((body.getPosition().x -0.5f) * PIXELS_IN_METRE, (body.getPosition().y -0.5f) * PIXELS_IN_METRE);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    public void detach(){
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }
}
