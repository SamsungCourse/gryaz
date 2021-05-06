package com.mygdx.game.screens;

import com.badlogic.gdx.Screen;

import com.mygdx.game.Main;

public class BaseScreen implements Screen {//это класс экрана, от которого наследуются все остальные, он нам нужен, чтобы не перегружать лишними методами другие классы

    protected Main game;

    public BaseScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
