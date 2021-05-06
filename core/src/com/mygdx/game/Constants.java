package com.mygdx.game;

import com.badlogic.gdx.Gdx;

public class Constants {//здесь создавай все величины, избегай "пустых" интов, так костя сказал
    public static final float SCREEN_WIDTH = Gdx.graphics.getWidth();
    public static final float SCREEN_HEIGHT = Gdx.graphics.getHeight();
    public static final float PIXELS_IN_METRE = 45f;
    public static final int IMPULSE_JUMP = 6;
    public static final float PLAYER_SPEED = 8f;
    public static final int SIZE_OF_BULLET = 200;
    public static final int WIDTH_OF_PLATFORM = 600;
    public static final int HEIGHT_OF_PLATFORM = 200;
}
