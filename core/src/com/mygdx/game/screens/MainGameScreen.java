package com.mygdx.game.screens;//package ru.samsung.game.screens;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//
//import ru.samsung.game.Main;
//import ru.samsung.game.actors.PlayerActor;
//import ru.samsung.game.actors.ActorSpike;
//
//public class MainGameScreen extends BaseScreen {
//
//    private Stage stage;
//    private PlayerActor player;
//    private ActorSpike spike;
//    private Texture texturePlayer, textureSpike;
//    TextureRegion regionSpike;
//
//    public MainGameScreen(Main game) {
//        super(game);
//        texturePlayer = new Texture("player.png");
//        textureSpike = new Texture("spike.png");
//        regionSpike = new TextureRegion(textureSpike, 0, 0, 814, 1092);
//    }
//
//    @Override
//    public void show() {
//        stage = new Stage();
//        stage.setDebugAll(true);
//
//        player = new PlayerActor(texturePlayer);
//        spike = new ActorSpike(regionSpike);
//
//        stage.addActor(player);
//        stage.addActor(spike);
//
//        player.setPosition(600 - player.getWidth(), 600 - player.getHeight());
//        spike.setPosition(2000 - player.getWidth(), 600 - player.getHeight());
//    }
//
//    @Override
//    public void hide() {
//        stage.dispose();
//        texturePlayer.dispose();
//        textureSpike.dispose();
//    }
//
//    @Override
//    public void render(float delta) {
//        Gdx.gl.glClearColor(0, 0, 1, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//
//        stage.act();
//        stage.draw();
//    }
//}
