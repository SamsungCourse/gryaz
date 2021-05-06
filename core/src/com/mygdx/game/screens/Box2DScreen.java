package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import com.mygdx.game.Main;

public class Box2DScreen extends BaseScreen { //это чисто тетовый класс для дебагинга, его пока не трогаем

    public Box2DScreen(Main game){
        super(game);
    }

    private World world;
    private Box2DDebugRenderer renderer;
    private OrthographicCamera camera;

    private Body playerBody, bulletBody;
    private Fixture platformFixture, groundFixture, bulletFixture;

    private boolean canJump = true;
    private boolean isAlive = true;

    @Override
    public void show() {
        world = new World(new Vector2(0, -10), true);
        renderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(16, 9);
        camera.translate(0, 1);

//страшная хрень тут проверяет столкновения
//        world.setContactListener(new ContactListener() {
//            @Override
//            public void beginContact(Contact contact) {
//                Fixture fixtureA = contact.getFixtureA();
//                Fixture fixtureB = contact.getFixtureB();
//
//                if ((fixtureA.getUserData().equals("player") && fixtureB.getUserData().equals("ground")) ||
//                        (fixtureA.getUserData().equals("ground") && fixtureB.getUserData().equals("player"))){
//                    canJump = true;
//                }
//
//                if ((fixtureA.getUserData().equals("player") && fixtureB.getUserData().equals("spike")) ||
//                        (fixtureA.getUserData().equals("spike") && fixtureB.getUserData().equals("player"))){
//                    isAlive = false;
//                }
//            }
//
//            @Override
//            public void endContact(Contact contact) {
//
//            }
//
//            @Override
//            public void preSolve(Contact contact, Manifold oldManifold) {
//
//            }
//
//            @Override
//            public void postSolve(Contact contact, ContactImpulse impulse) {
//
//            }
//        });

        playerBody = world.createBody(createPlayerBodyDef());
        bulletBody = world.createBody(createBulletBodyDef());

        PolygonShape playerShape = new PolygonShape();
        playerShape.setAsBox(0.5f,0.5f);
        platformFixture = playerBody.createFixture(playerShape, 1);
        bulletFixture = bulletBody.createFixture(playerShape, 1);
        playerShape.dispose();

        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsBox(500,1);
        groundShape.dispose();

        platformFixture.setUserData("platform");
        bulletFixture.setUserData("bullet");
    }

    private BodyDef createPlayerBodyDef() {
        BodyDef def = new BodyDef();
        def.position.set(-5, 2);
        def.type = BodyDef.BodyType.DynamicBody;
        return def;
    }

    private BodyDef createBulletBodyDef() {
        BodyDef def = new BodyDef();
        def.position.set(-5, 2);
        def.type = BodyDef.BodyType.DynamicBody;
        return def;
    }

    @Override
    public void dispose() {
        bulletBody.destroyFixture(bulletFixture);
        playerBody.destroyFixture(platformFixture);
        world.destroyBody(playerBody);
        world.destroyBody(bulletBody);
        world.dispose();
        renderer.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos); //перемещение перса тут будет
        }

        if (isAlive) {
            float velocityY = playerBody.getLinearVelocity().y;
            playerBody.setLinearVelocity(8, velocityY);
        }

        world.step(delta, 6, 2);

        camera.update();
        renderer.render(world, camera.combined);
    }

    private void jump(){
        if (canJump) {
            canJump = false;
            Vector2 position = playerBody.getPosition();
            playerBody.applyLinearImpulse(0, 6, position.x, position.y, true);
        }
    }
}
