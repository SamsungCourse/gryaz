package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.mygdx.game.Constants.HEIGHT_OF_PLATFORM;
import static com.mygdx.game.Constants.IMPULSE_JUMP;
import static com.mygdx.game.Constants.PIXELS_IN_METRE;
import static com.mygdx.game.Constants.PLAYER_SPEED;
import static com.mygdx.game.Constants.WIDTH_OF_PLATFORM;

public class PlayerActor extends Actor {

    private Texture texture;

    private World world;
    private Body body;
    private Fixture fixture;

    private boolean alive = true;

    private boolean canJump = false;
    private boolean autoJump = false;

    public PlayerActor(World world, Texture texture, Vector2 position) {
        this.world = world;
        this.texture = texture;

        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.KinematicBody;
        body = world.createBody(def);

        PolygonShape box = new PolygonShape();
        box.setAsBox((float)(HEIGHT_OF_PLATFORM / 2),(float)(WIDTH_OF_PLATFORM / 2));
        fixture = body.createFixture(box, 1);
        fixture.setUserData("platform");
        box.dispose();

        setSize(WIDTH_OF_PLATFORM, HEIGHT_OF_PLATFORM);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((body.getPosition().x -0.5f) * PIXELS_IN_METRE, (body.getPosition().y -0.5f) * PIXELS_IN_METRE);
        batch.draw(texture, getX(), getY(), WIDTH_OF_PLATFORM, HEIGHT_OF_PLATFORM);
    }

    @Override
    public void act(float delta) {
        if (Gdx.input.justTouched() || autoJump){
            autoJump = false;
            jump();
        }

    }

    private void jump(){
        if (canJump && alive) {
            canJump = false;
            Vector2 position = body.getPosition();
            body.applyLinearImpulse(0, IMPULSE_JUMP, position.x, position.y, true);
        }
    }

    public void detach(){
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAutoJump() {
        return autoJump;
    }

    public void setAutoJump(boolean autoJump) {
        this.autoJump = autoJump;
    }

    public boolean isCanJump() {
        return canJump;
    }

    public void setCanJump(boolean canJump) {
        this.canJump = canJump;
    }
}
