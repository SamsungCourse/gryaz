package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import com.mygdx.game.Constants;
import com.mygdx.game.Main;
import com.mygdx.game.actors.BulletActor;
import com.mygdx.game.actors.PlatformActor;

public class GameScreen extends BaseScreen {

    private Stage stage;
    private World world;

    private PlatformActor platform;
    private BulletActor bullet;

    public GameScreen(Main game) {//основной экран для игровой логики, он вызывается классом main
        super(game);
        stage = new Stage(new FitViewport(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));//создаем сцену даем вьюпорт размера экрана
        world = new World(new Vector2(0, 0), true);//мы создаем мир и даем нулевую гравитацю(гравитация это обьект Vector2)

        world.setContactListener(new ContactListener() {//контакт лисенер реагирует на хитбоксы обьектов с тегами, которые мы указали

            private boolean isCollide(Contact contact, String userA, String userB){//здесь ничего не менять, это метод, который делает всю логику для последующих
                return (contact.getFixtureA().getUserData().equals(userA) && contact.getFixtureB().getUserData().equals(userB) ||
                        contact.getFixtureA().getUserData().equals(userB) && contact.getFixtureB().getUserData().equals(userA));
            }

            @Override
            public void beginContact(Contact contact) {
                if (isCollide(contact, "platform", "bullet")){
                    bullet.angle += 90;
                    }
                }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });
    }

    @Override
    public void show() { //этот метод создает все при появлении экрана, в частности создает платформу и пулю и добавляет их на сцену(stage)
        Texture playerTexture = game.getManager().get("platform.png");
        Texture bulletTexture = game.getManager().get("ball.png");
        platform = new PlatformActor(world, playerTexture, new Vector2(Constants.SCREEN_WIDTH / (2 * Constants.PIXELS_IN_METRE) - 6.6f, 5f));
        bullet = new BulletActor(world, bulletTexture, new Vector2(Constants.SCREEN_WIDTH / (2 * Constants.PIXELS_IN_METRE) - 2.2f, 20f));
        stage.addActor(platform);
        stage.addActor(bullet);

    }

    @Override
    public void hide() { //метод для логики при скрытии экрана, можно чтонибудь умное придумать с ним
    }

    @Override
    public void render(float delta) { //с ним мы знакомы, он отвечает за динамику на сцене
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        world.step(delta, 6, 2);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        world.dispose();
    }
}
