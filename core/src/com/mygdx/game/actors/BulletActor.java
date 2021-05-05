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

import static com.mygdx.game.Constants.PIXELS_IN_METRE;
import static com.mygdx.game.Constants.SIZE_OF_BULLET;

public class BulletActor extends Actor {

    private static final float BULLET_SPEED = 8;
    private Texture texture;
    private World world;
    private Body body;
    private Fixture fixture;
    double angle = 1;

    public BulletActor(World world, Texture texture, Vector2 position) {
        this.world = world;
        this.texture = texture;

        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.KinematicBody;
        body = world.createBody(def);

        PolygonShape box = new PolygonShape();
        box.setAsBox((float)(SIZE_OF_BULLET / 2), (float)(SIZE_OF_BULLET / 2));
        fixture = body.createFixture(box, 1);
        fixture.setUserData("bullet");
        box.dispose();

        setSize(SIZE_OF_BULLET, SIZE_OF_BULLET);
    }

    @Override
    public void act(float delta) {
        float x = (float)(BULLET_SPEED * Math.sin(angle));
        float y = (float)(BULLET_SPEED * Math.cos(angle));
        body.setLinearVelocity(x, y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((body.getPosition().x -0.5f) * PIXELS_IN_METRE, (body.getPosition().y -0.5f) * PIXELS_IN_METRE);
        batch.draw(texture, getX(), getY(), SIZE_OF_BULLET, SIZE_OF_BULLET);
    }

    public void detach(){
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }
}
