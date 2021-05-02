package com.mygdx.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.mygdx.game.Constants.PIXELS_IN_METRE;

public class GroundActor extends Actor {

    private Texture ground, overground;
    private World world;
    private Body body;
    private Fixture fixture;

    public GroundActor(World world, Texture ground, Texture overground, float x, float y, float width, float height) {
        this.world = world;
        this.ground = ground;
        this.overground = overground;

        BodyDef def = new BodyDef();
        def.position.set(x + width / 2, y - height / 2);
        body = world.createBody(def);

        PolygonShape box = new PolygonShape();
        box.setAsBox(width / 2,height / 2);
        fixture = body.createFixture(box, 1);
        fixture.setUserData("ground");
        box.dispose();

        setSize(width * PIXELS_IN_METRE, height * PIXELS_IN_METRE);
        setPosition(x * PIXELS_IN_METRE, (y - height) * PIXELS_IN_METRE);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(ground, getX(), getY(), getWidth(), getHeight());
        batch.draw(overground, getX(), getY() + getHeight() * 0.8f, getWidth(), getHeight() * 0.2f);
    }

    public void detach(){
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }
}
