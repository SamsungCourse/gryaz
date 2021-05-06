package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector;
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
import static com.mygdx.game.Constants.SCREEN_WIDTH;
import static com.mygdx.game.Constants.WIDTH_OF_PLATFORM;

public class PlatformActor extends Actor {

    private Texture texture;

    private World world;
    private Body body;
    private Fixture fixture;
    public float speed = 2;

    public PlatformActor(World world, Texture texture, Vector2 position) {
        this.world = world;
        this.texture = texture;

        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.KinematicBody;
        body = world.createBody(def);//создаем наш обьект(тело)

        PolygonShape box = new PolygonShape();//создаем коробку
        box.setAsBox((float)(HEIGHT_OF_PLATFORM / 2),(float)(WIDTH_OF_PLATFORM / 2));
        fixture = body.createFixture(box, 1);//делаем хитбокс ака Fixture из коробки и делаем параметр плотности
        fixture.setUserData("platform");//даем название хитбоксу platform, далее это нам пригодится, чтобы реагировать на столкновения
        box.dispose();//анигилируем ненужную коробку

        setSize(WIDTH_OF_PLATFORM/PIXELS_IN_METRE, HEIGHT_OF_PLATFORM/PIXELS_IN_METRE);//это умгмгм наверно изменяет размер хитбокса, но странно то, что это метод внутри класса actor и изменение данных ни к чему не ведет
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {//рисуем платформу
        setPosition((body.getPosition().x -0.5f) * PIXELS_IN_METRE, (body.getPosition().y -0.5f) * PIXELS_IN_METRE);
        batch.draw(texture, getX(), getY(), WIDTH_OF_PLATFORM, HEIGHT_OF_PLATFORM);
    }

    @Override
    public void act(float delta) { //метод, который отвечает за все действия актера
        if (Gdx.input.isTouched()){//проверяем нажатие на экран
            Vector2 posOfBody = body.getPosition();
            float x = makeMetres((Gdx.input.getX() - SCREEN_WIDTH/2));//отняв половину ширины экрана я сделал, чтобы координата 0 была по центру экрана
            float bodyOffset = 0;
            if (posOfBody.x >= x) {bodyOffset += x - posOfBody.x;} //уравнения, которые вычисляют нужный сдвиг, чтобы дойти до пальца
            if (posOfBody.x < x) {bodyOffset -= posOfBody.x - x;} //они почти едентичны тем, что были у прошлой пули в прошлой игре
            body.setLinearVelocity(bodyOffset * speed, 0);//дали скорость пуле в направлении х, сдвиг мы умножаем, чтобы регулировать скорость
        }
    }
    public float makeMetres(float oldNum){ //метод преобразует величину пикселей в метры
        return oldNum / PIXELS_IN_METRE + 10;
    }
}
